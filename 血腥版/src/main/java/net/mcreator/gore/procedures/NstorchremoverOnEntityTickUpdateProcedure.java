package net.mcreator.gore.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;

public class NstorchremoverOnEntityTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (world.getMaxLocalRawBrightness(BlockPos.containing(x, y, z)) > 0) {
            entity.getPersistentData().putBoolean("activate", true);
         }

         if (entity.getPersistentData().getBoolean("activate")) {
            if (entity.getPersistentData().getDouble("break_block") == 0.0) {
               entity.getPersistentData().putDouble("break_block_time_variant", (double)Mth.nextInt(RandomSource.create(), 1, 2));
               if (entity.getPersistentData().getDouble("break_block_time_variant") == 1.0) {
                  entity.getPersistentData().putDouble("min_time_to_break_block", 150.0);
               }

               if (entity.getPersistentData().getDouble("break_block_time_variant") == 2.0) {
                  entity.getPersistentData().putDouble("min_time_to_break_block", 200.0);
               }
            }

            entity.getPersistentData().putDouble("break_block", entity.getPersistentData().getDouble("break_block") + 1.0);
            if (entity.getPersistentData().getDouble("break_block") >= entity.getPersistentData().getDouble("min_time_to_break_block")) {
               if (!entity.level().isClientSide()) {
                  entity.discard();
               }

               if (Math.random() < 0.4) {
                  BlockPos _pos = BlockPos.containing(x, y, z);
                  Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x, y, z), null);
                  world.destroyBlock(_pos, false);
               } else {
                  world.destroyBlock(BlockPos.containing(x, y, z), false);
               }
            }
         }

         if (world.getMaxLocalRawBrightness(BlockPos.containing(x, y, z)) <= 0 && !entity.level().isClientSide()) {
            entity.discard();
         }
      }
   }
}
