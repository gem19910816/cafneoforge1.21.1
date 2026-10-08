package net.mcreator.gore.procedures;

import net.mcreator.gore.init.GoreEditionModMobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class TripofobicAcidProjectileHitsLivingEntityProcedure {
   public static void execute(Entity entity, Entity sourceentity) {
      if (entity != null && sourceentity != null) {
         double acidlevel = 0.0;
         if (entity != sourceentity && (!(entity instanceof LivingEntity _livEnt1) || !_livEnt1.hasEffect(GoreEditionModMobEffects.XASH_ACID))) {
            entity.getPersistentData().putDouble("acid_time_of_potency", entity.getPersistentData().getDouble("acid_time_of_potency") + 20.0);
         }
      }
   }
}
