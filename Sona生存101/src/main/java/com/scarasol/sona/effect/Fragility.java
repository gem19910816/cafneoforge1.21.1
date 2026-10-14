package com.scarasol.sona.effect;

import com.scarasol.sona.SonaMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.jetbrains.annotations.NotNull;

public class Fragility extends MobEffectBase{

    private static final ResourceLocation FRAGILITY_ID = ResourceLocation.fromNamespaceAndPath(SonaMod.MODID, "effect_fragility_armor");

    public Fragility() {
        super(MobEffectCategory.HARMFUL, -7864320);
    }

    @Override
    public void addAttributeModifiers(@NotNull AttributeMap attributeMap, int amplifier) {
        double level = amplifier + 1;
        double addition = -1 * ((0.15 * level) / (0.15 * level + 1)) * 20;
        AttributeInstance attributeInstance = attributeMap.getInstance(Attributes.ARMOR);
        if (attributeInstance != null){
            attributeInstance.removeModifier(FRAGILITY_ID);
            attributeInstance.addPermanentModifier(new AttributeModifier(FRAGILITY_ID, addition, AttributeModifier.Operation.ADD_VALUE));
        }
    }

    @Override
    public void removeAttributeModifiers(@NotNull AttributeMap attributeMap) {
        super.removeAttributeModifiers(attributeMap);
        AttributeInstance attributeInstance = attributeMap.getInstance(Attributes.ARMOR);
        if (attributeInstance != null){
            attributeInstance.removeModifier(FRAGILITY_ID);
        }
    }

}
