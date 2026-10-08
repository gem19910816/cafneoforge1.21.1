package net.mcreator.gore.procedures;

import java.util.concurrent.atomic.AtomicInteger;
import net.mcreator.gore.configuration.GeSpiralsConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities.ItemHandler;
import net.neoforged.neoforge.items.IItemHandler;

public class ButtonClickedProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null && (double)(new Object() {
         public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
            AtomicInteger _retval = new AtomicInteger(0);
            BlockEntity _ent = world.getBlockEntity(pos);
            if (_ent != null) {
               IItemHandler capability = world instanceof Level _lvl ? (IItemHandler)ItemHandler.BLOCK.getCapability(_lvl, pos, null, null, null) : null;
               if (capability != null) {
                  _retval.set(capability.getStackInSlot(slotid).getCount());
               }
            }

            return _retval.get();
         }
      }).getAmount(world, BlockPos.containing(x, y, z), 0) >= (Double)GeSpiralsConfiguration.REQUIRED_CHARGE.get() && (new Object() {
         public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            return blockEntity != null ? blockEntity.getPersistentData().getBoolean(tag) : false;
         }
      }).getValue(world, BlockPos.containing(x, y, z), "skull")) {
         if (entity instanceof Player _player) {
            _player.closeContainer();
         }

         if (!world.isClientSide()) {
            BlockPos _bp = BlockPos.containing(x, y, z);
            BlockEntity _blockEntity = world.getBlockEntity(_bp);
            BlockState _bs = world.getBlockState(_bp);
            if (_blockEntity != null) {
               _blockEntity.getPersistentData().putBoolean("fully_charged", true);
            }

            if (world instanceof Level _level) {
               _level.sendBlockUpdated(_bp, _bs, _bs, 3);
            }
         }
      }
   }
}
