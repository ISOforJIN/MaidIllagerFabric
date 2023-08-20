package net.yokohama_miyazawa.maidillager.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(IllagerEntity.class)
public abstract class MixinIllagerEntity extends LivingEntity {
    protected MixinIllagerEntity(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    // 邪悪なメイドの音声ピッチを固定する
    @Override
    public float getSoundPitch() {
        return 1.0f;
    }
}
