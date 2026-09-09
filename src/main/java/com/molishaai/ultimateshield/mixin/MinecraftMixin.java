package com.molishaai.ultimateshield.mixin;

import com.molishaai.ultimateshield.ShieldHooks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.common.ForgeHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
    @Shadow
    private int missTime;

    @Inject(method = "startAttack", at = @At("HEAD"), cancellable = true)
    private void ultimateshield$attackWhileBlocking(CallbackInfoReturnable<Boolean> cir) {
        Minecraft self = (Minecraft) (Object) this;
        LocalPlayer player = self.player;
        if (player == null || self.gameMode == null || self.hitResult == null) {
            return;
        }
        if (!ShieldHooks.shouldAllowAttackWhileBlocking(player)) {
            return;
        }
        if (this.missTime > 0) {
            cir.setReturnValue(false);
            return;
        }

        var click = ForgeHooksClient.onClickInput(0, self.options.keyAttack, InteractionHand.MAIN_HAND);
        if (click.isCanceled()) {
            cir.setReturnValue(false);
            return;
        }

        HitResult.Type type = self.hitResult.getType();
        if (type == HitResult.Type.ENTITY) {
            self.gameMode.attack(player, ((EntityHitResult) self.hitResult).getEntity());
        } else {
            if (type == HitResult.Type.MISS && self.gameMode.hasMissTime()) {
                this.missTime = 10;
            }
            player.resetAttackStrengthTicker();
            ForgeHooks.onEmptyLeftClick(player);
        }

        if (click.shouldSwingHand()) {
            player.swing(InteractionHand.MAIN_HAND);
        }
        cir.setReturnValue(click.shouldSwingHand());
    }
}
