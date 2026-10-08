package net.mcreator.gore.network;

import net.mcreator.gore.procedures.XKeyPressedProcedure;
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
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(
   bus = Bus.MOD
)
public class ZKeyMessage implements CustomPacketPayload {
   public static final Type<ZKeyMessage> TYPE = new Type(ResourceLocation.fromNamespaceAndPath("gore_edition", "z_key"));
   public static final StreamCodec<RegistryFriendlyByteBuf, ZKeyMessage> STREAM_CODEC = StreamCodec.of((buf, msg) -> {
      buf.writeInt(msg.type);
      buf.writeInt(msg.pressedms);
   }, buf -> new ZKeyMessage(buf.readInt(), buf.readInt()));
   int type;
   int pressedms;

   public ZKeyMessage(int type, int pressedms) {
      this.type = type;
      this.pressedms = pressedms;
   }

   public Type<? extends CustomPacketPayload> type() {
      return TYPE;
   }

   public static void sendToServer(int type, int pressedms) {
      PacketDistributor.sendToServer(new ZKeyMessage(type, pressedms), new CustomPacketPayload[0]);
   }

   public static void handler(ZKeyMessage message, IPayloadContext context) {
      context.enqueueWork(() -> pressAction(context.player(), message.type, message.pressedms));
   }

   public static void pressAction(Player entity, int type, int pressedms) {
      if (entity != null) {
         Level world = entity.level();
         double x = entity.getX();
         double y = entity.getY();
         double z = entity.getZ();
         if (world.hasChunkAt(entity.blockPosition()) && type == 0) {
            XKeyPressedProcedure.execute(world, x, y, z, entity);
         }
      }
   }

   @SubscribeEvent
   public static void registerMessage(RegisterPayloadHandlersEvent event) {
      PayloadRegistrar registrar = event.registrar("1");
      registrar.playToServer(TYPE, STREAM_CODEC, ZKeyMessage::handler);
   }
}
