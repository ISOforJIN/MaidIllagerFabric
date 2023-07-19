package net.yokohama_miyazawa.maidillager.init;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.yokohama_miyazawa.maidillager.MaidIllager;

public class MaidIllagerSounds {
    public static SoundEvent MAID_PILLAGER_AMBIENT;
    public static SoundEvent MAID_PILLAGER_CELEBRATE;
    public static SoundEvent MAID_PILLAGER_DEATH;
    public static SoundEvent MAID_PILLAGER_HURT;

    public static void onInitialize() {
        MAID_PILLAGER_AMBIENT = register("entity.pillager.ambient");
        MAID_PILLAGER_CELEBRATE = register("entity.pillager.celebrate");
        MAID_PILLAGER_DEATH = register("entity.pillager.death");
        MAID_PILLAGER_HURT = register("entity.pillager.hurt");
    }

    private static SoundEvent register(String soundId) {
        Identifier id = new Identifier(makeId(MaidIllager.MODID, soundId));
        SoundEvent soundEvent = SoundEvent.of(id);
        return Registry.register(Registries.SOUND_EVENT, id, soundEvent);
    }

    private static String makeId(String modId, String sound) {
        return modId + ':' + sound;
    }
}
