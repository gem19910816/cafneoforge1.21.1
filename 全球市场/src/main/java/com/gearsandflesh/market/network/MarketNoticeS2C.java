package com.gearsandflesh.market.network;

import com.gearsandflesh.market.MarketConstants;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record MarketNoticeS2C(boolean success, String message) implements CustomPacketPayload {
    private static final int MAX_MESSAGE_LENGTH = 256;

    public static final Type<MarketNoticeS2C> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(MarketConstants.MOD_ID, "notice"));

    public static final StreamCodec<RegistryFriendlyByteBuf, MarketNoticeS2C> STREAM_CODEC =
            StreamCodec.of(
                    (buffer, message) -> {
                        buffer.writeBoolean(message.success);
                        buffer.writeUtf(message.message, MAX_MESSAGE_LENGTH);
                    },
                    buffer -> new MarketNoticeS2C(
                            buffer.readBoolean(),
                            buffer.readUtf(MAX_MESSAGE_LENGTH)
                    )
            );

    public MarketNoticeS2C {
        message = sanitize(message);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(MarketNoticeS2C message, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (FMLEnvironment.dist == Dist.CLIENT) {
                com.gearsandflesh.market.client.ClientMarketState.onNotice(message);
            }
        });
    }

    private static String sanitize(String value) {
        if (value == null) {
            return "";
        }
        String clean = value.replaceAll("[\\p{Cntrl}]", "");
        return clean.length() <= MAX_MESSAGE_LENGTH
                ? clean
                : clean.substring(0, MAX_MESSAGE_LENGTH);
    }
}
