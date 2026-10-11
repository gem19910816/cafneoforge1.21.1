package net.mcreator.dyairdrop.network;

import java.util.HashMap;
import net.mcreator.dyairdrop.compat.PasswordUtil;
import net.mcreator.dyairdrop.procedures.Button0Procedure;
import net.mcreator.dyairdrop.procedures.Button1Procedure;
import net.mcreator.dyairdrop.procedures.Button2Procedure;
import net.mcreator.dyairdrop.procedures.Button3Procedure;
import net.mcreator.dyairdrop.procedures.Button4Procedure;
import net.mcreator.dyairdrop.procedures.Button5Procedure;
import net.mcreator.dyairdrop.procedures.Button6Procedure;
import net.mcreator.dyairdrop.procedures.Button7Procedure;
import net.mcreator.dyairdrop.procedures.Button8Procedure;
import net.mcreator.dyairdrop.procedures.Button9Procedure;
import net.mcreator.dyairdrop.procedures.ButtoncheckProcedure;
import net.mcreator.dyairdrop.procedures.ButtondelateProcedure;
import net.mcreator.dyairdrop.procedures.OpProcedure;
import net.mcreator.dyairdrop.procedures.SetpwProcedure;
import net.mcreator.dyairdrop.procedures.TestpwProcedure;
import net.mcreator.dyairdrop.world.inventory.PannelMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class PannelButtonMessage implements CustomPacketPayload {
   private final int buttonID;
   private final int x;
   private final int y;
   private final int z;
   private final String text;
   public static final CustomPacketPayload.Type<PannelButtonMessage> TYPE = new CustomPacketPayload.Type<>(
      ResourceLocation.fromNamespaceAndPath("dyairdrop", "pannel_button")
   );
   public static final StreamCodec<RegistryFriendlyByteBuf, PannelButtonMessage> STREAM_CODEC = StreamCodec.of(
      (buf, message) -> {
         buf.writeInt(message.buttonID);
         buf.writeInt(message.x);
         buf.writeInt(message.y);
         buf.writeInt(message.z);
         buf.writeUtf(message.text, 32);
      },
      buf -> new PannelButtonMessage(buf.readInt(), buf.readInt(), buf.readInt(), buf.readInt(), buf.readUtf())
   );

   public PannelButtonMessage(int buttonID, int x, int y, int z) {
      this(buttonID, x, y, z, "");
   }

   public PannelButtonMessage(int buttonID, int x, int y, int z, String text) {
      this.buttonID = buttonID;
      this.x = x;
      this.y = y;
      this.z = z;
      this.text = text;
   }

   public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
      return TYPE;
   }

   public static void handle(PannelButtonMessage message, IPayloadContext context) {
      context.enqueueWork(() -> {
         Player entity = context.player();
         if (entity instanceof ServerPlayer) {
            String input = PasswordUtil.normalizeDigits(message.text);
            PanelText.set(entity, input);
            // The old generated buttons appended to a server-side capability
            // while the text box lived only on the client. Copy the submitted
            // value first so typed input and keypad input use one source of truth.
            if (message.buttonID <= 14) {
               DyairdropModVariables.with(entity, variables -> {
                  variables.password = input;
                  variables.syncPlayerVariables(entity);
               });
            }
            handleButtonAction(entity, message.buttonID, message.x, message.y, message.z);
         }
      });
   }

   public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
      if (!(entity instanceof ServerPlayer) || entity.level().isClientSide()
         || !(entity.containerMenu instanceof PannelMenu menu)
         || menu.x != x || menu.y != y || menu.z != z) {
         return;
      }
      Level world = entity.level();
      HashMap guistate = PannelMenu.guistate;
      if (world.hasChunkAt(new BlockPos(x, y, z))) {
         String input = PasswordUtil.normalizeDigits(PanelText.get(entity));
         if (buttonID == 0) {
            setNumericInput(entity, input);
         }

         if (buttonID == 1) {
            setNumericInput(entity, input);
         }

         if (buttonID == 2) {
            setNumericInput(entity, input);
         }

         if (buttonID == 3) {
            setNumericInput(entity, input);
         }

         if (buttonID == 4) {
            setNumericInput(entity, input);
         }

         if (buttonID == 5) {
            setNumericInput(entity, input);
         }

         if (buttonID == 6) {
            setNumericInput(entity, input);
         }

         if (buttonID == 7) {
            setNumericInput(entity, input);
         }

         if (buttonID == 8) {
            setNumericInput(entity, input);
         }

         if (buttonID == 9) {
            setNumericInput(entity, "");
         }

         if (buttonID == 10) {
            setNumericInput(entity, input);
         }

         if (buttonID == 11) {
            ButtoncheckProcedure.execute(world, (double)x, (double)y, (double)z, entity, guistate);
         }

         if (buttonID == 12) {
            OpProcedure.execute(world, (double)x, (double)y, (double)z, entity, guistate);
         }

         if (buttonID == 13) {
            SetpwProcedure.execute(world, (double)x, (double)y, (double)z, entity, guistate);
         }

         if (buttonID == 14) {
            TestpwProcedure.execute(world, (double)x, (double)y, (double)z, entity);
         }
      }
   }

   private static void setNumericInput(Player entity, String input) {
      DyairdropModVariables.with(entity, variables -> {
         variables.password = input.length() > 6 ? input.substring(0, 6) : input;
         variables.showlight = 0.0;
         variables.pw = "";
         variables.syncPlayerVariables(entity);
      });
   }

}
