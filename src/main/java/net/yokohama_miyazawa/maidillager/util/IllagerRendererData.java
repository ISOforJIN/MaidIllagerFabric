package net.yokohama_miyazawa.maidillager.util;

import net.minecraft.entity.mob.IllagerEntity;

public interface IllagerRendererData {
    void setEntity(IllagerEntity entity);
    IllagerEntity getEntity();
}
