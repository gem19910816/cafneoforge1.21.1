package net.gem19910816.dyairdrop.procedures;

import net.gem19910816.dyairdrop.core.Vars;

import net.minecraft.world.entity.Entity;

public class Wrong1Procedure {
   public static boolean execute(Entity entity) {
      return entity == null
         ? false
         : Vars.of(entity)
            .passwordre
            .contains("A");
   }
}
