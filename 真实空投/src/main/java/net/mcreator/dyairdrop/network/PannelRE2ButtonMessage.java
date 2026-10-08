package net.mcreator.dyairdrop.network;

import java.util.HashMap;
import net.mcreator.dyairdrop.procedures.Buttonre1Procedure;
import net.mcreator.dyairdrop.procedures.Buttonre2Procedure;
import net.mcreator.dyairdrop.procedures.Buttonre3Procedure;
import net.mcreator.dyairdrop.procedures.Buttonre4Procedure;
import net.mcreator.dyairdrop.procedures.Buttonre5Procedure;
import net.mcreator.dyairdrop.procedures.Buttonre6Procedure;
import net.mcreator.dyairdrop.procedures.ChecknewliteProcedure;
import net.mcreator.dyairdrop.world.inventory.PannelRE2Menu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class PannelRE2ButtonMessage implements CustomPacketPayload {
   private final int buttonID;
   private final int x;
   private final int y;
   private final int z;
   private final String text;
   public static final CustomPacketPayload.Type<PannelRE2ButtonMessage> TYPE = new CustomPacketPayload.Type<>(
      ResourceLocation.fromNamespaceAndPath("dyairdrop", "pannel_re2_button")
   );
   public static final StreamCodec<RegistryFriendlyByteBuf, PannelRE2ButtonMessage> STREAM_CODEC = StreamCodec.of(
      (buf, message) -> {
         buf.writeInt(message.buttonID);
         buf.writeInt(message.x);
         buf.writeInt(message.y);
         buf.writeInt(message.z);
         buf.writeUtf(message.text);
      },
      buf -> new PannelRE2ButtonMessage(buf.readInt(), buf.readInt(), buf.readInt(), buf.readInt(), buf.readUtf())
   );

   public PannelRE2ButtonMessage(int buttonID, int x, int y, int z) {
      this(buttonID, x, y, z, "");
   }

   public PannelRE2ButtonMessage(int buttonID, int x, int y, int z, String text) {
      this.buttonID = buttonID;
      this.x = x;
      this.y = y;
      this.z = z;
      this.text = text;
   }

   public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
      return TYPE;
   }

   public static void handle(PannelRE2ButtonMessage message, IPayloadContext context) {
      PanelText.set(message.text);
      context.enqueueWork(() -> {
         Player entity = context.player();
         if (entity != null) {
            handleButtonAction(entity, message.buttonID, message.x, message.y, message.z);
         }
      });
   }

   public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
      Level world = entity.level();
      HashMap guistate = PannelRE2Menu.guistate;
      if (world.hasChunkAt(new BlockPos(x, y, z))) {
         if (buttonID == 0) {
            Buttonre1Procedure.execute(world, (double)x, (double)y, (double)z, entity);
         }

         if (buttonID == 1) {
            Buttonre2Procedure.execute(world, (double)x, (double)y, (double)z, entity);
         }

         if (buttonID == 2) {
            Buttonre3Procedure.execute(world, (double)x, (double)y, (double)z, entity);
         }

         if (buttonID == 3) {
            Buttonre4Procedure.execute(world, (double)x, (double)y, (double)z, entity);
         }

         if (buttonID == 4) {
            Buttonre5Procedure.execute(world, (double)x, (double)y, (double)z, entity);
         }

         if (buttonID == 5) {
            Buttonre6Procedure.execute(world, (double)x, (double)y, (double)z, entity);
         }

         if (buttonID == 6) {
            ChecknewliteProcedure.execute(world, (double)x, (double)y, (double)z, entity);
         }
      }
   }

}
