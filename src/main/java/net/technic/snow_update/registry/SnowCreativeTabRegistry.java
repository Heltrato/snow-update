package net.technic.snow_update.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.technic.snow_update.SnowUpdate;

public class SnowCreativeTabRegistry {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SnowUpdate.MOD_ID);

    public static final RegistryObject<CreativeModeTab> SNOW_UPDATE_TAB = CREATIVE_TAB.register("snow_update_tab", ()-> new CreativeModeTab.Builder(CreativeModeTab.Row.TOP, 0).icon(()-> new ItemStack(SnowItemsRegistry.YETI_HORN.get()))
        .title(Component.translatable("creativetab.snow_update_tab"))
        .displayItems((pParameters, pOutput) -> {
            pOutput.accept(SnowItemsRegistry.YETI_HORN.get());
        })
        .build());

    public static void register(IEventBus pBus) {
        CREATIVE_TAB.register(pBus);
    }
}
