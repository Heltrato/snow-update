package net.technic.snow_update.datagen;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.technic.snow_update.SnowUpdate;

public class SnowUpdateBlockTagGenerator extends BlockTagsProvider{

    public SnowUpdateBlockTagGenerator(PackOutput output, CompletableFuture<Provider> lookupProvider,
            @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, SnowUpdate.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(Provider pProvider) {
        
    }

}
