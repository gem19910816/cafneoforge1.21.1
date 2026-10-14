package com.scarasol.sona.network;

import com.scarasol.sona.client.renderer.ClientRenderDispatcher;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static com.scarasol.sona.SonaMod.MODID;

public record PositionIndicatorPacket(double x, double y, double z, double renderRange, int duration) implements CustomPacketPayload {

    public static final Type<PositionIndicatorPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(MODID, "position_indicator"));

    public static final StreamCodec<FriendlyByteBuf, PositionIndicatorPacket> CODEC = StreamCodec.of(
            (buf, msg) -> {
                buf.writeDouble(msg.x);
                buf.writeDouble(msg.y);
                buf.writeDouble(msg.z);
                buf.writeDouble(msg.renderRange);
                buf.writeInt(msg.duration);
            },
            buf -> new PositionIndicatorPacket(buf.readDouble(), buf.readDouble(), buf.readDouble(), buf.readDouble(), buf.readInt()));

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handler(PositionIndicatorPacket msg, IPayloadContext context) {
        context.enqueueWork(() -> ClientRenderDispatcher.handlePositionIndicator(msg));
    }
}
