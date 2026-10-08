package net.mcreator.gore.potion;

import net.mcreator.gore.procedures.DeformityOnEffectActiveTickProcedure;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class DeformityMobEffect extends MobEffect {
   public DeformityMobEffect() {
      super(MobEffectCategory.HARMFUL, -16186364);
   }

   public boolean applyEffectTick(LivingEntity entity, int amplifier) {
      DeformityOnEffectActiveTickProcedure.execute(entity.level(), entity);
      return true;
   }

   public boolean isDurationEffectTick(int duration, int amplifier) {
      return true;
   }
}
