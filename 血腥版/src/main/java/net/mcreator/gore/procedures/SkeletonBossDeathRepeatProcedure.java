package net.mcreator.gore.procedures;

import net.mcreator.gore.GoreEditionMod;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class SkeletonBossDeathRepeatProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         SkeletonDeathPiecesConfigProcedure.execute(world, x, y, z, entity);
         GoreEditionMod.queueServerWork(10, () -> SkeletonDeathPiecesConfigProcedure.execute(world, x, y, z, entity));
         GoreEditionMod.queueServerWork(20, () -> SkeletonDeathPiecesConfigProcedure.execute(world, x, y, z, entity));
         GoreEditionMod.queueServerWork(30, () -> SkeletonDeathPiecesConfigProcedure.execute(world, x, y, z, entity));
         GoreEditionMod.queueServerWork(40, () -> SkeletonDeathPiecesConfigProcedure.execute(world, x, y, z, entity));
         GoreEditionMod.queueServerWork(50, () -> SkeletonDeathPiecesConfigProcedure.execute(world, x, y, z, entity));
         GoreEditionMod.queueServerWork(60, () -> SkeletonDeathPiecesConfigProcedure.execute(world, x, y, z, entity));
      }
   }
}
