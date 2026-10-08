package net.mcreator.gore.procedures;

import net.minecraft.world.entity.Entity;

public class DeformtyForAshesDontMessAroundConditionProcedure {
   public static boolean execute(Entity entity) {
      return entity == null ? false : entity.getPersistentData().getBoolean("messingaround");
   }
}
