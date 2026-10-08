package net.mcreator.gore.procedures;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level.ExplosionInteraction;

public class ProjectileWitherSkullProjectileHitsLivingEntityProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity immediatesourceentity, Entity sourceentity) {
      if (entity != null && immediatesourceentity != null && sourceentity != null) {
         if (world instanceof Level _level && !_level.isClientSide()) {
            _level.explode(null, x, y, z, 1.0F, ExplosionInteraction.MOB);
         }

         if (!immediatesourceentity.level().isClientSide()) {
            immediatesourceentity.discard();
         }

         if (entity != sourceentity && entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
            _entity.addEffect(new MobEffectInstance(MobEffects.WITHER, 800, 2));
         }
      }
   }
}
