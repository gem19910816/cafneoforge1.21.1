package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.GoreEditionMod;
import net.mcreator.gore.init.GoreEditionModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent.RightClickBlock;

@EventBusSubscriber
public class MusicDiscAshesRickClickedOnJukeboxProcedure {
   @SubscribeEvent
   public static void onRightClickBlock(RightClickBlock event) {
      if (event.getHand() == event.getEntity().getUsedItemHand()) {
         execute(event, event.getLevel(), (double)event.getPos().getX(), (double)event.getPos().getY(), (double)event.getPos().getZ(), event.getEntity());
      }
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      execute(null, world, x, y, z, entity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null
         && world.getBlockState(BlockPos.containing(x, y, z)).getBlock() == Blocks.JUKEBOX
         && (world instanceof Level _lvl ? _lvl.dimension() : (world instanceof WorldGenLevel _wgl ? _wgl.getLevel().dimension() : Level.OVERWORLD))
            == Level.OVERWORLD) {
         if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == GoreEditionModItems.MUSIC_DISC_THE_XASH.get()
            && !(new Object() {
               public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
                  BlockEntity blockEntity = world.getBlockEntity(pos);
                  return blockEntity != null ? blockEntity.getPersistentData().getBoolean(tag) : false;
               }
            }).getValue(world, BlockPos.containing(x, y, z), "ashed")
            && Math.random() < 0.3) {
            GoreEditionMod.queueServerWork(1, () -> {
               MusicDiscAshedRightclickedOnBlockProcedure.execute(world, x, y, z, entity);
               if (!world.isClientSide()) {
                  BlockPos _bpx = BlockPos.containing(x, y, z);
                  BlockEntity _blockEntityx = world.getBlockEntity(_bpx);
                  BlockState _bsx = world.getBlockState(_bpx);
                  if (_blockEntityx != null) {
                     _blockEntityx.getPersistentData().putBoolean("ashed", true);
                  }

                  if (world instanceof Level _levelx) {
                     _levelx.sendBlockUpdated(_bpx, _bsx, _bsx, 3);
                  }
               }
            });
         }

         if ((new Object() {
            public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
               BlockEntity blockEntity = world.getBlockEntity(pos);
               return blockEntity != null ? blockEntity.getPersistentData().getBoolean(tag) : false;
            }
         }).getValue(world, BlockPos.containing(x, y, z), "ashed")) {
            if (!world.isClientSide()) {
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

            DimensionalGapToAshesProcedureProcedure.execute(world, x, y, z);
         }
      }
   }
}
