package net.mcreator.gore.procedures;

import net.mcreator.gore.configuration.GoreEditionModeSettingsConfiguration;
import net.mcreator.gore.network.GoreEditionModVariables;
import net.minecraft.world.entity.Entity;

public class WarningScreenDisplayOverlayIngameProcedure {
   public static boolean execute(Entity entity) {
      return entity == null
         ? false
         : !GoreEditionModVariables.getPlayerVariables(entity).warning_screen_toggle && (Boolean)GoreEditionModeSettingsConfiguration.WARNING_SCREEN.get();
   }
}
