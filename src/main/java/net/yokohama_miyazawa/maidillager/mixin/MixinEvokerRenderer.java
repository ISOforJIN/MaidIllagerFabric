package net.yokohama_miyazawa.maidillager.mixin;

import net.minecraft.client.render.entity.EvokerEntityRenderer;
import net.minecraft.client.render.entity.state.EvokerEntityRenderState;
import net.minecraft.entity.mob.SpellcastingIllagerEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.yokohama_miyazawa.maidillager.MaidIllager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.util.Identifier;

import static net.yokohama_miyazawa.maidillager.config.ModConfigs.UMU_LIKE;

@Environment(EnvType.CLIENT)
@Mixin(EvokerEntityRenderer.class)
public class MixinEvokerRenderer<T extends SpellcastingIllagerEntity> {
    private static final String TEXTURE() {return UMU_LIKE ? "textures/entity/maid_evoker_umu.png" : "textures/entity/maid_evoker.png";};
    private static final Identifier EVOKER_ILLAGER() {return Identifier.of(MaidIllager.MODID, TEXTURE());}

    @Inject(method = "getTexture(Lnet/minecraft/client/render/entity/state/EvokerEntityRenderState;)Lnet/minecraft/util/Identifier;", at = @At("RETURN"), cancellable = true)
    public void onGetTexture(EvokerEntityRenderState evokerEntityRenderState, CallbackInfoReturnable<Identifier> cir) { cir.setReturnValue(EVOKER_ILLAGER()); }
}
