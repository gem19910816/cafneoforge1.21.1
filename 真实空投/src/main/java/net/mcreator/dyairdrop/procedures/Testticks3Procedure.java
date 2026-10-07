package net.mcreator.dyairdrop.procedures;

import net.gem19910816.dyairdrop.core.Nbt;

import java.text.DecimalFormat;
import net.mcreator.dyairdrop.configuration.AirdropconfigConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class Testticks3Procedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      double i = 0.0;
      if (Nbt.getDouble(world, BlockPos.containing(x, y, z), "timer") < 0.0) {
         if (!world.isClientSide()) {
            BlockPos _bp = BlockPos.containing(x, y, z);
            BlockEntity _blockEntity = world.getBlockEntity(_bp);
            BlockState _bs = world.getBlockState(_bp);
            if (_blockEntity != null) {
               _blockEntity.getPersistentData().putDouble("timer", 0.0);
            }

            if (world instanceof Level _level) {
               _level.sendBlockUpdated(_bp, _bs, _bs, 3);
            }
         }
      } else {
         if (!world.isClientSide()) {
            BlockPos _bp = BlockPos.containing(x, y, z);
            BlockEntity _blockEntity = world.getBlockEntity(_bp);
            BlockState _bs = world.getBlockState(_bp);
            if (_blockEntity != null) {
               _blockEntity.getPersistentData().putDouble("timer", Nbt.getDouble(world, BlockPos.containing(x, y, z), "timer") + 1.0);
            }

            if (world instanceof Level _level) {
               _level.sendBlockUpdated(_bp, _bs, _bs, 3);
            }
         }

         if (!world.isClientSide() && world.getServer() != null) {
            world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(new DecimalFormat("##").format(Nbt.getDouble(world, BlockPos.containing(x, y, z), "timer")) + "," + (Double)AirdropconfigConfiguration.ENEMYARRIVETIME.get()), false);
         }
      }
   }
}
