package net.mcreator.dyairdrop.network;

import net.mcreator.dyairdrop.procedures.ChecknewliteProcedure;
import net.mcreator.dyairdrop.world.inventory.PannelRE2Menu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class PannelRE2ButtonMessage implements CustomPacketPayload {
   private final int buttonID;
   private final int x;
   private final int y;
   private final int z;

   public static final CustomPacketPayload.Type<PannelRE2ButtonMessage> TYPE = new CustomPacketPayload.Type<>(
      ResourceLocation.fromNamespaceAndPath("dyairdrop", "pannel_re2_button"));
   public static final StreamCodec<RegistryFriendlyByteBuf, PannelRE2ButtonMessage> STREAM_CODEC = StreamCodec.of(
      (buf, message) -> {
         buf.writeInt(message.buttonID);
         buf.writeInt(message.x);
         buf.writeInt(message.y);
         buf.writeInt(message.z);
      },
      buf -> new PannelRE2ButtonMessage(buf.readInt(), buf.readInt(), buf.readInt(), buf.readInt()));

   public PannelRE2ButtonMessage(int buttonID, int x, int y, int z) {
      this.buttonID = buttonID;
      this.x = x;
      this.y = y;
      this.z = z;
   }

   public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
      return TYPE;
   }

   public static void handle(PannelRE2ButtonMessage message, IPayloadContext context) {
      context.enqueueWork(() -> {
         if (context.player() instanceof ServerPlayer player) {
            handleButtonAction(player, message.buttonID, new BlockPos(message.x, message.y, message.z));
         }
      });
   }

   private static void handleButtonAction(ServerPlayer player, int buttonID, BlockPos pos) {
      if (!(player.containerMenu instanceof PannelRE2Menu menu)
         || menu.x != pos.getX() || menu.y != pos.getY() || menu.z != pos.getZ()
         || !player.level().hasChunkAt(pos)) {
         return;
      }
      if (buttonID >= 0 && buttonID < 6) {
         PannelREButtonMessage.pressLetter(player, pos, (char)('a' + buttonID));
      } else if (buttonID == 6) {
         ChecknewliteProcedure.execute(player.level(), pos.getX(), pos.getY(), pos.getZ(), player);
      }
   }
}
