package com.scarasol.sona.network;

import com.scarasol.sona.manager.SoundManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static com.scarasol.sona.SonaMod.MODID;

public record SoundDecoyPacket(double x, double y, double z, int amplifier) implements CustomPacketPayload {

    public static final Type<SoundDecoyPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(MODID, "sound_decoy"));

    public static final StreamCodec<FriendlyByteBuf, SoundDecoyPacket> CODEC = StreamCodec.of(
            (buf, msg) -> {
                buf.writeDouble(msg.x);
                buf.writeDouble(msg.y);
                buf.writeDouble(msg.z);
                buf.writeInt(msg.amplifier);
            },
            buf -> new SoundDecoyPacket(buf.readDouble(), buf.readDouble(), buf.readDouble(), buf.readInt()));

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public int getAmplifier() {
        return amplifier;
    }

    public static void handler(SoundDecoyPacket msg, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player) {
                // 安全校验：目标点必须在玩家附近
                if (player.distanceToSqr(msg.getX(), msg.getY(), msg.getZ()) > 64 * 64) {
                    return;
                }
                int amplifier = Math.max(0, Math.min(msg.getAmplifier(), 4));
                SoundManager.spawnSoundDecoy(player.level(), msg.getX(), msg.getY(), msg.getZ(), amplifier);
            }
        });
    }
}
