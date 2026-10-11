package com.scarasol.itemrarity.network;

import com.scarasol.itemrarity.ItemRarityMod;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

public final class NetworkHandler {
    private NetworkHandler() {
    }

    public static void register(RegisterPayloadHandlersEvent event) {
        event.registrar(ItemRarityMod.MODID)
                .versioned("1")
                .playBidirectional(RarityGradePacket.TYPE, RarityGradePacket.STREAM_CODEC, RarityGradePacket::handle);
    }
}
