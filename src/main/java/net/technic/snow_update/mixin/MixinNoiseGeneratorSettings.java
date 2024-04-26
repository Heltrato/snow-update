package net.technic.snow_update.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.technic.snow_update.worldgen.IExtendedNoiseGeneratorSettings;
import net.technic.snow_update.worldgen.api.SurfaceRuleManager;

@Mixin(NoiseGeneratorSettings.class)
public class MixinNoiseGeneratorSettings implements IExtendedNoiseGeneratorSettings{
    @Shadow
    private SurfaceRules.RuleSource surfaceRule;
    
    @Unique
    private SurfaceRuleManager.RuleCategory ruleCategory = null;
    @Unique
    private SurfaceRules.RuleSource namespacedSurfaceRuleSource = null;

    @Inject(method = "surfaceRule", at = @At("HEAD"), cancellable = true)
    private void surfaceRule(CallbackInfoReturnable<SurfaceRules.RuleSource> cir)
    {
        if (this.ruleCategory != null)
        {
            if (this.namespacedSurfaceRuleSource == null)
                this.namespacedSurfaceRuleSource = SurfaceRuleManager.getNamespacedRules(this.ruleCategory, this.surfaceRule);

            cir.setReturnValue(this.namespacedSurfaceRuleSource);
        }
    }

    @Override
    public void setRuleCategory(SurfaceRuleManager.RuleCategory ruleCategory)
    {
        this.ruleCategory = ruleCategory;
    }
}
