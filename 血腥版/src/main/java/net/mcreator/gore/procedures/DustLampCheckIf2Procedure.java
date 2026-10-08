package net.mcreator.gore.procedures;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import net.mcreator.gore.init.GoreEditionModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.capabilities.Capabilities.ItemHandler;
import net.neoforged.neoforge.items.IItemHandler;

public class DustLampCheckIf2Procedure {
   public static boolean execute(LevelAccessor world, double x, double y, double z) {
      return (new Object() {
         public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
            AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
            BlockEntity _ent = world.getBlockEntity(pos);
            if (_ent != null) {
               IItemHandler capability = world instanceof Level _lvl ? (IItemHandler)ItemHandler.BLOCK.getCapability(_lvl, pos, null, null, null) : null;
               if (capability != null) {
                  _retval.set(capability.getStackInSlot(slotid).copy());
               }
            }

            return _retval.get();
         }
      }).getItemStack(world, BlockPos.containing(x, y, z), 1).getItem() == ((Block)GoreEditionModBlocks.ASHED_UNKNOWN_SKULL.get()).asItem() && (new Object() {
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
      }).getAmount(world, BlockPos.containing(x, y, z), 1) > 0;
   }
}
