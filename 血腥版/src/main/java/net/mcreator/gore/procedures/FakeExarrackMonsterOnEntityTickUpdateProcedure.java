package net.mcreator.gore.procedures;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelAccessor;

public class FakeExarrackMonsterOnEntityTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         entity.getPersistentData().putDouble("distance", 10.0);
         AshesNativesSetAttackTargetProcedure.execute(world, x, y, z, entity);
         if (entity.getVehicle() instanceof LivingEntity && entity.getVehicle() instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
            _entity.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 20, 0, false, false));
         }
      }
   }
}
