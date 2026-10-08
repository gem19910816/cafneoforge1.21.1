package net.mcreator.gore.procedures;

import net.minecraft.world.entity.Entity;

public class SeveredLegsSkeletoncanJumpProcedure {
   public static boolean execute(Entity entity) {
      return entity == null ? false : entity.getPersistentData().getDouble("timer") >= 140.0;
   }
}
