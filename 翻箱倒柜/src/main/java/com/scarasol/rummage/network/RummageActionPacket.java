package com.scarasol.rummage.network;

import com.scarasol.rummage.RummageMod;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record RummageActionPacket(int slotIndex, int action, int totalTicks) implements CustomPacketPayload {
    public static final Type<RummageActionPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(RummageMod.MODID, "rummage_action"));
    public static final StreamCodec<RegistryFriendlyByteBuf, RummageActionPacket> STREAM_CODEC =
            StreamCodec.composite(ByteBufCodecs.VAR_INT, RummageActionPacket::slotIndex,
                    ByteBufCodecs.VAR_INT, RummageActionPacket::action,
                    ByteBufCodecs.VAR_INT, RummageActionPacket::totalTicks,
                    RummageActionPacket::new);
    @Override public Type<RummageActionPacket> type() { return TYPE; }
}
