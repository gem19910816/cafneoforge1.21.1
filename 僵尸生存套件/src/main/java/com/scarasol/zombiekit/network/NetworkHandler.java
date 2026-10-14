package com.scarasol.zombiekit.network;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import static com.scarasol.zombiekit.ZombieKitMod.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD)
public class NetworkHandler {

    @SubscribeEvent
    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");
        registrar.playToServer(KeyBindPacket.TYPE, KeyBindPacket.CODEC, KeyBindPacket::handler);
        registrar.playToClient(SavedDataSyncPacket.TYPE, SavedDataSyncPacket.CODEC, SavedDataSyncPacket::handler);
        registrar.playBidirectional(SyncBlockPacket.TYPE, SyncBlockPacket.CODEC, SyncBlockPacket::handler);
        registrar.playToServer(CoverFirePacket.TYPE, CoverFirePacket.CODEC, CoverFirePacket::handler);
        registrar.playToServer(MouseInputPacket.TYPE, MouseInputPacket.CODEC, MouseInputPacket::handler);
        registrar.playToServer(DoubleJumpPacket.TYPE, DoubleJumpPacket.CODEC, DoubleJumpPacket::handler);
    }
}
