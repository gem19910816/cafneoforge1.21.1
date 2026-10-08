package net.mcreator.gore.procedures;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.LevelAccessor;

public class FloatingLuxSycaridaeOnEntityTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         SycarideLookProcedure.execute(world, x, y, z, entity);
         entity.getPersistentData().putDouble("distance", 3.0);
         LivingEntity var10 = entity instanceof Mob _mobEnt ? _mobEnt.getTarget() : null;
         if (!(var10 instanceof LivingEntity) || !var10.hasEffect(MobEffects.GLOWING)) {
            AshesNativesSetAttackTargetProcedure.execute(world, x, y, z, entity);
         }
      }
   }
}
