package net.technic.snow_update.worldgen;

import net.minecraft.core.RegistryAccess;

public interface IExtendedTheEndBiomeSource {
    void initializeForTerraBlender(RegistryAccess registryAccess, long seed);
}
