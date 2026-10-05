package com.gearsandflesh.market.network;

import com.gearsandflesh.market.MarketConstants;
import com.gearsandflesh.market.service.MarketService;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/** Converts physical caf:money from the inventory into website balance. */
public record DepositMoneyC2S(int amount) implements CustomPacketPayload {
    public static final Type<DepositMoneyC2S> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(MarketConstants.MOD_ID, "deposit_money"));

    public static final StreamCodec<RegistryFriendlyByteBuf, DepositMoneyC2S> STREAM_CODEC =
            StreamCodec.of(
                    (buffer, message) -> buffer.writeVarInt(message.amount),
                    buffer -> new DepositMoneyC2S(buffer.readVarInt())
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(DepositMoneyC2S message, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player) {
                MarketService.deposit(player, message.amount);
            }
        });
    }
}
