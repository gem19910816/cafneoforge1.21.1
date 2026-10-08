package net.mcreator.dyairdrop.procedures;

import net.mcreator.dyairdrop.network.DyairdropModVariables;
import net.minecraft.world.entity.Entity;

public class C1Procedure {
   public C1Procedure() {
   }

   public static boolean execute(Entity entity) {
      return entity == null
         ? false
         : DyairdropModVariables.get(entity)
                  .pw
                  .length()
               == 6
            && DyairdropModVariables.get(entity)
                  .showlight
               == 1.0
            && DyairdropModVariables.get(entity)
               .pw
               .substring(0, 1)
               .equals("1");
   }
}
