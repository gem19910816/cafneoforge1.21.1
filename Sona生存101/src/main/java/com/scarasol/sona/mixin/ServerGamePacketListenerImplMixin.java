package com.scarasol.sona.mixin;

import com.scarasol.sona.manager.ChatManager;
import net.minecraft.network.TickablePacketListener;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.LastSeenMessages;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.network.chat.SignedMessageChain;
import net.minecraft.network.protocol.game.ServerGamePacketListener;
import net.minecraft.network.protocol.game.ServerboundChatPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.FilteredText;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.server.network.ServerPlayerConnection;
import net.minecraft.util.FutureChain;
import net.neoforged.neoforge.common.CommonHooks;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Mixin(ServerGamePacketListenerImpl.class)
public abstract class ServerGamePacketListenerImplMixin implements ServerPlayerConnection, TickablePacketListener, ServerGamePacketListener {


    @Shadow protected abstract PlayerChatMessage getSignedMessage(ServerboundChatPacket p_251061_, LastSeenMessages p_250566_) throws SignedMessageChain.DecodeException;

    @Shadow protected abstract Optional<LastSeenMessages> unpackAndApplyLastSeen(LastSeenMessages.Update p_249614_);

    @Shadow protected abstract void tryHandleChat(String p_251364_, Runnable p_299348_);


    @Shadow protected abstract void handleMessageDecodeFailure(SignedMessageChain.DecodeException p_252068_);

    @Shadow protected abstract CompletableFuture<FilteredText> filterTextPacket(String p_243213_);

    @Shadow @Final private FutureChain chatMessageChain;

    @Shadow public ServerPlayer player;

    // 接管 handleChat：取消原版广播，走 Sona 的分范围聊天；tryHandleChat 只调用一次
    @Inject(method = "handleChat", at = @At("HEAD"), cancellable = true)
    private void onHandleChat(ServerboundChatPacket p_9841_, CallbackInfo ci) {
        ci.cancel();
        Optional<LastSeenMessages> optional = this.unpackAndApplyLastSeen(p_9841_.lastSeenMessages());
        if (optional.isEmpty())
            return;
        tryHandleChat(p_9841_.message(), () -> {
            PlayerChatMessage playerchatmessage;
            try {
                playerchatmessage = this.getSignedMessage(p_9841_, optional.get());
            } catch (SignedMessageChain.DecodeException signedmessagechain$decodeexception) {
                this.handleMessageDecodeFailure(signedmessagechain$decodeexception);
                return;
            }
            CompletableFuture<FilteredText> completablefuture = this.filterTextPacket(playerchatmessage.signedContent());
            Component decoratedContent = CommonHooks.getServerChatSubmittedDecorator().decorate(this.player, playerchatmessage.decoratedContent());
            this.chatMessageChain.append(completablefuture, (filteredText) -> {
                if (decoratedContent == null)
                    return; // NeoForge: ServerChatEvent 被取消时为 null
                PlayerChatMessage playerchatmessage1 = playerchatmessage.withUnsignedContent(decoratedContent).filter(filteredText.mask());
                ChatManager.broadcastMessage(this.player, playerchatmessage1);
            });
        });
    }

}
