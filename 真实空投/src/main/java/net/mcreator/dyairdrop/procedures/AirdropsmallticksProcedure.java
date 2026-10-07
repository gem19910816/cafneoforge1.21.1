package net.mcreator.dyairdrop.procedures;

import net.gem19910816.dyairdrop.core.Commands;

import net.gem19910816.dyairdrop.core.Nbt;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class AirdropsmallticksProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      double i = 0.0;
      String table = "";
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

      if (Nbt.getDouble(world, BlockPos.containing(x, y, z), "timer") % 80.0 == 0.0 && Nbt.getDouble(world, BlockPos.containing(x, y, z), "timer") <= 6000.0 && world instanceof ServerLevel _level) {
         Commands.run(_level, x, y, z, "particle dyairdrop:signalsmoke " + x + " " + (y + 17.0) + " " + z + " 2 6 2 0 2000 force"
            );
      }

      if (Nbt.getDouble(world, BlockPos.containing(x, y, z), "timer") == 1200.0) {
         if (world instanceof ServerLevel _level) {
            Entity entityToSpawn = EntityType.PILLAGER.spawn(_level, BlockPos.containing(x, y + 1.0, z), MobSpawnType.MOB_SUMMONED);
            if (entityToSpawn != null) {
               entityToSpawn.setYRot(200.0F);
               entityToSpawn.setYBodyRot(200.0F);
               entityToSpawn.setYHeadRot(200.0F);
               entityToSpawn.setDeltaMovement(0.5, 0.0, 0.5);
            }
         }

         if (world instanceof ServerLevel _level) {
            Entity entityToSpawn = EntityType.PILLAGER.spawn(_level, BlockPos.containing(x, y + 1.0, z), MobSpawnType.MOB_SUMMONED);
            if (entityToSpawn != null) {
               entityToSpawn.setYRot(200.0F);
               entityToSpawn.setYBodyRot(200.0F);
               entityToSpawn.setYHeadRot(200.0F);
               entityToSpawn.setDeltaMovement(0.5, 0.0, -0.5);
            }
         }

         if (world instanceof ServerLevel _level) {
            Entity entityToSpawn = EntityType.PILLAGER.spawn(_level, BlockPos.containing(x, y + 1.0, z), MobSpawnType.MOB_SUMMONED);
            if (entityToSpawn != null) {
               entityToSpawn.setYRot(200.0F);
               entityToSpawn.setYBodyRot(200.0F);
               entityToSpawn.setYHeadRot(200.0F);
               entityToSpawn.setDeltaMovement(-0.5, 0.0, 0.5);
            }
         }

         if (world instanceof ServerLevel _level) {
            Entity entityToSpawn = EntityType.PILLAGER.spawn(_level, BlockPos.containing(x, y + 1.0, z), MobSpawnType.MOB_SUMMONED);
            if (entityToSpawn != null) {
               entityToSpawn.setYRot(200.0F);
               entityToSpawn.setYBodyRot(200.0F);
               entityToSpawn.setYHeadRot(200.0F);
               entityToSpawn.setDeltaMovement(-0.5, 0.0, 0.5);
            }
         }
      }
   }
}
