package net.mcreator.dyairdrop.procedures;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class SDADProcedure {
   public static void execute(LevelAccessor world, Entity entity) {
      if (entity != null) {
         String t1 = "";
         t1 = entity.getStringUUID();
         if (!world.isClientSide() && world.getServer() != null) {
            world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(t1), false);
         }
      }
   }
}
