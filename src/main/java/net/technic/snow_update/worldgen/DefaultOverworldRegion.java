package net.technic.snow_update.worldgen;

import java.util.function.Consumer;

import com.mojang.datafixers.util.Pair;

import net.technic.snow_update.worldgen.api.Region;
import net.technic.snow_update.worldgen.api.RegionType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;

public class DefaultOverworldRegion extends Region{
    public static final ResourceLocation LOCATION = new ResourceLocation("minecraft:overworld");

    public DefaultOverworldRegion(int weight)
    {
        super(LOCATION, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {
        (new OverworldBiomeBuilder()).addBiomes(mapper);
    }
}
