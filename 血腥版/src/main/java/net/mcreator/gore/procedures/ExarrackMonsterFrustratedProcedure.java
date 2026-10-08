package net.mcreator.gore.procedures;

import net.minecraft.world.entity.Entity;

public class ExarrackMonsterFrustratedProcedure {
   public static void execute(Entity entity) {
      if (entity != null) {
         if (entity.getPersistentData().getBoolean("recentlyattack")) {
            entity.getPersistentData().putDouble("asfdfsda", 0.0);
            entity.getPersistentData().putBoolean("recentlyattack", false);
         } else {
            entity.getPersistentData().putDouble("asfdfsda", entity.getPersistentData().getDouble("asfdfsda") + 1.0);
         }

         if (entity.getPersistentData().getDouble("asfdfsda") == 200.0) {
            entity.getPersistentData().putBoolean("frustrated", true);
         }
      }
   }
}
