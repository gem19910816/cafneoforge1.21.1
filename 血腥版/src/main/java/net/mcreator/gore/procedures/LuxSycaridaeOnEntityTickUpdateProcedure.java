package net.mcreator.gore.procedures;

import net.mcreator.gore.init.GoreEditionModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;

public class LuxSycaridaeOnEntityTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         SycarideLookProcedure.execute(world, x, y, z, entity);
         if (world.getBlockState(BlockPos.containing(x, y - 1.0, z)).getBlock() == GoreEditionModBlocks.EXARRACK.get()
            && entity.getPersistentData().getDouble("ready") != 1.0) {
            world.setBlock(BlockPos.containing(x, y - 1.0, z), Blocks.AIR.defaultBlockState(), 3);
            entity.teleportTo(Math.floor(x) + 0.5, Math.floor(y) - 1.0, Math.floor(z) + 0.5);
            if (entity instanceof ServerPlayer _serverPlayer) {
               _serverPlayer.connection.teleport(Math.floor(x) + 0.5, Math.floor(y) - 1.0, Math.floor(z) + 0.5, entity.getYRot(), entity.getXRot());
            }

            entity.getPersistentData().putDouble("ready", 1.0);
         }
      }
   }
}
