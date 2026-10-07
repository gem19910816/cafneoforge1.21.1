package net.gem19910816.dyairdrop.network.payload;

import net.gem19910816.dyairdrop.compat.map.MapMarkerService;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/**
 * 客户端 → 服务端：登录后声明本客户端是否装有 Xaero。
 *
 * <p>服务端只给声明过的玩家发 {@link MapMarkerPayload}，避免给没有地图模组的玩家白发包、
 * 也避免服务器在聊天里塞 Xaero 分享格式的兜底消息。
 *
 * @param hasXaero 是否装有 xaerominimap / xaeroworldmap
 */
public record XaeroPresencePayload(boolean hasXaero) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<XaeroPresencePayload> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("dyairdrop", "xaero_presence"));

    public static final StreamCodec<RegistryFriendlyByteBuf, XaeroPresencePayload> STREAM_CODEC = StreamCodec.ofMember(
            (msg, buf) -> buf.writeBoolean(msg.hasXaero()),
            buf -> new XaeroPresencePayload(buf.readBoolean()));

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(XaeroPresencePayload message, IPayloadContext context) {
        if (!context.flow().isServerbound()) {
            return;
        }
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player) {
                MapMarkerService.setXaeroPresence(player, message.hasXaero());
            }
        });
    }
}
