package net.technic.snow_update.entity.ai;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.phys.Vec3;
import net.technic.snow_update.entity.JuvenileYetiEntity;

import javax.annotation.Nullable;

public class SnowBallThrowGoal extends RangedAttackGoal {

    private final float avoidDistSqr = 4F;
    private boolean shouldCountTillNextAttack = false;
    private int ticksUntilNextAttack = 35;
    private JuvenileYetiEntity entity;
    private int attackDelay = 35;
    private float attackRadiusSqr;
    private double speedModifier;
    private float attackRadius;
    private Mob mob;
    @Nullable
    private LivingEntity target;


    public SnowBallThrowGoal(RangedAttackMob pRangedAttackMob, double pSpeedModifier, int pAttackInterval, float pAttackRadius) {
        super(pRangedAttackMob, pSpeedModifier, pAttackInterval, pAttackRadius);
        this.entity = ((JuvenileYetiEntity) pRangedAttackMob);
        this.mob = (Mob) pRangedAttackMob;
        this.speedModifier = pSpeedModifier;
        this.attackRadius = pAttackRadius;
        this.attackRadiusSqr = pAttackRadius * pAttackRadius;

    }

    public boolean canUse() {
        LivingEntity livingentity = this.mob.getTarget();
        if (livingentity != null && livingentity.isAlive()) {
            this.target = livingentity;
            return true;
        } else {
            return false;
        }
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public boolean canContinueToUse() {
        return this.canUse() || this.target.isAlive() && !this.mob.getNavigation().isDone();
    }

    protected void checkAndPerformAttack(LivingEntity pEnemy, double pDistToEnemySqr) {
        if (isEnemyInDistance(pDistToEnemySqr)) {
            shouldCountTillNextAttack = true;

            if (isTimeToStartAnim()) {
                entity.setAttacking(true);

            }

            if (isTimeToAttack()) {
                float f = (float) Math.sqrt(pDistToEnemySqr) / this.attackRadius;
                float f1 = Mth.clamp(f, 0.1F, 1.0F);
                this.mob.getLookControl().setLookAt(pEnemy.getX(), pEnemy.getEyeY(), pEnemy.getZ());
                performRangedAttack(pEnemy, f1);
            }

        } else {
            resetCD();
            shouldCountTillNextAttack = false;
            entity.setAttacking(false);
            entity.setAnimationTimeout(0);


            if (!(pDistToEnemySqr > (double) this.attackRadiusSqr)) {
                this.mob.getNavigation().stop();
            } else {
                this.mob.getNavigation().moveTo(this.target, this.speedModifier);
            }
        }
    }

    private boolean isTimeToStartAnim() {
        return ticksUntilNextAttack <= attackDelay;
    }

    private boolean isEnemyInDistance(double pDistToEnemySqr) {
        return pDistToEnemySqr <= this.attackRadiusSqr;
    }

    protected void resetCD() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(attackDelay + 15);
    }

    protected boolean isTimeToAttack() {
        return this.ticksUntilNextAttack <= 0;
    }

    protected void performRangedAttack(LivingEntity pEnemy, float pDistToEnemySqr) {
        this.resetCD();
        entity.performRangedAttack(pEnemy, pDistToEnemySqr);
    }

    @Override
    public void start() {
        super.start();
        this.ticksUntilNextAttack = 35;
        this.attackDelay = 35;
    }

    @Override
    public void tick() {
        if (shouldCountTillNextAttack) {
            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
        }
        double d0 = this.mob.distanceToSqr(this.target.getX(), this.target.getY(), this.target.getZ());
        if (d0 <= this.avoidDistSqr) {
            Vec3 vec3 = DefaultRandomPos.getPosAway(this.entity, 10, 5, this.target.position());
            this.mob.getLookControl().setLookAt(entity);
            if (vec3 == null) {
                return;
            }
            this.mob.getNavigation().moveTo(vec3.x, vec3.y, vec3.z, this.speedModifier);
        }
        checkAndPerformAttack(target, d0);
    }

    @Override
    public void stop() {
        entity.setAttacking(false);
        super.stop();
        //
    }
}
    
    
