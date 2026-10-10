package net.mcreator.survivalinstinct.potion;

import net.mcreator.survivalinstinct.procedures.BleedingOnEffectActiveTickProcedure;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class BleedingMobEffect extends MobEffect {
   public BleedingMobEffect() {
      super(MobEffectCategory.HARMFUL, -3407872);
   }

   @Override
   public boolean applyEffectTick(LivingEntity entity, int amplifier) {
      BleedingOnEffectActiveTickProcedure.execute(entity.level(), entity);
      return true;
   }

   @Override
   public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
      return true;
   }
}
