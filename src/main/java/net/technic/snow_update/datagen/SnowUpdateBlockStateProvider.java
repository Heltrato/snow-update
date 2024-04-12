package net.technic.snow_update.datagen;

import org.jetbrains.annotations.NotNull;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.technic.snow_update.SnowUpdate;
import net.technic.snow_update.blocks.GlacierIce;
import net.technic.snow_update.blocks.KeyStone;
import net.technic.snow_update.registry.SnowBlockRegistry;

public class SnowUpdateBlockStateProvider extends BlockStateProvider{
    

    public SnowUpdateBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SnowUpdate.MOD_ID, existingFileHelper);
    }



    @Override
    protected void registerStatesAndModels() {
        createKeyStoneBlock();
        createGlacierIce();
        logBlock(((RotatedPillarBlock)SnowBlockRegistry.FROSTED_LOG.get()));
        axisBlock(((RotatedPillarBlock)SnowBlockRegistry.FROSTED_WOOD.get()), blockTexture(SnowBlockRegistry.FROSTED_LOG.get()), blockTexture(SnowBlockRegistry.FROSTED_LOG.get()));
        axisBlock(((RotatedPillarBlock)SnowBlockRegistry.STRIPPED_FROSTED_LOG.get()), blockTexture(SnowBlockRegistry.STRIPPED_FROSTED_LOG.get()), 
            new ResourceLocation(SnowUpdate.MOD_ID, "block/stripped_frosted_log_top"));
        axisBlock(((RotatedPillarBlock)SnowBlockRegistry.STRIPPED_FROSTED_WOOD.get()), blockTexture(SnowBlockRegistry.STRIPPED_FROSTED_LOG.get()),
        blockTexture(SnowBlockRegistry.FROSTED_LOG.get()));
        leavesBlock(SnowBlockRegistry.FROSTED_LEAVES.get());
        blockAndItem(SnowBlockRegistry.FROSTED_PLANKS);

        blockItem(SnowBlockRegistry.FROSTED_LOG);
        blockItem(SnowBlockRegistry.FROSTED_WOOD);
        blockItem(SnowBlockRegistry.STRIPPED_FROSTED_LOG);
        blockItem(SnowBlockRegistry.STRIPPED_FROSTED_WOOD);


        fenceBlock(((FenceBlock)SnowBlockRegistry.FROSTED_WOOD_FENCE.get()), blockTexture(SnowBlockRegistry.FROSTED_PLANKS.get()));
        slabBlock(((SlabBlock)SnowBlockRegistry.FROSTED_PLANKS_SLAB.get()), blockTexture(SnowBlockRegistry.FROSTED_PLANKS.get()), blockTexture(SnowBlockRegistry.FROSTED_PLANKS.get()));
        stairsBlock(((StairBlock)SnowBlockRegistry.FROSTED_PLANKS_STAIRS.get()), blockTexture(SnowBlockRegistry.FROSTED_PLANKS.get()));
        
        blockItem(SnowBlockRegistry.FROSTED_PLANKS_SLAB);
        blockItem(SnowBlockRegistry.FROSTED_PLANKS_STAIRS);

        blockAndItem(SnowBlockRegistry.SNOWY_COBBLESTONE);
        blockAndItem(SnowBlockRegistry.CHISELED_FRIGIDITE);
        blockAndItem(SnowBlockRegistry.CHISELED_KORISTONE);
        blockAndItem(SnowBlockRegistry.FRIGITIDE);
        blockAndItem(SnowBlockRegistry.FRIGITIDE_BRICKS);
        blockAndItem(SnowBlockRegistry.FROSTED_FRIGITIDE);
        blockAndItem(SnowBlockRegistry.GLACIER_BLOCK);
        blockAndItem(SnowBlockRegistry.GLACIER_CRYSTAL);
        blockAndItem(SnowBlockRegistry.PACKED_ICE_BRICKS);
        blockAndItem(SnowBlockRegistry.PACKED_SNOW);
        blockAndItem(SnowBlockRegistry.PACKED_SNOW_BRICKS);
        blockAndItem(SnowBlockRegistry.POLISHED_BLUE_ICE);
        blockAndItem(SnowBlockRegistry.POLISHED_FRIGITIDE);
        blockAndItem(SnowBlockRegistry.POLISHED_KORISTONE);
        blockAndItem(SnowBlockRegistry.POLISHED_PACKED_ICE);
        blockAndItem(SnowBlockRegistry.SHREDDED_KORISTONE);
        blockAndItem(SnowBlockRegistry.SNOW_BRICKS);
        blockAndItem(SnowBlockRegistry.CHISELED_HOWLITE);
        blockAndItem(SnowBlockRegistry.CHISELED_HOWLITE_BRICKS);
        blockAndItem(SnowBlockRegistry.CHISELED_HOWLITE_TILES);
        blockAndItem(SnowBlockRegistry.CHISELED_POLISHED_HOWLITE);
        blockAndItem(SnowBlockRegistry.HOWLITE);
        blockAndItem(SnowBlockRegistry.HOWLITE_BRICKS);
        blockAndItem(SnowBlockRegistry.HOWLITE_TILES);
        blockAndItem(SnowBlockRegistry.POLISHED_HOWLITE);


    }

    private void leavesBlock(@NotNull Block block) {
        simpleBlockWithItem(block, models().singleTexture(ForgeRegistries.BLOCKS.getKey(block).getPath(), new ResourceLocation("minecraft:block/leaves"), "all", 
        blockTexture(block)).renderType("cutout"));
    }



    private void createKeyStoneBlock(){
        getVariantBuilder(SnowBlockRegistry.KEY_STONE.get()).forAllStates(pState -> {
            if (pState.getValue(KeyStone.HAS_KEY)){
                return ConfiguredModel.builder().modelFile(models().getExistingFile(new ResourceLocation(SnowUpdate.MOD_ID, "block/keystone_with_key")))
                .rotationY((int)(pState.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180)%360).build();
            } else {
                return ConfiguredModel.builder().modelFile(models().getExistingFile(new ResourceLocation(SnowUpdate.MOD_ID, "block/keystone")))
                .rotationY((int)(pState.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180)%360).build();
            }
            
        });
        simpleBlockItem(SnowBlockRegistry.KEY_STONE.get(), models().getExistingFile(new ResourceLocation(SnowUpdate.MOD_ID, "block/keystone")));
    }

    private void blockAndItem(RegistryObject<Block> registryObject) {
        simpleBlockWithItem(registryObject.get(), cubeAll(registryObject.get()));
    }

    private void blockItem(RegistryObject<Block> registryObject) {
        simpleBlockItem(registryObject.get(), new ModelFile.UncheckedModelFile(SnowUpdate.MOD_ID + ":block/" + ForgeRegistries.BLOCKS.getKey(registryObject.get()).getPath()));
    }
    
    private void createGlacierIce() {

        simpleBlockItem(SnowBlockRegistry.GLACIER_ICE.get(), models().getExistingFile(new ResourceLocation(SnowUpdate.MOD_ID, "block/glacier_ice")));

        getVariantBuilder(SnowBlockRegistry.GLACIER_ICE.get()).forAllStates(pState -> {
            switch (pState.getValue(GlacierIce.AGE)) {
                case 0:
                    return ConfiguredModel.builder().modelFile(models().getExistingFile(new ResourceLocation(SnowUpdate.MOD_ID, "block/glacier_ice"))).build();
                    
                case 1:
                    return ConfiguredModel.builder().modelFile(models().getExistingFile(new ResourceLocation(SnowUpdate.MOD_ID, "block/glacier_ice_1"))).build();
                
                case 2: 
                    return ConfiguredModel.builder().modelFile(models().getExistingFile(new ResourceLocation(SnowUpdate.MOD_ID, "block/glacier_ice_2"))).build();
                case 3: 
                    return ConfiguredModel.builder().modelFile(models().getExistingFile(new ResourceLocation(SnowUpdate.MOD_ID, "block/glacier_ice_3"))).build();

                default:
                    return null;
            }
        });
        
    }
    
    

}
