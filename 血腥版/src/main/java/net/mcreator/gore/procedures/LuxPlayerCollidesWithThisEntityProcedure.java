package net.mcreator.gore.procedures;

import net.minecraft.world.entity.Entity;

public class LuxPlayerCollidesWithThisEntityProcedure {
   public static void execute(Entity entity, Entity sourceentity) {
      if (entity != null && sourceentity != null) {
         sourceentity.getPersistentData().putDouble("luxxed", sourceentity.getPersistentData().getDouble("luxxed") + 1.0);
         if (!entity.level().isClientSide()) {
            entity.discard();
         }
      }
   }
}
