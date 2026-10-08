package net.mcreator.gore.procedures;

import net.mcreator.gore.GoreEditionMod;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class GuardianDeathDustRepeatConfigProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         GuardianDeathDustConfigProcedure.execute(world, x, y, z, entity);
         GoreEditionMod.queueServerWork(10, () -> GuardianDeathDustConfigProcedure.execute(world, x, y, z, entity));
         GoreEditionMod.queueServerWork(20, () -> GuardianDeathDustConfigProcedure.execute(world, x, y, z, entity));
         GoreEditionMod.queueServerWork(30, () -> GuardianDeathDustConfigProcedure.execute(world, x, y, z, entity));
         GoreEditionMod.queueServerWork(40, () -> GuardianDeathDustConfigProcedure.execute(world, x, y, z, entity));
         GoreEditionMod.queueServerWork(50, () -> GuardianDeathDustConfigProcedure.execute(world, x, y, z, entity));
         GoreEditionMod.queueServerWork(60, () -> GuardianDeathDustConfigProcedure.execute(world, x, y, z, entity));
      }
   }
}
