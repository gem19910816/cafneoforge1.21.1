package net.mcreator.dyairdrop.procedures;

import net.gem19910816.dyairdrop.core.Nbt;

import io.netty.buffer.Unpooled;
import net.mcreator.dyairdrop.DyairdropMod;
import net.mcreator.dyairdrop.world.inventory.AirdropGUIMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class AirdropweaponanimeProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
      if (entity != null) {
         if ((blockstate.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _getip1 ? (Integer)blockstate.getValue(_getip1) : -1) == 0) {
            if (!Nbt.getBoolean(world, BlockPos.containing(x, y, z), "canopen")) {
               if (!world.isClientSide()) {
                  BlockPos _bp = BlockPos.containing(x, y, z);
                  BlockEntity _blockEntity = world.getBlockEntity(_bp);
                  BlockState _bs = world.getBlockState(_bp);
                  if (_blockEntity != null) {
                     _blockEntity.getPersistentData().putBoolean("canopen", true);
                  }

                  if (world instanceof Level _level) {
                     _level.sendBlockUpdated(_bp, _bs, _bs, 3);
                  }
               }

               int _value = 1;
               BlockPos _pos = BlockPos.containing(x, y, z);
               BlockState _bs = world.getBlockState(_pos);
               if (_bs.getBlock().getStateDefinition().getProperty("animation") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value)) {
                  world.setBlock(_pos, (BlockState)_bs.setValue(_integerProp, _value), 3);
               }

               DyairdropMod.queueServerWork(
                  16,
                  () -> {
                     int _valuex = 1;
                     BlockPos _posx = BlockPos.containing(x, y, z);
                     BlockState _bsx = world.getBlockState(_posx);
                     if (_bsx.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerPropxx
                        && _integerPropxx.getPossibleValues().contains(_valuex)) {
                        world.setBlock(_posx, (BlockState)_bsx.setValue(_integerPropxx, _valuex), 3);
                     }

                     _valuex = 0;
                     _posx = BlockPos.containing(x, y, z);
                     _bsx = world.getBlockState(_posx);
                     if (_bsx.getBlock().getStateDefinition().getProperty("animation") instanceof IntegerProperty _integerPropx && _integerPropx.getPossibleValues().contains(_valuex)
                        )
                      {
                        world.setBlock(_posx, (BlockState)_bsx.setValue(_integerPropx, _valuex), 3);
                     }
                  }
               );
            }
         } else if (entity instanceof ServerPlayer _ent) {
            final BlockPos _bpos = BlockPos.containing(x, y, z);
            _ent.openMenu(new MenuProvider() {
               public Component getDisplayName() {
                  return Component.literal("AirdropGUI");
               }

               public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                  return new AirdropGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
               }
            }, _buf -> _buf.writeBlockPos(_bpos));
         }
      }
   }
}
