package net.technic.snow_update.entity.ai;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.LookControl;
import net.technic.snow_update.entity.TitanYetiEntity;

public class CustomLookControl extends LookControl{

    private TitanYetiEntity entity; 

    public CustomLookControl(Mob pMob) {
        super(pMob);
        if (pMob instanceof TitanYetiEntity){
            this.entity = ((TitanYetiEntity)pMob);
        }
    }

    @Override
    public void tick() {
        if (this.entity != null){
            if (this.entity.isStunned())
                return;
        }
        super.tick();
    }

}
