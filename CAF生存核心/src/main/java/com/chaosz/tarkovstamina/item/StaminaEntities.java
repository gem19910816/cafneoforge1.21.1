package com.chaosz.tarkovstamina.item;

import com.chaosz.tarkovstamina.TarkovStamina;
import com.chaosz.tarkovstamina.entity.ShitballEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class StaminaEntities {
    public static final DeferredRegister<EntityType<?>> REGISTRY =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, TarkovStamina.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<ShitballEntity>> SHITBALL =
            REGISTRY.register("shitball", () -> EntityType.Builder
                    .<ShitballEntity>of(ShitballEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("shitball"));

    private StaminaEntities() {
    }
}
