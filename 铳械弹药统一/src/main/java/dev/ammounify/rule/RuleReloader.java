package dev.ammounify.rule;

import dev.ammounify.AmmoUnify;
import dev.ammounify.data.GunDataDeriver;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;

/**
 * 规则与缓存的生命周期。
 *
 * <p>枪包是数据驱动的，随时可能被数据包／枪包重载替换掉整个 {@code CommonGunIndex} 实例，
 * 所以我们缓存的一切都必须跟着失效。三处挂钩：数据包重载、服务端启动、以及整包同步。</p>
 */
@EventBusSubscriber(modid = AmmoUnify.MOD_ID)
public final class RuleReloader {

    private RuleReloader() {
    }

    /** 由 mod 构造阶段调用，确保即便没有任何事件也至少加载过一次规则。 */
    public static void attach() {
        // 规则在 AmmoUnify 构造函数里已加载；这里只做一次缓存清空，保证状态干净。
        GunDataDeriver.invalidate();
    }

    @SubscribeEvent
    public static void onAddReloadListener(AddReloadListenerEvent event) {
        invalidateAll();
    }

    @SubscribeEvent
    public static void onServerStarted(ServerStartedEvent event) {
        invalidateAll();
    }

    @SubscribeEvent
    public static void onDatapackSync(OnDatapackSyncEvent event) {
        invalidateAll();
    }

    private static void invalidateAll() {
        AmmoRules.reload();
        GunDataDeriver.invalidate();
        AmmoUnify.LOG.debug("[Ammo Unify] 规则已重载，规则条目 {} 条", AmmoRules.get().totalRules());
    }
}
