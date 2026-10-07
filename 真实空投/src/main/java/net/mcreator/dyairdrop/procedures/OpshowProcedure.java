package net.mcreator.dyairdrop.procedures;

import net.gem19910816.dyairdrop.core.GameModes;

import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;

public class OpshowProcedure {
   public static boolean execute(Entity entity) {
      return entity == null
         ? false
         : GameModes.isCreative(entity);
   }
}
