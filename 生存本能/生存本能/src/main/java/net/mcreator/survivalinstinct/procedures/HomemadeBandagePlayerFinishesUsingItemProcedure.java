package net.mcreator.survivalinstinct.procedures;

import net.mcreator.survivalinstinct.init.SurvivalInstinctModMobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class HomemadeBandagePlayerFinishesUsingItemProcedure {
   public static void execute(Entity entity) {
      if (entity != null) {
         if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
            _entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 400, 1, true, false));
         }

         if (entity instanceof LivingEntity _entity) {
            _entity.removeEffect(SurvivalInstinctModMobEffects.BLEEDING);
         }
      }
   }
}
