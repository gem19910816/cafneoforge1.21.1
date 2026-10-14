package com.scarasol.sona.network;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import static com.scarasol.sona.SonaMod.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD)
public class NetworkHandler {

    @SubscribeEvent
    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");
        registrar.playToClient(RotPacket.TYPE, RotPacket.CODEC, RotPacket::handler);
        registrar.playToServer(SoundDecoyPacket.TYPE, SoundDecoyPacket.CODEC, SoundDecoyPacket::handler);
        registrar.playToClient(SyncSoundPacket.TYPE, SyncSoundPacket.CODEC, SyncSoundPacket::handler);
        registrar.playToServer(LockPacket.TYPE, LockPacket.CODEC, LockPacket::handler);
        registrar.playToClient(SyncChatPacket.TYPE, SyncChatPacket.CODEC, SyncChatPacket::handler);
        registrar.playToClient(ChunkDataSyncPacket.TYPE, ChunkDataSyncPacket.CODEC, ChunkDataSyncPacket::handler);
        registrar.playToClient(SavedDataSyncPacket.TYPE, SavedDataSyncPacket.CODEC, SavedDataSyncPacket::handler);
        registrar.playToClient(PositionIndicatorPacket.TYPE, PositionIndicatorPacket.CODEC, PositionIndicatorPacket::handler);
    }
}
