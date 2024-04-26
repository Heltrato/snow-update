package net.technic.snow_update.worldgen;


import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ClampedNormalFloat;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.DripstoneClusterConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.technic.snow_update.SnowUpdate;
import net.technic.snow_update.registry.SnowBlockRegistry;
import net.technic.snow_update.registry.SnowFeaturesRegistry;
import net.technic.snow_update.worldgen.tree.FrostwoodFoliagePlacer;
import net.technic.snow_update.worldgen.tree.FrostwoodTrunkPlacer;

public class SnowUpdateConfigFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> ICE_STALACTITE =  registerKey("ice_stalactite");

    public static final ResourceKey<ConfiguredFeature<?, ?>> FROSTWOOD_KEY = registerKey("frostwood_key");

    public static void boostrap(BootstapContext<ConfiguredFeature<?, ?>> pContext){
        register(pContext, ICE_STALACTITE, SnowFeaturesRegistry.ICE_STALACTITE_FEATURE.get(), 
            new DripstoneClusterConfiguration(12, UniformInt.of(3, 6), 
            UniformInt.of(2, 8), 1, 3, 
            UniformInt.of(2, 4), UniformFloat.of(0.3F, 0.7F), 
            ClampedNormalFloat.of(0.1F, 0.3F, 0.1F, 0.9F), 0.1F, 3, 8));

        register(pContext, FROSTWOOD_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(SnowBlockRegistry.FROSTED_LOG.get()), 
        new FrostwoodTrunkPlacer(2, 3, 4), 
        BlockStateProvider.simple(SnowBlockRegistry.FROSTED_LEAVES.get()), 
        new FrostwoodFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), 3), 
        new TwoLayersFeatureSize(1, 0, 2)).build());
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> pContext,
            ResourceKey<ConfiguredFeature<?, ?>> pKey,F pFeature,
            FC pFeatureConfig){
        pContext.register(pKey, new ConfiguredFeature<>(pFeature, pFeatureConfig));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String pName) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(SnowUpdate.MOD_ID, pName));
    }



}
