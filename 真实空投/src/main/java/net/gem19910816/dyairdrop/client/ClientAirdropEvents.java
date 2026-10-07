package net.gem19910816.dyairdrop.client;

import net.mcreator.dyairdrop.DyairdropMod;
import net.gem19910816.dyairdrop.client.compat.MapMarkerClient;
import net.gem19910816.dyairdrop.client.compat.XaeroMapCompat;
import net.gem19910816.dyairdrop.network.payload.XaeroPresencePayload;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.network.PacketDistributor;

/**
 * 客户端侧的事件：登录时告知服务端「本客户端有没有装 Xaero」，换维度/重登时清理本地路点引用。
 *
 * <p>只有声明过装有 Xaero 的玩家才会收到地图标记包，服务端因此不用猜、也不会给无关玩家发包。
 */
@EventBusSubscriber(modid = DyairdropMod.MODID, value = Dist.CLIENT)
public final class ClientAirdropEvents {

    private ClientAirdropEvents() {
    }

    @SubscribeEvent
    public static void onLoggingIn(ClientPlayerNetworkEvent.LoggingIn event) {
        PacketDistributor.sendToServer(new XaeroPresencePayload(XaeroMapCompat.isModPresent()));
        MapMarkerClient.onDimensionChanged();
    }
}
