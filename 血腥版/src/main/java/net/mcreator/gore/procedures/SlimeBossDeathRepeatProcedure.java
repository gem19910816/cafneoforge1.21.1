package net.mcreator.gore.procedures;

import net.mcreator.gore.GoreEditionMod;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class SlimeBossDeathRepeatProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         SlimeDeathSlimeConfigProcedure.execute(world, x, y, z, entity);
         GoreEditionMod.queueServerWork(10, () -> SlimeDeathSlimeConfigProcedure.execute(world, x, y, z, entity));
         GoreEditionMod.queueServerWork(20, () -> SlimeDeathSlimeConfigProcedure.execute(world, x, y, z, entity));
         GoreEditionMod.queueServerWork(30, () -> SlimeDeathSlimeConfigProcedure.execute(world, x, y, z, entity));
         GoreEditionMod.queueServerWork(40, () -> SlimeDeathSlimeConfigProcedure.execute(world, x, y, z, entity));
         GoreEditionMod.queueServerWork(50, () -> SlimeDeathSlimeConfigProcedure.execute(world, x, y, z, entity));
         GoreEditionMod.queueServerWork(60, () -> SlimeDeathSlimeConfigProcedure.execute(world, x, y, z, entity));
      }
   }
}
