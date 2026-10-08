package net.mcreator.gore.procedures;

import net.mcreator.gore.init.GoreEditionModMobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class AcidMobplayerCollidesBlockProcedure {
   public static void execute(Entity entity) {
      if (entity != null) {
         if (entity.getPersistentData().getDouble("acid_time_of_potency") < 201.0) {
            entity.getPersistentData().putDouble("acid_time_of_potency", entity.getPersistentData().getDouble("acid_time_of_potency") + 1.0);
         }

         entity.getPersistentData().putBoolean("recently_exposed_to_acid", true);
         if (entity.getPersistentData().getDouble("acid_time_of_potency") <= 20.0 && entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
            _entity.addEffect(new MobEffectInstance(GoreEditionModMobEffects.XASH_ACID, 100, 0));
         }

         if (entity.getPersistentData().getDouble("acid_time_of_potency") <= 40.0
            && entity.getPersistentData().getDouble("acid_time_of_potency") > 20.0
            && entity instanceof LivingEntity _entity
            && !_entity.level().isClientSide()) {
            _entity.addEffect(new MobEffectInstance(GoreEditionModMobEffects.XASH_ACID, 150, 1));
         }

         if (entity.getPersistentData().getDouble("acid_time_of_potency") <= 60.0
            && entity.getPersistentData().getDouble("acid_time_of_potency") > 40.0
            && entity instanceof LivingEntity _entity
            && !_entity.level().isClientSide()) {
            _entity.addEffect(new MobEffectInstance(GoreEditionModMobEffects.XASH_ACID, 200, 2));
         }

         if (entity.getPersistentData().getDouble("acid_time_of_potency") <= 80.0
            && entity.getPersistentData().getDouble("acid_time_of_potency") > 60.0
            && entity instanceof LivingEntity _entity
            && !_entity.level().isClientSide()) {
            _entity.addEffect(new MobEffectInstance(GoreEditionModMobEffects.XASH_ACID, 250, 3));
         }

         if (entity.getPersistentData().getDouble("acid_time_of_potency") <= 100.0
            && entity.getPersistentData().getDouble("acid_time_of_potency") > 80.0
            && entity instanceof LivingEntity _entity
            && !_entity.level().isClientSide()) {
            _entity.addEffect(new MobEffectInstance(GoreEditionModMobEffects.XASH_ACID, 300, 4));
         }

         if (entity.getPersistentData().getDouble("acid_time_of_potency") <= 200.0
            && entity.getPersistentData().getDouble("acid_time_of_potency") > 100.0
            && entity instanceof LivingEntity _entity
            && !_entity.level().isClientSide()) {
            _entity.addEffect(new MobEffectInstance(GoreEditionModMobEffects.XASH_ACID, 400, 6));
         }
      }
   }
}
