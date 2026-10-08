package net.mcreator.gore.procedures;

import net.mcreator.gore.network.GoreEditionModVariables;
import net.minecraft.world.entity.Entity;

public class S8DisplayOverlayInGameProcedure {
   public static boolean execute(Entity entity) {
      return entity == null ? false : GoreEditionModVariables.getPlayerVariables(entity).blood_in_screen_i_amount >= 8.0;
   }
}
