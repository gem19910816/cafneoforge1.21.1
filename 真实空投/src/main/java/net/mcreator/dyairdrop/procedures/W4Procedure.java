package net.mcreator.dyairdrop.procedures;

import net.mcreator.dyairdrop.network.DyairdropModVariables;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;

public class W4Procedure {
   public static boolean execute(LevelAccessor world, Entity entity) {
      return entity == null
         ? false
         : ((DyairdropModVariables.PlayerVariables)entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()))
                  .pw
                  .length()
               == 6
            && ((DyairdropModVariables.PlayerVariables)entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()))
                  .showlight
               == 1.0
            && ((DyairdropModVariables.PlayerVariables)entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()))
               .pw
               .substring(3, 4)
               .equals("0")
            && ((Level)world).getDayTime()
                  - ((DyairdropModVariables.PlayerVariables)entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()))
                     .keyticking
               > 21.0;
   }
}
