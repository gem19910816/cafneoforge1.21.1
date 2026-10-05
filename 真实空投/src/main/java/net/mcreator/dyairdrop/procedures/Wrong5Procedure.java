package net.mcreator.dyairdrop.procedures;

import net.mcreator.dyairdrop.network.DyairdropModVariables;
import net.minecraft.world.entity.Entity;

public class Wrong5Procedure {
   public static boolean execute(Entity entity) {
      return entity == null
         ? false
         : ((DyairdropModVariables.PlayerVariables)entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()))
            .passwordre
            .contains("E");
   }
}
