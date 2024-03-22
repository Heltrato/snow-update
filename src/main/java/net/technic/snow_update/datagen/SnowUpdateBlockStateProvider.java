package net.technic.snow_update.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
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
