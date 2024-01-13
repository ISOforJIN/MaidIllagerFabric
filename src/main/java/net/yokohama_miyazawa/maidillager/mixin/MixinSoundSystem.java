package net.yokohama_miyazawa.maidillager.mixin;

import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.sound.SoundSystem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(SoundSystem.class)
public abstract class MixinSoundSystem {
    private boolean isIllager(String Id) {
        List<String> isIllagerList = List.of(new String[]{
                "entity.illusioner",
                "entity.pillager",
                "entity.evoker",
                "entity.vindicator"
        });

        for (String listId : isIllagerList) {
            if (Id.startsWith(listId)) {
                return true;
            }
        }

        return false;
    }

    @Inject(method = "getAdjustedPitch", at = @At("RETURN"), cancellable = true)
    protected void onGetAdjustedPitch(SoundInstance sound, CallbackInfoReturnable<Float> info) {
        if (isIllager(sound.getId().getPath())) {
            info.setReturnValue(1.0f);
        }
    }
}
