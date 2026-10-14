package com.scarasol.sona.network;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static com.scarasol.sona.SonaMod.MODID;

public record SavedDataSyncPacket(CompoundTag tag) implements CustomPacketPayload {

    public static final Type<SavedDataSyncPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(MODID, "saved_data_sync"));

    public static final StreamCodec<FriendlyByteBuf, SavedDataSyncPacket> CODEC = StreamCodec.composite(
            ByteBufCodecs.COMPOUND_TAG, SavedDataSyncPacket::tag,
            SavedDataSyncPacket::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static SavedDataSyncPacket of(MapVariables data) {
        return new SavedDataSyncPacket(data.save(new CompoundTag(), null));
    }

    public static void handler(SavedDataSyncPacket msg, IPayloadContext context) {
        context.enqueueWork(() -> {
            MapVariables mapVariables = new MapVariables();
            mapVariables.read(msg.tag());
            MapVariables.clientSide = mapVariables;
        });
    }
}
