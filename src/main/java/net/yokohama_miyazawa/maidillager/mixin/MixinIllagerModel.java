package net.yokohama_miyazawa.maidillager.mixin;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.IllagerEntityModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.entity.mob.IllusionerEntity;
import net.minecraft.entity.mob.PillagerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(IllagerEntityModel.class)
public class MixinIllagerModel {

    private ModelPart hire;
    private ModelPart sideburns;
    private ModelPart chignonB;
    private ModelPart tail;
    private ModelPart Skirt;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(ModelPart root, CallbackInfo cir) {
        ModelPart head = ((IllagerEntityModel)(Object)this).getHead();
        this.hire = head.getChild("hire");
        this.sideburns = head.getChild("sideburns");
        this.chignonB = head.getChild("chignonB");
        this.tail = head.getChild("tail");
        this.Skirt = root.getChild("Skirt");

        this.hire.visible = false;
        this.sideburns.visible = false;
        this.chignonB.visible = false;
        this.tail.visible = false;

        ModelPart hat = ((IllagerEntityModel)(Object)this).getHat();
        hat.visible = false;

        ModelPart arms = ((IllagerModelAccessor) (Object)this).getArms();
        arms.visible = false;
    }

    @Inject(method = "getTexturedModelData", at = @At("HEAD"), cancellable = true)
    private static void onCreateBodyLayer(CallbackInfoReturnable<TexturedModelData> cir) {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        // オリジナルとの身長差分、体全体を下にずらす
        float heightOffset = 8.0F;

        ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F), ModelTransform.pivot(0.0F, 0.0F+heightOffset, 0.0F));
        head.addChild("hire", ModelPartBuilder.create().uv(24, 0).cuboid(-4.0F, 0.0F, 1.0F, 8.0F, 4.0F, 3.0F), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        head.addChild("sideburns", ModelPartBuilder.create().uv(24, 0).cuboid(-4.0F, 0.0F, -4.0F, 8.0F, 1.0F, 1.0F), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        head.addChild("chignonB", ModelPartBuilder.create().uv(52, 10).cuboid(-2.0F, -7.2F, 4.0F, 4.0F, 4.0F, 2.0F), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        head.addChild("tail", ModelPartBuilder.create().uv(46, 20).cuboid(-1.5F, -7.8F, 4.0F, 3.0F, 9.0F, 3.0F), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(32, 8).cuboid(-3.0F, 0.0F, -2.0F, 6.0F, 7.0F, 4.0F), ModelTransform.pivot(0.0F, 0.0F+heightOffset, 0.0F));
        ModelPartData rightArm = modelPartData.addChild("right_arm", ModelPartBuilder.create().uv(48, 0).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F), ModelTransform.pivot(-4.0F, 1.0F+heightOffset, 0.0F));
        ModelPartData leftArm = modelPartData.addChild("left_arm", ModelPartBuilder.create().uv(56, 0).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F), ModelTransform.pivot(4.0F, 1.0F+heightOffset, 0.0F));
        ModelPartData rightLeg = modelPartData.addChild("right_leg", ModelPartBuilder.create().uv(32, 19).cuboid(-1.5F, 0.0F, -2.0F, 3.0F, 9.0F, 4.0F), ModelTransform.pivot(-1.5F, 7.0F+heightOffset, 0.0F));
        ModelPartData leftLeg = modelPartData.addChild("left_leg", ModelPartBuilder.create().uv(32, 19).mirrored().cuboid(-1.5F, 0.0F, -2.0F, 3.0F, 9.0F, 4.0F).mirrored(false), ModelTransform.pivot(1.5F, 7.0F+heightOffset, 0.0F));
        ModelPartData Skirt = modelPartData.addChild("Skirt", ModelPartBuilder.create().uv(0, 16).cuboid(-4.0F, 0.0F, -4.0F, 8.0F, 8.0F, 8.0F), ModelTransform.pivot(0.0F, 5.0F+heightOffset, 0.0F));

        // ダミーの帽子と腕
        head.addChild("hat", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        modelPartData.addChild("arms", ModelPartBuilder.create().uv(32, 8).cuboid(-3.0F, 0.0F, -2.0F, 6.0F, 7.0F, 4.0F), ModelTransform.pivot(0.0F, 0.0F+heightOffset, 0.0F));

        cir.setReturnValue(TexturedModelData.of(modelData, 64, 32));
    }

    @Inject(method = "setAngles", at = @At("HEAD"), cancellable = true)
    private <T extends IllagerEntity> void onSetAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo cir) {
        if (entity instanceof IllusionerEntity) {
            this.Skirt.visible = false;
            ModelPart hat = ((IllagerEntityModel)(Object)this).getHat();
            hat.visible = false;
        }
        if (entity instanceof PillagerEntity || entity instanceof IllusionerEntity) {
            this.chignonB.visible = true;
            this.tail.visible = true;
        } else {
            this.sideburns.visible = true;
            this.hire.visible = true;
        }

        ModelPart head = ((IllagerEntityModel)(Object)this).getHead();
        head.yaw = netHeadYaw * ((float)Math.PI / 180F);
        head.pitch = headPitch * ((float)Math.PI / 180F);

        ModelPart rightArm = ((IllagerModelAccessor) (Object)this).getRightArm();
        ModelPart leftArm  = ((IllagerModelAccessor) (Object)this).getLeftArm();
        ModelPart rightLeg = ((IllagerModelAccessor) (Object)this).getRightLeg();
        ModelPart leftLeg  = ((IllagerModelAccessor) (Object)this).getLeftLeg();
        // TODO: ラヴェジャーに乗っている時に下半身がめり込むのを解消する
        if (((IllagerEntityModel)(Object)this).riding) {  // ラヴェジャーに乗っている時
            this.setAngle(rightArm, -0.6283185F, 0.0F, 0.0F);
            this.setAngle(leftArm, -0.6283185F, 0.0F, 0.0F);
            this.setAngle(rightLeg, -1.256637F, 0.3141593F, 0.0F);
            this.setAngle(leftLeg, -1.256637F, -0.3141593F, 0.0F);
        } else {
            this.setAngle(rightArm, (float)Math.cos(limbSwing * 0.6662 + Math.PI) * 2.0F * limbSwingAmount * 0.5F, 0.0F, (float)Math.PI / 5.0F);
            this.setAngle(leftArm, (float)Math.cos(limbSwing * 0.6662) * 2.0F * limbSwingAmount * 0.5F, 0.0F, -(float)Math.PI / 5.0F);
            this.setAngle(rightLeg, (float)Math.cos(limbSwing * 0.6662) * 1.4F * limbSwingAmount * 0.5F, 0.0F, 0.0F);
            this.setAngle(leftLeg, (float)Math.cos(limbSwing * 0.6662 + Math.PI) * 1.4F * limbSwingAmount * 0.5F, 0.0F, 0.0F);
        }

        IllagerEntity.State state = entity.getState();
        switch(state){
            case SPELLCASTING -> {
                this.setAngle(rightArm, 0.0F, 0.0F, (float)Math.PI * (2.0F / 3.0F));
                this.setAngle(leftArm, 0.0F, 0.0F, -(float)Math.PI * (2.0F / 3.0F));
            }
            case BOW_AND_ARROW, CROSSBOW_HOLD, CROSSBOW_CHARGE -> {
                this.setAngle(rightArm, -(float)Math.PI / 2.0F + head.pitch, -(float)Math.PI / 15.0F, 0.0F);
                this.setAngle(leftArm, -(float)Math.PI / 2.0F + head.pitch, (float)Math.PI / 15.0F, 0.0F);
            }
            case CELEBRATING -> {
                this.setAngle(rightArm, 0.0F, 0.0F, (float)Math.PI * (5.0F / 6.0F));
                this.setAngle(leftArm, 0.0F, 0.0F, -(float)Math.PI * (5.0F / 6.0F));
            }
        }

        cir.cancel();
    }

    private void setAngle(ModelPart part, float xAngle, float yAngle, float zAngle) {
        part.pitch = xAngle;
        part.yaw = yAngle;
        part.roll = zAngle;
    }
}
