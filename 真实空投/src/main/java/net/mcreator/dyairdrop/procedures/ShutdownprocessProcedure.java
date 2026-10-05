package net.mcreator.dyairdrop.procedures;

import net.mcreator.dyairdrop.network.DyairdropModVariables;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;

public class ShutdownprocessProcedure {
   public static void execute(LevelAccessor world, Entity entity) {
      if (entity != null) {
         if (((DyairdropModVariables.PlayerVariables)entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()))
                  .pw
                  .length()
               == 6
            && ((DyairdropModVariables.PlayerVariables)entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()))
                  .showlight
               == 1.0
            && ((Level)world).getDayTime()
                  - ((DyairdropModVariables.PlayerVariables)entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()))
                     .keyticking
               >= 55.0
            && entity instanceof Player _player) {
            _player.closeContainer();
         }
      }
   }
}
