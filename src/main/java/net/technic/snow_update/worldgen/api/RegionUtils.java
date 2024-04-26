package net.technic.snow_update.worldgen.api;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;

public class RegionUtils {
    private static final List<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> VANILLA_POINTS;

    private static Map<ResourceKey<Biome>, List<Climate.ParameterPoint>> biomeParameterPointCache = Maps.newHashMap();

    public static List<Climate.ParameterPoint> getVanillaParameterPoints(ResourceKey<Biome> biome)
    {
        if (biomeParameterPointCache.containsKey(biome))
            return biomeParameterPointCache.get(biome);

        List<Climate.ParameterPoint> points = VANILLA_POINTS.stream().filter(pair -> pair.getSecond() == biome).map(pair -> pair.getFirst()).collect(ImmutableList.toImmutableList());
        biomeParameterPointCache.put(biome, points);
        return points;
    }

    static
    {
        @SuppressWarnings({ "rawtypes", "unchecked" })
        ImmutableList.Builder<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> builder = new ImmutableList.Builder();
        (new OverworldBiomeBuilder()).addBiomes(pair -> builder.add(pair));
        VANILLA_POINTS = builder.build();
    }
}
