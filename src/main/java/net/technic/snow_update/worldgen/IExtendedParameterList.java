package net.technic.snow_update.worldgen;

import net.minecraft.core.RegistryAccess;
import net.minecraft.world.level.biome.Climate;
import net.technic.snow_update.worldgen.api.RegionType;

public interface IExtendedParameterList<T> {
    void initializeForTerraBlender(RegistryAccess registryAccess, RegionType regionType, long seed);
    T findValuePositional(Climate.TargetPoint target, int x, int y, int z);
    int getUniqueness(int x, int y, int z);
    @SuppressWarnings("rawtypes")
    Climate.RTree getTree(int uniqueness);
    int getTreeCount();
    boolean isInitialized();
}
