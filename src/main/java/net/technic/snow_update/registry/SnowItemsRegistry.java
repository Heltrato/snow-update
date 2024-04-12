package net.technic.snow_update.registry;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.technic.snow_update.SnowUpdate;
import net.technic.snow_update.Items.IceWand;
import net.technic.snow_update.Items.SnowUpdateArmorMaterials;
import net.technic.snow_update.Items.SnowUpdateFoodProperties;
import net.technic.snow_update.Items.SnowUpdateToolTiers;
import net.technic.snow_update.Items.YetiFurArmor;
import net.technic.snow_update.Items.YetiHornItem;

public class SnowItemsRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SnowUpdate.MOD_ID);
    
    public static final RegistryObject<Item> JUVENILE_YETI_SPAWN_EGG = ITEMS.register("juvenile_yeti_egg", ()-> new ForgeSpawnEggItem(SnowEntityRegistry.JUVENILE_YETI, 0x5fcde4, 0x5b6ee1,
    new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> GLACIER_RAW_YETI_MEAT = ITEMS.register("glacier_raw_yeti_meat", 
    ()-> new Item(new Item.Properties().food(SnowUpdateFoodProperties.GLACIAL_RAW_YETI_MEAT)));

    public static final RegistryObject<Item> RAW_YETI_MEAT = ITEMS.register("raw_yeti_meat", 
    ()-> new Item(new Item.Properties().food(SnowUpdateFoodProperties.RAW_YETI_MEAT)));

    public static final RegistryObject<Item> COOKED_YETI_MEAT = ITEMS.register("cooked_yeti_meat", 
    ()-> new Item(new Item.Properties().food(SnowUpdateFoodProperties.COOKED_YETI_MEAT)));

    public static final RegistryObject<Item> GLACIER_FRAGMENT_SHARD = ITEMS.register("glacier_fragment_shard", ()-> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GLACIER_GEM = ITEMS.register("glacier_gem", ()-> new Item(new Item.Properties()));

    public static final RegistryObject<Item> YETI_FUR = ITEMS.register("yeti_fur", ()-> new Item(new Item.Properties()));

    public static final RegistryObject<Item> YETI_HORN = ITEMS.register("yeti_horn", ()-> new YetiHornItem(new Item.Properties().stacksTo(1).durability(300)));

    public static final RegistryObject<Item> YETI_FUR_HELMET = ITEMS.register("yeti_fur_helmet", ()-> new YetiFurArmor(SnowUpdateArmorMaterials.YETI_FUR, ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> YETI_FUR_CHESTPLATE = ITEMS.register("yeti_fur_chestplate", ()-> new YetiFurArmor(SnowUpdateArmorMaterials.YETI_FUR, ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> YETI_FUR_LEGGINGS = ITEMS.register("yeti_fur_leggings", ()-> new YetiFurArmor(SnowUpdateArmorMaterials.YETI_FUR, ArmorItem.Type.LEGGINGS, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> YETI_FUR_BOOTS = ITEMS.register("yeti_fur_boots", ()-> new YetiFurArmor(SnowUpdateArmorMaterials.YETI_FUR, ArmorItem.Type.BOOTS, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> GLACIER_SWORD = ITEMS.register("glacier_sword", ()-> new SwordItem(SnowUpdateToolTiers.GLACIER, 1, -2.4F, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> GLACIER_PICKAXE = ITEMS.register("glacier_pickaxe", ()-> new PickaxeItem(SnowUpdateToolTiers.GLACIER, 1, -2.2F, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> GLACIER_AXE = ITEMS.register("glacier_axe", ()-> new AxeItem(SnowUpdateToolTiers.GLACIER, 6F, -3.0F, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> GLACIER_SHOVEL = ITEMS.register("glacier_shovel", ()-> new ShovelItem(SnowUpdateToolTiers.GLACIER, 1.5F, -2.8F, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> GLACIER_HOE = ITEMS.register("glacier_hoe", ()-> new HoeItem(SnowUpdateToolTiers.GLACIER, -2, -1F, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> GLACIER_SHARD = ITEMS.register("glacier_shard", ()->  new Item(new Item.Properties().stacksTo(4)));

    public static final RegistryObject<Item> ICE_WAND = ITEMS.register("ice_wand", ()-> new IceWand(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> GLACIER_HELMET = ITEMS.register("glacier_helmet", ()-> new ArmorItem(SnowUpdateArmorMaterials.GLACIER, ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> GLACIER_CHESTPLATE = ITEMS.register("glacier_chestplate", ()-> new ArmorItem(SnowUpdateArmorMaterials.GLACIER, ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> GLACIER_LEGGINGS = ITEMS.register("glacier_leggings", ()-> new ArmorItem(SnowUpdateArmorMaterials.GLACIER, ArmorItem.Type.LEGGINGS, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> GLACIER_BOOTS = ITEMS.register("glacier_boots", ()-> new ArmorItem(SnowUpdateArmorMaterials.GLACIER, ArmorItem.Type.BOOTS, new Item.Properties().stacksTo(1)));

    public static void register(IEventBus pEventBus){
        ITEMS.register(pEventBus);
    }
}
