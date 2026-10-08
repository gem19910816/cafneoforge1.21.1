package net.mcreator.gore.procedures;

import net.mcreator.gore.init.GoreEditionModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class GrenadeOfAcidTemporalAcidOnEntityTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      if (!world.isClientSide()) {
         BlockPos _bp = BlockPos.containing(x, y, z);
         BlockEntity _blockEntity = world.getBlockEntity(_bp);
         BlockState _bs = world.getBlockState(_bp);
         if (_blockEntity != null) {
            _blockEntity.getPersistentData().putDouble("acid_remove_timer", (new Object() {
               public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                  BlockEntity blockEntity = world.getBlockEntity(pos);
                  return blockEntity != null ? blockEntity.getPersistentData().getDouble(tag) : -1.0;
               }
            }).getValue(world, BlockPos.containing(x, y, z), "acid_remove_timer") + 1.0);
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
      }).getValue(world, BlockPos.containing(x, y, z), "acid_remove_timer") >= 30.0) {
         if (!world.isClientSide()) {
            BlockPos _bpx = BlockPos.containing(x, y, z);
            BlockEntity _blockEntityx = world.getBlockEntity(_bpx);
            BlockState _bsx = world.getBlockState(_bpx);
            if (_blockEntityx != null) {
               _blockEntityx.getPersistentData().putDouble("acid_remove_timer", 0.0);
            }

            if (world instanceof Level _level) {
               _level.sendBlockUpdated(_bpx, _bsx, _bsx, 3);
            }
         }

         if (world.getBlockState(BlockPos.containing(x, y - 1.0, z)).getBlock() == GoreEditionModBlocks.ACID.get()) {
            world.setBlock(BlockPos.containing(x, y - 1.0, z), Blocks.AIR.defaultBlockState(), 3);
         }

         if (world.getBlockState(BlockPos.containing(x, y - 1.0, z)).getBlock() == Blocks.AIR) {
            world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
         }
      }
   }
}
