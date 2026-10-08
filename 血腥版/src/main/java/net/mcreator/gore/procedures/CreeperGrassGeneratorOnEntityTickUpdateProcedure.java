package net.mcreator.gore.procedures;

import net.mcreator.gore.init.GoreEditionModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class CreeperGrassGeneratorOnEntityTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         boolean found = false;
         double sx = 0.0;
         double sy = 0.0;
         double sz = 0.0;
         if (world.getBlockState(BlockPos.containing(x, y - 1.0, z)).getBlock() == Blocks.GRASS_BLOCK
            && world.getBlockState(BlockPos.containing(x, y, z)).getBlock() != GoreEditionModBlocks.CREEPER_GRASS.get()) {
            if (!entity.level().isClientSide()) {
               entity.discard();
            }

            world.setBlock(BlockPos.containing(x, y, z), ((Block)GoreEditionModBlocks.CREEPER_GRASS.get()).defaultBlockState(), 3);
         } else {
            sx = -3.0;
            found = false;

            for (int index0 = 0; index0 < 6; index0++) {
               sy = -3.0;

               for (int index1 = 0; index1 < 6; index1++) {
                  sz = -3.0;

                  for (int index2 = 0; index2 < 6; index2++) {
                     if (world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz)).getBlock() == Blocks.GRASS_BLOCK) {
                        found = true;
                     }

                     sz++;
                  }

                  sy++;
               }

               sx++;
            }

            if (found) {
               if (entity instanceof Mob _entity) {
                  _entity.getNavigation().moveTo(x + sx, y + sy, z + sz, 1.0);
               }
            } else if (!entity.level().isClientSide()) {
               entity.discard();
            }
         }

         entity.getPersistentData().putDouble("despawn_tick", entity.getPersistentData().getDouble("despawn_tick") + 1.0);
         if (entity.getPersistentData().getDouble("despawn_tick") == 200.0 && !entity.level().isClientSide()) {
            entity.discard();
         }
      }
   }
}
