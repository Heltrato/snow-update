package net.technic.snow_update.worldgen;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Consumer;

import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ClampedNormalFloat;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Column;
import net.minecraft.world.level.levelgen.feature.DripstoneUtils;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.DripstoneClusterConfiguration;
import net.technic.snow_update.blocks.PointedIceStalactite;
import net.technic.snow_update.blocks.properties.IceStalactiteThickness;
import net.technic.snow_update.registry.SnowBlockRegistry;

public class IceStalactiteFeature extends Feature<DripstoneClusterConfiguration>{
   public IceStalactiteFeature(Codec<DripstoneClusterConfiguration> p_159575_) {
        super(p_159575_);
     }
  
   public boolean place(FeaturePlaceContext<DripstoneClusterConfiguration> p_159605_) {
        WorldGenLevel worldgenlevel = p_159605_.level();
        BlockPos blockpos = p_159605_.origin();
        DripstoneClusterConfiguration dripstoneclusterconfiguration = p_159605_.config();
        RandomSource randomsource = p_159605_.random();
        if (!isEmptyOrWater(worldgenlevel, blockpos)) {
           return false;
        } else {
           int i = dripstoneclusterconfiguration.height.sample(randomsource);
           float f = dripstoneclusterconfiguration.wetness.sample(randomsource);
           float f1 = dripstoneclusterconfiguration.density.sample(randomsource);
           int j = dripstoneclusterconfiguration.radius.sample(randomsource);
           int k = dripstoneclusterconfiguration.radius.sample(randomsource);
  
           for(int l = -j; l <= j; ++l) {
              for(int i1 = -k; i1 <= k; ++i1) {
                 double d0 = this.getChanceOfStalagmiteOrStalactite(j, k, l, i1, dripstoneclusterconfiguration);
                 BlockPos blockpos1 = blockpos.offset(l, 0, i1);
                 this.placeColumn(worldgenlevel, randomsource, blockpos1, l, i1, f, d0, i, f1, dripstoneclusterconfiguration);
              }
           }
  
           return true;
        }
     }
  
   private void placeColumn(WorldGenLevel p_225016_, RandomSource p_225017_, BlockPos p_225018_, int p_225019_, int p_225020_, float p_225021_, double p_225022_, int p_225023_, float p_225024_, DripstoneClusterConfiguration p_225025_) {
      Optional<Column> optional = Column.scan(p_225016_, p_225018_, p_225025_.floorToCeilingSearchRange, DripstoneUtils::isEmptyOrWater, DripstoneUtils::isNeitherEmptyNorWater);
        if (optional.isPresent()) {
           OptionalInt optionalint = optional.get().getCeiling();
           OptionalInt optionalint1 = optional.get().getFloor();
           if (optionalint.isPresent() || optionalint1.isPresent()) {
              boolean flag = p_225017_.nextFloat() < p_225021_;
              Column column;
              if (flag && optionalint1.isPresent() && this.canPlacePool(p_225016_, p_225018_.atY(optionalint1.getAsInt()))) {
                 int i = optionalint1.getAsInt();
                 column = optional.get().withFloor(OptionalInt.of(i - 1));
                 p_225016_.setBlock(p_225018_.atY(i), Blocks.ICE.defaultBlockState(), 2);
              } else {
                 column = optional.get();
              }
  
              OptionalInt optionalint2 = column.getFloor();
              boolean flag1 = p_225017_.nextDouble() < p_225022_;
              int j;
              if (optionalint.isPresent() && flag1 && !this.isLava(p_225016_, p_225018_.atY(optionalint.getAsInt()))) {
                 int k = p_225025_.dripstoneBlockLayerThickness.sample(p_225017_);
                 this.replaceBlocksWithDripstoneBlocks(p_225016_, p_225018_.atY(optionalint.getAsInt()), k, Direction.UP);
                 int l;
                 if (optionalint2.isPresent()) {
                    l = Math.min(p_225023_, optionalint.getAsInt() - optionalint2.getAsInt());
                 } else {
                    l = p_225023_;
                 }
  
                 j = this.getDripstoneHeight(p_225017_, p_225019_, p_225020_, p_225024_, l, p_225025_);
              } else {
                 j = 0;
              }
  
              boolean flag2 = p_225017_.nextDouble() < p_225022_;
              int i3;
              if (optionalint2.isPresent() && flag2 && !this.isLava(p_225016_, p_225018_.atY(optionalint2.getAsInt()))) {
                 int i1 = p_225025_.dripstoneBlockLayerThickness.sample(p_225017_);
                 this.replaceBlocksWithDripstoneBlocks(p_225016_, p_225018_.atY(optionalint2.getAsInt()), i1, Direction.DOWN);
                 if (optionalint.isPresent()) {
                    i3 = Math.max(0, j + Mth.randomBetweenInclusive(p_225017_, -p_225025_.maxStalagmiteStalactiteHeightDiff, p_225025_.maxStalagmiteStalactiteHeightDiff));
                 } else {
                    i3 = this.getDripstoneHeight(p_225017_, p_225019_, p_225020_, p_225024_, p_225023_, p_225025_);
                 }
              } else {
                 i3 = 0;
              }
  
              int j1;
              int j3;
              if (optionalint.isPresent() && optionalint2.isPresent() && optionalint.getAsInt() - j <= optionalint2.getAsInt() + i3) {
                 int k1 = optionalint2.getAsInt();
                 int l1 = optionalint.getAsInt();
                 int i2 = Math.max(l1 - j, k1 + 1);
                 int j2 = Math.min(k1 + i3, l1 - 1);
                 int k2 = Mth.randomBetweenInclusive(p_225017_, i2, j2 + 1);
                 int l2 = k2 - 1;
                 j3 = l1 - k2;
                 j1 = l2 - k1;
              } else {
                 j3 = j;
                 j1 = i3;
              }
  
              boolean flag3 = p_225017_.nextBoolean() && j3 > 0 && j1 > 0 && column.getHeight().isPresent() && j3 + j1 == column.getHeight().getAsInt();
              if (optionalint.isPresent()) {
                 growPointedDripstone(p_225016_, p_225018_.atY(optionalint.getAsInt() - 1), Direction.DOWN, j3, flag3);
              }
  
              if (optionalint2.isPresent()) {
                 growPointedDripstone(p_225016_, p_225018_.atY(optionalint2.getAsInt() + 1), Direction.UP, j1, flag3);
              }
  
           }
        }
   }

   protected static boolean isEmptyOrWater(LevelAccessor pLevel, BlockPos pPos) {
        return pLevel.isStateAtPosition(pPos, DripstoneUtils::isEmptyOrWater);
   }
  
   private boolean isLava(LevelReader p_159586_, BlockPos p_159587_) {
        return p_159586_.getBlockState(p_159587_).is(Blocks.LAVA);
   }
  
   private int getDripstoneHeight(RandomSource p_225009_, int p_225010_, int p_225011_, float p_225012_, int p_225013_, DripstoneClusterConfiguration p_225014_) {
        if (p_225009_.nextFloat() > p_225012_) {
           return 0;
        } else {
           int i = Math.abs(p_225010_) + Math.abs(p_225011_);
           float f = (float)Mth.clampedMap((double)i, 0.0D, (double)p_225014_.maxDistanceFromCenterAffectingHeightBias, (double)p_225013_ / 2.0D, 0.0D);
           return (int)randomBetweenBiased(p_225009_, 0.0F, (float)p_225013_, f, (float)p_225014_.heightDeviation);
        }
   }
  
   private boolean canPlacePool(WorldGenLevel p_159620_, BlockPos p_159621_) {
        BlockState blockstate = p_159620_.getBlockState(p_159621_);
        if (!blockstate.is(Blocks.ICE) && !blockstate.is(SnowBlockRegistry.ICE_STALACTITE_BLOCK.get()) && !blockstate.is(SnowBlockRegistry.POINTED_ICE_STALACTITE.get())) {
           if (p_159620_.getBlockState(p_159621_.above()).getBlock() == Blocks.ICE) {
              return false;
           } else {
              for(Direction direction : Direction.Plane.HORIZONTAL) {
                 if (!this.canBeAdjacentToWater(p_159620_, p_159621_.relative(direction))) {
                    return false;
                 }
              }
  
              return this.canBeAdjacentToWater(p_159620_, p_159621_.below());
           }
        } else {
           return false;
        }
   }
  
   private boolean canBeAdjacentToWater(LevelAccessor p_159583_, BlockPos p_159584_) {
        BlockState blockstate = p_159583_.getBlockState(p_159584_);
        return blockstate.is(BlockTags.BASE_STONE_OVERWORLD) || blockstate.getFluidState().is(FluidTags.WATER);
    }
  
   private void replaceBlocksWithDripstoneBlocks(WorldGenLevel p_159589_, BlockPos p_159590_, int p_159591_, Direction p_159592_) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = p_159590_.mutable();
  
        for(int i = 0; i < p_159591_; ++i) {
           if (!placeIceSpikeBlockIfPossible(p_159589_, blockpos$mutableblockpos)) {
              return;
           }
  
           blockpos$mutableblockpos.move(p_159592_);
        }
  
   }

   public static void growPointedDripstone(LevelAccessor p_190848_, BlockPos p_190849_, Direction p_190850_, int p_190851_, boolean p_190852_) {
        if (isDripstoneBase(p_190848_.getBlockState(p_190849_.relative(p_190850_.getOpposite())))) {
            BlockPos.MutableBlockPos blockpos$mutableblockpos = p_190849_.mutable();
            buildBaseToTipColumn(p_190850_, p_190851_, p_190852_, (p_190846_) -> {
               if(p_190846_.is(SnowBlockRegistry.POINTED_ICE_STALACTITE.get())){
                  p_190846_ = p_190846_.setValue(PointedIceStalactite.WATERLOGGED, Boolean.valueOf(p_190848_.isWaterAt(blockpos$mutableblockpos)));
               }
               p_190848_.setBlock(blockpos$mutableblockpos, p_190846_, 2);
               blockpos$mutableblockpos.move(p_190850_);
            });
        }
   }

   protected static void buildBaseToTipColumn(Direction p_159652_, int p_159653_, boolean p_159654_, Consumer<BlockState> p_159655_) {
        if (p_159653_ >= 3) {
            p_159655_.accept(createPointedIceSpike(p_159652_, IceStalactiteThickness.BASE));

            for(int i = 0; i < p_159653_ - 3; ++i) {
                p_159655_.accept(createPointedIceSpike(p_159652_, IceStalactiteThickness.MIDDLE));
            }
        }

        if (p_159653_ >= 2) {
            p_159655_.accept(createPointedIceSpike(p_159652_, IceStalactiteThickness.FRUSTUM));
        }

        if (p_159653_ >= 1) {
            p_159655_.accept(createPointedIceSpike(p_159652_, p_159654_ ? IceStalactiteThickness.TIP_MERGE : IceStalactiteThickness.TIP));
        }

   }

   public static boolean isDripstoneBaseOrLava(BlockState pState) {
        return isDripstoneBase(pState) || pState.is(Blocks.LAVA);
   }

   public static boolean isDripstoneBase(BlockState pState) {
        return pState.is(Blocks.DRIPSTONE_BLOCK) || pState.is(BlockTags.DRIPSTONE_REPLACEABLE);
   }
  
   private double getChanceOfStalagmiteOrStalactite(int p_159577_, int p_159578_, int p_159579_, int p_159580_, DripstoneClusterConfiguration p_159581_) {
        int i = p_159577_ - Math.abs(p_159579_);
        int j = p_159578_ - Math.abs(p_159580_);
        int k = Math.min(i, j);
        return (double)Mth.clampedMap((float)k, 0.0F, (float)p_159581_.maxDistanceFromEdgeAffectingChanceOfDripstoneColumn, p_159581_.chanceOfDripstoneColumnAtMaxDistanceFromCenter, 1.0F);
   }

   private static BlockState createPointedIceSpike(Direction p_159657_, IceStalactiteThickness p_159658_) {
        return SnowBlockRegistry.POINTED_ICE_STALACTITE.get().defaultBlockState().setValue(PointedIceStalactite.TIP_DIRECTION, p_159657_).setValue(PointedIceStalactite.THICKNESS, p_159658_);
   }

   public static boolean placeIceSpikeBlockIfPossible(LevelAccessor p_190854_, BlockPos p_190855_) {
      BlockState blockstate = p_190854_.getBlockState(p_190855_);
      if (blockstate.is(BlockTags.DRIPSTONE_REPLACEABLE)) {
          p_190854_.setBlock(p_190855_, SnowBlockRegistry.ICE_STALACTITE_BLOCK.get().defaultBlockState(), 2);
          return true;
      } else {
          return false;
      }
   }
  
   private static float randomBetweenBiased(RandomSource p_225003_, float p_225004_, float p_225005_, float p_225006_, float p_225007_) {
        return ClampedNormalFloat.sample(p_225003_, p_225006_, p_225007_, p_225004_, p_225005_);
   }
}