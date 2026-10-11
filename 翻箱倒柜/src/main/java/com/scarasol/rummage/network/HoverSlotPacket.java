package com.scarasol.rummage.network;

import com.scarasol.rummage.RummageMod;
import com.scarasol.rummage.api.mixin.IRummageMenu;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record HoverSlotPacket(int slotIndex) implements CustomPacketPayload {
    public static final Type<HoverSlotPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(RummageMod.MODID, "hover_slot"));
    public static final StreamCodec<RegistryFriendlyByteBuf, HoverSlotPacket> STREAM_CODEC =
            StreamCodec.composite(ByteBufCodecs.VAR_INT, HoverSlotPacket::slotIndex, HoverSlotPacket::new);
    @Override public Type<HoverSlotPacket> type() { return TYPE; }
    public static void handle(HoverSlotPacket msg, IPayloadContext context) {
        if (context.player() instanceof ServerPlayer player && player.containerMenu instanceof IRummageMenu menu) {
            menu.rummage$setHoveredSlot(msg.slotIndex());
        }
    }
}
