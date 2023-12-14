package net.yokohama_miyazawa.maidillager.config;

import com.mojang.datafixers.util.Pair;
import net.yokohama_miyazawa.maidillager.MaidIllager;

public class ModConfigs {
    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;

    public static Boolean UMU_LIKE;
    public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(MaidIllager.MODID + "config").provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("umuLike", false), "Boolean");
    }

    private static void assignConfigs() {
        UMU_LIKE = CONFIG.getOrDefault("umuLike", false);

        System.out.println("All " + configs.getConfigsList().size() + " have been set properly");
    }
}
