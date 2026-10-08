package net.mcreator.dyairdrop.procedures;

import net.mcreator.dyairdrop.network.DyairdropModVariables;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class W2Procedure {
   public W2Procedure() {
   }

   public static boolean execute(LevelAccessor world, Entity entity) {
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
               .substring(1, 2)
               .equals("0")
            && (double)world.dayTime()
                  - DyairdropModVariables.get(entity)
                     .keyticking
               > 7.0;
   }
}
