package com.molishaai.ultimateshield.mixin;

import com.molishaai.ultimateshield.ShieldHooks;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemCooldowns;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemCooldowns.class)
public abstract class ItemCooldownsMixin {
    @Inject(method = "addCooldown", at = @At("HEAD"), cancellable = true)
    private void ultimateshield$noShieldCooldown(Item item, int ticks, CallbackInfo ci) {
        if (ShieldHooks.shouldBlockCooldown(item)) {
            ci.cancel();
        }
    }
}
