package com.molishaai.ultimateshield;

import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = UltimateShieldMod.MOD_ID)
public final class ForgeEvents {
    private ForgeEvents() {
    }

    @SubscribeEvent
    public static void onShieldBlock(ShieldBlockEvent event) {
        if (!ShieldHooks.shouldSkipDurability()) {
            return;
        }
        if (!event.getEntity().getUseItem().is(Items.SHIELD)) {
            return;
        }
        event.setShieldTakesDamage(false);
    }
}
