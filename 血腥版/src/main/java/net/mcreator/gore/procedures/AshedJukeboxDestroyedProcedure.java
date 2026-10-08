package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent.BreakEvent;

@EventBusSubscriber
public class AshedJukeboxDestroyedProcedure {
   @SubscribeEvent
   public static void onBlockBreak(BreakEvent event) {
      execute(event, event.getLevel(), (double)event.getPos().getX(), (double)event.getPos().getY(), (double)event.getPos().getZ());
   }

   public static void execute(LevelAccessor world, double x, double y, double z) {
      execute(null, world, x, y, z);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z) {
      if (world.getBlockState(BlockPos.containing(x, y, z)).getBlock() == Blocks.JUKEBOX && (new Object() {
         public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            return blockEntity != null ? blockEntity.getPersistentData().getBoolean(tag) : false;
         }
      }).getValue(world, BlockPos.containing(x, y, z), "ashed") && !world.isClientSide()) {
         BlockPos _bp = BlockPos.containing(x, y, z);
         BlockEntity _blockEntity = world.getBlockEntity(_bp);
         BlockState _bs = world.getBlockState(_bp);
         if (_blockEntity != null) {
            _blockEntity.getPersistentData().putBoolean("ashed", false);
         }

         if (world instanceof Level _level) {
            _level.sendBlockUpdated(_bp, _bs, _bs, 3);
         }
      }
   }
}
