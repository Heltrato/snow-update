package net.technic.snow_update.blocks;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class GlacierIce extends Block{
    public static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");
    public static final IntegerProperty AGE =BlockStateProperties.AGE_3;
    

    public GlacierIce(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(ACTIVATED, false));
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> pBuilder) {
        pBuilder.add(ACTIVATED, AGE);
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        

        return this.defaultBlockState().setValue(ACTIVATED, Boolean.valueOf(false)).setValue(AGE, Integer.valueOf(0));
    }

    @Override
    public boolean canStickTo(BlockState state, BlockState other) {
        return false;
    }
    

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pState.getValue(ACTIVATED) == true) {
            int i = pState.getValue(AGE);
            if (i < 3){
                pLevel.setBlock(pPos, pState.setValue(AGE, i+1), 3);
            } else {
                pLevel.setBlockAndUpdate(pPos, Blocks.AIR.defaultBlockState());
                pLevel.neighborChanged(pPos, Blocks.AIR, pPos);
            }
            
        }
    }

    public boolean skipRendering(BlockState pState, BlockState pAdjacentBlockState, Direction pSide) {
        return pAdjacentBlockState.is(this) ? true : false;
    }

}
