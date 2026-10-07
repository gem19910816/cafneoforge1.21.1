package net.gem19910816.dyairdrop.compat.zombiekit;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

/**
 * 可选前置 {@code zombiekit}（末日生存工具包）的兼容层。
 *
 * <p>模组自带 20 个引用 zombiekit 物品的掉落表：装了它就用 {@code zombiekit:chests/…}，
 * 没装就退回自己的 {@code dyairdrop:chests/…}；同时原来散落在各处的
 * {@code ModList.get().isLoaded("zombiekit")} 判断集中到这里。
 *
 * <p>另外合并了原 {@code UnlockflaregunProcedure}：未安装 zombiekit 时，
 * 玩家一进入世界就补发 {@code dyairdrop:unlockflare} 成就（原来那份实现带了三个重载、
 * 其中一个只用于兼容旧事件签名，现已去掉）。
 */
@EventBusSubscriber
public final class ZombieKitCompat {

    private static final String MOD_ID = "zombiekit";
    private static final ResourceLocation UNLOCK_FLARE_ADVANCEMENT = ResourceLocation.parse("dyairdrop:unlockflare");

    private ZombieKitCompat() {
    }

    public static boolean isLoaded() {
        return ModList.get().isLoaded(MOD_ID);
    }

    /** 掉落表命名空间：装了 zombiekit 用它，否则用本模组。 */
    public static String lootNamespace() {
        return isLoaded() ? MOD_ID : "dyairdrop";
    }

    /** 未装 zombiekit 时补发信号枪解锁成就。 */
    @SubscribeEvent
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        grantUnlockAdvancement(event.getEntity());
    }

    private static void grantUnlockAdvancement(Entity entity) {
        if (isLoaded() || !(entity instanceof ServerPlayer player)) {
            return;
        }
        AdvancementHolder advancement = player.server.getAdvancements().get(UNLOCK_FLARE_ADVANCEMENT);
        if (advancement == null) {
            return;
        }
        AdvancementProgress progress = player.getAdvancements().getOrStartProgress(advancement);
        if (progress.isDone()) {
            return;
        }
        for (String criterion : progress.getRemainingCriteria()) {
            player.getAdvancements().award(advancement, criterion);
        }
    }
}
