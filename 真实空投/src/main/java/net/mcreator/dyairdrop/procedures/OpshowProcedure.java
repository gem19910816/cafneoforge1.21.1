package net.mcreator.dyairdrop.procedures;

import net.gem19910816.dyairdrop.core.GameModes;

import net.minecraft.world.entity.Entity;

public class OpshowProcedure {
   public static boolean execute(Entity entity) {
      return entity == null
         ? false
         : GameModes.isCreative(entity);
   }
}
