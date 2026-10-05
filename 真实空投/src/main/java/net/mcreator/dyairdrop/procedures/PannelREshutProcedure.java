package net.mcreator.dyairdrop.procedures;

import net.mcreator.dyairdrop.network.DyairdropModVariables;
import net.minecraft.world.entity.Entity;

public class PannelREshutProcedure {
   public static void execute(Entity entity) {
      if (entity != null) {
         String _setval = "";
         entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()).ifPresentData(capability -> {
            capability.passwordre = _setval;
            capability.syncPlayerVariables(entity);
         });
      }
   }
}
