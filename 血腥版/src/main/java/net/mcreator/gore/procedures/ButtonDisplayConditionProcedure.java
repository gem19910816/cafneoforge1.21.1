package net.mcreator.gore.procedures;

import java.util.concurrent.atomic.AtomicInteger;
import net.mcreator.gore.configuration.GeSpiralsConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.capabilities.Capabilities.ItemHandler;
import net.neoforged.neoforge.items.IItemHandler;

public class ButtonDisplayConditionProcedure {
   public static boolean execute(LevelAccessor world, double x, double y, double z) {
      return (double)(new Object() {
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
      }).getValue(world, BlockPos.containing(x, y, z), "skull");
   }
}
