package net.gem19910816.dyairdrop.client.compat;

import net.gem19910816.dyairdrop.network.payload.MapMarkerPayload;

/**
 * 客户端的地图标记分发入口：把服务端下发的 ADD / REMOVE 落到具体的地图模组实现上。
 *
 * <p>目前只有 Xaero 一种实现；将来要接 JourneyMap / 原版地图时在这里加分支即可，
 * 网络层与空投逻辑都不用动。
 */
public final class MapMarkerClient {

    private MapMarkerClient() {
    }

    public static void handle(MapMarkerPayload payload) {
        if (payload.action() == MapMarkerPayload.ACTION_ADD) {
            XaeroMapCompat.add(payload.id(), payload.x(), payload.y(), payload.z(), payload.label(), payload.color());
        } else if (payload.action() == MapMarkerPayload.ACTION_REMOVE) {
            XaeroMapCompat.remove(payload.id());
        }
    }

    /** 切换维度时调用：Xaero 的路点按世界存放，本地引用需要清空。 */
    public static void onDimensionChanged() {
        XaeroMapCompat.clearLocalReferences();
    }
}
