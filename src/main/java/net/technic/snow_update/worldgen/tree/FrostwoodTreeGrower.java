package net.technic.snow_update.worldgen.tree;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.technic.snow_update.worldgen.SnowUpdateConfigFeatures;

public class FrostwoodTreeGrower extends AbstractTreeGrower{

    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom, boolean pHasFlowers) {
        return SnowUpdateConfigFeatures.FROSTWOOD_KEY;
    }

}
