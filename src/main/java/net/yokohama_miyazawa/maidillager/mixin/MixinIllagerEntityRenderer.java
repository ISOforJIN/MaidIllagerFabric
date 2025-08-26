package net.yokohama_miyazawa.maidillager.mixin;

import net.minecraft.client.render.entity.IllagerEntityRenderer;
import net.minecraft.client.render.entity.state.IllagerEntityRenderState;
import net.minecraft.entity.mob.IllagerEntity;
import net.yokohama_miyazawa.maidillager.util.IllagerRendererData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(IllagerEntityRenderer.class)
public class MixinIllagerEntityRenderer {
    @Inject(method = "updateRenderState(Lnet/minecraft/entity/mob/IllagerEntity;Lnet/minecraft/client/render/entity/state/IllagerEntityRenderState;F)V", at = @At("HEAD"))
    public <T extends IllagerEntity, S extends IllagerEntityRenderState> void injectUpdateRenderState(T entity, S state, float tickDelta, CallbackInfo info) {
        ((IllagerRendererData) state).setEntity(entity);
    }
}
