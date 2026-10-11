package net.mcreator.dyairdrop.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

/**
 * Compatibility entry point retained for older generated procedure references.
 * The safe2 English lock uses the same server-authoritative validation path.
 */
public class ChecknewProcedure {
   private ChecknewProcedure() {
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      ChecknewliteProcedure.execute(world, x, y, z, entity);
   }
}
