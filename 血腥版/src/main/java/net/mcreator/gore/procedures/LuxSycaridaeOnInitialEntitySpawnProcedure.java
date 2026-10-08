package net.mcreator.gore.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;

public class LuxSycaridaeOnInitialEntitySpawnProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null && world.getBlockState(BlockPos.containing(x, y, z)).getBlock() == Blocks.AIR) {
         entity.teleportTo(x, y, z);
         if (entity instanceof ServerPlayer _serverPlayer) {
            _serverPlayer.connection.teleport(x, y, z, entity.getYRot(), entity.getXRot());
         }
      }
   }
}
