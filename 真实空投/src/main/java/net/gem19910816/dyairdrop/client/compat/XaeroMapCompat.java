package net.gem19910816.dyairdrop.client.compat;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.mcreator.dyairdrop.DyairdropMod;
import net.neoforged.fml.ModList;

/**
 * Xaero 小地图 / 世界地图的软依赖集成（纯反射，零编译期依赖）。
 *
 * <p>Xaero 没有公开 Java API，这里用 26.5.0 / 1.46.0 实测过的调用链直接操作它的路点集合：
 * <pre>
 * XaeroMinimapSession.getCurrentSession()
 *   → getWaypointsManager()
 *   → getCurrentWorld()            // WaypointWorld
 *   → getCurrentSet().getList()    // ArrayList&lt;Waypoint&gt;（可读写的活集合）
 * new Waypoint(x, y, z, name, initials, colorIndex)  … 然后 setTemporary(true)
 * </pre>
 * 小地图与世界地图共用同一份路点数据，所以标记在两边都会出现。
 *
 * <p>所有反射调用都被保护：任何一步失败只记一条日志并停用集成，绝不把异常抛进渲染/网络线程。
 * 集成只影响「地图上有没有标记」，不影响空投本体逻辑。
 */
public final class XaeroMapCompat {

    private static final String[] MOD_IDS = {"xaerominimap", "xaeroworldmap"};

    private static boolean initialized;
    private static boolean available;
    private static boolean broken;

    private static Method getCurrentSession;
    private static Method getWaypointsManager;
    private static Method getCurrentWorld;
    private static Method getCurrentSet;
    private static Method getList;
    private static Method setTemporary;
    private static Constructor<?> waypointConstructor;

    /** 已添加的标记：id → Xaero 的 Waypoint 实例（REMOVE 时按引用移除）。 */
    private static final Map<Integer, Object> ADDED = new HashMap<>();

    private XaeroMapCompat() {
    }

    public static boolean isModPresent() {
        for (String id : MOD_IDS) {
            if (ModList.get().isLoaded(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 向 Xaero 当前世界的当前路点集合里加一个临时路点。
     *
     * @return 是否成功
     */
    public static boolean add(int id, int x, int y, int z, String label, int color) {
        if (!ensureInitialized()) {
            return false;
        }
        try {
            Object session = getCurrentSession.invoke(null);
            if (session == null) {
                return false;
            }
            Object manager = getWaypointsManager.invoke(session);
            if (manager == null) {
                return false;
            }
            Object world = getCurrentWorld.invoke(manager);
            if (world == null) {
                return false;
            }
            Object set = getCurrentSet.invoke(world);
            if (set == null) {
                return false;
            }
            Object rawList = getList.invoke(set);
            if (!(rawList instanceof List<?> list)) {
                return false;
            }

            String safeName = sanitize(label);
            String initials = initialsOf(safeName);
            Object waypoint = waypointConstructor.newInstance(x, y, z, safeName, initials, clampColor(color));
            if (setTemporary != null) {
                setTemporary.invoke(waypoint, true);
            }
            @SuppressWarnings("unchecked")
            List<Object> target = (List<Object>) list;
            target.add(waypoint);

            Object previous = ADDED.put(id, waypoint);
            if (previous != null) {
                target.remove(previous);
            }
            return true;
        } catch (ReflectiveOperationException | RuntimeException e) {
            disable("添加标记失败", e);
            return false;
        }
    }

    /** 移除先前添加的标记。 */
    public static void remove(int id) {
        Object waypoint = ADDED.remove(id);
        if (waypoint == null || !available) {
            return;
        }
        try {
            Object session = getCurrentSession.invoke(null);
            if (session == null) {
                return;
            }
            Object manager = getWaypointsManager.invoke(session);
            Object world = manager == null ? null : getCurrentWorld.invoke(manager);
            Object set = world == null ? null : getCurrentSet.invoke(world);
            Object rawList = set == null ? null : getList.invoke(set);
            if (rawList instanceof List<?> list) {
                @SuppressWarnings("unchecked")
                List<Object> target = (List<Object>) list;
                target.remove(waypoint);
            }
        } catch (ReflectiveOperationException | RuntimeException e) {
            // 移除失败不算致命：临时路点不会落盘，重登即消失
            DyairdropMod.LOGGER.debug("[dyairdrop] 移除 Xaero 标记 #{} 失败: {}", id, e.toString());
        }
    }

    /** 玩家切换维度 / 重登后，把本地引用清掉（Xaero 的路点按世界分开存放）。 */
    public static void clearLocalReferences() {
        ADDED.clear();
    }

    private static synchronized boolean ensureInitialized() {
        if (initialized) {
            return available;
        }
        initialized = true;
        if (!isModPresent()) {
            return false;
        }
        try {
            Class<?> sessionClass = Class.forName("xaero.common.XaeroMinimapSession");
            Class<?> waypointClass = Class.forName("xaero.common.minimap.waypoints.Waypoint");

            getCurrentSession = sessionClass.getMethod("getCurrentSession");
            getWaypointsManager = sessionClass.getMethod("getWaypointsManager");

            Class<?> managerClass = Class.forName("xaero.common.minimap.waypoints.WaypointsManager");
            getCurrentWorld = managerClass.getMethod("getCurrentWorld");

            Class<?> worldClass = Class.forName("xaero.common.minimap.waypoints.WaypointWorld");
            getCurrentSet = worldClass.getMethod("getCurrentSet");

            Class<?> setClass = Class.forName("xaero.common.minimap.waypoints.WaypointSet");
            getList = setClass.getMethod("getList");

            try {
                setTemporary = waypointClass.getMethod("setTemporary", boolean.class);
            } catch (NoSuchMethodException ignored) {
                setTemporary = null; // 老版本没有该方法，退化为普通路点
            }

            waypointConstructor = waypointClass.getConstructor(
                    int.class, int.class, int.class, String.class, String.class, int.class);

            available = true;
            DyairdropMod.LOGGER.info("[dyairdrop] Xaero 地图集成已启用（反射模式，临时路点）");
        } catch (ReflectiveOperationException | RuntimeException e) {
            disable("Xaero API 反射初始化失败", e);
        }
        return available;
    }

    private static void disable(String what, Throwable e) {
        if (!broken) {
            broken = true;
            DyairdropMod.LOGGER.warn("[dyairdrop] {}，本会话停用 Xaero 地图集成（空投功能不受影响）: {}", what, e.toString());
        }
        available = false;
    }

    private static String sanitize(String label) {
        if (label == null || label.isBlank()) {
            return "Airdrop";
        }
        String cleaned = label.replaceAll("§.", "").trim();
        if (cleaned.isEmpty()) {
            return "Airdrop";
        }
        return cleaned.length() > 32 ? cleaned.substring(0, 32) : cleaned;
    }

    private static String initialsOf(String name) {
        String letters = name.replaceAll("[^0-9A-Za-z\\u4e00-\\u9fa5]", "");
        if (letters.isEmpty()) {
            return "A";
        }
        return letters.length() >= 2 ? letters.substring(0, 2) : letters;
    }

    private static int clampColor(int color) {
        return Math.max(0, Math.min(15, color));
    }
}
