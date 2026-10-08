package net.mcreator.gore.procedures;

import net.mcreator.gore.network.GoreEditionModVariables;
import net.minecraft.world.entity.Entity;

public class WarningScreenFrame2Procedure {
   public static boolean execute(Entity entity) {
      return entity == null
         ? false
         : GoreEditionModVariables.getPlayerVariables(entity).warning_screen_frames >= 4.0
            && GoreEditionModVariables.getPlayerVariables(entity).warning_screen_frames <= 6.0;
   }
}
