package me.xjqsh.lesraisinsarmor.init;

import me.xjqsh.lesraisinsarmor.LesRaisinsArmor;
import me.xjqsh.lesraisinsarmor.effect.SuitEffect;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModEffects {
    public static final DeferredRegister<MobEffect> REGISTER = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, LesRaisinsArmor.MOD_ID);

    public static final DeferredHolder<MobEffect, MobEffect> TOUGH = REGISTER.register("tough", () ->
            new SuitEffect(MobEffectCategory.BENEFICIAL, 0x000000, "attacker")
                    .addAttributeModifier(Attributes.ARMOR_TOUGHNESS,
                            ResourceLocation.fromNamespaceAndPath(LesRaisinsArmor.MOD_ID, "tough_armor_toughness"),
                            15, AttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE,
                            ResourceLocation.fromNamespaceAndPath(LesRaisinsArmor.MOD_ID, "tough_kb_resist"),
                            0.25d, AttributeModifier.Operation.ADD_VALUE)
    );

    public static final DeferredHolder<MobEffect, MobEffect> LIGHT_LEG = REGISTER.register("light_leg", () ->
            new SuitEffect(MobEffectCategory.BENEFICIAL, 0x000000, "scout")
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED,
                            ResourceLocation.fromNamespaceAndPath(LesRaisinsArmor.MOD_ID, "light_leg_speed"),
                            0.025, AttributeModifier.Operation.ADD_VALUE)
    );

    public static final DeferredHolder<MobEffect, MobEffect> HEAVY_ARMOR = REGISTER.register("heavy_armor", () ->
            new SuitEffect(MobEffectCategory.BENEFICIAL, 0x000000, "defender")
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED,
                            ResourceLocation.fromNamespaceAndPath(LesRaisinsArmor.MOD_ID, "heavy_armor_speed"),
                            -0.02, AttributeModifier.Operation.ADD_VALUE)
    );

    public static final DeferredHolder<MobEffect, MobEffect> RESCUE = REGISTER.register("rescue", () ->
            new SuitEffect(MobEffectCategory.BENEFICIAL, 0x000000, "medical")
    );

    public static final DeferredHolder<MobEffect, MobEffect> RESCUE_COOLDOWN = REGISTER.register("rescue_cooldown", () ->
            new MobEffect(MobEffectCategory.HARMFUL, 0x000000) {}
    );
}
