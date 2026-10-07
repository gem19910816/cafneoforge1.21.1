package net.mcreator.dyairdrop.procedures;

import net.gem19910816.dyairdrop.core.Vars;

import net.mcreator.dyairdrop.network.DyairdropModVariables;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class PannelREticksProcedure {
   public static void execute(Entity entity) {
      if (entity != null) {
         if (Vars.of(entity)
            .passwordre
            .contains("Y")) {
            String _setval = "";
            Vars.of(entity).ifPresentData(capability -> {
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
