package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.init.GoreEditionModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent.EntityPlaceEvent;

@EventBusSubscriber
public class SpawnAshedWitherIfAltarHasMakedProcedure {
   @SubscribeEvent
   public static void onBlockPlace(EntityPlaceEvent event) {
      execute(event, event.getLevel(), (double)event.getPos().getX(), (double)event.getPos().getY(), (double)event.getPos().getZ());
   }

   public static void execute(LevelAccessor world, double x, double y, double z) {
      execute(null, world, x, y, z);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z) {
      if (world.getBlockState(BlockPos.containing(x - 1.0, y - 1.0, z)).getBlock() == GoreEditionModBlocks.ASH_SAND.get()
         && world.getBlockState(BlockPos.containing(x, y - 1.0, z)).getBlock() == GoreEditionModBlocks.ASH_SAND.get()
         && world.getBlockState(BlockPos.containing(x + 1.0, y - 1.0, z)).getBlock() == GoreEditionModBlocks.ASH_SAND.get()
         && world.getBlockState(BlockPos.containing(x, y - 2.0, z)).getBlock() == GoreEditionModBlocks.ASH_SAND.get()
         && world.getBlockState(BlockPos.containing(x - 1.0, y, z)).getBlock() == GoreEditionModBlocks.ASHED_UNKNOWN_SKULL.get()
         && world.getBlockState(BlockPos.containing(x, y, z)).getBlock() == GoreEditionModBlocks.ASHED_UNKNOWN_SKULL.get()
         && world.getBlockState(BlockPos.containing(x + 1.0, y, z)).getBlock() == GoreEditionModBlocks.ASHED_UNKNOWN_SKULL.get()
         && world.getBlockState(BlockPos.containing(x + 1.0, y - 2.0, z)).getBlock() instanceof SimpleWaterloggedBlock
         && world.getBlockState(BlockPos.containing(x - 1.0, y - 2.0, z)).getBlock() instanceof SimpleWaterloggedBlock) {
         if (world instanceof ServerLevel _level) {
            Entity entityToSpawn = EntityType.LIGHTNING_BOLT.spawn(_level, BlockPos.containing(x, y - 2.0, z), MobSpawnType.MOB_SUMMONED);
            if (entityToSpawn != null) {
               entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
            }
         }

         world.destroyBlock(BlockPos.containing(x - 1.0, y - 1.0, z), false);
         world.destroyBlock(BlockPos.containing(x, y - 1.0, z), false);
         world.destroyBlock(BlockPos.containing(x + 1.0, y - 1.0, z), false);
         world.destroyBlock(BlockPos.containing(x, y - 2.0, z), false);
         world.destroyBlock(BlockPos.containing(x - 1.0, y, z), false);
         world.destroyBlock(BlockPos.containing(x, y, z), false);
         world.destroyBlock(BlockPos.containing(x + 1.0, y, z), false);
      }
   }
}
