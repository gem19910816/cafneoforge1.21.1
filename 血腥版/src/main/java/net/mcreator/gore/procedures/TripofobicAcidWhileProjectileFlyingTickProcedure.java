package net.mcreator.gore.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class TripofobicAcidWhileProjectileFlyingTickProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity immediatesourceentity) {
      if (immediatesourceentity != null) {
         if (immediatesourceentity.getPersistentData().getDouble("TICK") == 0.0) {
            immediatesourceentity.setNoGravity(true);
         }

         immediatesourceentity.getPersistentData().putDouble("TICK", immediatesourceentity.getPersistentData().getDouble("TICK") + 1.0);
         if (immediatesourceentity.getPersistentData().getDouble("TICK") == 200.0 && !immediatesourceentity.level().isClientSide()) {
            immediatesourceentity.discard();
         }

         if (immediatesourceentity.isInWater()) {
            TripofobicAcidCollidesFXProcedure.execute(world, x, y, z);
            if (!immediatesourceentity.level().isClientSide()) {
               immediatesourceentity.discard();
            }
         }
      }
   }
}
