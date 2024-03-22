package net.technic.snow_update.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.technic.snow_update.SnowUpdate;

public class MinecraftItemModelProvider extends net.minecraftforge.client.model.generators.ItemModelProvider{

    float[] values = new float[]{0.1F, 0.3F, 0.4F, 0.5F, 0.6F, 0.7F, 0.8F, 0.85F, 0.9F, 1.0F};
    String[] names = new String[]{"_quartz_trim", "_netherite_trim", "_redstone_trim", "_copper_trim", "_gold_trim", "_emerald_trim", "_diamond_trim", "_glacier_trim", "_lapis_trim", "_amethyst_trim"};

    public MinecraftItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, "minecraft", existingFileHelper);
        
    }

    @Override
    protected void registerModels() {
        armorTrims("chainmail_helmet");
        armorTrims("chainmail_chestplate");
        armorTrims("chainmail_leggings");
        armorTrims("chainmail_boots");

        armorTrims("iron_helmet");
        armorTrims("iron_chestplate");
        armorTrims("iron_leggings");
        armorTrims("iron_boots");

        armorTrims("golden_helmet");
        armorTrims("golden_chestplate");
        armorTrims("golden_leggings");
        armorTrims("golden_boots");

        armorTrims("diamond_helmet");
        armorTrims("diamond_chestplate");
        armorTrims("diamond_leggings");
        armorTrims("diamond_boots");

        armorTrims("netherite_helmet");
        armorTrims("netherite_chestplate");
        armorTrims("netherite_leggings");
        armorTrims("netherite_boots");
    }

    private ItemModelBuilder armorTrims(String pName){
        ItemModelBuilder model = withExistingParent(pName, new ResourceLocation("item/generated")).texture("layer0", mcLoc("item/"+pName));
        for (int i = 0; i<10 ; i++){
            ItemModelBuilder modelOverride;
            if (i == 7){
                modelOverride = new ItemModelBuilder(new ResourceLocation(SnowUpdate.MOD_ID, "item/"+pName+names[i]), existingFileHelper);
            } else {
                modelOverride = new ItemModelBuilder(new ResourceLocation("item/"+pName+names[i]), existingFileHelper);    
            }
            model.override().predicate(new ResourceLocation("trim_type"), values[i]).model(modelOverride).end();
        }
        
        return model;
    }
}
