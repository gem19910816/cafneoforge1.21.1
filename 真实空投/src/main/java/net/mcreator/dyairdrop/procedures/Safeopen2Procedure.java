package net.mcreator.dyairdrop.procedures;

import net.gem19910816.dyairdrop.core.GameModes;

import net.gem19910816.dyairdrop.core.Nbt;

import io.netty.buffer.Unpooled;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.mcreator.dyairdrop.world.inventory.PannelREMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class Safeopen2Procedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         String output = "";
         String input = "";
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
                     return Component.literal("PannelRE");
                  }

                  public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                     return new PannelREMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
                  }
               }, _buf -> _buf.writeBlockPos(_bpos));
            }
         } else if (entity instanceof ServerPlayer _ent) {
            final BlockPos _bpos = BlockPos.containing(x, y, z);
            _ent.openMenu(new MenuProvider() {
               public Component getDisplayName() {
                  return Component.literal("PannelRE");
               }

               public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                  return new PannelREMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
               }
            }, _buf -> _buf.writeBlockPos(_bpos));
         }

         if (Nbt.getString(world, BlockPos.containing(x, y, z), "pw").isEmpty()) {
            input = "abcdef";
            List<Character> charList = new ArrayList<>();

            for (char c : input.toCharArray()) {
               charList.add(c);
            }

            Collections.shuffle(charList);

            for (char c : charList) {
               output = output + c;
            }

            if (!world.isClientSide()) {
               BlockPos _bp = BlockPos.containing(x, y, z);
               BlockEntity _blockEntity = world.getBlockEntity(_bp);
               BlockState _bs = world.getBlockState(_bp);
               if (_blockEntity != null) {
                  _blockEntity.getPersistentData().putString("pw", output);
               }

               if (world instanceof Level _level) {
                  _level.sendBlockUpdated(_bp, _bs, _bs, 3);
               }
            }

            if (GameModes.isCreative(entity)
               && !world.isClientSide()
               && world.getServer() != null) {
               world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(Component.translatable("message.creativepassword").getString() + output), false);
            }
         }
      }
   }
}
