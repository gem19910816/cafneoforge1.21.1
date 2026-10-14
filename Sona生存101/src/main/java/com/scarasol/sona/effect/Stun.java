package com.scarasol.sona.effect;

import com.scarasol.sona.SonaMod;
import com.scarasol.sona.init.SonaSounds;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class Stun extends PhysicalEffect{

    public Stun() {
        super(MobEffectCategory.HARMFUL, -10027060);
        addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.fromNamespaceAndPath(SonaMod.MODID, "effect_stun_movement_speed"), -1.0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(Attributes.ATTACK_DAMAGE, ResourceLocation.fromNamespaceAndPath(SonaMod.MODID, "effect_stun_attack_damage"), -1.0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(Attributes.GRAVITY, ResourceLocation.fromNamespaceAndPath(SonaMod.MODID, "effect_stun_gravity"), 1000.0, AttributeModifier.Operation.ADD_VALUE);
    }

    @Override
    public void onEffectAdded(LivingEntity entity, int amplifier) {
        if (entity instanceof ServerPlayer serverPlayer){
            serverPlayer.playNotifySound(SonaSounds.TINNITUS.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
        }
    }

}
