package net.mcreator.gore.procedures;

import net.mcreator.gore.network.GoreEditionModVariables;
import net.minecraft.world.entity.Entity;

public class S6DIsplayOverlayInGameProcedure {
   public static boolean execute(Entity entity) {
      return entity == null ? false : GoreEditionModVariables.getPlayerVariables(entity).blood_in_screen_i_amount >= 6.0;
   }
}
