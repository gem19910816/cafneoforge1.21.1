package net.mcreator.survivalinstinct.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.level.block.Blocks;

public class HomemadeMineExploteProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
      if (world instanceof Level _level && !_level.isClientSide()) {
         _level.explode(null, x, y, z, 4.0F, ExplosionInteraction.TNT);
      }
   }
}
