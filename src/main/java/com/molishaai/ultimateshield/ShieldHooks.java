package com.molishaai.ultimateshield;

import com.molishaai.ultimateshield.config.ShieldConfig;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

/**
 * Shared predicates so mixins stay tiny and do not fight other mods.
 * Only vanilla {@link Items#SHIELD} is touched.
 */
public final class ShieldHooks {
    private ShieldHooks() {
    }

    public static boolean enabled() {
        return ShieldConfig.SPEC.isLoaded() && ShieldConfig.ENABLED.get();
    }

    public static boolean isVanillaShield(ItemStack stack) {
        return !stack.isEmpty() && stack.is(Items.SHIELD);
    }

    public static boolean isVanillaShield(Item item) {
        return item == Items.SHIELD;
    }

    public static boolean isUsingVanillaShield(LivingEntity entity) {
        return entity.isUsingItem() && isVanillaShield(entity.getUseItem());
    }

    public static boolean isGuardingWithOffhand(LivingEntity entity) {
        return isUsingVanillaShield(entity) && entity.getUsedItemHand() == InteractionHand.OFF_HAND;
    }

    public static boolean shouldCancelDisable(LivingEntity entity) {
        return enabled() && ShieldConfig.PREVENT_AXE_DISABLE.get() && isUsingVanillaShield(entity);
    }

    public static boolean shouldBlockCooldown(Item item) {
        return enabled() && ShieldConfig.PREVENT_SHIELD_COOLDOWN.get() && isVanillaShield(item);
    }

    public static boolean shouldAllowAttackWhileBlocking(LivingEntity entity) {
        return enabled() && ShieldConfig.ATTACK_WHILE_BLOCKING.get() && isGuardingWithOffhand(entity);
    }

    public static boolean shouldIgnoreUseSlowdown(LivingEntity entity) {
        return enabled() && ShieldConfig.NO_BLOCKING_SLOWDOWN.get() && isUsingVanillaShield(entity);
    }

    public static boolean shouldAllowSprintWhileBlocking(LivingEntity entity) {
        return enabled() && ShieldConfig.ALLOW_SPRINT_WHILE_BLOCKING.get() && isUsingVanillaShield(entity);
    }

    public static boolean shouldSkipDurability() {
        return enabled() && ShieldConfig.INFINITE_DURABILITY.get();
    }
}
