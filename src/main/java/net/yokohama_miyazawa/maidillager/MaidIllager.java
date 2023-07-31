package net.yokohama_miyazawa.maidillager;

import com.mojang.serialization.Lifecycle;
import net.fabricmc.api.ModInitializer;

import net.minecraft.registry.MutableRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

public class MaidIllager implements ModInitializer {
	public static final String MODID = "maidillager";
    public static final Logger LOGGER = LoggerFactory.getLogger("maidillager");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Maid Illager");

		LOGGER.info("Registries");
		Iterator<RegistryKey<SoundEvent>> iterator = Registries.SOUND_EVENT.getKeys().iterator();
		while(iterator.hasNext()){
			LOGGER.info(iterator.next().toString());
		}
		LOGGER.info("Registries End");

		replaceVoice(SoundEvents.ENTITY_EVOKER_AMBIENT, "entity.evoker.ambient");
		replaceVoice(SoundEvents.ENTITY_EVOKER_CELEBRATE, "entity.evoker.celebrate");
		replaceVoice(SoundEvents.ENTITY_EVOKER_DEATH, "entity.evoker.death");
		replaceVoice(SoundEvents.ENTITY_EVOKER_HURT, "entity.evoker.hurt");
		replaceVoice(SoundEvents.ENTITY_ILLUSIONER_AMBIENT, "entity.illusioner.ambient");
		replaceVoice(SoundEvents.ENTITY_ILLUSIONER_DEATH, "entity.illusioner.death");
		replaceVoice(SoundEvents.ENTITY_ILLUSIONER_HURT, "entity.illusioner.hurt");
		replaceVoice(SoundEvents.ENTITY_PILLAGER_AMBIENT, "entity.pillager.ambient");
		replaceVoice(SoundEvents.ENTITY_PILLAGER_CELEBRATE, "entity.pillager.celebrate");
		replaceVoice(SoundEvents.ENTITY_PILLAGER_DEATH, "entity.pillager.death");
		replaceVoice(SoundEvents.ENTITY_PILLAGER_HURT, "entity.pillager.hurt");
		replaceVoice(SoundEvents.ENTITY_VINDICATOR_AMBIENT, "entity.vindicator.ambient");
		replaceVoice(SoundEvents.ENTITY_VINDICATOR_CELEBRATE, "entity.vindicator.celebrate");
		replaceVoice(SoundEvents.ENTITY_VINDICATOR_DEATH, "entity.vindicator.death");
		replaceVoice(SoundEvents.ENTITY_VINDICATOR_HURT, "entity.vindicator.hurt");
	}

	private void replaceVoice(SoundEvent eventReplaced, String newVoicePath) {
		int rawId = Registries.SOUND_EVENT.getRawId(eventReplaced);
		RegistryKey<SoundEvent> key = RegistryKey.of(Registries.SOUND_EVENT.getKey(), eventReplaced.getId());
		SoundEvent newVoice = SoundEvent.of(new Identifier(MODID + ':' + newVoicePath));
		((MutableRegistry<SoundEvent>)Registries.SOUND_EVENT).set(rawId, key, newVoice, Lifecycle.stable());
	}
}