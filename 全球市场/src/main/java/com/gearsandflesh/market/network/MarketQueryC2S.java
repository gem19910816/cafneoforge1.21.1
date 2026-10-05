package com.gearsandflesh.market.network;

import com.gearsandflesh.market.MarketConstants;
import com.gearsandflesh.market.data.MarketQuery;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record MarketQueryC2S(MarketQuery query) implements CustomPacketPayload {
    public static final Type<MarketQueryC2S> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(MarketConstants.MOD_ID, "query"));

    public static final StreamCodec<RegistryFriendlyByteBuf, MarketQueryC2S> STREAM_CODEC =
            StreamCodec.of(
                    (buffer, message) -> message.query.write(buffer),
                    buffer -> new MarketQueryC2S(NetworkCodecs.readQuery(buffer))
            );

    public MarketQueryC2S {
        query = query == null ? MarketQuery.defaults() : query;
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(MarketQueryC2S message, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player) {
                MarketNetwork.rememberQuery(player, message.query);
                MarketNetwork.sendSnapshot(player, message.query);
            }
        });
    }
}
