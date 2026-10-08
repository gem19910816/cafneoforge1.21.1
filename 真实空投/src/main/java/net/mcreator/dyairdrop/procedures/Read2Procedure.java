package net.mcreator.dyairdrop.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;

public class Read2Procedure {
   public Read2Procedure() {
   }

   public static void execute(LevelAccessor world, double x, double y, double z) {
      if (!world.isClientSide() && world.getServer() != null) {
         world.getServer().getPlayerList().broadcastSystemMessage(Component.literal((new Object() {
            public String getValue(LevelAccessor world, BlockPos pos, String tag) {
               BlockEntity blockEntity = world.getBlockEntity(pos);
               return blockEntity != null ? blockEntity.getPersistentData().getString(tag) : "";
            }
         }).getValue(world, BlockPos.containing(x, y, z), "key")), false);
      }
   }
}
