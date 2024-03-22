package net.technic.snow_update.Items;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.technic.snow_update.SnowUpdate;
import net.technic.snow_update.registry.SnowItemsRegistry;

public class SnowUpdateItemProperties {
    public static void addItemProperties(){
        ItemProperties.register(SnowItemsRegistry.YETI_HORN.get(), new ResourceLocation(SnowUpdate.MOD_ID, "tooting"), 
        (pStack, pLevel, pEntity, pSeed) -> {return pEntity != null && pEntity.isUsingItem() && pEntity.getUseItem() == pStack ? 1.0F : 0;});
    }
}
