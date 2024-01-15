package net.yokohama_miyazawa.maidillager.configgui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class ModConfigGui extends Screen {
    Screen parent;

    public ModConfigGui(Screen parent) {
        super(Text.translatable("title.maidillager.config"));

        this.parent = parent;
    }

    @Override
    protected void init() {
        super.init();

        ButtonWidget appearanceConfigButton = new ConfigButton(this.width / 2 - (200 / 2), this.height / 4, 200, 20, Config.VIEW, this);

        ButtonWidget closeButton = ButtonWidget.builder(Text.translatable("maidillager.modconfiggui.closebtn"), (b) -> {
            close();
        })
                .dimensions(this.width / 2 - (200 / 2), this.height - 50, 200, 20)
                .build();

        addDrawableChild(appearanceConfigButton);
        addDrawableChild(closeButton);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackgroundTexture(context);

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public void close() {
        client.setScreen(parent);
    }

    enum Config {
        VIEW
    }

    class ConfigButton extends ButtonWidget {
        Config config;
        Screen currentScreen;

        protected ConfigButton(int x, int y, int width, int height, Config config, Screen currentScreen) {
            super(x, y, width, height, null, null, DEFAULT_NARRATION_SUPPLIER);

            this.config = config;
            this.currentScreen = currentScreen;

            Text message;
            switch (this.config) {
                case VIEW:
                    message = Text.translatable("maidillager.modconfiggui.appearanceconfigbtn");
                    break;
                default:
                    message = Text.literal("config unknown");
                    break;
            }
            this.setMessage(message);
        }

        @Override
        public void onPress() {
            switch (this.config) {
                case VIEW:
                    MinecraftClient.getInstance().setScreen(new ModAppearanceConfig(this.currentScreen).getScreen());
                    break;
            }
        }
    }
}
