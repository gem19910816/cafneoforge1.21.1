package net.mcreator.dyairdrop.procedures;

import net.mcreator.dyairdrop.network.DyairdropModVariables;
import net.minecraft.world.entity.Entity;

public class Light4Procedure {
   public Light4Procedure() {
   }

   public static boolean execute(Entity entity) {
      return entity == null
         ? false
         : DyairdropModVariables.get(entity)
            .passwordre
            .contains("d");
   }
}
