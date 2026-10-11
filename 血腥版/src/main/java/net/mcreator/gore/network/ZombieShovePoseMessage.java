package net.mcreator.gore.network;

import java.util.UUID;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/** Full pose timing also accompanies a marker when a player starts tracking mid-animation. */
@EventBusSubscriber(modid = "gore_edition", bus = EventBusSubscriber.Bus.MOD)
public record ZombieShovePoseMessage(UUID entityId, float bodyYaw, float direction,
   int duration, int remaining) implements CustomPacketPayload {
   public static final Type<ZombieShovePoseMessage> TYPE =
      new Type<>(ResourceLocation.fromNamespaceAndPath("gore_edition", "shove_pose"));
   public static final StreamCodec<RegistryFriendlyByteBuf, ZombieShovePoseMessage> STREAM_CODEC =
      StreamCodec.of((buffer, message) -> {
         buffer.writeUUID(message.entityId);
         buffer.writeFloat(message.bodyYaw);
         buffer.writeFloat(message.direction);
         buffer.writeInt(message.duration);
         buffer.writeInt(message.remaining);
      }, buffer -> new ZombieShovePoseMessage(buffer.readUUID(), buffer.readFloat(),
         buffer.readFloat(), buffer.readInt(), buffer.readInt()));

   @Override
   public Type<? extends CustomPacketPayload> type() { return TYPE; }

   @SubscribeEvent
   public static void register(RegisterPayloadHandlersEvent event) {
      event.registrar("1").playToClient(TYPE, STREAM_CODEC, ZombieShovePoseMessage::handle);
   }

   private static void handle(ZombieShovePoseMessage message, IPayloadContext context) {
      context.enqueueWork(() -> net.mcreator.gore.client.ZombieShovePoseClient.accept(message));
   }
}
