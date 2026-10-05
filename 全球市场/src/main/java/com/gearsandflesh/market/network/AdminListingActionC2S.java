package com.gearsandflesh.market.network;

import com.gearsandflesh.market.MarketConstants;
import com.gearsandflesh.market.service.MarketService;
import io.netty.handler.codec.DecoderException;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record AdminListingActionC2S(long listingId, Action action) implements CustomPacketPayload {
    public enum Action {
        COPY_ITEM,
        REMOVE_AND_RETURN,
        DELETE_WITHOUT_RETURN;

        public String apiName() {
            return switch (this) {
                case COPY_ITEM -> "copy";
                case REMOVE_AND_RETURN -> "remove";
                case DELETE_WITHOUT_RETURN -> "delete";
            };
        }
    }

    public static final Type<AdminListingActionC2S> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(MarketConstants.MOD_ID, "admin_listing_action"));

    public static final StreamCodec<RegistryFriendlyByteBuf, AdminListingActionC2S> STREAM_CODEC =
            StreamCodec.of(
                    (buffer, message) -> {
                        buffer.writeVarLong(message.listingId);
                        buffer.writeVarInt(message.action.ordinal());
                    },
                    buffer -> {
                        long listingId = buffer.readVarLong();
                        int ordinal = buffer.readVarInt();
                        Action[] actions = Action.values();
                        if (ordinal < 0 || ordinal >= actions.length) {
                            throw new DecoderException("Invalid administrator market action " + ordinal);
                        }
                        return new AdminListingActionC2S(listingId, actions[ordinal]);
                    }
            );

    public AdminListingActionC2S {
        if (action == null) {
            throw new IllegalArgumentException("Administrator market action is required");
        }
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(AdminListingActionC2S message, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player) {
                if (!player.createCommandSourceStack().hasPermission(
                        MarketConstants.ADMIN_PERMISSION_LEVEL)) {
                    MarketNetwork.sendNotice(player, false, "没有市场管理权限");
                    return;
                }
                MarketService.adminAction(player, message.listingId, message.action);
            }
        });
    }
}
