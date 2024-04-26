package net.technic.snow_update.mixin;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.google.common.collect.ImmutableSet;

import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.SectionPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.TheEndBiomeSource;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.technic.snow_update.SnowUpdate;
import net.technic.snow_update.worldgen.IExtendedTheEndBiomeSource;
import net.technic.snow_update.worldgen.api.EndBiomeRegistry;
import net.technic.snow_update.worldgen.area.Area;
import net.technic.snow_update.worldgen.noise.LayeredNoiseUtil;

@Mixin(TheEndBiomeSource.class)
public class MixinTheEndBiomeSource implements IExtendedTheEndBiomeSource{
    @Shadow
    @Final
    private Holder<Biome> end;

    @Unique
    private boolean tbInitialized = false;

    @Unique
    private Registry<Biome> biomeRegistry;
    @Unique
    private Set<Holder<Biome>> tbPossibleBiomes;
    @Unique
    private Area highlandsArea;
    @Unique
    private Area midlandsArea;
    @Unique
    private Area edgeArea;
    @Unique
    private Area islandsArea;

    @Override
    public void initializeForTerraBlender(RegistryAccess registryAccess, long seed)
    {
        this.biomeRegistry = registryAccess.registryOrThrow(Registries.BIOME);

        var highlands = EndBiomeRegistry.getHighlandsBiomes();
        var midlands = EndBiomeRegistry.getMidlandsBiomes();
        var edge = EndBiomeRegistry.getEdgeBiomes();
        var islands = EndBiomeRegistry.getIslandBiomes();

        // Create a set of all biomes
        var builder = ImmutableSet.<ResourceKey<Biome>>builder();
        builder.addAll(highlands.stream().map(WeightedEntry.Wrapper::getData).toList());
        builder.addAll(midlands.stream().map(WeightedEntry.Wrapper::getData).toList());
        builder.addAll(edge.stream().map(WeightedEntry.Wrapper::getData).toList());
        builder.addAll(islands.stream().map(WeightedEntry.Wrapper::getData).toList());
        builder.add(Biomes.THE_END);
        Set<ResourceKey<Biome>> allBiomes = builder.build();

        // Ensure all biomes are registered
        allBiomes.forEach(key -> {
            if (!biomeRegistry.containsKey(key))
                throw new RuntimeException("Biome " + key + " has not been registered!");
        });

        this.tbPossibleBiomes = allBiomes.stream().map(biomeRegistry::getHolderOrThrow).collect(Collectors.toSet());
        this.highlandsArea = LayeredNoiseUtil.biomeArea(registryAccess, seed, SnowUpdate.CONFIG.endHighlandsBiomeSize, highlands);
        this.midlandsArea = LayeredNoiseUtil.biomeArea(registryAccess, seed, SnowUpdate.CONFIG.endMidlandsBiomeSize, midlands);
        this.edgeArea = LayeredNoiseUtil.biomeArea(registryAccess, seed, SnowUpdate.CONFIG.endEdgeBiomeSize, edge);
        this.islandsArea = LayeredNoiseUtil.biomeArea(registryAccess, seed, SnowUpdate.CONFIG.endIslandBiomeSize, edge);

        // This may not be initialized with e.g. BCLib
        this.tbInitialized = true;
    }

    @Inject(method = "collectPossibleBiomes", at=@At("RETURN"), cancellable = true)
    protected void onCollectPossibleBiomes(CallbackInfoReturnable<Stream<Holder<Biome>>> cir)
    {
        if (!this.tbInitialized)
            return;

        var builder = ImmutableSet.<Holder<Biome>>builder();
        builder.addAll(cir.getReturnValue().collect(Collectors.toSet()));
        builder.addAll(this.tbPossibleBiomes);
        cir.setReturnValue(builder.build().stream());
    }

    @Inject(method = "getNoiseBiome", at=@At("HEAD"), cancellable = true)
    public void onGetNoiseBiome(int x, int y, int z, Climate.Sampler sampler, CallbackInfoReturnable<Holder<Biome>> cir)
    {
        if (!this.tbInitialized)
            return;

        int blockX = QuartPos.toBlock(x);
        int blockY = QuartPos.toBlock(y);
        int blockZ = QuartPos.toBlock(z);

        int sectionX = SectionPos.blockToSectionCoord(blockX);
        int sectionZ = SectionPos.blockToSectionCoord(blockZ);

        if ((long)sectionX * (long)sectionX + (long)sectionZ * (long)sectionZ <= 4096L)
        {
            cir.setReturnValue(this.end);
        }
        else
        {
            double heightNoise = sampler.erosion().compute(new DensityFunction.SinglePointContext(blockX, blockY, blockZ));

            if (heightNoise > 0.25)
            {
                cir.setReturnValue(this.getBiomeHolder(this.highlandsArea.get(x, z)));
            }
            else if (heightNoise >= -0.0625)
            {
                cir.setReturnValue(this.getBiomeHolder(this.midlandsArea.get(x, z)));
            }
            else
            {
                cir.setReturnValue(heightNoise < -0.21875 ? this.getBiomeHolder(this.islandsArea.get(x, z)) : this.getBiomeHolder(this.edgeArea.get(x, z)));
            }
        }
    }

    @Unique
    private Holder<Biome> getBiomeHolder(int id)
    {
        return this.biomeRegistry.getHolder(id).orElseThrow();
    }
}
