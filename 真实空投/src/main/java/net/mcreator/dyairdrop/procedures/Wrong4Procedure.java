package net.mcreator.dyairdrop.procedures;

import net.gem19910816.dyairdrop.core.Vars;

import net.minecraft.world.entity.Entity;

public class Wrong4Procedure {
   public static boolean execute(Entity entity) {
      return entity == null
         ? false
         : Vars.of(entity)
            .passwordre
            .contains("D");
   }
}
