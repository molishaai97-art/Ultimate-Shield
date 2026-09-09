package com.molishaai.ultimateshield.client;

import com.molishaai.ultimateshield.ShieldHooks;
import com.molishaai.ultimateshield.UltimateShieldMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = UltimateShieldMod.MOD_ID, value = Dist.CLIENT)
public final class ClientEvents {
    private ClientEvents() {
    }

    /**
     * Vanilla zeros sprint while using an item. Re-apply sprint at end of tick
     * so we do not overwrite other mods' aiStep mixins.
     */
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        if (player == null || mc.options == null) {
            return;
        }
        if (!ShieldHooks.shouldAllowSprintWhileBlocking(player)) {
            return;
        }
        if (player.isPassenger() || player.isMovingSlowly() || !player.canSprint()) {
            return;
        }
        boolean wantsSprint = mc.options.keySprint.isDown()
                || player.input.forwardImpulse >= 0.8F && player.isSprinting();
        if (wantsSprint && player.input.hasEnoughImpulseToStartSprinting()) {
            player.setSprinting(true);
        }
    }
}
