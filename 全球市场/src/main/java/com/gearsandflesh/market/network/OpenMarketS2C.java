package com.gearsandflesh.market.network;

import com.gearsandflesh.market.MarketConstants;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record OpenMarketS2C(boolean adminMode) implements CustomPacketPayload {
    public static final Type<OpenMarketS2C> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(MarketConstants.MOD_ID, "open_market"));

    public static final StreamCodec<RegistryFriendlyByteBuf, OpenMarketS2C> STREAM_CODEC =
            StreamCodec.of(
                    (buffer, message) -> buffer.writeBoolean(message.adminMode),
                    buffer -> new OpenMarketS2C(buffer.readBoolean())
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(OpenMarketS2C message, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (FMLEnvironment.dist == Dist.CLIENT) {
                com.gearsandflesh.market.client.ClientMarketState.open(message.adminMode);
            }
        });
    }
}
