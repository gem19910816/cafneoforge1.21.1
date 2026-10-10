package net.mcreator.survivalinstinct.procedures;

import net.mcreator.survivalinstinct.init.SurvivalInstinctModMobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class BaseballBatWithNailsLivingEntityIsHitWithToolProcedure {
   public static void execute(Entity entity) {
      if (entity != null) {
         if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
            _entity.addEffect(new MobEffectInstance(SurvivalInstinctModMobEffects.BLEEDING, 320, 0, true, true));
         }
      }
   }
}
