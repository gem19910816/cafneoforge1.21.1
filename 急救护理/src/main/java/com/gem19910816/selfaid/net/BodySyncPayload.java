package com.gem19910816.selfaid.net;

import com.gem19910816.selfaid.SelfAidMod;
import com.gem19910816.selfaid.body.BodyPart;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/**
 * S2C：把某个玩家的 6 个部位血量发给客户端 HUD / 物品判定使用。
 */
public record BodySyncPayload(float[] parts) implements CustomPacketPayload {

    public static final Type<BodySyncPayload> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(SelfAidMod.MODID, "body_sync"));

    public static final StreamCodec<ByteBuf, BodySyncPayload> STREAM_CODEC = StreamCodec.of(
            BodySyncPayload::write, BodySyncPayload::read);

    private static void write(ByteBuf buf, BodySyncPayload payload) {
        for (int i = 0; i < BodyPart.COUNT; i++) {
            buf.writeFloat(payload.parts[i]);
        }
    }

    private static BodySyncPayload read(ByteBuf buf) {
        float[] parts = new float[BodyPart.COUNT];
        for (int i = 0; i < BodyPart.COUNT; i++) {
            parts[i] = buf.readFloat();
        }
        return new BodySyncPayload(parts);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
