package net.yokohama_miyazawa.maidillager.mixin;

import net.minecraft.client.render.entity.PillagerEntityRenderer;
import net.minecraft.entity.mob.PillagerEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.yokohama_miyazawa.maidillager.MaidIllager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
@Mixin(PillagerEntityRenderer.class)
public class MixinPillagerRenderer {
    private static final Identifier PILLAGER = new Identifier(MaidIllager.MODID + ":textures/entity/maid_pillager.png");

    @Inject(method = "getTexture", at = @At("RETURN"), cancellable = true)
    public void onGetTexture(PillagerEntity entity, CallbackInfoReturnable<Identifier> cir){
        cir.setReturnValue(PILLAGER);
    }
}
