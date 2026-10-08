package net.mcreator.gore.procedures;

import net.minecraft.world.entity.Entity;

public class ProjectileWitherSkullWhileProjectileFlyingTickProcedure {
   public static void execute(Entity immediatesourceentity) {
      if (immediatesourceentity != null && !immediatesourceentity.isNoGravity()) {
         immediatesourceentity.setNoGravity(true);
      }
   }
}
