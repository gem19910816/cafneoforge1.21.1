package net.gem19910816.dyairdrop.compat.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import net.mcreator.dyairdrop.DyairdropMod;
import net.gem19910816.dyairdrop.network.payload.MapMarkerPayload;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

/**
 * 服务端的地图标记管理器：空投箱落地 → 给装了 Xaero 的玩家打临时路点；箱子被搜空 / 被移除 → 撤掉标记。
 *
 * <p>这是「保留现有机制、替换实现」的核心：原版调用了一条并不存在的服务端命令
 * {@code addwaypointxaero}（见 {@code 重构说明.md} 第六节），永远静默失败；
 * 现在改为服务端权威地维护标记状态，通过 {@link MapMarkerPayload} 下发。
 *
 * <p>标记的存活判定每 20 tick 检查一次：方块实体消失或容器已空即回收，避免地图上留下过期标记。
 */
@EventBusSubscriber
public final class MapMarkerService {

    /** 每个维度 → (标记 id → 标记)。 */
    private static final Map<ResourceKey<Level>, Map<Integer, MapMarker>> MARKERS = new HashMap<>();
    /** 声明装有 Xaero 的玩家。 */
    private static final Set<UUID> XAERO_USERS = ConcurrentHashMap.newKeySet();

    private static int nextId = 1;
    private static int lastValidatedTick = -1;

    private MapMarkerService() {
    }

    public static void setXaeroPresence(ServerPlayer player, boolean hasXaero) {
        if (hasXaero) {
            XAERO_USERS.add(player.getUUID());
        } else {
            XAERO_USERS.remove(player.getUUID());
        }
    }

    public static boolean hasXaero(ServerPlayer player) {
        return XAERO_USERS.contains(player.getUUID());
    }

    /**
     * 按空投箱类型给一个 Xaero 颜色序号（0~15，枚举 ordinal）：
     * 大型＝金、医疗＝红、武器＝暗红、小型＝绿，其它用金（沿用旧行为里的 6）。
     */
    public static int colorForBlockId(String blockId) {
        if (blockId == null) {
            return 6;
        }
        String id = blockId.replace("locked", "").toLowerCase(java.util.Locale.ENGLISH);
        if (id.contains("medical")) {
            return 12; // RED
        }
        if (id.contains("weapon")) {
            return 4; // DARK_RED
        }
        if (id.contains("small")) {
            return 10; // GREEN
        }
        return 6; // GOLD
    }

    /**
     * 新增（或复用）一个地图标记，并把 ADD 包发给该维度内所有装了 Xaero 的玩家。
     *
     * @return 标记 id；若服务端不可用则返回 -1
     */
    public static int addMarker(ServerLevel level, BlockPos pos, String label, int color) {
        int id = nextId++;
        MapMarker marker = new MapMarker(id, level.dimension(), pos.immutable(), label, color);
        MARKERS.computeIfAbsent(level.dimension(), key -> new HashMap<>()).put(id, marker);
        broadcast(level, marker, MapMarkerPayload.ACTION_ADD);
        return id;
    }

    /** 撤掉某维度下的某个标记。 */
    public static void removeMarker(ServerLevel level, int id) {
        Map<Integer, MapMarker> inLevel = MARKERS.get(level.dimension());
        if (inLevel == null) {
            return;
        }
        MapMarker marker = inLevel.remove(id);
        if (marker != null) {
            broadcast(level, marker, MapMarkerPayload.ACTION_REMOVE);
        }
    }

    /** 玩家换维度 / 退出时不需要特殊处理：由于是临时路点，Xaero 不落盘，客户端会在下次 ADD 时重建。 */
    public static void forgetPlayer(ServerPlayer player) {
        XAERO_USERS.remove(player.getUUID());
    }

    private static void broadcast(ServerLevel level, MapMarker marker, byte action) {
        MapMarkerPayload payload = new MapMarkerPayload(
                action,
                marker.level().location().toString(),
                marker.pos().getX(),
                marker.pos().getY(),
                marker.pos().getZ(),
                marker.label(),
                marker.color(),
                marker.id());
        for (ServerPlayer player : level.players()) {
            if (hasXaero(player)) {
                PacketDistributor.sendToPlayer(player, payload);
            }
        }
        if (action == MapMarkerPayload.ACTION_ADD) {
            DyairdropMod.LOGGER.debug("[dyairdrop] 地图标记 #{} {} @ {} ({})",
                    marker.id(), marker.label(), marker.pos(), marker.level().location());
        }
    }

    /** 玩家退出时清掉「装有 Xaero」的标记，避免同一 UUID 复用时残留。 */
    @SubscribeEvent
    public static void onPlayerLoggedOut(net.neoforged.neoforge.event.entity.player.PlayerEvent.PlayerLoggedOutEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            forgetPlayer(player);
        }
    }

    /** 标记存活判定：方块实体不存在、或容器已空 → 回收。每 20 tick 一次，开销可忽略。 */
    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Post event) {        MinecraftServer server = event.getServer();
        if (server == null || server.getTickCount() % 20 != 0 || server.getTickCount() == lastValidatedTick) {
            return;
        }
        lastValidatedTick = server.getTickCount();
        Iterator<Map.Entry<ResourceKey<Level>, Map<Integer, MapMarker>>> levelIt = MARKERS.entrySet().iterator();
        while (levelIt.hasNext()) {
            Map.Entry<ResourceKey<Level>, Map<Integer, MapMarker>> entry = levelIt.next();
            ServerLevel level = server.getLevel(entry.getKey());
            if (level == null) {
                levelIt.remove();
                continue;
            }
            List<Integer> stale = new ArrayList<>();
            for (MapMarker marker : entry.getValue().values()) {
                BlockEntity be = level.getBlockEntity(marker.pos());
                if (!(be instanceof Container container) || container.isEmpty()) {
                    stale.add(marker.id());
                }
            }
            for (int id : stale) {
                removeMarker(level, id);
            }
            if (entry.getValue().isEmpty()) {
                levelIt.remove();
            }
        }
    }
}
