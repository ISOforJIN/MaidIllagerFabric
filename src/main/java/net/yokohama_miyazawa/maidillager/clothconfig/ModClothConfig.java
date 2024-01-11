package net.yokohama_miyazawa.maidillager.clothconfig;

import com.mojang.datafixers.util.Pair;
import com.mojang.logging.LogUtils;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.yokohama_miyazawa.maidillager.config.ModConfigs;
import org.apache.logging.log4j.Logger;

import java.util.function.Consumer;

public class ModClothConfig {
    ConfigBuilder builder;

    public ModClothConfig(Screen parent) {
        builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.translatable("title.maidillager.config"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        ConfigCategory generalCategory = builder.getOrCreateCategory(Text.translatable("category.maidillager.general"));

        generalCategory.addEntry(entryBuilder.startBooleanToggle(Text.translatable("option.maidillager.umulike"), umuLike)
                        .setDefaultValue(false)
                        .setSaveConsumer(newValue -> {
                            ModConfigs.configs.up
                        })
                        .build()
        );
    }

    public Screen getScreen() {
        return builder.build();
    }
}
