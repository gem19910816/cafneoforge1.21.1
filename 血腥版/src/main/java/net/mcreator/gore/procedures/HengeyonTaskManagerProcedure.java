package net.mcreator.gore.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.LevelAccessor;

public class HengeyonTaskManagerProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (!entity.getPersistentData().getBoolean("registered")) {
            entity.getPersistentData().putDouble("elevate_velocity", 0.4);
            entity.getPersistentData().putBoolean("registered", true);
            entity.getPersistentData().putString("attack", "none");
         }

         if ((entity instanceof Mob _mobEnt ? _mobEnt.getTarget() : null) != null) {
            if (entity.getPersistentData().getString("attack").equals("none") && entity.isAlive()) {
               if (Math.random() < 0.6) {
                  entity.getPersistentData().putString("attack", "avalanche");
               } else if (Math.random() < 0.5) {
                  entity.getPersistentData().putString("attack", "ABH");
               } else {
                  entity.getPersistentData().putString("attack", "ONSLAUGHT");
               }
            }

            if (!entity.getPersistentData().getBoolean("cant_move")) {
               if (entity.getPersistentData().getString("attack").equals("avalanche")) {
                  HengeyonAvalancheAttackProcedure.execute(world, x, y, z, entity);
               }

               if (entity.getPersistentData().getString("attack").equals("ABH")) {
                  HengeyonAshesBlackHoleAttackProcedure.execute(world, x, y, z, entity);
               }

               if (entity.getPersistentData().getString("attack").equals("ONSLAUGHT")) {
                  HengeyonOnslaughtAttackProcedure.execute(world, x, y, z, entity);
               }
            }
         }

         if (entity.isAlive()) {
            if (entity.getPersistentData().getDouble("freezing") >= 200.0) {
               entity.getPersistentData().putBoolean("cant_move", true);
            }

            if (entity.getPersistentData().getBoolean("cant_move")) {
               entity.getPersistentData().putDouble("freezing", entity.getPersistentData().getDouble("freezing") - 1.3);
               if (entity.getPersistentData().getDouble("freezing") <= 0.0) {
                  entity.getPersistentData().putString("attack", "none");
                  entity.getPersistentData().putDouble("freezing", 0.0);
                  entity.getPersistentData().putBoolean("cant_move", false);
               }
            }
         } else {
            entity.getPersistentData().putBoolean("cant_move", true);
         }

         if (entity.getPersistentData().getDouble("elevate_velocity") != 0.4) {
            entity.getPersistentData().putDouble("elevate_velocity_change_tick", entity.getPersistentData().getDouble("elevate_velocity_change_tick") - 1.3);
            if (entity.getPersistentData().getDouble("elevate_velocity_change_tick") >= 60.0) {
               entity.getPersistentData().putDouble("elevate_velocity_change_tick", 0.0);
               entity.getPersistentData().putDouble("elevate_velocity", 0.4);
            }
         }
      }
   }
}
