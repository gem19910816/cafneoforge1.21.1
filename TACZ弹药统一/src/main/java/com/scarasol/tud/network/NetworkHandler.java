package com.scarasol.tud.network;

import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

/** NeoForge play-phase payload registration, required on both ends. */
public final class NetworkHandler {
    private NetworkHandler() {}

    public static void register(RegisterPayloadHandlersEvent event) {
        event.registrar("1").playBidirectional(SwitchAmmoPacket.TYPE, SwitchAmmoPacket.STREAM_CODEC, SwitchAmmoPacket::handle);
    }
}
