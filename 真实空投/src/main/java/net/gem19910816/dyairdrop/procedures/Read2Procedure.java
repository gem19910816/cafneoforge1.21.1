package net.gem19910816.dyairdrop.procedures;

import net.gem19910816.dyairdrop.core.Nbt;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.LevelAccessor;

public class Read2Procedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      if (!world.isClientSide() && world.getServer() != null) {
         world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(Nbt.getString(world, BlockPos.containing(x, y, z), "key")), false);
      }
   }
}
