package com.molishaai.ultimateshield;

import com.molishaai.ultimateshield.config.ShieldConfig;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(UltimateShieldMod.MOD_ID)
public class UltimateShieldMod {
    public static final String MOD_ID = "ultimateshield";
    public static final Logger LOGGER = LogManager.getLogger();

    public UltimateShieldMod() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ShieldConfig.SPEC);
        LOGGER.info("Ultimate Shield loaded");
    }
}
