package net.mcreator.gore.procedures;

import io.netty.buffer.Unpooled;
import net.mcreator.gore.world.inventory.AshtrayCycleGuiMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;

public class DustLampOnBlockRightClickedProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null && !(new Object() {
         public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            return blockEntity != null ? blockEntity.getPersistentData().getBoolean(tag) : false;
         }
      }).getValue(world, BlockPos.containing(x, y, z), "fully_charged") && entity instanceof ServerPlayer _ent) {
         final BlockPos _bpos = BlockPos.containing(x, y, z);
         _ent.openMenu(new MenuProvider() {
            public Component getDisplayName() {
               return Component.literal("AshtrayCycleGui");
            }

            public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
               return new AshtrayCycleGuiMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
            }
         }, _bpos);
      }
   }
}
