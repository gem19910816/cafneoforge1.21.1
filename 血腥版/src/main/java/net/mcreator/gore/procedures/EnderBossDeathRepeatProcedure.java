package net.mcreator.gore.procedures;

import net.mcreator.gore.GoreEditionMod;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class EnderBossDeathRepeatProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         EnderDeathBloodConfigProcedure.execute(world, x, y, z, entity);
         GoreEditionMod.queueServerWork(10, () -> EnderDeathBloodConfigProcedure.execute(world, x, y, z, entity));
         GoreEditionMod.queueServerWork(20, () -> EnderDeathBloodConfigProcedure.execute(world, x, y, z, entity));
         GoreEditionMod.queueServerWork(30, () -> EnderDeathBloodConfigProcedure.execute(world, x, y, z, entity));
         GoreEditionMod.queueServerWork(40, () -> EnderDeathBloodConfigProcedure.execute(world, x, y, z, entity));
         GoreEditionMod.queueServerWork(50, () -> EnderDeathBloodConfigProcedure.execute(world, x, y, z, entity));
         GoreEditionMod.queueServerWork(60, () -> EnderDeathBloodConfigProcedure.execute(world, x, y, z, entity));
      }
   }
}
