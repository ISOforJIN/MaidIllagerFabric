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

		//Identifier id = new Identifier("entity.pillager.ambient");
		Identifier soundId = new Identifier(MaidIllager.MODID + ':' + "entity.pillager.ambient");
		//Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(soundId));
		//SoundEvents.ENTITY_PILLAGER_AMBIENT;
		int rawId = Registries.SOUND_EVENT.getRawId(SoundEvents.ENTITY_PILLAGER_AMBIENT);
		((MutableRegistry)Registries.SOUND_EVENT).set(rawId, RegistryKey.of(Registries.SOUND_EVENT.getKey(), SoundEvents.ENTITY_PILLAGER_AMBIENT.getId()), SoundEvent.of(soundId), Lifecycle.stable());
	}
}