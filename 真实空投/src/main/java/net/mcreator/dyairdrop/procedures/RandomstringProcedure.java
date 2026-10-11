package net.mcreator.dyairdrop.procedures;

import io.netty.buffer.Unpooled;
import net.mcreator.dyairdrop.compat.PasswordUtil;
import net.mcreator.dyairdrop.world.inventory.AirdropGUIMenu;
import net.mcreator.dyairdrop.world.inventory.PannelRE2Menu;
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

/** Opens the English-key panel for the lite safe. */
public class RandomstringProcedure {
   private RandomstringProcedure() {
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (world == null || world.isClientSide() || !(entity instanceof ServerPlayer player)) {
         return;
      }
      BlockPos pos = BlockPos.containing(x, y, z);
      String key = PasswordUtil.ensureLetterKey(world, pos);
      String valid = PasswordUtil.blockString(world, pos, "valid");
      if (!valid.isEmpty() && !valid.equals(player.getDisplayName().getString()) && !"shutdown".equals(valid)) {
         player.displayClientMessage(Component.translatable("message.currentlybeingused "), true);
         return;
      }
      if (!readOpen(world, pos)) {
         PasswordUtil.setBlockString(world, pos, "valid", player.getDisplayName().getString());
      }
      if (player.gameMode.getGameModeForPlayer().isCreative()) {
         player.displayClientMessage(Component.literal(Component.translatable("message.creativepassword").getString() + key), false);
      }

      MenuProvider provider = Boolean.TRUE.equals(readOpen(world, pos))
         ? new MenuProvider() {
            public Component getDisplayName() {
               return Component.literal("AirdropGUI");
            }

            public AbstractContainerMenu createMenu(int id, Inventory inventory, Player menuPlayer) {
               return new AirdropGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(pos));
            }
         }
         : new MenuProvider() {
            public Component getDisplayName() {
               return Component.literal("PannelRE2");
            }

            public AbstractContainerMenu createMenu(int id, Inventory inventory, Player menuPlayer) {
               return new PannelRE2Menu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(pos));
            }
         };
      player.openMenu(provider, pos);
   }

   private static Boolean readOpen(LevelAccessor world, BlockPos pos) {
      var blockEntity = world.getBlockEntity(pos);
      return blockEntity != null && blockEntity.getPersistentData().getBoolean("isopen");
   }
}
