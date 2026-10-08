package net.mcreator.gore.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level.ExplosionInteraction;

public class ProjectileSquitchgunProjectileHitsBlockProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity immediatesourceentity) {
      if (immediatesourceentity != null) {
         if (!immediatesourceentity.level().isClientSide()) {
            immediatesourceentity.discard();
         }

         if (world instanceof Level _level && !_level.isClientSide()) {
            _level.explode(null, x, y, z, 9.0F, ExplosionInteraction.MOB);
         }
      }
   }
}
