package me.xjqsh.lesraisinsarmor.network.s2c;

import me.xjqsh.lesraisinsarmor.LesRaisinsArmor;
import me.xjqsh.lesraisinsarmor.resource.ArmorDataManager;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.HashMap;
import java.util.Map;

public record ArmorDataMessage(Map<ResourceLocation, String> networkCache) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<ArmorDataMessage> TYPE = new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(LesRaisinsArmor.MOD_ID, "armor_data"));

    public static final StreamCodec<RegistryFriendlyByteBuf, ArmorDataMessage> STREAM_CODEC = StreamCodec.ofMember(
            (ArmorDataMessage msg, RegistryFriendlyByteBuf buf) -> {
                buf.writeVarInt(msg.networkCache.size());
                msg.networkCache.forEach((rl, s) -> {
                    buf.writeResourceLocation(rl);
                    buf.writeUtf(s);
                });
            },
            buf -> {
                int size = buf.readVarInt();
                Map<ResourceLocation, String> map = new HashMap<>(size);
                for (int i = 0; i < size; i++) {
                    map.put(buf.readResourceLocation(), buf.readUtf());
                }
                return new ArmorDataMessage(map);
            }
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(ArmorDataMessage message, IPayloadContext context) {
        if (context.flow().isClientbound()) {
            context.enqueueWork(() -> onReceive(message));
        }
    }

    @OnlyIn(Dist.CLIENT)
    private static void onReceive(ArmorDataMessage message) {
        ArmorDataManager.fromNetwork(message.networkCache);
    }
}
