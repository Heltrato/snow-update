package net.technic.snow_update.mixin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeResolver;
import net.minecraft.world.level.biome.BiomeSource;
import net.technic.snow_update.worldgen.IExtendedBiomeSource;

@Mixin(BiomeSource.class)
public abstract class MixinBiomeSource implements BiomeResolver, IExtendedBiomeSource {
    @Shadow
    public Supplier<Set<Holder<Biome>>> possibleBiomes;

    @Unique
    private boolean hasAppended = false;

    @Override
    public void appendDeferredBiomesList(List<Holder<Biome>> biomesToAppend)
    {
        // Don't append the biomes list again if we have already done so
        if (this.hasAppended) {
            return;
        }

        List<Holder<Biome>> possibleBiomes = new ArrayList<>();
        possibleBiomes.addAll(this.possibleBiomes.get());
        possibleBiomes.addAll(biomesToAppend);

        this.possibleBiomes = () -> new ObjectLinkedOpenHashSet<>(possibleBiomes.stream().distinct().collect(Collectors.toList()));
        this.hasAppended = true;
    }
}
