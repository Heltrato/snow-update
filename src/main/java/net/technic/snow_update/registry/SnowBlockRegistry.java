package net.technic.snow_update.registry;

import java.util.function.Supplier;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.technic.snow_update.SnowUpdate;
import net.technic.snow_update.blocks.FrostedWood;
import net.technic.snow_update.blocks.GlacierIce;
import net.technic.snow_update.blocks.KeyStone;
import net.technic.snow_update.blocks.PointedIceStalactite;

public class SnowBlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SnowUpdate.MOD_ID);
    
    public static final RegistryObject<Block> ICE_STALACTITE_BLOCK = registerBlock("ice_stalactite_block", 
    ()-> new Block(BlockBehaviour.Properties.copy(Blocks.ICE).sound(SoundType.GLASS).noLootTable()));

    public static final RegistryObject<Block> POINTED_ICE_STALACTITE = registerBlock("pointed_ice_stalactite", 
    ()-> new PointedIceStalactite(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion().sound(SoundType.POINTED_DRIPSTONE).randomTicks()
    .dynamicShape().offsetType(BlockBehaviour.OffsetType.XZ).noLootTable()));

    public static final RegistryObject<Block> KEY_STONE = registerBlock("key_stone", ()-> new KeyStone(BlockBehaviour.Properties.of().sound(SnowSoundsRegistry.KEYSTONE).mapColor(MapColor.COLOR_BLUE).noLootTable()
    .strength(-1, 3600000.0F)));

    public static final RegistryObject<Block> GLACIER_ICE = registerBlock("glacier_ice", ()-> new GlacierIce(BlockBehaviour.Properties.of().sound(SoundType.GLASS).strength(-1, 3600000.0F).randomTicks()
    .noOcclusion().isSuffocating(SnowBlockRegistry::never).noLootTable()));

    public static final RegistryObject<Block> FROSTED_LOG = registerBlock("frosted_log", ()-> new FrostedWood(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));

    public static final RegistryObject<Block> FROSTED_WOOD = registerBlock("frosted_wood", ()-> new FrostedWood(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));

    public static final RegistryObject<Block> STRIPPED_FROSTED_LOG = registerBlock("stripped_frosted_log", ()-> new FrostedWood(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));

    public static final RegistryObject<Block> STRIPPED_FROSTED_WOOD = registerBlock("stripped_frosted_wood", ()-> new FrostedWood(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));

    public static final RegistryObject<Block> FROSTED_PLANKS = registerBlock("frosted_planks", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)){
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 20;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 5;
        }
    });

    public static final RegistryObject<Block> FROSTED_LEAVES = registerBlock("frosted_leaves", ()-> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)){
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 60;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 5;
        }
    });

    public static final RegistryObject<Block> SNOWY_COBBLESTONE = registerBlock("snowy_cobblestone", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));

    public static final RegistryObject<Block> FROSTED_WOOD_FENCE = registerBlock("frosted_wood_fence", ()-> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)));

    public static final RegistryObject<Block> FROSTED_PLANKS_SLAB = registerBlock("frosted_planks_slab", ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)));

    public static final RegistryObject<Block> FROSTED_PLANKS_STAIRS = registerBlock("frosted_planks_stairs", 
    ()-> new StairBlock(()-> SnowBlockRegistry.FROSTED_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)));

    public static final RegistryObject<Block> CHISELED_FRIGIDITE = registerBlock("chiseled_frigidite", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));

    public static final RegistryObject<Block> CHISELED_KORISTONE = registerBlock("chiseled_koristone", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));

    public static final RegistryObject<Block> FRIGITIDE = registerBlock("frigidite", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));

    public static final RegistryObject<Block> FRIGITIDE_BRICKS = registerBlock("frigidite_bricks", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));

    public static final RegistryObject<Block> FROSTED_FRIGITIDE = registerBlock("frosted_frigidite", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));

    public static final RegistryObject<Block> GLACIER_BLOCK = registerBlock("glacier_block_material", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    public static final RegistryObject<Block> GLACIER_CRYSTAL = registerBlock("glacier_crystal", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK)));

    public static final RegistryObject<Block> PACKED_ICE_BRICKS = registerBlock("packed_ice_bricks", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));

    public static final RegistryObject<Block> PACKED_SNOW = registerBlock("packed_snow", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.SNOW_BLOCK)));

    public static final RegistryObject<Block> PACKED_SNOW_BRICKS = registerBlock("packed_snow_bricks", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.SNOW_BLOCK)));

    public static final RegistryObject<Block> POLISHED_FRIGITIDE = registerBlock("polished_frigidite", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));

    public static final RegistryObject<Block> POLISHED_BLUE_ICE = registerBlock("polished_blue_ice", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.BLUE_ICE)));

    public static final RegistryObject<Block> POLISHED_KORISTONE = registerBlock("polished_koristone", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));

    public static final RegistryObject<Block> POLISHED_PACKED_ICE = registerBlock("polished_packed_ice", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.PACKED_ICE)));

    public static final RegistryObject<Block> SHREDDED_KORISTONE = registerBlock("shredded_koristone", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));

    public static final RegistryObject<Block> SNOW_BRICKS = registerBlock("snow_bricks", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.SNOW_BLOCK)));

    public static final RegistryObject<Block> CHISELED_HOWLITE = registerBlock("chiseled_howlite", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));

    public static final RegistryObject<Block> CHISELED_HOWLITE_BRICKS = registerBlock("chiseled_howlite_bricks", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));

    public static final RegistryObject<Block> CHISELED_HOWLITE_TILES = registerBlock("chiseled_howlite_tiles", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));

    public static final RegistryObject<Block> CHISELED_POLISHED_HOWLITE = registerBlock("chiseled_polished_howlite", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.POLISHED_ANDESITE)));

    public static final RegistryObject<Block> HOWLITE = registerBlock("howlite", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> HOWLITE_BRICKS = registerBlock("howlite_bricks", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));

    public static final RegistryObject<Block> HOWLITE_TILES = registerBlock("howlite_tiles", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> POLISHED_HOWLITE = registerBlock("polished_howlite", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

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

    private static boolean never(BlockState p_50806_, BlockGetter p_50807_, BlockPos p_50808_) {
      return false;
    }
}
