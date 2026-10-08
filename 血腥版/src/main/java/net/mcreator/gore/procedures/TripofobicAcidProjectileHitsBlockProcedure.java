package net.mcreator.gore.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.level.block.Blocks;

public class TripofobicAcidProjectileHitsBlockProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      TripofobicAcidCollidesFXProcedure.execute(world, x, y, z);
      if (world.getBlockState(BlockPos.containing(x, y, z)).getBlock() != Blocks.BARRIER
         && world.getBlockState(BlockPos.containing(x, y, z)).getBlock() != Blocks.BEDROCK
         && Math.random() < 0.1
         && world instanceof Level _level
         && !_level.isClientSide()) {
         _level.explode(null, x, y, z, 2.0F, ExplosionInteraction.MOB);
      }
   }
}
