package net.mcreator.dyairdrop.procedures;

import java.util.HashMap;
import net.mcreator.dyairdrop.network.DyairdropModVariables;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class OpenProcedure {
   public OpenProcedure() {
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, HashMap guistate) {
      if (entity != null && guistate != null) {
         String _setval = "";
         DyairdropModVariables.with(entity, capability -> {
            capability.password = _setval;
            capability.syncPlayerVariables(entity);
         });
         if (guistate.get("text:password_panel") instanceof EditBox _tf) {
            _tf.setValue("");
         }

         if ((new Object() {
            public String getValue(LevelAccessor world, BlockPos pos, String tag) {
               BlockEntity blockEntity = world.getBlockEntity(pos);
               return blockEntity != null ? blockEntity.getPersistentData().getString(tag) : "";
            }
         }).getValue(world, BlockPos.containing(x, y, z), "valid").isEmpty() && !world.isClientSide()) {
            BlockPos _bp = BlockPos.containing(x, y, z);
            BlockEntity _blockEntity = world.getBlockEntity(_bp);
            BlockState _bs = world.getBlockState(_bp);
            if (_blockEntity != null) {
               _blockEntity.getPersistentData().putString("valid", entity.getDisplayName().getString());
            }

            if (world instanceof Level _level) {
               _level.sendBlockUpdated(_bp, _bs, _bs, 3);
            }
         }
      }
   }
}
