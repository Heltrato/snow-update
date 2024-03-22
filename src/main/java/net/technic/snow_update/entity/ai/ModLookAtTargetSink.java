package net.technic.snow_update.entity.ai;

import com.google.common.collect.ImmutableMap;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.technic.snow_update.entity.TitanYetiEntity;


public class ModLookAtTargetSink extends Behavior<Mob>{
   public ModLookAtTargetSink(int pMinDuration, int pMaxDuration){
        super(ImmutableMap.of(MemoryModuleType.LOOK_TARGET, MemoryStatus.VALUE_PRESENT), pMinDuration, pMaxDuration);
    }

   @Override
   protected boolean checkExtraStartConditions(ServerLevel pLevel, Mob pMob) {
      TitanYetiEntity pOwner = ((TitanYetiEntity)pMob);
         return !(pOwner.isCharging() || pOwner.isCrashing() || pOwner.isStartCharging());
   }

   protected boolean canStillUse(ServerLevel pLevel, Mob pMob, long pGameTime) {
      TitanYetiEntity pEntity = ((TitanYetiEntity)pMob);
      return pEntity.getBrain().getMemory(MemoryModuleType.LOOK_TARGET).filter((p_23497_) -> {
         return p_23497_.isVisibleBy(pEntity);
      }).isPresent() && !(pEntity.isCharging() || pEntity.isCrashing() || pEntity.isStartCharging());
   }

   protected void stop(ServerLevel pLevel, Mob pEntity, long pGameTime) {
      pEntity.getBrain().eraseMemory(MemoryModuleType.LOOK_TARGET);
   }

   protected void tick(ServerLevel pLevel, Mob pOwner, long pGameTime) {
      pOwner.getBrain().getMemory(MemoryModuleType.LOOK_TARGET).ifPresent((p_23486_) -> {
         pOwner.getLookControl().setLookAt(p_23486_.currentPosition());
      });
   }
}

