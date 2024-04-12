package net.technic.snow_update.datagen;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.technic.snow_update.SnowUpdate;
import net.technic.snow_update.registry.SnowBlockRegistry;

public class SnowUpdateBlockTagGenerator extends BlockTagsProvider{

    public SnowUpdateBlockTagGenerator(PackOutput output, CompletableFuture<Provider> lookupProvider,
            @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, SnowUpdate.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(Provider pProvider) {
        this.tag(BlockTags.FENCES).add(SnowBlockRegistry.FROSTED_WOOD_FENCE.get());
        this.tag(BlockTags.LOGS_THAT_BURN).add(
            SnowBlockRegistry.FROSTED_LOG.get(),
            SnowBlockRegistry.FROSTED_WOOD.get(),
            SnowBlockRegistry.STRIPPED_FROSTED_LOG.get(),
            SnowBlockRegistry.STRIPPED_FROSTED_WOOD.get()
        );
        this.tag(BlockTags.PLANKS).add(SnowBlockRegistry.FROSTED_PLANKS.get());
        this.tag(BlockTags.STAIRS).add(SnowBlockRegistry.FROSTED_PLANKS_STAIRS.get());
        this.tag(BlockTags.SNOW_LAYER_CANNOT_SURVIVE_ON).add(SnowBlockRegistry.KEY_STONE.get());
    }

}
