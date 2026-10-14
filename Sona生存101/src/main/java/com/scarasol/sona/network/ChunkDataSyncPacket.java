package com.scarasol.sona.network;

import com.scarasol.sona.accessor.mixin.IChunkAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static com.scarasol.sona.SonaMod.MODID;

public record ChunkDataSyncPacket(int x, int z, CompoundTag tag) implements CustomPacketPayload {

    public static final Type<ChunkDataSyncPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(MODID, "chunk_data_sync"));

    public static final StreamCodec<FriendlyByteBuf, ChunkDataSyncPacket> CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, ChunkDataSyncPacket::x,
            ByteBufCodecs.INT, ChunkDataSyncPacket::z,
            ByteBufCodecs.COMPOUND_TAG, ChunkDataSyncPacket::tag,
            ChunkDataSyncPacket::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handler(ChunkDataSyncPacket msg, IPayloadContext context) {
        context.enqueueWork(() -> {
            Minecraft minecraft = Minecraft.getInstance();
            if (minecraft.level == null) {
                return;
            }
            if (minecraft.level.hasChunk(msg.x, msg.z)) {
                IChunkAccessor chunkAccessor = IChunkAccessor.fromLevelChunk(minecraft.level.getChunk(msg.x, msg.z));
                chunkAccessor.setSonaCompoundTag(msg.tag());
                int sectionX = msg.x();
                int minY = minecraft.level.getMinBuildHeight();
                int maxY = minecraft.level.getMaxBuildHeight();
                for (int y = minY; y < maxY; y += 16) {
                    minecraft.levelRenderer.setSectionDirty(sectionX, (y >> 4), msg.z());
                }
            }
        });
    }
}
