package net.mcreator.gore.procedures;

import net.minecraft.world.entity.Entity;

public class CordycepsAttackOrderProcedure {
   public static boolean execute(Entity entity) {
      return entity == null ? false : !entity.getPersistentData().getBoolean("cattack");
   }
}
