package net.yokohama_miyazawa.maidillager.mixin;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.IllagerEntityModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.entity.mob.EvokerEntity;
import net.minecraft.entity.mob.IllusionerEntity;
import net.minecraft.entity.mob.PillagerEntity;
import net.minecraft.entity.mob.VindicatorEntity;
import net.minecraft.util.Arm;
import org.joml.Quaternionf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(IllagerEntityModel.class)
public class MixinIllagerModel {

    private ModelPart chignonB;
    private ModelPart tail;
    private ModelPart hair;
    private ModelPart forelock;
    private ModelPart blinkEyeR;
    private ModelPart blinkEyeL;
    private ModelPart hurtEyeR;
    private ModelPart hurtEyeL;
    private ModelPart mouth;
    private ModelPart Skirt;

    // オリジナルとの身長差分、体全体を下にずらす
    private static final float heightOffset = 8.0F;
    // ラヴェジャー等に乗っている時は、少しだけ下半身がめり込む程度に位置を補正する
    private static final float ridingOffset = heightOffset - 2.0F;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(ModelPart root, CallbackInfo cir) {
        ModelPart head = ((IllagerEntityModel)(Object)this).getHead();
        this.chignonB = head.getChild("chignonB");
        this.tail = head.getChild("tail");
        this.hair = head.getChild("hair");
        this.forelock = head.getChild("forelock");
        this.blinkEyeR = head.getChild("blinkEyeR");
        this.blinkEyeL = head.getChild("blinkEyeL");
        this.hurtEyeR = head.getChild("hurtEyeR");
        this.hurtEyeL = head.getChild("hurtEyeL");
        this.mouth = head.getChild("mouth");
        this.Skirt = root.getChild("Skirt");

        this.chignonB.visible = false;
        this.tail.visible = false;
        this.forelock.visible = false;
        this.blinkEyeR.visible = false;
        this.blinkEyeL.visible = false;
        this.hurtEyeR.visible = false;
        this.hurtEyeL.visible = false;
        this.mouth.visible = false;

        ModelPart hat = ((IllagerEntityModel)(Object)this).getHat();
        hat.visible = false;

        ModelPart arms = ((IllagerModelAccessor) (Object)this).getArms();
        arms.visible = false;
    }

    @Inject(method = "getTexturedModelData", at = @At("HEAD"), cancellable = true)
    private static void onCreateBodyLayer(CallbackInfoReturnable<TexturedModelData> cir) {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F), ModelTransform.pivot(0.0F, 0.0F+heightOffset, 0.0F));
        head.addChild("chignonB", ModelPartBuilder.create().uv(52, 10).cuboid(-2.0F, -7.2F, 4.0F, 4.0F, 4.0F, 2.0F), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        head.addChild("tail", ModelPartBuilder.create().uv(46, 20).cuboid(-1.5F, -7.8F, 4.0F, 3.0F, 9.0F, 3.0F), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        head.addChild("hair", ModelPartBuilder.create().uv(0, 32).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 12.0F, 8.0F, new Dilation(0.25F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        head.addChild("forelock", ModelPartBuilder.create().uv(44, 33).cuboid(-4.0F, -8.0F, -4.5F, 8.0F, 4.0F, 2.0F, new Dilation(0.3F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        head.addChild("blinkEyeR", ModelPartBuilder.create().uv(4, 0).cuboid(-3.0F, -4.0F, -4.01F, 2.0F, 3.0F, 0.0F), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        head.addChild("blinkEyeL", ModelPartBuilder.create().uv(4, 0).mirrored().cuboid(1.0F, -4.0F, -4.01F, 2.0F, 3.0F, 0.0F).mirrored(false), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        head.addChild("hurtEyeR", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -4.0F, -4.01F, 2.0F, 3.0F, 0.0F), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        head.addChild("hurtEyeL", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(1.0F, -4.0F, -4.01F, 2.0F, 3.0F, 0.0F).mirrored(false), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        head.addChild("mouth", ModelPartBuilder.create().uv(0, 6).cuboid(-1.0F, -1.0F, -4.01F, 2.0F, 1.0F, 0.0F), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(32, 8).cuboid(-3.0F, 0.0F, -2.0F, 6.0F, 7.0F, 4.0F), ModelTransform.pivot(0.0F, 0.0F+heightOffset, 0.0F));
        ModelPartData rightArm = modelPartData.addChild("right_arm", ModelPartBuilder.create().uv(48, 0).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F), ModelTransform.pivot(-4.0F, 1.0F+heightOffset, 0.0F));
        ModelPartData leftArm = modelPartData.addChild("left_arm", ModelPartBuilder.create().uv(56, 0).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F), ModelTransform.pivot(4.0F, 1.0F+heightOffset, 0.0F));
        ModelPartData rightLeg = modelPartData.addChild("right_leg", ModelPartBuilder.create().uv(32, 19).cuboid(-1.5F, 0.0F, -2.0F, 3.0F, 9.0F, 4.0F), ModelTransform.pivot(-1.5F, 7.0F+heightOffset, 0.0F));
        ModelPartData leftLeg = modelPartData.addChild("left_leg", ModelPartBuilder.create().uv(32, 19).mirrored().cuboid(-1.5F, 0.0F, -2.0F, 3.0F, 9.0F, 4.0F).mirrored(false), ModelTransform.pivot(1.5F, 7.0F+heightOffset, 0.0F));
        ModelPartData Skirt = modelPartData.addChild("Skirt", ModelPartBuilder.create().uv(0, 16).cuboid(-4.0F, 0.0F, -4.0F, 8.0F, 8.0F, 8.0F), ModelTransform.pivot(0.0F, 5.0F+heightOffset, 0.0F));

        // ダミーの帽子と腕
        head.addChild("hat", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        modelPartData.addChild("arms", ModelPartBuilder.create().uv(32, 8).cuboid(-3.0F, 0.0F, -2.0F, 6.0F, 7.0F, 4.0F), ModelTransform.pivot(0.0F, 0.0F+heightOffset, 0.0F));

        cir.setReturnValue(TexturedModelData.of(modelData, 64, 64));
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
        }
        if (entity instanceof EvokerEntity || entity instanceof PillagerEntity) {
            this.forelock.visible = true;
        }

        ModelPart root = ((IllagerEntityModel)(Object)this).getPart();
        ModelPart head = ((IllagerEntityModel)(Object)this).getHead();
        head.yaw = netHeadYaw * ((float)Math.PI / 180F);
        head.pitch = headPitch * ((float)Math.PI / 180F);

        ModelPart rightArm = ((IllagerModelAccessor) (Object)this).getRightArm();
        ModelPart leftArm  = ((IllagerModelAccessor) (Object)this).getLeftArm();
        ModelPart rightLeg = ((IllagerModelAccessor) (Object)this).getRightLeg();
        ModelPart leftLeg  = ((IllagerModelAccessor) (Object)this).getLeftLeg();

        if (((IllagerEntityModel)(Object)this).riding) {  // ラヴェジャーに乗っている時
            root.pivotY = -ridingOffset;
            this.setAngle(rightArm, -0.6283185F, 0.0F, 0.0F);
            this.setAngle(leftArm, -0.6283185F, 0.0F, 0.0F);
            this.setAngle(rightLeg, -1.256637F, 0.3141593F, 0.0F);
            this.setAngle(leftLeg, -1.256637F, -0.3141593F, 0.0F);
        } else {
            root.pivotY = 0.0F;
            float xAngle = (float)Math.cos(limbSwing * 0.6662) * 2.0F * limbSwingAmount * 0.5F;
            float defaultZAngle = (float)Math.PI / 5.0F;
            float zSwing = ((float)Math.PI / 40.0F) * (float)Math.sin(3.0F * ageInTicks * ((float)Math.PI / 180F));
            float zAngle = defaultZAngle + zSwing;
            this.setAngle(rightArm, -xAngle, 0.0F, zAngle);
            this.setAngle(leftArm, xAngle, 0.0F, -zAngle);
            this.setAngle(rightLeg, (float)Math.cos(limbSwing * 0.6662) * 1.4F * limbSwingAmount * 0.5F, 0.0F, 0.0F);
            this.setAngle(leftLeg, (float)Math.cos(limbSwing * 0.6662 + Math.PI) * 1.4F * limbSwingAmount * 0.5F, 0.0F, 0.0F);
        }

        IllagerEntity.State state = entity.getState();
        switch(state){
            case SPELLCASTING -> {
                this.setAngle(rightArm, 0.0F, 0.0F, (float)Math.PI * (2.0F / 3.0F));
                this.setAngle(leftArm, 0.0F, 0.0F, -(float)Math.PI * (2.0F / 3.0F));
            }
            case BOW_AND_ARROW -> {
                this.setAngle(rightArm, -(float)Math.PI / 2.0F + head.pitch, -(float)Math.PI / 15.0F, 0.0F);
                this.setAngle(leftArm, -(float)Math.PI / 2.0F + head.pitch, (float)Math.PI / 15.0F, 0.0F);
            }
            case CROSSBOW_CHARGE -> {
                float xAngle_right = (entity.getMainArm() == Arm.LEFT) ? -(float)Math.PI / 2.5F : -(float)Math.PI / 3.0F;
                float xAngle_left  = (entity.getMainArm() == Arm.LEFT) ? -(float)Math.PI / 3.0F : -(float)Math.PI / 2.5F;
                this.setAngle(rightArm, xAngle_right, -(float)Math.PI / 5.0F, 0.0F);
                this.setAngle(leftArm, xAngle_left, (float)Math.PI / 5.0F, 0.0F);
            }
            case CROSSBOW_HOLD -> {
                float yAngle_right = (entity.getMainArm() == Arm.LEFT) ? -(float)Math.PI / 4.5F : -(float)Math.PI / 10.0F;
                float yAngle_left  = (entity.getMainArm() == Arm.LEFT) ? (float)Math.PI / 10.0F : (float)Math.PI / 4.5F;
                this.setAngle(rightArm, -(float)Math.PI / 2.0F + head.pitch, yAngle_right, 0.0F);
                this.setAngle(leftArm, -(float)Math.PI / 2.0F + head.pitch, yAngle_left, 0.0F);
            }
            case CELEBRATING -> {
                this.setAngle(rightArm, 0.0F, 0.0F, (float)Math.PI * (5.0F / 6.0F));
                this.setAngle(leftArm, 0.0F, 0.0F, -(float)Math.PI * (5.0F / 6.0F));
            }
        }

        if (!(entity instanceof IllusionerEntity)) {  // イリュージョナー以外の表情変化
            if (this.isHurt(entity) || entity.isDead()) {  // ダメージを受けたもしくは死ぬ時
                this.blinkEyeR.visible = false;
                this.blinkEyeL.visible = false;
                if (entity instanceof PillagerEntity && !entity.isDead()) {  // ピリジャーがダメージを受けた時は利き腕とは逆の目だけ閉じる
                    ModelPart closeEye = entity.getMainArm() == Arm.LEFT ? this.hurtEyeR : this.hurtEyeL;
                    closeEye.visible = true;
                } else {
                    this.hurtEyeR.visible = true;
                    this.hurtEyeL.visible = true;
                }
                if (entity instanceof PillagerEntity || entity instanceof VindicatorEntity || entity.getId() % 2 == 0) {
                    this.mouth.visible = true;
                }
            } else if (this.shouldBlink(entity, ageInTicks)) {  // 瞬き
                this.hurtEyeR.visible = false;
                this.hurtEyeL.visible = false;
                this.mouth.visible = false;
                this.blinkEyeR.visible = true;
                this.blinkEyeL.visible = true;
            } else {
                this.hurtEyeR.visible = false;
                this.hurtEyeL.visible = false;
                this.mouth.visible = false;
                this.blinkEyeR.visible = false;
                this.blinkEyeL.visible = false;
            }
        }

        cir.cancel();
    }

    private <T extends IllagerEntity> boolean isHurt(T entity) {
        return entity.hurtTime > 0;
    }

    private <T extends IllagerEntity> boolean shouldBlink(T entity, float tick) {
        int entityId = entity.getId();
        float blinkTheta = tick +(float)entityId;
        return 0 > (float) Math.sin(blinkTheta * 0.05F) + (float) Math.sin(blinkTheta * 0.13F) + (float) Math.sin(blinkTheta * 0.7F) + 2.55F;
    }

    private void setAngle(ModelPart part, float xAngle, float yAngle, float zAngle) {
        part.pitch = xAngle;
        part.yaw = yAngle;
        part.roll = zAngle;
    }

    // 腕と武器の位置関係を調整
    @Inject(method = "setArmAngle", at = @At("HEAD"), cancellable = true)
    private void onSetArmAngle(net.minecraft.util.Arm arm, net.minecraft.client.util.math.MatrixStack matrices, CallbackInfo cir) {
        ModelPart attackingArm = (arm == net.minecraft.util.Arm.LEFT)
                ? ((IllagerModelAccessor) (Object)this).getLeftArm()
                : ((IllagerModelAccessor) (Object)this).getRightArm();

        // 武器と腕の位置関係を微調整
        matrices.translate(
                ((arm == net.minecraft.util.Arm.LEFT) ? 3.0F : -3.0F) / 16.0F,
                ((((IllagerEntityModel)(Object)this).riding) ? 7.5F - ridingOffset : 7.5F) / 16.0F,
                0.75F / 16.0F);
        // 中心点を設定しつつ、腕と武器の角度を同期
        matrices.multiply(
                (new Quaternionf()).rotateZYX(attackingArm.roll, attackingArm.yaw, attackingArm.pitch),
                attackingArm.pivotX / 320.0F,
                attackingArm.pivotY / 56.0F,
                0.0F);

        cir.cancel();
    }
}
