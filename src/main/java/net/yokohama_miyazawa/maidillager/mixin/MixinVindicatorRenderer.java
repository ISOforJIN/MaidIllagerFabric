package net.yokohama_miyazawa.maidillager.mixin;

import net.minecraft.client.render.entity.VindicatorEntityRenderer;
import net.minecraft.entity.mob.VindicatorEntity;
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
@Mixin(VindicatorEntityRenderer.class)
public class MixinVindicatorRenderer {
    private static final String TEXTURE = UMU_LIKE ? ":textures/entity/maid_vindicator_umu.png" : ":textures/entity/maid_vindicator.png";
    private static final Identifier VINDICATOR = new Identifier(MaidIllager.MODID + TEXTURE);

    @Inject(method = "getTexture", at = @At("RETURN"), cancellable = true)
    public void onGetTexture(VindicatorEntity vindicatorEntity, CallbackInfoReturnable<Identifier> cir){
        cir.setReturnValue(VINDICATOR);
    }
}
