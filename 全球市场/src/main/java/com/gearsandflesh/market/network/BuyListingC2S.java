package com.gearsandflesh.market.network;

import com.gearsandflesh.market.MarketConstants;
import com.gearsandflesh.market.service.MarketService;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record BuyListingC2S(long listingId) implements CustomPacketPayload {
    public static final Type<BuyListingC2S> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(MarketConstants.MOD_ID, "buy_listing"));

    public static final StreamCodec<RegistryFriendlyByteBuf, BuyListingC2S> STREAM_CODEC =
            StreamCodec.of(
                    (buffer, message) -> buffer.writeVarLong(message.listingId),
                    buffer -> new BuyListingC2S(buffer.readVarLong())
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(BuyListingC2S message, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player) {
                MarketService.buyListing(player, message.listingId);
            }
        });
    }
}
