package net.technic.snow_update.datagen;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.technic.snow_update.SnowUpdate;

@Mod.EventBusSubscriber(modid = SnowUpdate.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGen {
    
    @SubscribeEvent
    public static void gatherData(GatherDataEvent pEvent){
        DataGenerator generator = pEvent.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = pEvent.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookUpProvider = pEvent.getLookupProvider();
        SnowUpdateBlockTagGenerator blockTagsProvider = generator.addProvider(pEvent.includeServer(), new SnowUpdateBlockTagGenerator(packOutput, lookUpProvider, existingFileHelper));
        generator.addProvider(pEvent.includeClient(), new SnowUpdateBlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(pEvent.includeServer(), new SnowUpdateRecipeProvider(packOutput));
        generator.addProvider(pEvent.includeClient(), new ItemModelProvider(packOutput, existingFileHelper));
        generator.addProvider(pEvent.includeClient(), new MinecraftItemModelProvider(packOutput, existingFileHelper));
        generator.addProvider(pEvent.includeServer(), SnowUpdateLootTableProvider.create(packOutput));
        
        generator.addProvider(pEvent.includeServer(), new SnowUpdateItemTagGenerator(packOutput, lookUpProvider, blockTagsProvider.contentsGetter(), existingFileHelper));
        generator.addProvider(pEvent.includeServer(), new SnowUpdateWorldGenProvider(packOutput, lookUpProvider));
    }
}
