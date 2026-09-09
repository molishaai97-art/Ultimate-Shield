package com.molishaai.ultimateshield.mixin;

import com.molishaai.ultimateshield.ShieldHooks;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin {
    @Inject(method = "disableShield", at = @At("HEAD"), cancellable = true)
    private void ultimateshield$neverDisable(boolean guaranteed, CallbackInfo ci) {
        Player self = (Player) (Object) this;
        if (ShieldHooks.shouldCancelDisable(self)) {
            ci.cancel();
        }
    }
}
