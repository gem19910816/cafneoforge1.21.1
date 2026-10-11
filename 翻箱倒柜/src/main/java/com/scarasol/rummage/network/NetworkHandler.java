package com.scarasol.rummage.network;

import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

public final class NetworkHandler {
    private NetworkHandler() {}
    public static void register(RegisterPayloadHandlersEvent event) {
        var registrar = event.registrar("1");
        registrar.playToClient(SyncRummageStatePacket.TYPE, SyncRummageStatePacket.STREAM_CODEC,
                (msg, ctx) -> ctx.enqueueWork(() -> ClientPacketHandler.handle(msg)));
        registrar.playToServer(HoverSlotPacket.TYPE, HoverSlotPacket.STREAM_CODEC,
                (msg, ctx) -> ctx.enqueueWork(() -> HoverSlotPacket.handle(msg, ctx)));
        registrar.playToClient(RummageActionPacket.TYPE, RummageActionPacket.STREAM_CODEC,
                (msg, ctx) -> ctx.enqueueWork(() -> ClientPacketHandler.handleAction(msg)));
    }
}
