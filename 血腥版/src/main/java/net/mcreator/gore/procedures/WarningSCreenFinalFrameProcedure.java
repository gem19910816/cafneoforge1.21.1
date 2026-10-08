package net.mcreator.gore.procedures;

import net.mcreator.gore.network.GoreEditionModVariables;
import net.minecraft.world.entity.Entity;

public class WarningSCreenFinalFrameProcedure {
   public static boolean execute(Entity entity) {
      return entity == null ? false : GoreEditionModVariables.getPlayerVariables(entity).warning_screen_frames >= 8.0;
   }
}
