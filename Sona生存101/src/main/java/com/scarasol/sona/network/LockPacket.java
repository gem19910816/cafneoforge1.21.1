package com.scarasol.sona.network;

import com.scarasol.sona.accessor.mixin.IBaseContainerBlockEntityAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.UUID;

import static com.scarasol.sona.SonaMod.MODID;

public record LockPacket(BlockPos blockPos, UUID lockCode, boolean locked) implements CustomPacketPayload {

    public static final Type<LockPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(MODID, "lock"));

    public static final StreamCodec<FriendlyByteBuf, LockPacket> CODEC = StreamCodec.of(
            (buf, msg) -> {
                buf.writeBlockPos(msg.blockPos);
                buf.writeUUID(msg.lockCode);
                buf.writeBoolean(msg.locked);
            },
            buf -> new LockPacket(buf.readBlockPos(), buf.readUUID(), buf.readBoolean()));

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public UUID getLockCode() {
        return lockCode;
    }

    public boolean isLocked() {
        return locked;
    }

    public static void handler(LockPacket msg, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player) {
                Level level = player.level();
                // 安全校验：只允许操作附近的已加载方块
                if (!level.isLoaded(msg.getBlockPos()) || !msg.getBlockPos().closerToCenterThan(player.position(), 10.0)) {
                    return;
                }
                BlockEntity blockEntity = level.getBlockEntity(msg.getBlockPos());
                BlockState blockState = level.getBlockState(msg.getBlockPos());
                if (blockEntity instanceof IBaseContainerBlockEntityAccessor baseContainerBlockEntity && msg.isLocked() && !baseContainerBlockEntity.isLocked()) {
                    baseContainerBlockEntity.lockContainer(new CompoundTag(), msg.getLockCode());
                    level.sendBlockUpdated(msg.getBlockPos(), blockState, blockState, 3);
                }
            }
        });
    }
}
