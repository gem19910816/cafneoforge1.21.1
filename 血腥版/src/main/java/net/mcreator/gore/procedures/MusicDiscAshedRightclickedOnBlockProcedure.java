package net.mcreator.gore.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;

public class MusicDiscAshedRightclickedOnBlockProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (world.getBlockState(BlockPos.containing(x, y + 1.0, z)).getBlock() == Blocks.AIR) {
            world.setBlock(BlockPos.containing(x, y + 1.0, z), Blocks.FIRE.defaultBlockState(), 3);
         } else {
            entity.igniteForSeconds(15.0F);
         }
      }
   }
}
