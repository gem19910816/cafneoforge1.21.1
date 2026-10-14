package com.scarasol.sona.effect;

import com.scarasol.sona.SonaMod;
import com.scarasol.sona.init.SonaMobEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.WeakHashMap;

public class Insane extends MobEffectBase{

    // Mob.targetSelector 在 1.21.1 mojmap 中为 protected（NeoForge 仅运行时 AT 放开），编译期只能通过反射访问
    private static final Field TARGET_SELECTOR_FIELD;

    static {
        try {
            TARGET_SELECTOR_FIELD = Mob.class.getDeclaredField("targetSelector");
            TARGET_SELECTOR_FIELD.setAccessible(true);
        } catch (NoSuchFieldException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private final Map<Mob, NearestAttackableTargetGoal<LivingEntity>> crazyAttackGoals = new WeakHashMap<>();

    public Insane() {
        super(MobEffectCategory.HARMFUL, -6750208);
        addAttributeModifier(Attributes.ARMOR, ResourceLocation.fromNamespaceAndPath(SonaMod.MODID, "effect_insane_armor"), -0.8, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.fromNamespaceAndPath(SonaMod.MODID, "effect_insane_movement_speed"), 0.3, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(Attributes.ATTACK_DAMAGE, ResourceLocation.fromNamespaceAndPath(SonaMod.MODID, "effect_insane_attack_damage"), 0.3, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }

    private static GoalSelector targetSelector(Mob mob) {
        try {
            return (GoalSelector) TARGET_SELECTOR_FIELD.get(mob);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        if (entity instanceof Mob mob && !crazyAttackGoals.containsKey(mob)){
            GoalSelector targetSelector = targetSelector(mob);
            for (WrappedGoal goal : targetSelector.getAvailableGoals()){
                if (goal.getGoal() instanceof TargetGoal){
                    goal.stop();
                }
            }
            NearestAttackableTargetGoal<LivingEntity> crazyAttackGoal = new NearestAttackableTargetGoal<>(mob, LivingEntity.class, 5, false, false, livingEntity -> !livingEntity.equals(mob)) {
                @Override
                public boolean canUse() {
                    if (!mob.hasEffect(SonaMobEffects.INSANE)){
                        crazyAttackGoals.remove(mob);
                        targetSelector(mob).removeGoal(this);
                        return false;
                    }
                    return super.canUse();
                }
            };
            targetSelector.addGoal(1, crazyAttackGoal);
            mob.setTarget(null);
            crazyAttackGoals.put(mob, crazyAttackGoal);
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 10 == 0;
    }
}
