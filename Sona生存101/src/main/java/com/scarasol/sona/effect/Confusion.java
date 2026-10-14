package com.scarasol.sona.effect;

import com.scarasol.sona.SonaMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class Confusion extends MobEffectBase{

    public Confusion() {
        super(MobEffectCategory.HARMFUL, -256);
        addAttributeModifier(Attributes.FOLLOW_RANGE, ResourceLocation.fromNamespaceAndPath(SonaMod.MODID, "effect_confusion_follow_range"), -1.0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }

}
