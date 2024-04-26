package net.technic.snow_update.worldgen.noise;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import com.google.common.collect.ImmutableList;

import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.world.level.biome.Biome;
import net.technic.snow_update.worldgen.api.Region;
import net.technic.snow_update.worldgen.api.RegionType;
import net.technic.snow_update.worldgen.api.Regions;

public class InitialLayer extends WeightedRandomLayer<WeightedEntry.Wrapper<Region>> {
    private final RegionType regionType;

    public InitialLayer(RegistryAccess registryAccess, RegionType regionType)
    {
        super(createEntries(registryAccess, regionType));
        this.regionType = regionType;
    }

    @Override
    protected int getEntryIndex(WeightedEntry.Wrapper<Region> entry)
    {
        return Regions.getIndex(this.regionType, entry.getData().getName());
    }

    private static List<WeightedEntry.Wrapper<Region>> createEntries(RegistryAccess registryAccess, RegionType regionType)
    {
        Registry<Biome> biomeRegistry = registryAccess.registryOrThrow(Registries.BIOME);
        return Regions.get(regionType).stream().filter(region -> {
            AtomicBoolean biomesAdded = new AtomicBoolean(false);
            region.addBiomes(biomeRegistry, pair -> biomesAdded.set(true));

            // Filter out irrelevant regions or regions without any biomes
            return region.getType() == regionType && biomesAdded.get();
        }).map(region -> WeightedEntry.wrap(region, region.getWeight())).collect(ImmutableList.toImmutableList());
    }
}
