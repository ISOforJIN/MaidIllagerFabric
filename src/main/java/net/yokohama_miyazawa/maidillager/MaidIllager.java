package net.yokohama_miyazawa.maidillager;

import net.fabricmc.api.ModInitializer;

import net.yokohama_miyazawa.maidillager.config.ModConfigs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MaidIllager implements ModInitializer {
    public static final String MODID = "maidillager";
    public static final Logger LOGGER = LoggerFactory.getLogger("maidillager");

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        ModConfigs.registerConfigs();

        LOGGER.info("Maid Illager");
    }
}