package com.scarasol.rummage.network;

import com.scarasol.rummage.RummageMod;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import java.util.BitSet;

public record SyncRummageStatePacket(BitSet maskedMenuSlots) implements CustomPacketPayload {
    public static final Type<SyncRummageStatePacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(RummageMod.MODID, "sync_state"));
    public static final StreamCodec<RegistryFriendlyByteBuf, SyncRummageStatePacket> STREAM_CODEC =
            StreamCodec.of((buf, msg) -> buf.writeByteArray(msg.maskedMenuSlots().toByteArray()),
                    buf -> new SyncRummageStatePacket(BitSet.valueOf(buf.readByteArray())));
    @Override public Type<SyncRummageStatePacket> type() { return TYPE; }
}
