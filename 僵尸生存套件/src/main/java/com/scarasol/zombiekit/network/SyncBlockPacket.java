package com.scarasol.zombiekit.network;

import com.scarasol.zombiekit.block.entity.ShortwaveRadioBlockEntity;
import com.scarasol.zombiekit.inventory.ShortwaveRadioMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static com.scarasol.zombiekit.ZombieKitMod.MODID;

public record SyncBlockPacket(int id, BlockPos pos, String content) implements CustomPacketPayload {

    public static final Type<SyncBlockPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(MODID, "sync_block"));

    public static final StreamCodec<FriendlyByteBuf, SyncBlockPacket> CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, SyncBlockPacket::id,
            BlockPos.STREAM_CODEC, SyncBlockPacket::pos,
            ByteBufCodecs.STRING_UTF8, SyncBlockPacket::content,
            SyncBlockPacket::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handler(SyncBlockPacket msg, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player) {
                Level level = player.level();
                // 安全校验：只接受附近已加载、且玩家当前确实开着对应电台 GUI 的方块
                if (!level.isLoaded(msg.pos()) || !msg.pos().closerToCenterThan(player.position(), 16.0)) {
                    return;
                }
                if (!(player.containerMenu instanceof ShortwaveRadioMenu menu)
                        || menu.x != msg.pos().getX() || menu.y != msg.pos().getY() || menu.z != msg.pos().getZ()) {
                    return;
                }
                handleButton(level.getBlockEntity(msg.pos()), msg.id(), msg.content());
            } else {
                Minecraft minecraft = Minecraft.getInstance();
                if (minecraft.level != null) {
                    handleButton(minecraft.level.getBlockEntity(msg.pos()), msg.id(), msg.content());
                }
            }
        });
    }

    public static void handleButton(BlockEntity blockEntity, int id, String content) {
        if (id == 0) {
            if (blockEntity instanceof ShortwaveRadioBlockEntity shortwaveRadioBlockEntity)
                shortwaveRadioBlockEntity.setContent(content);
        }
    }
}
