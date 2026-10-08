package net.mcreator.dyairdrop.procedures;

import net.mcreator.dyairdrop.network.DyairdropModVariables;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;

public class ShutdownprocessProcedure {
   public ShutdownprocessProcedure() {
   }

   public static void execute(LevelAccessor world, Entity entity) {
      if (entity != null) {
         if (DyairdropModVariables.get(entity)
                  .pw
                  .length()
               == 6
            && DyairdropModVariables.get(entity)
                  .showlight
               == 1.0
            && (double)world.dayTime()
                  - DyairdropModVariables.get(entity)
                     .keyticking
               >= 55.0
            && entity instanceof Player _player) {
            _player.closeContainer();
         }
      }
   }
}
