package net.technic.snow_update.datagen;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

public class SnowUpdateWorldGenProvider extends DatapackBuiltinEntriesProvider{

    public SnowUpdateWorldGenProvider(PackOutput output, CompletableFuture<Provider> registries,
            RegistrySetBuilder datapackEntriesBuilder, Set<String> modIds) {
        super(output, registries, datapackEntriesBuilder, modIds);
    }
    
}
