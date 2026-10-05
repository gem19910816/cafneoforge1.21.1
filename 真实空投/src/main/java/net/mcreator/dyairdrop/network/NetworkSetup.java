package net.mcreator.dyairdrop.network;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class NetworkSetup {
	@SubscribeEvent
	public static void register(final RegisterPayloadHandlersEvent event) {
		final PayloadRegistrar registrar = event.registrar("1");
		registrar.playToClient(PlayerVariablesSyncPayload.TYPE, PlayerVariablesSyncPayload.STREAM_CODEC, PlayerVariablesSyncPayload::handle);
		registrar.playToClient(SavedDataSyncPayload.TYPE, SavedDataSyncPayload.STREAM_CODEC, SavedDataSyncPayload::handle);
		registrar.playToServer(PannelButtonPayload.TYPE, PannelButtonPayload.STREAM_CODEC, PannelButtonPayload::handle);
		registrar.playToServer(PannelREButtonPayload.TYPE, PannelREButtonPayload.STREAM_CODEC, PannelREButtonPayload::handle);
		registrar.playToServer(PannelRE2ButtonPayload.TYPE, PannelRE2ButtonPayload.STREAM_CODEC, PannelRE2ButtonPayload::handle);
		registrar.playToServer(TestGUI2ButtonPayload.TYPE, TestGUI2ButtonPayload.STREAM_CODEC, TestGUI2ButtonPayload::handle);
	}
}
