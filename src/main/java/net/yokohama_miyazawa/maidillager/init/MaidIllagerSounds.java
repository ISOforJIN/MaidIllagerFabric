package net.yokohama_miyazawa.maidillager.init;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.yokohama_miyazawa.maidillager.MaidIllager;

public class MaidIllagerSounds {
    public static final Identifier MAID_PILLAGER_AMBIENT_ID = new Identifier(makeId(MaidIllager.MODID, "entity.pillager.ambient"));
    public static final Identifier MAID_PILLAGER_CELEBRATE_ID = new Identifier(makeId(MaidIllager.MODID, "entity.pillager.celebrate"));
    public static final Identifier MAID_PILLAGER_DEATH_ID = new Identifier(makeId(MaidIllager.MODID, "entity.pillager.death"));
    public static final Identifier MAID_PILLAGER_HURT_ID = new Identifier(makeId(MaidIllager.MODID, "entity.pillager.hurt"));

    public static SoundEvent MAID_PILLAGER_AMBIENT = SoundEvent.of(MAID_PILLAGER_AMBIENT_ID);
    public static SoundEvent MAID_PILLAGER_CELEBRATE = SoundEvent.of(MAID_PILLAGER_CELEBRATE_ID);
    public static SoundEvent MAID_PILLAGER_DEATH = SoundEvent.of(MAID_PILLAGER_DEATH_ID);
    public static SoundEvent MAID_PILLAGER_HURT = SoundEvent.of(MAID_PILLAGER_HURT_ID);

    private static String makeId(String modId, String sound) {
        return modId + ':' + sound;
    }

    public static void onInitialize() {
        Registry.register(Registries.SOUND_EVENT, MaidIllagerSounds.MAID_PILLAGER_AMBIENT_ID, MAID_PILLAGER_AMBIENT);
        Registry.register(Registries.SOUND_EVENT, MaidIllagerSounds.MAID_PILLAGER_CELEBRATE_ID, MAID_PILLAGER_CELEBRATE);
        Registry.register(Registries.SOUND_EVENT, MaidIllagerSounds.MAID_PILLAGER_DEATH_ID, MAID_PILLAGER_DEATH);
        Registry.register(Registries.SOUND_EVENT, MaidIllagerSounds.MAID_PILLAGER_HURT_ID, MAID_PILLAGER_HURT);
    }
}
