package com.gem19910816.selfaid.client;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 纯 Java 的客户端血量同步缓存（不引用任何 Minecraft 类，服务端加载也无害）。
 * 键为玩家 UUID，仅本地玩家的数据会被 HUD 和物品使用判定读取。
 */
public final class ClientBodyHealthStore {

    private static final Map<UUID, float[]> PARTS = new ConcurrentHashMap<>();

    public static void accept(UUID playerId, float[] parts) {
        if (parts != null && parts.length == 6) {
            PARTS.put(playerId, parts.clone());
        }
    }

    /** 缺失时返回按 20 点总血量折算的满血值。 */
    public static float[] partsOf(UUID playerId) {
        float[] stored = PARTS.get(playerId);
        if (stored != null) {
            return stored;
        }
        return new float[] { 4.0F, 6.0F, 2.0F, 2.0F, 3.0F, 3.0F };
    }

    public static void clear(UUID playerId) {
        PARTS.remove(playerId);
    }

    public static void clearAll() {
        PARTS.clear();
    }

    private ClientBodyHealthStore() {
    }
}
