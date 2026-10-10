package net.mcreator.survivalinstinct.procedures;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelAccessor;

public class BleedingOnEffectActiveTickProcedure {
   public static void execute(LevelAccessor world, Entity entity) {
      if (!(entity instanceof LivingEntity living) || living.level().isClientSide()) return;
      living.removeEffect(MobEffects.REGENERATION);
      double previous = living.getPersistentData().getDouble("da\u00f1o");
      int ticks = Double.isFinite(previous) && previous >= 0 && previous < 30 ? (int) previous + 1 : 30;
      if (ticks >= 30) {
         living.hurt(living.damageSources().generic(), 1.0F);
         ticks = 0;
      }
      living.getPersistentData().putInt("da\u00f1o", ticks);
   }
}
