package net.technic.snow_update.worldgen.api;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.apache.commons.compress.utils.Lists;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;

public class ModifiedOverworldBuilder {
    
    private Map<ResourceKey<Biome>, ResourceKey<Biome>> originalBiomeMappings = Maps.newHashMap();
    private Map<Climate.ParameterPoint, ResourceKey<Biome>> parameterBiomeMappings = Maps.newHashMap();
    private Map<Climate.ParameterPoint, Climate.ParameterPoint> parameterMappings = Maps.newHashMap();
    private List<Climate.ParameterPoint> parametersToRemove = Lists.newArrayList();
    private final OverworldBiomeBuilder biomeBuilder = new OverworldBiomeBuilder();

    public ModifiedOverworldBuilder() {}

    /**
     * Replaces a Vanilla biome with another.
     * @param original the biome to replace.
     * @param replacement the biome to replace the original biome.
     */
    public void replaceBiome(ResourceKey<Biome> original, ResourceKey<Biome> replacement)
    {
        originalBiomeMappings.put(original, replacement);
    }

    /**
     * Replaces a Vanilla biome at a given {@link Climate.ParameterPoint} with another.
     * @param point the parameter point corresponding to a Vanilla biome.
     * @param biome the biome to be used at the provided parameter point.
     */
    public void replaceBiome(Climate.ParameterPoint point, ResourceKey<Biome> biome)
    {
        parameterBiomeMappings.put(point, biome);
    }

    /**
     * Replaces a {@link Climate.ParameterPoint} with another.
     * @param original the {@link Climate.ParameterPoint} to be replaced.
     * @param replacement the {@link Climate.ParameterPoint} to replace the original.
     */
    public void replaceParameter(Climate.ParameterPoint original, Climate.ParameterPoint replacement)
    {
        parameterMappings.put(original, replacement);
    }

    /**
     * Removes a {@link Climate.ParameterPoint} parameter.
     * @param parameter the {@link Climate.ParameterPoint} to be removed.
     */
    public void removeParameter(Climate.ParameterPoint parameter)
    {
        parametersToRemove.add(parameter);
    }

    /**
     * Builds a list of {@link Climate.ParameterPoint} and {@link ResourceKey<Biome>} pairs.
     * @return the built list.
     */
    public List<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> build()
    {
        ImmutableList.Builder<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> builder = new ImmutableList.Builder<>();
        Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper = pair -> {
            Climate.ParameterPoint parameters = pair.getFirst();
            ResourceKey<Biome> biome = pair.getSecond();

            if (!parametersToRemove.contains(parameters))
            {
                // Replace the biome if required.
                if (originalBiomeMappings.containsKey(biome))
                    biome = originalBiomeMappings.get(biome);
                else if (parameterBiomeMappings.containsKey(parameters))
                    biome = parameterBiomeMappings.get(parameters);

                // Replace the original parameters if required
                if (parameterMappings.containsKey(parameters))
                    parameters = parameterMappings.get(parameters);

                builder.add(Pair.of(parameters, biome));
            }
        };

        biomeBuilder.addBiomes(mapper);
        return builder.build();
    }
}
