package net.gem19910816.dyairdrop.network.payload;

import net.gem19910816.dyairdrop.client.compat.MapMarkerClient;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/**
 * 服务端 → 客户端：新增 / 移除一个空投地图标记。
 *
 * <p>只发给「声明装有 Xaero」的玩家（见 {@link XaeroPresencePayload}），
 * 客户端收到后交给 {@link MapMarkerClient} 落到 Xaero 的临时路点上。
 *
 * @param action    {@link #ACTION_ADD} / {@link #ACTION_REMOVE}
 * @param dimension 维度 id 字符串（如 {@code minecraft:overworld}）
 * @param x         X 坐标（方块）
 * @param y         Y 坐标（方块）
 * @param z         Z 坐标（方块）
 * @param label     显示名
 * @param color     Xaero 颜色序号 0~15
 * @param id        标记 id
 */
public record MapMarkerPayload(byte action, String dimension, int x, int y, int z, String label, int color, int id)
        implements CustomPacketPayload {

    public static final byte ACTION_ADD = 0;
    public static final byte ACTION_REMOVE = 1;
    private static final int MAX_STRING = 128;

    public static final CustomPacketPayload.Type<MapMarkerPayload> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("dyairdrop", "map_marker"));

    public static final StreamCodec<RegistryFriendlyByteBuf, MapMarkerPayload> STREAM_CODEC = StreamCodec.ofMember(
            (msg, buf) -> {
                buf.writeByte(msg.action());
                buf.writeUtf(msg.dimension(), MAX_STRING);
                buf.writeInt(msg.x());
                buf.writeInt(msg.y());
                buf.writeInt(msg.z());
                buf.writeUtf(msg.label(), MAX_STRING);
                buf.writeByte(msg.color());
                buf.writeVarInt(msg.id());
            },
            buf -> new MapMarkerPayload(
                    buf.readByte(),
                    buf.readUtf(MAX_STRING),
                    buf.readInt(),
                    buf.readInt(),
                    buf.readInt(),
                    buf.readUtf(MAX_STRING),
                    buf.readByte(),
                    buf.readVarInt()));

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(MapMarkerPayload message, IPayloadContext context) {
        if (!context.flow().isClientbound()) {
            return;
        }
        if (FMLEnvironment.dist != Dist.CLIENT) {
            return; // 专用服务端不会有这条包，保险起见再挡一层
        }
        context.enqueueWork(() -> MapMarkerClient.handle(message));
    }
}
