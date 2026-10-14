package com.scarasol.sona.effect;

import com.google.common.collect.Maps;
import com.scarasol.sona.SonaMod;
import com.scarasol.sona.init.SonaMobEffects;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.*;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class Sliminess extends PhysicalEffect{

    private final Map<Holder<Attribute>, AttributeModifier> frostWithSliminess = Maps.newHashMap();

    public Sliminess() {
        super(MobEffectCategory.HARMFUL, -6684826);
        init();
        addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.fromNamespaceAndPath(SonaMod.MODID, "effect_sliminess_movement_speed"), -0.2, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(Attributes.FLYING_SPEED, ResourceLocation.fromNamespaceAndPath(SonaMod.MODID, "effect_sliminess_flying_speed"), -0.2, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(Attributes.ATTACK_SPEED, ResourceLocation.fromNamespaceAndPath(SonaMod.MODID, "effect_sliminess_attack_speed"), -0.2, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }

    public void init(){
        frostWithSliminess.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(ResourceLocation.fromNamespaceAndPath(SonaMod.MODID, "sliminess_frozen_movement_speed"), -1.0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        frostWithSliminess.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ResourceLocation.fromNamespaceAndPath(SonaMod.MODID, "sliminess_frozen_attack_damage"), -1.0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    }

    public void removeFrozen(AttributeMap attributeMap) {
        for (Map.Entry<Holder<Attribute>, AttributeModifier> entry : this.frostWithSliminess.entrySet()) {
            AttributeInstance attributeInstance = attributeMap.getInstance(entry.getKey());
            if (attributeInstance == null) continue;
            attributeInstance.removeModifier(entry.getValue().id());
        }
    }

    public void addFrozen(AttributeMap attributeMap) {
        for (Map.Entry<Holder<Attribute>, AttributeModifier> entry : this.frostWithSliminess.entrySet()) {
            AttributeInstance attributeInstance = attributeMap.getInstance(entry.getKey());
            if (attributeInstance == null) continue;
            AttributeModifier attributeModifier = entry.getValue();
            if (!attributeInstance.hasModifier(attributeModifier.id()))
                attributeInstance.addPermanentModifier(attributeModifier);
        }
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.isInPowderSnow || entity.wasInPowderSnow || entity.hasEffect(SonaMobEffects.FROST)){
            addFrozen(entity.getAttributes());
        }else {
            removeFrozen(entity.getAttributes());
        }
        return true;
    }

    @Override
    public void removeAttributeModifiers(@NotNull AttributeMap attributeMap) {
        super.removeAttributeModifiers(attributeMap);
        removeFrozen(attributeMap);
    }

    @Override
    public @NotNull String getDescriptionId() {
        return "effect.sona.sliminess";
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

}
