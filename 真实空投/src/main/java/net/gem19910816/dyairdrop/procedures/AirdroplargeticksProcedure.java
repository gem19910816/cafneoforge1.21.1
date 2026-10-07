package net.gem19910816.dyairdrop.procedures;

import net.gem19910816.dyairdrop.core.Commands;

import net.gem19910816.dyairdrop.core.Nbt;

import net.gem19910816.dyairdrop.configuration.AirdropconfigConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class AirdroplargeticksProcedure {
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
      } else if (Nbt.getDouble(world, BlockPos.containing(x, y, z), "timer") >= 20000.0) {
         world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
      } else if (!world.isClientSide()) {
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

      if (Nbt.getDouble(world, BlockPos.containing(x, y, z), "timer") == 1.0 && !world.isClientSide()) {
         BlockPos _bp = BlockPos.containing(x, y, z);
         BlockEntity _blockEntity = world.getBlockEntity(_bp);
         BlockState _bs = world.getBlockState(_bp);
         if (_blockEntity != null) {
            _blockEntity.getPersistentData().putBoolean("s", true);
         }

         if (world instanceof Level _level) {
            _level.sendBlockUpdated(_bp, _bs, _bs, 3);
         }
      }

      if (Nbt.getDouble(world, BlockPos.containing(x, y, z), "timer") % 80.0 == 0.0 && Nbt.getDouble(world, BlockPos.containing(x, y, z), "timer") <= 3000.0 && Nbt.getBoolean(world, BlockPos.containing(x, y, z), "s") && world instanceof ServerLevel _level) {
         Commands.run(_level, x, y, z, "particle dyairdrop:signalsmoke " + x + " " + (y + 17.0) + " " + z + " 2 6 2 0 2000 force"
            );
      }

      if (Nbt.getDouble(world, BlockPos.containing(x, y, z), "timer") / 1200.0 == (Double)AirdropconfigConfiguration.ENEMYARRIVETIME.get()
         && (Boolean)AirdropconfigConfiguration.ENABLEENEMIES.get()) {
         SelectsummonpositionProcedure.execute(world, x, y, z);
      }

      if (Nbt.getDouble(world, BlockPos.containing(x, y, z), "timer") >= Math.round((Double)AirdropconfigConfiguration.AIRDROPSTOLENTIME.get() * 1200.0)
         && Nbt.getBoolean(world, BlockPos.containing(x, y, z), "s")
         && Nbt.getDouble(world, BlockPos.containing(x, y, z), "timer") % 20.0 == 0.0
          && world.getEntitiesOfClass(
               Player.class,
               AABB.ofSize(
                  new Vec3(x, y, z),
                  Math.round((Double)AirdropconfigConfiguration.DISTANCE.get() * 2.0),
                  Math.round((Double)AirdropconfigConfiguration.DISTANCE.get() * 2.0),
                  Math.round((Double)AirdropconfigConfiguration.DISTANCE.get() * 2.0)
               ),
               e -> true
            )
            .isEmpty()) {
         world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
         if (!world.isClientSide() && world.getServer() != null) {
            world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(Component.translatable("message.airdropstolen").getString()), false);
         }
      }
   }
}
