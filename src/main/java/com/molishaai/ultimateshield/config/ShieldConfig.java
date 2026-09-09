package com.molishaai.ultimateshield.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class ShieldConfig {
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.BooleanValue ENABLED;
    public static final ForgeConfigSpec.BooleanValue PREVENT_AXE_DISABLE;
    public static final ForgeConfigSpec.BooleanValue PREVENT_SHIELD_COOLDOWN;
    public static final ForgeConfigSpec.BooleanValue ATTACK_WHILE_BLOCKING;
    public static final ForgeConfigSpec.BooleanValue NO_BLOCKING_SLOWDOWN;
    public static final ForgeConfigSpec.BooleanValue ALLOW_SPRINT_WHILE_BLOCKING;
    public static final ForgeConfigSpec.BooleanValue INFINITE_DURABILITY;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("Ultimate Shield — vanilla shield only. Other mods' shields are left alone.")
                .push("general");

        ENABLED = builder
                .comment("Master switch for the whole mod.")
                .define("enabled", true);
        PREVENT_AXE_DISABLE = builder
                .comment("Axes (players, vindicators, etc.) can no longer disable a raised vanilla shield.")
                .define("preventAxeDisable", true);
        PREVENT_SHIELD_COOLDOWN = builder
                .comment("Vanilla shields never receive an item cooldown (hotbar overlay / use lock).")
                .define("preventShieldCooldown", true);
        ATTACK_WHILE_BLOCKING = builder
                .comment("Allow left-click attacks with any main-hand item while an off-hand vanilla shield is raised. Guarding stays active during the swing. Does not change sword attack cooldown.")
                .define("attackWhileBlocking", true);
        NO_BLOCKING_SLOWDOWN = builder
                .comment("Remove the 80% movement penalty while blocking with a vanilla shield.")
                .define("noBlockingSlowdown", true);
        ALLOW_SPRINT_WHILE_BLOCKING = builder
                .comment("Allow sprinting while blocking with a vanilla shield.")
                .define("allowSprintWhileBlocking", true);
        INFINITE_DURABILITY = builder
                .comment("Vanilla shields do not lose durability when they block.")
                .define("infiniteDurability", true);

        builder.pop();
        SPEC = builder.build();
    }

    private ShieldConfig() {
    }
}
