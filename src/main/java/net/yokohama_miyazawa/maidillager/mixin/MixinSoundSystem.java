package net.yokohama_miyazawa.maidillager.mixin;

import net.minecraft.client.sound.*;
import org.spongepowered.asm.mixin.Mixin;
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

//    @Inject(method = "play(Lnet/minecraft/client/sound/SoundInstance;)V", at = @At("HEAD"))
//    protected void onPlay(CallbackInfo ci, @Local LocalRef<SoundInstance> sound) {
//        WeightedSoundSet soundSet = new WeightedSoundSet(SoundEvents.UI_BUTTON_CLICK.value().getId(), (String) null);
//        Sound newSound = new Sound(SoundEvents.UI_BUTTON_CLICK.value().getId().toString(), ConstantFloatProvider.create(1.0f), ConstantFloatProvider.create(1.0f), 1, Sound.RegistrationType.FILE, false, true, 16);
//        sound.set(new SoundInstance() {
//            @Override
//            public Identifier getId() {
//                return newSound.getIdentifier();
//            }
//
//            @Nullable
//            @Override
//            public WeightedSoundSet getSoundSet(SoundManager soundManager) {
//                return soundSet;
//            }
//
//            @Override
//            public Sound getSound() {
//                return newSound;
//            }
//
//            @Override
//            public SoundCategory getCategory() {
//                return SoundCategory.AMBIENT;
//            }
//
//            @Override
//            public boolean isRepeatable() {
//                return false;
//            }
//
//            @Override
//            public boolean isRelative() {
//                return false;
//            }
//
//            @Override
//            public int getRepeatDelay() {
//                return 0;
//            }
//
//            @Override
//            public float getVolume() {
//                return newSound.getVolume().get(Random.create());
//            }
//
//            @Override
//            public float getPitch() {
//                return newSound.getPitch().get(Random.create());
//            }
//
//            @Override
//            public double getX() {
//                return 0;
//            }
//
//            @Override
//            public double getY() {
//                return 0;
//            }
//
//            @Override
//            public double getZ() {
//                return 0;
//            }
//
//            @Override
//            public AttenuationType getAttenuationType() {
//                return null;
//            }
//        });
//    }
}
