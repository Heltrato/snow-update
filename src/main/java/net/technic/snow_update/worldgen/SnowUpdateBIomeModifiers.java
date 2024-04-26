package net.technic.snow_update.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import net.technic.snow_update.SnowUpdate;

public class SnowUpdateBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_TREE_FROSTWOOD = registerKey("add_tree_frosted");

    public static void bootstrap(BootstapContext<BiomeModifier> pContext) {
        var placedFeatures = pContext.lookup(Registries.PLACED_FEATURE);
        var biomes = pContext.lookup(Registries.BIOME);

        pContext.register(ADD_TREE_FROSTWOOD, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(biomes.getOrThrow(Tags.Biomes.IS_COLD), 
        HolderSet.direct(placedFeatures.getOrThrow(SnowUpdatePlacedFeatures.FROSTWOOD_PLACED_KEY)), 
        GenerationStep.Decoration.VEGETAL_DECORATION));
    }

    private static ResourceKey<BiomeModifier> registerKey(String string) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(SnowUpdate.MOD_ID, string));
    }
}
