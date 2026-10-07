package net.gem19910816.dyairdrop.procedures;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;

public class GetrandomplayerProcedure {
   public static Player getRandomPlayer(LevelAccessor level, List<String> strings) {
      if (!level.isClientSide()) {
         MinecraftServer server = level.getServer();
         List<ServerPlayer> chosenPlayers = new ArrayList<>();

         for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            if (strings.contains(player.level().dimension().location().toString())) {
               chosenPlayers.add(player);
            }
         }

         if (chosenPlayers.size() > 0) {
            return (Player)chosenPlayers.get(new Random().nextInt(chosenPlayers.size()));
         }
      }

      return null;
   }
}
