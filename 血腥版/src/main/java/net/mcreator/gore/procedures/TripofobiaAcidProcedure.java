package net.mcreator.gore.procedures;

import net.mcreator.gore.init.GoreEditionModMobEffects;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelAccessor;

public class TripofobiaAcidProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity immediatesourceentity) {
      if (entity != null && immediatesourceentity != null) {
         if (!immediatesourceentity.level().isClientSide()) {
            immediatesourceentity.discard();
         }

         TripofobicAcidCollidesFXProcedure.execute(world, x, y, z);
         if ((!(entity instanceof LivingEntity _livEnt1) || !_livEnt1.isBlocking())
            && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("gore_edition:ashes_natives")))) {
            if (entity.getPersistentData().getDouble("acid_time_of_potency") <= 20.0
               && entity instanceof LivingEntity _entity
               && !_entity.level().isClientSide()) {
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

            if (entity.getPersistentData().getDouble("acid_time_of_potency") < 201.0) {
               entity.getPersistentData().putDouble("acid_time_of_potency", entity.getPersistentData().getDouble("acid_time_of_potency") + 20.0);
            }

            entity.getPersistentData().putBoolean("recently_exposed_to_acid", true);
         }
      }
   }
}
