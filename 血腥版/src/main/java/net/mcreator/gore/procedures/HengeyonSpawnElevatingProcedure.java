package net.mcreator.gore.procedures;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

public class HengeyonSpawnElevatingProcedure {
   public static void execute(double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (!(entity.getY() >= entity.getPersistentData().getDouble("spawn_y") + 30.0)) {
            entity.getPersistentData().putBoolean("hengeyon_spawn_elevating", true);
         } else {
            entity.getPersistentData().putBoolean("hengeyon_spawn_elevating", false);
         }

         if (entity.getPersistentData().getBoolean("hengeyon_spawn_elevating")) {
            entity.teleportTo(x, y + entity.getPersistentData().getDouble("elevate_velocity"), z);
            if (entity instanceof ServerPlayer _serverPlayer) {
               _serverPlayer.connection.teleport(x, y + entity.getPersistentData().getDouble("elevate_velocity"), z, entity.getYRot(), entity.getXRot());
            }
         }
      }
   }
}
