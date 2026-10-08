package net.mcreator.gore.procedures;

import net.minecraft.world.entity.Entity;

public class CordycepsStopOrderNotWalkingProcedure {
   public static boolean execute(Entity entity) {
      return entity == null ? false : entity.getPersistentData().getDouble("orders") != 1.0 && entity.getPersistentData().getDouble("orders") != 4.0;
   }
}
