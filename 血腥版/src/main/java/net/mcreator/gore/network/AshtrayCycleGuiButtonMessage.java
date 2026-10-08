package net.mcreator.gore.network;

import java.util.HashMap;
import net.mcreator.gore.procedures.ButtonClickedProcedure;
import net.mcreator.gore.world.inventory.AshtrayCycleGuiMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload.Type;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(
   bus = Bus.MOD
)
public class AshtrayCycleGuiButtonMessage implements CustomPacketPayload {
   public static final Type<AshtrayCycleGuiButtonMessage> TYPE = new Type(ResourceLocation.fromNamespaceAndPath("gore_edition", "ashtray_cycle_gui_button"));
   public static final StreamCodec<RegistryFriendlyByteBuf, AshtrayCycleGuiButtonMessage> STREAM_CODEC = StreamCodec.of((buf, msg) -> {
      buf.writeInt(msg.buttonID);
      buf.writeInt(msg.x);
      buf.writeInt(msg.y);
      buf.writeInt(msg.z);
   }, buf -> new AshtrayCycleGuiButtonMessage(buf.readInt(), buf.readInt(), buf.readInt(), buf.readInt()));
   private final int buttonID;
   private final int x;
   private final int y;
   private final int z;

   public AshtrayCycleGuiButtonMessage(int buttonID, int x, int y, int z) {
      this.buttonID = buttonID;
      this.x = x;
      this.y = y;
      this.z = z;
   }

   public Type<? extends CustomPacketPayload> type() {
      return TYPE;
   }

   public static void handler(AshtrayCycleGuiButtonMessage message, IPayloadContext context) {
      context.enqueueWork(() -> {
         Player entity = context.player();
         handleButtonAction(entity, message.buttonID, message.x, message.y, message.z);
      });
   }

   public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
      Level world = entity.level();
      HashMap guistate = AshtrayCycleGuiMenu.guistate;
      if (world.hasChunkAt(new BlockPos(x, y, z)) && buttonID == 0) {
         ButtonClickedProcedure.execute(world, (double)x, (double)y, (double)z, entity);
      }
   }

   @SubscribeEvent
   public static void registerMessage(RegisterPayloadHandlersEvent event) {
      PayloadRegistrar registrar = event.registrar("1");
      registrar.playToServer(TYPE, STREAM_CODEC, AshtrayCycleGuiButtonMessage::handler);
   }
}
