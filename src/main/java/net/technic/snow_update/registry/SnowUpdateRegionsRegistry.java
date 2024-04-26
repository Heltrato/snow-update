package net.technic.snow_update.registry;

import net.minecraft.resources.ResourceLocation;
import net.technic.snow_update.SnowUpdate;
import net.technic.snow_update.worldgen.api.Regions;
import net.technic.snow_update.worldgen.biomes.SnowUpdateOverworldRegion;

public class SnowUpdateRegionsRegistry {
    public static void registerRegions() {
        Regions.register(new SnowUpdateOverworldRegion(new ResourceLocation(SnowUpdate.MOD_ID, "overworld"), 5));
    }
}
