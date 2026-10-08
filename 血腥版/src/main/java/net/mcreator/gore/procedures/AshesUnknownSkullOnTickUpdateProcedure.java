package net.mcreator.gore.procedures;

import net.mcreator.gore.init.GoreEditionModBlocks;
import net.mcreator.gore.init.GoreEditionModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class AshesUnknownSkullOnTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      if (!world.isClientSide()) {
         BlockPos _bp = BlockPos.containing(x, y, z);
         BlockEntity _blockEntity = world.getBlockEntity(_bp);
         BlockState _bs = world.getBlockState(_bp);
         if (_blockEntity != null) {
            _blockEntity.getPersistentData().putDouble("tick_rate", (new Object() {
               public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                  BlockEntity blockEntity = world.getBlockEntity(pos);
                  return blockEntity != null ? blockEntity.getPersistentData().getDouble(tag) : -1.0;
               }
            }).getValue(world, BlockPos.containing(x, y, z), "tick_rate") + 1.0);
         }

         if (world instanceof Level _level) {
            _level.sendBlockUpdated(_bp, _bs, _bs, 3);
         }
      }

      if ((new Object() {
         public double getValue(LevelAccessor world, BlockPos pos, String tag) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            return blockEntity != null ? blockEntity.getPersistentData().getDouble(tag) : -1.0;
         }
      }).getValue(world, BlockPos.containing(x, y, z), "tick_rate") == 100.0) {
         BlockPos _pos = BlockPos.containing(x, y, z);
         Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x, y, z), null);
         world.destroyBlock(_pos, false);
      }

      if (world.getDifficulty() != Difficulty.PEACEFUL
         && world.getBlockState(BlockPos.containing(x, y + 1.0, z)).getBlock() == GoreEditionModBlocks.ASHTRAY_CYCLE.get()
         && world.getBlockState(BlockPos.containing(x, y - 1.0, z)).getBlock() == GoreEditionModBlocks.ASHTRAY_CYCLE.get()
         && world.getBlockState(BlockPos.containing(x + 1.0, y, z)).getBlock() == GoreEditionModBlocks.ASHTRAY_CYCLE.get()
         && world.getBlockState(BlockPos.containing(x - 1.0, y, z)).getBlock() == GoreEditionModBlocks.ASHTRAY_CYCLE.get()
         && world.getBlockState(BlockPos.containing(x, y, z + 1.0)).getBlock() == GoreEditionModBlocks.ASHTRAY_CYCLE.get()
         && world.getBlockState(BlockPos.containing(x, y, z - 1.0)).getBlock() == GoreEditionModBlocks.ASHTRAY_CYCLE.get()) {
         world.destroyBlock(BlockPos.containing(x, y, z), false);
         world.destroyBlock(BlockPos.containing(x, y + 1.0, z), false);
         world.destroyBlock(BlockPos.containing(x, y - 1.0, z), false);
         world.destroyBlock(BlockPos.containing(x + 1.0, y, z), false);
         world.destroyBlock(BlockPos.containing(x - 1.0, y, z), false);
         world.destroyBlock(BlockPos.containing(x, y, z + 1.0), false);
         world.destroyBlock(BlockPos.containing(x, y, z - 1.0), false);
         if (world instanceof ServerLevel _level) {
            Entity entityToSpawn = ((EntityType)GoreEditionModEntities.HENGEYON.get()).spawn(_level, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
            if (entityToSpawn != null) {
               entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
            }
         }
      }
   }
}
