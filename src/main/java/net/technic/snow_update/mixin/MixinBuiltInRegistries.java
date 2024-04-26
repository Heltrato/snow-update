package net.technic.snow_update.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.mojang.serialization.Lifecycle;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.technic.snow_update.SnowUpdate;
import net.technic.snow_update.worldgen.surface.NamespacedSurfaceRuleSource;

@Mixin(BuiltInRegistries.class)
public class MixinBuiltInRegistries {
    @Shadow
    private static <T> Registry<T> registerSimple(ResourceKey<? extends Registry<T>> key, Lifecycle lifecycle, BuiltInRegistries.RegistryBootstrap<T> bootstrap) { return null; }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Inject(method="registerSimple(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/core/registries/BuiltInRegistries$RegistryBootstrap;)Lnet/minecraft/core/Registry;", at=@At("HEAD"), cancellable = true)
    private static void registerSimple(ResourceKey key, BuiltInRegistries.RegistryBootstrap bootstrap, CallbackInfoReturnable<Registry> cir)
    {
        if (key == Registries.MATERIAL_RULE)
        {
            cir.setReturnValue(registerSimple(key, Lifecycle.stable(), (registry -> {
                // Run the Vanilla bootstrap
                bootstrap.run(registry);

                // Run our bootstrap
                return Registry.register(registry, new ResourceLocation(SnowUpdate.MOD_ID, "merged"), NamespacedSurfaceRuleSource.CODEC.codec());
            })));
        }
    }
}
