package net.mcreator.gore.network;

import java.util.UUID;
import net.mcreator.gore.procedures.ZombieAppearanceTransfer;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;

@EventBusSubscriber(modid = "gore_edition", bus = EventBusSubscriber.Bus.MOD)
public record ZombieAppearanceMessage(int entityId, UUID sourceId, String texture) implements CustomPacketPayload {
   public static final Type<ZombieAppearanceMessage> TYPE =
      new Type<>(ResourceLocation.fromNamespaceAndPath("gore_edition", "zombie_appearance"));
   public static final StreamCodec<RegistryFriendlyByteBuf, ZombieAppearanceMessage> STREAM_CODEC =
      StreamCodec.of((buffer, message) -> {
         buffer.writeInt(message.entityId);
         buffer.writeUUID(message.sourceId);
         buffer.writeUtf(message.texture, 256);
      }, buffer -> new ZombieAppearanceMessage(buffer.readInt(), buffer.readUUID(), buffer.readUtf(256)));

   @Override
   public Type<? extends CustomPacketPayload> type() { return TYPE; }

   @SubscribeEvent
   public static void register(RegisterPayloadHandlersEvent event) {
      event.registrar("1").playToServer(TYPE, STREAM_CODEC, ZombieAppearanceMessage::handle);
   }

   private static void handle(ZombieAppearanceMessage message, IPayloadContext context) {
      context.enqueueWork(() -> {
         Player player = context.player();
         if (!(player.level() instanceof ServerLevel level)) return;
         Entity entity = level.getEntity(message.entityId);
         ResourceLocation texture = ResourceLocation.tryParse(message.texture);
         if (entity == null || !ZombieAppearanceTransfer.isZombie(entity)
            || player.distanceToSqr(entity) > 64.0D * 64.0D || texture == null
            || !isEntityTexture(texture.getPath()) || !texture.getPath().endsWith(".png")
            || texture.getPath().contains("..")) return;
         UUID expected = entity.getPersistentData().hasUUID(ZombieAppearanceTransfer.SOURCE_KEY)
            ? entity.getPersistentData().getUUID(ZombieAppearanceTransfer.SOURCE_KEY) : entity.getUUID();
         String stored = entity.getPersistentData().getString(ZombieAppearanceTransfer.TEXTURE_KEY);
         if (!expected.equals(message.sourceId)
            || (!stored.isEmpty() && !stored.equals("minecraft:textures/entity/zombie/zombie.png")
               && !stored.equals("minecraft:textures/entity/zombie/husk.png"))) return;
         entity.getPersistentData().putString(ZombieAppearanceTransfer.TEXTURE_KEY, message.texture);
         ZombieAppearanceTransfer.setTexture(entity, message.texture);
      });
   }

   private static boolean isEntityTexture(String path) {
      return path.startsWith("textures/") || path.startsWith("optifine/random/entity/")
         || path.startsWith("optifine/mob/") || path.startsWith("mcpatcher/mob/");
   }
}
