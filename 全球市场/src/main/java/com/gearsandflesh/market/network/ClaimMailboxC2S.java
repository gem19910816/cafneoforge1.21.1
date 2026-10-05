package com.gearsandflesh.market.network;

import com.gearsandflesh.market.MarketConstants;
import com.gearsandflesh.market.service.MarketService;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record ClaimMailboxC2S() implements CustomPacketPayload {
    public static final Type<ClaimMailboxC2S> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(MarketConstants.MOD_ID, "claim_mailbox"));

    public static final StreamCodec<RegistryFriendlyByteBuf, ClaimMailboxC2S> STREAM_CODEC =
            StreamCodec.of(
                    (buffer, message) -> {
                    },
                    buffer -> new ClaimMailboxC2S()
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(ClaimMailboxC2S message, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player) {
                MarketService.claimMailbox(player);
            }
        });
    }
}
