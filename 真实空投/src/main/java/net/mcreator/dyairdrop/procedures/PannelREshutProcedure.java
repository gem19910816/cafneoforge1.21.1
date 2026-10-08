package net.mcreator.dyairdrop.procedures;

import net.mcreator.dyairdrop.network.DyairdropModVariables;
import net.minecraft.world.entity.Entity;

public class PannelREshutProcedure {
   public PannelREshutProcedure() {
   }

   public static void execute(Entity entity) {
      if (entity != null) {
         String _setval = "";
         DyairdropModVariables.with(entity, capability -> {
            capability.passwordre = _setval;
            capability.syncPlayerVariables(entity);
         });
      }
   }
}
