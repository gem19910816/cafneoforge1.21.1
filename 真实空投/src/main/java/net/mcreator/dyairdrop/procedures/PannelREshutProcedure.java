package net.mcreator.dyairdrop.procedures;

import net.gem19910816.dyairdrop.core.Vars;

import net.mcreator.dyairdrop.network.DyairdropModVariables;
import net.minecraft.world.entity.Entity;

public class PannelREshutProcedure {
   public static void execute(Entity entity) {
      if (entity != null) {
         String _setval = "";
         Vars.of(entity).ifPresentData(capability -> {
            capability.passwordre = _setval;
            capability.syncPlayerVariables(entity);
         });
      }
   }
}
