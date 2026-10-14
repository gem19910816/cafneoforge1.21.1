package com.scarasol.sona.network;

import com.scarasol.sona.manager.SoundManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.nio.charset.StandardCharsets;

import static com.scarasol.sona.SonaMod.MODID;

public record SyncSoundPacket(String soundWhiteList, boolean soundOpen) implements CustomPacketPayload {

    public static final Type<SyncSoundPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(MODID, "sync_sound"));

    public static final StreamCodec<FriendlyByteBuf, SyncSoundPacket> CODEC = StreamCodec.of(
            (buf, msg) -> {
                buf.writeByteArray(msg.soundWhiteList.getBytes(StandardCharsets.UTF_8));
                buf.writeBoolean(msg.soundOpen);
            },
            buf -> new SyncSoundPacket(new String(buf.readByteArray(), StandardCharsets.UTF_8), buf.readBoolean()));

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public boolean isSoundOpen() {
        return soundOpen;
    }

    public String getSoundWhiteList() {
        return soundWhiteList;
    }

    public static void handler(SyncSoundPacket msg, IPayloadContext context) {
        context.enqueueWork(() -> {
            SoundManager.addSoundWhiteList(msg.getSoundWhiteList());
            SoundManager.setSoundOpen(msg.isSoundOpen());
        });
    }
}
