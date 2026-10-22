package com.november.mcphone.feature.caf.net;

import com.november.mcphone.MCphone;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record OpenCafStatusPacket() implements CustomPacketPayload {
    public static final Type<OpenCafStatusPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(MCphone.MODID, "open_caf_status"));
    public static final StreamCodec<RegistryFriendlyByteBuf, OpenCafStatusPacket> STREAM_CODEC =
            StreamCodec.unit(new OpenCafStatusPacket());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
