package net.mcreator.gore.procedures;

import net.mcreator.gore.GoreEditionMod;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class ElderGuardianDeathDustRepeatConfigProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         ElderGuardianDeathDustConfigProcedure.execute(world, x, y, z, entity);
         GoreEditionMod.queueServerWork(10, () -> ElderGuardianDeathDustConfigProcedure.execute(world, x, y, z, entity));
         GoreEditionMod.queueServerWork(20, () -> ElderGuardianDeathDustConfigProcedure.execute(world, x, y, z, entity));
         GoreEditionMod.queueServerWork(30, () -> ElderGuardianDeathDustConfigProcedure.execute(world, x, y, z, entity));
         GoreEditionMod.queueServerWork(40, () -> ElderGuardianDeathDustConfigProcedure.execute(world, x, y, z, entity));
         GoreEditionMod.queueServerWork(50, () -> ElderGuardianDeathDustConfigProcedure.execute(world, x, y, z, entity));
         GoreEditionMod.queueServerWork(60, () -> ElderGuardianDeathDustConfigProcedure.execute(world, x, y, z, entity));
      }
   }
}
