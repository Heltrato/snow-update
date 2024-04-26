package net.technic.snow_update.worldgen.noise;

import java.util.List;
import java.util.function.LongFunction;

import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.world.level.biome.Biome;
import net.technic.snow_update.SnowUpdate;
import net.technic.snow_update.worldgen.api.RegionType;
import net.technic.snow_update.worldgen.area.Area;
import net.technic.snow_update.worldgen.area.AreaContext;
import net.technic.snow_update.worldgen.area.AreaFactory;
import net.technic.snow_update.worldgen.area.AreaTransformer0;
import net.technic.snow_update.worldgen.area.AreaTransformer1;

public class LayeredNoiseUtil {
    public static Area uniqueness(RegistryAccess registryAccess, RegionType regionType, long seed)
    {
        int numZooms = SnowUpdate.CONFIG.overworldRegionSize;

        if (regionType == RegionType.NETHER)
            numZooms = SnowUpdate.CONFIG.netherRegionSize;

        return createZoomedArea(seed, numZooms, new InitialLayer(registryAccess, regionType));
    }

    public static Area biomeArea(RegistryAccess registryAccess, long seed, int size, List<WeightedEntry.Wrapper<ResourceKey<Biome>>> entries)
    {
        return createZoomedArea(seed, size, new BiomeInitialLayer(registryAccess, entries));
    }

    public static Area createZoomedArea(long seed, int zooms, AreaTransformer0 initialTransformer)
    {
        LongFunction<AreaContext> contextFactory = (seedModifier) -> new AreaContext(25, seed, seedModifier);
        AreaFactory factory = initialTransformer.run(contextFactory.apply(1L));
        factory = ZoomLayer.FUZZY.run(contextFactory.apply(2000L), factory);
        factory = zoom(2001L, ZoomLayer.NORMAL, factory, 3, contextFactory);
        factory = zoom(1001L, ZoomLayer.NORMAL, factory, zooms, contextFactory);
        return factory.make();
    }

    public static AreaFactory zoom(long seedModifier, AreaTransformer1 transformer, AreaFactory initialAreaFactory, int times, LongFunction<AreaContext> contextFactory)
    {
        AreaFactory areaFactory = initialAreaFactory;

        for (int i = 0; i < times; ++i)
        {
            areaFactory = transformer.run(contextFactory.apply(seedModifier + (long)i), areaFactory);
        }

        return areaFactory;
    }
}
