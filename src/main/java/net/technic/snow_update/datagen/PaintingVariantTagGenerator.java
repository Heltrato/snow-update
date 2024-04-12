package net.technic.snow_update.datagen;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PaintingVariantTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.technic.snow_update.SnowUpdate;

public class PaintingVariantTagGenerator extends PaintingVariantTagsProvider {

    public PaintingVariantTagGenerator(PackOutput pOutput, CompletableFuture<Provider> pProvider,
            @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, SnowUpdate.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(Provider pProvider) {
        this.tag(PaintingVariantTags.PLACEABLE).addOptional(new ResourceLocation(SnowUpdate.MOD_ID, "snowy_hills"));
    }
}
