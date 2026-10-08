package net.mcreator.dyairdrop.procedures;

import net.mcreator.dyairdrop.network.DyairdropModVariables;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class PannelREticksProcedure {
   public PannelREticksProcedure() {
   }

   public static void execute(Entity entity) {
      if (entity != null) {
         if (DyairdropModVariables.get(entity)
            .passwordre
            .contains("Y")) {
            String _setval = "";
            DyairdropModVariables.with(entity, capability -> {
               capability.passwordre = _setval;
               capability.syncPlayerVariables(entity);
            });
            if (entity instanceof Player _player) {
               _player.closeContainer();
            }
         }
      }
   }
}
