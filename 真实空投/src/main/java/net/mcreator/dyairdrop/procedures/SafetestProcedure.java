package net.mcreator.dyairdrop.procedures;

import io.netty.buffer.Unpooled;
import net.mcreator.dyairdrop.world.inventory.PannelMenu;
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

public class SafetestProcedure {
   public SafetestProcedure() {
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if ((new Object() {
            public String getValue(LevelAccessor world, BlockPos pos, String tag) {
               BlockEntity blockEntity = world.getBlockEntity(pos);
               return blockEntity != null ? blockEntity.getPersistentData().getString(tag) : "";
            }
         }).getValue(world, BlockPos.containing(x, y, z), "valid").length() >= 1) {
            if (!entity.getDisplayName().getString().equals((new Object() {
               public String getValue(LevelAccessor world, BlockPos pos, String tag) {
                  BlockEntity blockEntity = world.getBlockEntity(pos);
                  return blockEntity != null ? blockEntity.getPersistentData().getString(tag) : "";
               }
            }).getValue(world, BlockPos.containing(x, y, z), "valid"))) {
               if (entity instanceof Player _player && !_player.level().isClientSide()) {
                  _player.displayClientMessage(Component.literal(Component.translatable("message.currentlybeingused ").getString()), true);
               }
            } else if ("shutdown".equals((new Object() {
               public String getValue(LevelAccessor world, BlockPos pos, String tag) {
                  BlockEntity blockEntity = world.getBlockEntity(pos);
                  return blockEntity != null ? blockEntity.getPersistentData().getString(tag) : "";
               }
            }).getValue(world, BlockPos.containing(x, y, z), "valid"))) {
               if (entity instanceof Player _player && !_player.level().isClientSide()) {
                  _player.displayClientMessage(Component.literal(Component.translatable("message.currentlybeingused ").getString()), true);
               }
            } else if (entity instanceof ServerPlayer _ent) {
               final BlockPos _bpos = BlockPos.containing(x, y, z);
               _ent.openMenu( new MenuProvider() {
                  public Component getDisplayName() {
                     return Component.literal("Pannel");
                  }

                  public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                     return new PannelMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
                  }
               }, _bpos);
            }
         } else if (entity instanceof ServerPlayer _ent) {
            final BlockPos _bpos = BlockPos.containing(x, y, z);
            _ent.openMenu( new MenuProvider() {
               public Component getDisplayName() {
                  return Component.literal("Pannel");
               }

               public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                  return new PannelMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
               }
            }, _bpos);
         }
      }
   }
}
