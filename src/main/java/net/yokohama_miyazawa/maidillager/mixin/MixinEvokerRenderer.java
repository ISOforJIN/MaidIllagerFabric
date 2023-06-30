package net.yokohama_miyazawa.maidillager.mixin;

import net.minecraft.client.render.entity.EvokerEntityRenderer;
import net.minecraft.entity.mob.SpellcastingIllagerEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.yokohama_miyazawa.maidillager.MaidIllager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
@Mixin(EvokerEntityRenderer.class)
public class MixinEvokerRenderer<T extends SpellcastingIllagerEntity> {
    private static final Identifier EVOKER_ILLAGER = new Identifier(MaidIllager.MODID + ":textures/entity/maid_evoker.png");

    @Inject(method = "getTexture", at = @At("RETURN"), cancellable = true)
    public void onGetTexture(T entity, CallbackInfoReturnable<Identifier> cir){
        cir.setReturnValue(EVOKER_ILLAGER);
    }
}
