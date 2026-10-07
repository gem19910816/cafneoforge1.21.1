package net.gem19910816.dyairdrop.network;

import net.gem19910816.dyairdrop.network.payload.MapMarkerPayload;
import net.gem19910816.dyairdrop.network.payload.PanelActionPayload;
import net.gem19910816.dyairdrop.network.payload.XaeroPresencePayload;
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
		// 密码面板：客户端只发「动作 + 文本」，服务端校验并执行（原来的 4 个按钮包把服务端逻辑在客户端又跑了一遍，已删除）
		registrar.playToServer(PanelActionPayload.TYPE, PanelActionPayload.STREAM_CODEC, PanelActionPayload::handle);
		// 空投地图标记（Xaero 小地图 / 世界地图 的软依赖集成）
		registrar.playToClient(MapMarkerPayload.TYPE, MapMarkerPayload.STREAM_CODEC, MapMarkerPayload::handle);
		registrar.playToServer(XaeroPresencePayload.TYPE, XaeroPresencePayload.STREAM_CODEC, XaeroPresencePayload::handle);
	}
}
