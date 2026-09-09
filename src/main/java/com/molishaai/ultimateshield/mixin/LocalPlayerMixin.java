package com.molishaai.ultimateshield.mixin;

import com.molishaai.ultimateshield.ShieldHooks;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin {
    @ModifyConstant(method = "aiStep", constant = @Constant(floatValue = 0.2F))
    private float ultimateshield$noBlockSlowdown(float original) {
        LocalPlayer self = (LocalPlayer) (Object) this;
        if (ShieldHooks.shouldIgnoreUseSlowdown(self)) {
            return 1.0F;
        }
        return original;
    }

    @Inject(method = "canStartSprinting", at = @At("RETURN"), cancellable = true)
    private void ultimateshield$sprintWhileBlocking(CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValueZ()) {
            return;
        }
        LocalPlayer self = (LocalPlayer) (Object) this;
        if (!ShieldHooks.shouldAllowSprintWhileBlocking(self)) {
            return;
        }
        if (self.isPassenger() || self.isMovingSlowly() || !self.canSprint()) {
            return;
        }
        if (!self.input.hasEnoughImpulseToStartSprinting()) {
            return;
        }
        cir.setReturnValue(true);
    }
}
