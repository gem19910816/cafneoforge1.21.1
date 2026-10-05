package com.gearsandflesh.market.network;

import com.gearsandflesh.market.MarketConstants;
import com.gearsandflesh.market.service.MarketService;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/** Converts website balance back into physical caf:money via the mailbox. */
public record WithdrawMoneyC2S(long amount) implements CustomPacketPayload {
    public static final Type<WithdrawMoneyC2S> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(MarketConstants.MOD_ID, "withdraw_money"));

    public static final StreamCodec<RegistryFriendlyByteBuf, WithdrawMoneyC2S> STREAM_CODEC =
            StreamCodec.of(
                    (buffer, message) -> buffer.writeVarLong(message.amount),
                    buffer -> new WithdrawMoneyC2S(buffer.readVarLong())
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(WithdrawMoneyC2S message, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player) {
                MarketService.withdraw(player, message.amount);
            }
        });
    }
}
