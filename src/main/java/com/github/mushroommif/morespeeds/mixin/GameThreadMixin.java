package com.github.mushroommif.morespeeds.mixin;

import aoh.kingdoms.history.mainGame.GameThreads.GameThread;
import com.github.mushroommif.morespeeds.MoreSpeedsMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameThread.class)
public class GameThreadMixin {
    @Shadow public int playMaxSpeed;

    @Shadow public int playSpeed;

    @Shadow public int playSpeedTIME;

    @Unique
    private final int[] speeds = MoreSpeedsMod.config.getSpeeds();

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onInit(CallbackInfo ci) {
        this.playMaxSpeed = speeds.length;
        this.playSpeed = this.playMaxSpeed / 2;
    }

    @Inject(method = "getPlaySpeed", at = @At("HEAD"), cancellable = true)
    private void setCustomPlaySpeedTime(CallbackInfoReturnable<Integer> cir) {
        if (this.playSpeed <= 0 || this.playSpeed > speeds.length) {
            return;
        }

        this.playSpeedTIME = speeds[this.playSpeed - 1];
        cir.setReturnValue(this.playSpeedTIME);
    }
}
