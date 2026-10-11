package net.mcreator.dyairdrop.procedures;

import io.netty.buffer.Unpooled;
import net.mcreator.dyairdrop.compat.PasswordUtil;
import net.mcreator.dyairdrop.world.inventory.PannelREMenu;
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

/** Opens the English-key panel for locked airdrop crates. */
public class Safeopen2Procedure {
   private Safeopen2Procedure() {
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (world == null || world.isClientSide() || !(entity instanceof ServerPlayer player)) {
         return;
      }
      BlockPos pos = BlockPos.containing(x, y, z);
      String valid = PasswordUtil.blockString(world, pos, "valid");
      if (!valid.isEmpty() && !valid.equals(player.getDisplayName().getString()) && !"shutdown".equals(valid)) {
         player.displayClientMessage(Component.translatable("message.currentlybeingused "), true);
         return;
      }

      String key = PasswordUtil.ensureLetterKey(world, pos);
      PasswordUtil.setBlockString(world, pos, "valid", player.getDisplayName().getString());
      if (player.gameMode.getGameModeForPlayer().isCreative()) {
         player.displayClientMessage(Component.literal(Component.translatable("message.creativepassword").getString() + key), false);
      }

      player.openMenu(new MenuProvider() {
         @Override
         public Component getDisplayName() {
            return Component.literal("PannelRE");
         }

         @Override
         public AbstractContainerMenu createMenu(int id, Inventory inventory, Player menuPlayer) {
            return new PannelREMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(pos));
         }
      }, pos);
   }
}
