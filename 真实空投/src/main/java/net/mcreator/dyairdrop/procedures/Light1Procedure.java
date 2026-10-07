package net.mcreator.dyairdrop.procedures;

import net.gem19910816.dyairdrop.core.Vars;

import net.mcreator.dyairdrop.network.DyairdropModVariables;
import net.minecraft.world.entity.Entity;

public class Light1Procedure {
   public static boolean execute(Entity entity) {
      return entity == null
         ? false
         : Vars.of(entity)
            .passwordre
            .contains("a");
   }
}
