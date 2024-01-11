package net.yokohama_miyazawa.maidillager.config;

/*
 * Copyright (c) 2021 Tutorials By Kaupenjoe
 * Slightly modified by Yokohama-Miyazawa 2023
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import net.minecraft.entity.player.PlayerEntity;
import net.yokohama_miyazawa.maidillager.MaidIllager;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

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
        configs.addKeyValue(new ConfigRow<Boolean>("umuLike", false, "Boolean"));
    }

    private static void assignConfigs() {
        UMU_LIKE = CONFIG.getOrDefault("umuLike", false);

        System.out.println("All " + configs.getConfigsList().size() + " have been set properly");
    }

    public static void setConfigRow(ConfigRow<?> configRow) {
        configs.setKeyValue(configRow);
    }

    public static HashMap<String, ConfigRow> getConfigRows() {
        return configs.configsList;
    }

    public static void saveConfig() throws IOException {
        CONFIG.saveConfig(configs.get(""));
        assignConfigs();
    }
}
