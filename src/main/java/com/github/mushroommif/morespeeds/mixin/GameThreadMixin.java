package com.github.mushroommif.morespeeds.mixin;

import aoc.kingdoms.lukasz.jakowski.GameThreads.GameThread;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameThread.class)
public class GameThreadMixin {
    @Shadow public int playMaxSpeed;

    @Shadow public int playSpeed;

    @Shadow public int playSpeedTIME;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onInit(CallbackInfo ci) {
        this.playMaxSpeed = 10;
        this.playSpeed = 5;
    }

    @Inject(method = "getPlaySpeed", at = @At("HEAD"), cancellable = true)
    private void setCustomPlaySpeedTime(CallbackInfoReturnable<Integer> cir) {
        switch (this.playSpeed) {
            case 1:
                this.playSpeedTIME = 1000; // 1
                break;
            case 2:
                this.playSpeedTIME = 500; // 2
                break;
            case 3:
                this.playSpeedTIME = 250; // 3
                break;
            case 4:
                this.playSpeedTIME = 188;
                break;
            case 5:
                this.playSpeedTIME = 125; // 4
                break;
            case 6:
                this.playSpeedTIME = 105;
                break;
            case 7:
                this.playSpeedTIME = 85;
                break;
            case 8:
                this.playSpeedTIME = 65;
                break;
            case 9:
                this.playSpeedTIME = 45; // 5
                break;
            case 10:
                this.playSpeedTIME = 25;
            default:
                return;
        }

        cir.setReturnValue(this.playSpeedTIME);
    }
}
