package net.technic.snow_update.worldgen;

import java.util.List;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.technic.snow_update.SnowUpdate;

public class SnowUpdatePlacedFeatures {
    public static final ResourceKey<PlacedFeature> ICE_STALACTITE_PLACED = registerKey("ice_stalactite_place");

    private static ResourceKey<PlacedFeature> registerKey(String string) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(SnowUpdate.MOD_ID, string));
    }

    public static void boostrap(BootstapContext<PlacedFeature> pContext){
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeature = pContext.lookup(Registries.CONFIGURED_FEATURE);

        register(pContext, ICE_STALACTITE_PLACED, configuredFeature.getOrThrow(SnowUpdateConifgFeatures.ICE_STALACTITE), 
        List.of(RarityFilter.onAverageOnceEvery(70), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(50))
        , BiomeFilter.biome()));
    }

    public static void register(BootstapContext<PlacedFeature> pContext, ResourceKey<PlacedFeature> pKey, Holder<ConfiguredFeature<?, ?>> pFeatureConfig, List<PlacementModifier> pModifiers){
        pContext.register(pKey, new PlacedFeature(pFeatureConfig, pModifiers));
    }
}
