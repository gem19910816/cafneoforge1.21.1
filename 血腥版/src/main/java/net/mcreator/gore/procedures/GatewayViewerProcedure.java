package net.mcreator.gore.procedures;

import net.mcreator.gore.entity.AshesGatewayEntity;
import net.minecraft.world.entity.Entity;

public class GatewayViewerProcedure {
   public static Entity execute(Entity entity) {
      if (entity == null) {
         return null;
      } else {
         return entity instanceof AshesGatewayEntity ? entity : null;
      }
   }
}
