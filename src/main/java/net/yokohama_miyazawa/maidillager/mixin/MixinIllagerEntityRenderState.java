package net.yokohama_miyazawa.maidillager.mixin;

import net.minecraft.client.render.entity.state.IllagerEntityRenderState;
import net.minecraft.entity.mob.IllagerEntity;
import net.yokohama_miyazawa.maidillager.util.IllagerRendererData;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(IllagerEntityRenderState.class)
public class MixinIllagerEntityRenderState implements IllagerRendererData {
    private IllagerEntity entity = null;

    @Override
    public void setEntity(IllagerEntity entity) {
        this.entity = entity;
    }

    @Override
    public IllagerEntity getEntity() {
        return entity;
    }
}
