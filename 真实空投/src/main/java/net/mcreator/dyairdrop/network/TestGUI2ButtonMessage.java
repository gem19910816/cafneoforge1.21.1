package net.mcreator.dyairdrop.network;

import java.util.HashMap;
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
import net.mcreator.dyairdrop.world.inventory.TestGUI2Menu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class TestGUI2ButtonMessage implements CustomPacketPayload {
   private final int buttonID;
   private final int x;
   private final int y;
   private final int z;
   private final String text;
   public static final CustomPacketPayload.Type<TestGUI2ButtonMessage> TYPE = new CustomPacketPayload.Type<>(
      ResourceLocation.fromNamespaceAndPath("dyairdrop", "test_gui2_button")
   );
   public static final StreamCodec<RegistryFriendlyByteBuf, TestGUI2ButtonMessage> STREAM_CODEC = StreamCodec.of(
      (buf, message) -> {
         buf.writeInt(message.buttonID);
         buf.writeInt(message.x);
         buf.writeInt(message.y);
         buf.writeInt(message.z);
         buf.writeUtf(message.text);
      },
      buf -> new TestGUI2ButtonMessage(buf.readInt(), buf.readInt(), buf.readInt(), buf.readInt(), buf.readUtf())
   );

   public TestGUI2ButtonMessage(int buttonID, int x, int y, int z) {
      this(buttonID, x, y, z, "");
   }

   public TestGUI2ButtonMessage(int buttonID, int x, int y, int z, String text) {
      this.buttonID = buttonID;
      this.x = x;
      this.y = y;
      this.z = z;
      this.text = text;
   }

   public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
      return TYPE;
   }

   public static void handle(TestGUI2ButtonMessage message, IPayloadContext context) {
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
      HashMap guistate = TestGUI2Menu.guistate;
      if (world.hasChunkAt(new BlockPos(x, y, z))) {
         if (buttonID == 0) {
            Button2Procedure.execute(entity, guistate);
         }

         if (buttonID == 1) {
            Button1Procedure.execute(entity, guistate);
         }

         if (buttonID == 2) {
            Button3Procedure.execute(entity, guistate);
         }

         if (buttonID == 3) {
            Button4Procedure.execute(entity, guistate);
         }

         if (buttonID == 4) {
            Button5Procedure.execute(entity, guistate);
         }

         if (buttonID == 5) {
            Button6Procedure.execute(entity, guistate);
         }

         if (buttonID == 6) {
            Button7Procedure.execute(entity, guistate);
         }

         if (buttonID == 7) {
            Button8Procedure.execute(entity, guistate);
         }

         if (buttonID == 8) {
            Button9Procedure.execute(entity, guistate);
         }

         if (buttonID == 9) {
            ButtondelateProcedure.execute(entity, guistate);
         }

         if (buttonID == 10) {
            Button0Procedure.execute(entity, guistate);
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

}
