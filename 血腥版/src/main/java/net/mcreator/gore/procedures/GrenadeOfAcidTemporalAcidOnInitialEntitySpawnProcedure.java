package net.mcreator.gore.procedures;

import net.mcreator.gore.init.GoreEditionModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;

public class GrenadeOfAcidTemporalAcidOnInitialEntitySpawnProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      world.setBlock(BlockPos.containing(x, y - 1.0, z), ((Block)GoreEditionModBlocks.ACID.get()).defaultBlockState(), 3);
   }
}
