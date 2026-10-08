package net.mcreator.dyairdrop.procedures;

import net.mcreator.dyairdrop.network.DyairdropModVariables;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class C6Procedure {
   public C6Procedure() {
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
               .substring(5, 6)
               .equals("1")
            && (double)world.dayTime()
                  - DyairdropModVariables.get(entity)
                     .keyticking
               > 35.0;
   }
}
