package net.yokohama_miyazawa.maidillager;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.yokohama_miyazawa.maidillager.configgui.ModConfigGui;

public class ModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() { return ModConfigGui::new; }
}
