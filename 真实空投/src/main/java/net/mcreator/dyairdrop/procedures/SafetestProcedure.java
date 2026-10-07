package net.mcreator.dyairdrop.procedures;

import net.gem19910816.dyairdrop.core.Nbt;

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

public class SafetestProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (Nbt.getString(world, BlockPos.containing(x, y, z), "valid").length() >= 1) {
            if (!entity.getDisplayName().getString().equals(Nbt.getString(world, BlockPos.containing(x, y, z), "valid"))) {
               if (entity instanceof Player _player && !_player.level().isClientSide()) {
                  _player.displayClientMessage(Component.literal(Component.translatable("message.currentlybeingused ").getString()), true);
               }
            } else if ("shutdown".equals(Nbt.getString(world, BlockPos.containing(x, y, z), "valid"))) {
               if (entity instanceof Player _player && !_player.level().isClientSide()) {
                  _player.displayClientMessage(Component.literal(Component.translatable("message.currentlybeingused ").getString()), true);
               }
            } else if (entity instanceof ServerPlayer _ent) {
               final BlockPos _bpos = BlockPos.containing(x, y, z);
               _ent.openMenu(new MenuProvider() {
                  public Component getDisplayName() {
                     return Component.literal("Pannel");
                  }

                  public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                     return new PannelMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
                  }
               }, _buf -> _buf.writeBlockPos(_bpos));
            }
         } else if (entity instanceof ServerPlayer _ent) {
            final BlockPos _bpos = BlockPos.containing(x, y, z);
            _ent.openMenu(new MenuProvider() {
               public Component getDisplayName() {
                  return Component.literal("Pannel");
               }

               public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                  return new PannelMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
               }
            }, _buf -> _buf.writeBlockPos(_bpos));
         }
      }
   }
}
