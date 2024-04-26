package net.technic.snow_update.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.serialization.Lifecycle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.client.gui.screens.worldselection.WorldOpenFlows;

@Mixin(WorldOpenFlows.class)
public class MixinWorldOpenFlows {
    @Inject(method="confirmWorldCreation", at=@At("HEAD"), cancellable = true)
    private static void skipConfirmation(Minecraft minecraft, CreateWorldScreen screen, Lifecycle lifecycle, Runnable next, boolean doWarning, CallbackInfo ci) {
        next.run();
        ci.cancel();
    }
}
