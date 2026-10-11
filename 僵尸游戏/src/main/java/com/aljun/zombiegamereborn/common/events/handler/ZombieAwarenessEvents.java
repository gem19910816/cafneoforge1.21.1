package com.aljun.zombiegamereborn.common.events.handler;

import com.aljun.zombiegamereborn.ZombieGameReborn;
import com.aljun.zombiegamereborn.common.config.StageProperty;
import com.aljun.zombiegamereborn.common.entity.awareness.AwarenessManager;
import com.aljun.zombiegamereborn.common.entity.awareness.AwarenessSettings;
import com.aljun.zombiegamereborn.common.entity.awareness.AwarenessTuning;
import com.aljun.zombiegamereborn.common.entity.awareness.LevelAwareness;
import com.aljun.zombiegamereborn.common.entity.awareness.SoundAwareness;
import com.aljun.zombiegamereborn.common.game.ZGRGame;
import com.aljun.zombiegamereborn.diplomat.ZGRDiplomacyCenter;
import com.aljun.zombiegamereborn.utils.ZombieUtils;
import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.PlayLevelSoundEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.level.ExplosionEvent;
import net.neoforged.neoforge.event.level.LevelEvent;
import net.neoforged.neoforge.event.server.ServerStoppedEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import org.slf4j.Logger;

import java.util.List;

/**
 * 感知系统的事件挂钩层：把世界里发生的事情翻译成刺激。
 *
 * <p>这是旧 {@code ZombieSenseHandler} 的替代品。关键区别在于<b>这里不做任何实体查询</b>：
 * 旧实现每来一个事件就 {@code level.getEntitiesOfClass(Zombie.class, aabb512)}，
 * 现在只往感知表里写一条记录，剩下交给僵尸在轮到自己相位时自己来问。
 */
@EventBusSubscriber(modid = ZombieGameReborn.MOD_ID)
public final class ZombieAwarenessEvents {

    private static final Logger LOGGER = LogUtils.getLogger();

    /** 维度维护间隔（tick）。只负责回收，不参与感知判定。 */
    private static final int MAINTENANCE_INTERVAL = 20;

    private ZombieAwarenessEvents() {
    }

    // ------------------------------------------------------------------
    // 声音：世界里的每一次播音
    // ------------------------------------------------------------------

    @SubscribeEvent
    public static void onSoundAtEntity(PlayLevelSoundEvent.AtEntity event) {
        if (event.getLevel().isClientSide()) {
            return;
        }
        Entity entity = event.getEntity();
        SoundAwareness.onSound(event.getLevel(), entity, entity.getX(), entity.getY(), entity.getZ(),
                event.getSound(), event.getSource(), event.getNewVolume());
    }

    @SubscribeEvent
    public static void onSoundAtPosition(PlayLevelSoundEvent.AtPosition event) {
        if (event.getLevel().isClientSide()) {
            return;
        }
        Vec3 position = event.getPosition();
        SoundAwareness.onSound(event.getLevel(), null, position.x, position.y, position.z,
                event.getSound(), event.getSource(), event.getNewVolume());
    }

    // ------------------------------------------------------------------
    // 冲击类
    // ------------------------------------------------------------------

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (event.isCanceled() || event.getPlayer() == null || event.getPlayer().level().isClientSide()) {
            return;
        }
        BlockPos pos = event.getPos();
        AwarenessManager.emitBlockBreak(event.getPlayer().level(),
                pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, event.getPlayer());
    }

    @SubscribeEvent
    public static void onBlockPlace(BlockEvent.EntityPlaceEvent event) {
        Entity entity = event.getEntity();
        if (event.isCanceled() || entity == null || entity.level().isClientSide()) {
            return;
        }
        BlockPos pos = event.getPos();
        AwarenessManager.emitBlockPlace(entity.level(),
                pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, entity);
    }

    @SubscribeEvent
    public static void onExplosion(ExplosionEvent.Detonate event) {
        Level level = event.getLevel();
        if (level.isClientSide()) {
            return;
        }
        Explosion explosion = event.getExplosion();
        Entity source = explosion.getDirectSourceEntity();
        Vec3 center = null;
        if (source != null) {
            center = source.position();
        } else {
            List<BlockPos> blocks = event.getAffectedBlocks();
            if (!blocks.isEmpty()) {
                BlockPos pos = blocks.get(0);
                center = new Vec3(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);
            }
        }
        if (center == null) {
            return;
        }
        AwarenessManager.emitExplosion(level, center.x, center.y, center.z, source);
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingIncomingDamageEvent event) {
        LivingEntity victim = event.getEntity();
        if (event.isCanceled() || victim.level().isClientSide()) {
            return;
        }
        if (!ZombieUtils.attackableEntity(victim)) {
            return;
        }
        AwarenessManager.emitHurt(victim.level(), victim.getX(), victim.getY(), victim.getZ(), victim);
    }

    // ------------------------------------------------------------------
    // 气味沉积：玩家走过留痕
    // ------------------------------------------------------------------

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (player.level().isClientSide()) {
            return;
        }
        AwarenessSettings settings = AwarenessTuning.settings();
        if (!settings.enabled) {
            return;
        }
        long gameTime = player.level().getGameTime();

        // 按 id 错峰 + 节流，避免每 tick 每玩家写网格
        int interval = Math.max(1, settings.scentDepositIntervalTicks);
        if (Math.floorMod(gameTime + player.getId(), interval) == 0
                && player.getVehicle() == null
                && movedSinceLastSample(player)) {
            float amount = settings.scentDepositPlayer;
            if (player.isCrouching()) {
                amount *= 0.4F;          // 潜行留味更淡
            } else if (player.isSprinting()) {
                amount *= 1.3F;
            }
            if (player.getHealth() <= player.getMaxHealth() * 0.3F) {
                amount *= 1.8F;          // 重伤：血腥味更浓
            }
            AwarenessManager.depositScent(player.level(), player.getX(), player.getY(), player.getZ(), amount);
        }

        // 原版「无限追猎」游戏规则：周期性把玩家位置作为超大半径刺激广播出去。
        // 走的是同一套感知通道，因此依然受僵尸错峰轮询的节流保护，不会退化成全服扫描。
        if (gameTime % 100L == 0L) {
            broadcastBoundlessHunting(player);
        }
    }

    /**
     * 气味只来自实际移动，不让站在原地的玩家持续刷新“新鲜轨迹”。
     * 采样窗口使用实体上一 tick 的位置，避免额外状态表和 UUID 强引用。
     */
    private static boolean movedSinceLastSample(Player player) {
        double dx = player.getX() - player.xo;
        double dz = player.getZ() - player.zo;
        return dx * dx + dz * dz >= 0.0025D;
    }

    private static void broadcastBoundlessHunting(Player player) {
        if (!(player.level() instanceof ServerLevel serverLevel)) {
            return;
        }
        StageProperty property = ZGRGame.getGameProperty().getStageProperty(serverLevel, player.blockPosition());
        boolean boundless = property.zombieProperty.boundlessHunting
                || (property.zombieProperty.bloodMoonBoundlessHunting
                    && ZGRDiplomacyCenter.ENHANCED_CELERESTIALS_DIPLOMAT.isBloodMoon(serverLevel.getServer()));
        if (boundless) {
            AwarenessManager.emitSound(player.level(), player.getX(), player.getY(), player.getZ(),
                    BOUNDLESS_RADIUS, BOUNDLESS_STRENGTH, player);
        } else if (player.getHealth() <= 4.0F) {
            // 濒死玩家的血腥味会被远距离闻到
            AwarenessManager.depositScent(player.level(), player.getX(), player.getY(), player.getZ(),
                    AwarenessTuning.settings().scentDepositPlayer * 3.0F);
        }
    }

    /** 无限追猎的广播半径/强度：这是刻意的难度设计，必须能覆盖大范围。 */
    private static final double BOUNDLESS_RADIUS = 512.0D;
    private static final int BOUNDLESS_STRENGTH = 40;

    // ------------------------------------------------------------------
    // 生命周期
    // ------------------------------------------------------------------

    @SubscribeEvent
    public static void onServerTickPre(ServerTickEvent.Pre event) {
        if (AwarenessTuning.debugLog()) {
            tickStartNanos = System.nanoTime();
        }
    }

    /**
     * 真实 MSPT 采样（仅调试模式）。
     * <p>
     * 注意必须用 Pre→Post 的墙钟差：两次 Post 之间的间隔包含服务端的空闲睡眠，
     * 服务端不卡时恒为 50ms，量不出任何东西。只有 tick 内部耗时才是有效数字。
     * 每 200 tick 打印一次平均值与峰值。
     */
    private static long tickStartNanos;
    private static long tickNanosSum;
    private static long tickNanosMax;
    private static int tickSamples;

    private static void sampleMspt() {
        if (tickStartNanos == 0L) {
            return;
        }
        long cost = System.nanoTime() - tickStartNanos;
        tickStartNanos = 0L;
        tickNanosSum += cost;
        if (cost > tickNanosMax) {
            tickNanosMax = cost;
        }
        if (++tickSamples >= 200) {
            LOGGER.info("[ZGR awareness] MSPT avg={} ms, max={} ms over {} ticks",
                    String.format("%.2f", tickNanosSum / (double) tickSamples / 1_000_000.0D),
                    String.format("%.2f", tickNanosMax / 1_000_000.0D),
                    tickSamples);
            tickNanosSum = 0L;
            tickNanosMax = 0L;
            tickSamples = 0;
        }
    }

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Post event) {
        if (AwarenessTuning.debugLog()) {
            sampleMspt();
        }
        if (!AwarenessTuning.enabled()) {
            return;
        }
        long gameTime = event.getServer().getTickCount();
        if (gameTime % MAINTENANCE_INTERVAL != 0L) {
            return;
        }
        for (ServerLevel level : event.getServer().getAllLevels()) {
            AwarenessManager.maintenance(level, level.getGameTime());
        }
        // 调试用：周期性打印僵尸的实际状态，排查"感知到了但走不过去"这类问题
        if (AwarenessTuning.debugLog() && gameTime % 100L == 0L) {
            dumpZombieState(event.getServer());
        }
    }

    private static void dumpZombieState(net.minecraft.server.MinecraftServer server) {
        for (ServerLevel level : server.getAllLevels()) {
            // 先打印感知表自身状态，并对不变式做一次自检（曾因 allocated 归零导致永久失明）
            LevelAwareness awareness = AwarenessManager.of(level);
            if (awareness != null) {
                LOGGER.info("[ZGR awareness] DEBUG state[{}] {}", level.dimension().location(),
                        awareness.debugStats());
                if (!awareness.isStateConsistent()) {
                    LOGGER.warn("[ZGR awareness] STATE INCONSISTENT in {}: {}",
                            level.dimension().location(), awareness.debugStats());
                }
            }
            for (Entity entity : level.getAllEntities()) {
                if (!(entity instanceof net.minecraft.world.entity.monster.Zombie zombie)) {
                    continue;
                }
                LOGGER.info("[ZGR awareness] DEBUG zombie#{} at ({}, {}, {}) onGround={} inWall={} "
                                + "blockAt={} blockBelow={} light={} target={} navDone={}",
                        zombie.getId(),
                        String.format("%.1f", zombie.getX()),
                        String.format("%.1f", zombie.getY()),
                        String.format("%.1f", zombie.getZ()),
                        zombie.onGround(),
                        zombie.isInWall(),
                        level.getBlockState(zombie.blockPosition()).getBlock(),
                        level.getBlockState(zombie.blockPosition().below()).getBlock(),
                        // 与光照通道用的是同一个 API，用来核对“午夜露天 / 火把旁 / 有顶棚”各是什么值
                        level.getMaxLocalRawBrightness(zombie.blockPosition()),
                        zombie.getTarget(),
                        zombie.getNavigation().isDone());
            }
        }
    }

    @SubscribeEvent
    public static void onLevelUnload(LevelEvent.Unload event) {
        if (event.getLevel() instanceof Level level && !level.isClientSide()) {
            AwarenessManager.unload(level);
        }
    }

    @SubscribeEvent
    public static void onServerStopped(ServerStoppedEvent event) {
        AwarenessManager.shutdown();
        GamePropertyRefresher.resetAwarenessState();
    }
}
