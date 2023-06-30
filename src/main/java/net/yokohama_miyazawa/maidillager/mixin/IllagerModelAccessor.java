package net.yokohama_miyazawa.maidillager.mixin;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.IllagerEntityModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Environment(EnvType.CLIENT)
@Mixin(IllagerEntityModel.class)
public interface IllagerModelAccessor {
    @Accessor("arms")
    public ModelPart getArms();

    @Accessor("rightLeg")
    public ModelPart getRightLeg();

    @Accessor("leftLeg")
    public ModelPart getLeftLeg();

    @Accessor("rightArm")
    public ModelPart getRightArm();

    @Accessor("leftArm")
    public ModelPart getLeftArm();
}
