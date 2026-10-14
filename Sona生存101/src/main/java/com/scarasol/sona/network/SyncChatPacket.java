package com.scarasol.sona.network;

import com.scarasol.sona.manager.ChatManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.nio.charset.StandardCharsets;

import static com.scarasol.sona.SonaMod.MODID;

public record SyncChatPacket(String chatIncreaseItem, int chatRange, boolean chatLimit) implements CustomPacketPayload {

    public static final Type<SyncChatPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(MODID, "sync_chat"));

    public static final StreamCodec<FriendlyByteBuf, SyncChatPacket> CODEC = StreamCodec.of(
            (buf, msg) -> {
                buf.writeByteArray(msg.chatIncreaseItem.getBytes(StandardCharsets.UTF_8));
                buf.writeInt(msg.chatRange);
                buf.writeBoolean(msg.chatLimit);
            },
            buf -> new SyncChatPacket(new String(buf.readByteArray(), StandardCharsets.UTF_8), buf.readInt(), buf.readBoolean()));

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public String getChatIncreaseItem() {
        return chatIncreaseItem;
    }

    public int getChatRange() {
        return chatRange;
    }

    public boolean isChatLimit() {
        return chatLimit;
    }

    public static void handler(SyncChatPacket msg, IPayloadContext context) {
        context.enqueueWork(() -> {
            ChatManager.addRangeItem(msg.chatIncreaseItem);
            ChatManager.setChatRange(msg.chatRange);
            ChatManager.setChatLimit(msg.chatLimit);
        });
    }
}
