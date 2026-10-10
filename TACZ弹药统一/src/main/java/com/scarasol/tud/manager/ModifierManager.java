package com.scarasol.tud.manager;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.Map;

/**
 * @author Scarasol
 */
public class ModifierManager {
    public static double getModifierById(Entity entity, Map<String, Double> modifierMap) {
        ResourceLocation entityId = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType());
        return modifierMap.getOrDefault(entityId.toString(), -1.0);
    }

    public static double getModifierByTag(Entity entity, Map<String, Double> modifierMap) {
        return modifierMap.entrySet().stream()
                .filter(e -> {
                    ResourceLocation rl = ResourceLocation.tryParse(e.getKey());
                    return rl != null && entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, rl));
                })
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(-1.0);
    }

    public static double getModifierByMobType(Entity entity, Map<String, Double> modifierMap) {
        if (entity instanceof Mob mob) {
            var type = mob.getType();
            String group = type.is(net.minecraft.tags.EntityTypeTags.UNDEAD) ? "undead"
                    : type.is(net.minecraft.tags.EntityTypeTags.ARTHROPOD) ? "arthropod"
                    : type.is(net.minecraft.tags.EntityTypeTags.ILLAGER) ? "illager"
                    : type.is(net.minecraft.tags.EntityTypeTags.AQUATIC) ? "water" : "undefined";
            return modifierMap.getOrDefault(group, -1.0);
        }
        return -1;
    }
}
