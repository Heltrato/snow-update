package net.technic.snow_update.worldgen;

import java.util.List;


import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.technic.snow_update.SnowUpdate;
import net.technic.snow_update.registry.SnowBlockRegistry;

public class SnowUpdatePlacedFeatures {
    public static final ResourceKey<PlacedFeature> ICE_STALACTITE_PLACED = registerKey("ice_stalactite_placed");
    public static final ResourceKey<PlacedFeature> FROSTWOOD_PLACED_KEY = registerKey("frostwood_placed");
    public static final ResourceKey<PlacedFeature> TEST_GEODE = registerKey("test_geode_placed");
    public static final ResourceKey<PlacedFeature> SNOW_LAYER_PLACED = registerKey("snow_layer_placed");
    public static final ResourceKey<PlacedFeature> ICE_PATCH_FLOOR_PLACED = registerKey("ice_patch_floor_placed");
    public static final ResourceKey<PlacedFeature> ICE_PATCH_CEILING_PLACED = registerKey("ice_patch_ceiling_placed");
    public static final ResourceKey<PlacedFeature> SNOW_PATCH_FLOOR_PLACED = registerKey("snow_patch_floor_placed");
    public static final ResourceKey<PlacedFeature> SNOW_PATCH_CEILING_PLACED = registerKey("snow_patch_ceiling_placed");

    public static ResourceKey<PlacedFeature> registerKey(String string) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(SnowUpdate.MOD_ID, string));
    }

    public static void boostrap(BootstapContext<PlacedFeature> pContext){
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeature = pContext.lookup(Registries.CONFIGURED_FEATURE);

        register(pContext, ICE_STALACTITE_PLACED, configuredFeature.getOrThrow(SnowUpdateConfigFeatures.ICE_STALACTITE), 
        List.of(CountPlacement.of(UniformInt.of(48, 96)), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, 
        BiomeFilter.biome()));

        register(pContext, FROSTWOOD_PLACED_KEY, configuredFeature.getOrThrow(SnowUpdateConfigFeatures.FROSTWOOD_KEY), VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1F, 2), 
        SnowBlockRegistry.FROSTWOOD_SAPLING.get()));

        register(pContext, TEST_GEODE,  configuredFeature.getOrThrow(SnowUpdateConfigFeatures.TEST_GEODE), List.of(RarityFilter.onAverageOnceEvery(80), InSquarePlacement.spread(), 
        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(50)), BiomeFilter.biome()));

        register(pContext, SNOW_LAYER_PLACED, configuredFeature.getOrThrow(SnowUpdateConfigFeatures.SNOW_LAYER), List.of(CountPlacement.of(5), InSquarePlacement.spread(), 
        PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome()));

        register(pContext, ICE_PATCH_FLOOR_PLACED, configuredFeature.getOrThrow(SnowUpdateConfigFeatures.ICE_PATCH_FLOOR), List.of(CountPlacement.of(5), InSquarePlacement.spread(), 
        PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome()));

        register(pContext, ICE_PATCH_CEILING_PLACED, configuredFeature.getOrThrow(SnowUpdateConfigFeatures.ICE_PATCH_CEILING), List.of(CountPlacement.of(5), InSquarePlacement.spread(), 
        PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome()));

        register(pContext, SNOW_PATCH_FLOOR_PLACED, configuredFeature.getOrThrow(SnowUpdateConfigFeatures.SNOW_PATCH_FLOOR), List.of(CountPlacement.of(50), InSquarePlacement.spread(), 
        PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome()));

        register(pContext, SNOW_PATCH_CEILING_PLACED, configuredFeature.getOrThrow(SnowUpdateConfigFeatures.SNOW_PATCH_CEILING), List.of(CountPlacement.of(50), InSquarePlacement.spread(), 
        PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome()));
    }

    

    public static void register(BootstapContext<PlacedFeature> pContext, ResourceKey<PlacedFeature> pKey, Holder<ConfiguredFeature<?, ?>> pFeatureConfig, List<PlacementModifier> pModifiers){
        pContext.register(pKey, new PlacedFeature(pFeatureConfig, pModifiers));
    }
}
