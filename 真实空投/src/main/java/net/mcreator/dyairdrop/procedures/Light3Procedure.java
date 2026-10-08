package net.mcreator.dyairdrop.procedures;

import net.mcreator.dyairdrop.network.DyairdropModVariables;
import net.minecraft.world.entity.Entity;

public class Light3Procedure {
   public Light3Procedure() {
   }

   public static boolean execute(Entity entity) {
      return entity == null
         ? false
         : DyairdropModVariables.get(entity)
            .passwordre
            .contains("c");
   }
}
