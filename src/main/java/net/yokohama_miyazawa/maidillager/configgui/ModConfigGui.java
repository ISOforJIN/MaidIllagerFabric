package net.yokohama_miyazawa.maidillager.configgui;

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

        ButtonWidget viewConfigButton = ButtonWidget.builder(Text.translatable("maidillager.modconfiggui.viewconfigbtn"), (b) -> {
                    client.setScreen(new ModViewConfig(this).getScreen());
        })
                .dimensions(this.width / 2 - (260 / 2), this.height / 4, 260, 20)
                .build();

        ButtonWidget exitButton = ButtonWidget.builder(Text.translatable("maidillager.modconfiggui.exitbtn"), (b) -> {
            close();
        })
                .dimensions(this.width / 2 - (260 / 2), this.height - 50, 260, 20)
                .build();

        addDrawableChild(viewConfigButton);
        addDrawableChild(exitButton);
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
}
