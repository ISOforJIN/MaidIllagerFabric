package net.yokohama_miyazawa.maidillager.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Environment(EnvType.CLIENT)
@Mixin(SoundEvents.class)
public interface SoundEventsInvoker {
    @Invoker("register")
    public static SoundEvent invokeRegister(Identifier id) {
        throw new AssertionError();
    }
}
