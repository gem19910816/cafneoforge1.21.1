package com.november.mcphone.feature.shop.net;

import com.november.mcphone.MCphone;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record OpenShopPacket() implements CustomPacketPayload {
    public static final Type<OpenShopPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(MCphone.MODID, "open_shop"));
    public static final StreamCodec<RegistryFriendlyByteBuf, OpenShopPacket> STREAM_CODEC =
            StreamCodec.unit(new OpenShopPacket());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
