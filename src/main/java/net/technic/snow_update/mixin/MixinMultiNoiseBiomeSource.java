package net.technic.snow_update.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.technic.snow_update.worldgen.IExtendedParameterList;

@Mixin(MultiNoiseBiomeSource.class)
public abstract class MixinMultiNoiseBiomeSource {
    @Shadow
    public abstract Climate.ParameterList<Holder<Biome>> parameters();

    @SuppressWarnings("unchecked")
    @Inject(method="getNoiseBiome(IIILnet/minecraft/world/level/biome/Climate$Sampler;)Lnet/minecraft/core/Holder;", at=@At("HEAD"), cancellable = true)
    public void getNoiseBiome(int x, int y, int z, Climate.Sampler sampler, CallbackInfoReturnable<Holder<Biome>> cir)
    {   
        cir.setReturnValue(((IExtendedParameterList<Holder<Biome>>)this.parameters()).findValuePositional(sampler.sample(x, y, z), x, y, z));
    }
}
