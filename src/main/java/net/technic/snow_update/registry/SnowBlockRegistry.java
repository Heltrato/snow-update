package net.technic.snow_update.registry;

import java.util.function.Supplier;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.technic.snow_update.SnowUpdate;
import net.technic.snow_update.blocks.GlacierIce;
import net.technic.snow_update.blocks.KeyStone;
import net.technic.snow_update.blocks.PointedIceStalactite;

public class SnowBlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SnowUpdate.MOD_ID);

    public static final RegistryObject<Block> ICE_STALACTITE_BLOCK = registerBlock("ice_stalactite_block", 
    ()-> new Block(BlockBehaviour.Properties.copy(Blocks.ICE).sound(SoundType.GLASS)));

    public static final RegistryObject<Block> POINTED_ICE_STALACTITE = registerBlock("pointed_ice_stalactite", 
    ()-> new PointedIceStalactite(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion().sound(SoundType.POINTED_DRIPSTONE).randomTicks()
    .dynamicShape().offsetType(BlockBehaviour.OffsetType.XZ)));

    public static final RegistryObject<Block> KEY_STONE = registerBlock("key_stone", ()-> new KeyStone(BlockBehaviour.Properties.of().sound(SnowSoundsRegistry.KEYSTONE).mapColor(MapColor.COLOR_BLUE)));

    public static final RegistryObject<Block> GLACIER_ICE = registerBlock("glacier_ice", ()-> new GlacierIce(BlockBehaviour.Properties.of().sound(SoundType.GLASS).strength(-1, 3600000.0F).randomTicks().noOcclusion()));

    private static <T extends Block> RegistryObject<T> registerBlock(String pName, Supplier<T> pSupplier){
        RegistryObject<T> toReturn = BLOCKS.register(pName, pSupplier);
        registerBlock(pName, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlock(String pName, RegistryObject<T> pBlock){
        return SnowItemsRegistry.ITEMS.register(pName, ()-> new BlockItem(pBlock.get(), new Item.Properties()));
    }

    public static void register(IEventBus pEventBus){
        BLOCKS.register(pEventBus);
    }
    
}
