package net.mcreator.dyairdrop.network;

import net.mcreator.dyairdrop.compat.PasswordUtil;
import net.mcreator.dyairdrop.procedures.CheckProcedure;
import net.mcreator.dyairdrop.world.inventory.PannelREMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class PannelREButtonMessage implements CustomPacketPayload {
   private final int buttonID;
   private final int x;
   private final int y;
   private final int z;

   public static final CustomPacketPayload.Type<PannelREButtonMessage> TYPE = new CustomPacketPayload.Type<>(
      ResourceLocation.fromNamespaceAndPath("dyairdrop", "pannel_re_button"));
   public static final StreamCodec<RegistryFriendlyByteBuf, PannelREButtonMessage> STREAM_CODEC = StreamCodec.of(
      (buf, message) -> {
         buf.writeInt(message.buttonID);
         buf.writeInt(message.x);
         buf.writeInt(message.y);
         buf.writeInt(message.z);
      },
      buf -> new PannelREButtonMessage(buf.readInt(), buf.readInt(), buf.readInt(), buf.readInt()));

   public PannelREButtonMessage(int buttonID, int x, int y, int z) {
      this.buttonID = buttonID;
      this.x = x;
      this.y = y;
      this.z = z;
   }

   public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
      return TYPE;
   }

   public static void handle(PannelREButtonMessage message, IPayloadContext context) {
      context.enqueueWork(() -> {
         if (context.player() instanceof ServerPlayer player) {
            handleButtonAction(player, message.buttonID, new BlockPos(message.x, message.y, message.z));
         }
      });
   }

   private static void handleButtonAction(ServerPlayer player, int buttonID, BlockPos pos) {
      if (!(player.containerMenu instanceof PannelREMenu menu)
         || menu.x != pos.getX() || menu.y != pos.getY() || menu.z != pos.getZ()
         || !player.level().hasChunkAt(pos)) {
         return;
      }
      if (buttonID >= 0 && buttonID < 6) {
         pressLetter(player, pos, (char)('a' + buttonID));
      } else if (buttonID == 6) {
         CheckProcedure.execute(player.level(), pos.getX(), pos.getY(), pos.getZ(), player);
      }
   }

   static void pressLetter(ServerPlayer player, BlockPos pos, char letter) {
      String current = DyairdropModVariables.get(player).passwordre;
      String key = PasswordUtil.ensureLetterKey(player.level(), pos);
      if (current.length() >= 6 || current.chars().anyMatch(Character::isUpperCase) || current.contains("Z") || current.contains("Y")) {
         return;
      }
      int index = current.length();
      char expected = key.charAt(index);
      String next = current + (expected == letter ? letter : Character.toUpperCase(letter));
      DyairdropModVariables.with(player, variables -> {
         variables.passwordre = next;
         variables.syncPlayerVariables(player);
      });
   }
}
