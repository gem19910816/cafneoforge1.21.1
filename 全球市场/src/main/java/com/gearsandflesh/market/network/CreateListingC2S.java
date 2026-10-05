package com.gearsandflesh.market.network;

import com.gearsandflesh.market.MarketConstants;
import com.gearsandflesh.market.service.MarketService;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record CreateListingC2S(int slot, int count, long price) implements CustomPacketPayload {
    public static final Type<CreateListingC2S> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(MarketConstants.MOD_ID, "create_listing"));

    public static final StreamCodec<RegistryFriendlyByteBuf, CreateListingC2S> STREAM_CODEC =
            StreamCodec.of(
                    (buffer, message) -> {
                        buffer.writeVarInt(message.slot);
                        buffer.writeVarInt(message.count);
                        buffer.writeVarLong(message.price);
                    },
                    buffer -> new CreateListingC2S(
                            buffer.readVarInt(), buffer.readVarInt(), buffer.readVarLong())
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(CreateListingC2S message, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player) {
                MarketService.createListing(player, message.slot, message.count, message.price);
            }
        });
    }
}
