package net.technic.snow_update.worldgen.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

public class IceCrystalSpikeConfig implements FeatureConfiguration {
    public static final Codec<IceCrystalSpikeConfig> CODEC = RecordCodecBuilder.create((codec) -> {
        return codec.group(BlockState.CODEC.fieldOf("crystal_state").forGetter((config) -> {
            return config.crystal_state;
        }), BlockState.CODEC.fieldOf("cluster_state").forGetter((config) -> {
            return config.cluster_state;
        }), IntProvider.CODEC.fieldOf("xz_radius").forGetter((config) -> {
            return config.xzRadius;
        }), CaveSurface.CODEC.fieldOf("crystal_direction").forGetter((config) -> {
            return config.crystal_direction;
        })).apply(codec, IceCrystalSpikeConfig::new);
    });
    public final BlockState crystal_state;
    public final BlockState cluster_state;
    public final IntProvider xzRadius;
    public final CaveSurface crystal_direction;

    public IceCrystalSpikeConfig(BlockState crystal_state, BlockState cluster_state, IntProvider xzRadius, CaveSurface crystal_direction) {
        this.cluster_state = cluster_state;
        this.crystal_state = crystal_state;
        this.xzRadius = xzRadius;
        this.crystal_direction = crystal_direction;
    }
}
