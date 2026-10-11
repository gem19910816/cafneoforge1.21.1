package com.aljun.zombiegamereborn.common.entity.awareness;

import com.aljun.zombiegamereborn.api.ZGRZombieAttributesAPI;
import com.aljun.zombiegamereborn.utils.ZombieUtils;
import com.mojang.logging.LogUtils;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

/**
 * 感知 → 锁定目标。
 *
 * <p>它<b>不是</b>一个持续运行的 Goal，而是一个“错峰传感器”：
 * {@link #canUse()} 只在轮到自己的那几个 tick 上做一次廉价查询，其余 tick 直接返回 false。
 * 取得刺激后把<b>需要的数据拷贝出来</b>（刺激对象是池化的，绝不能长期持有引用），
 * 再由本 Goal 在寿命内维持目标。
 *
 * <p>与旧实现的区别：
 * <ul>
 *   <li>旧实现由事件方主动扫描附近所有僵尸（512 格 AABB），本实现由僵尸自己按相位来问；</li>
 *   <li>旧实现每个事件都要遍历实体列表，本实现是 O(存活刺激数) 的内存遍历。</li>
 * </ul>
 */
public class ZombieAwarenessTargetGoal extends TargetGoal {

    private static final Logger LOGGER = LogUtils.getLogger();

    private final Zombie zombie;
    /** 相位种子：用实体 id 打散，避免全场僵尸挤在同一 tick 轮询。 */
    private final int phaseSeed;

    @Nullable
    private LivingEntity sensedTarget;
    private long senseUntil;
    private int sensedGeneration;
    private long lastAlertTick = NEVER;
    /**
     * “从未发生过”的哨兵值。
     * <p>
     * <b>绝对不能用 {@code Long.MIN_VALUE}</b>：这里要做的是减法比较，
     * {@code now - Long.MIN_VALUE} 会溢出成负数，于是永远小于冷却时间，
     * 整个警报链路（以及跟着它走的反馈音）会静默失效 —— 这个坑真踩过一次。
     */
    private static final long NEVER = -1_000_000L;

    private boolean piglinAngryMode;

    public ZombieAwarenessTargetGoal(Zombie zombie) {
        super(zombie, false);
        this.zombie = zombie;
        this.phaseSeed = zombie.getId();
    }

    public void setPiglinAngryMode() {
        this.piglinAngryMode = true;
    }

    @Override
    public boolean canUse() {
        if (!AwarenessTuning.enabled()) {
            return false;
        }
        // 错峰：每只僵尸只在自己相位命中时轮询一次，成本摊薄到 pollInterval 个 tick
        long now = this.zombie.level().getGameTime();
        if (AwarenessManager.isTargetDistracted(this.zombie, now)) {
            return false;
        }
        int interval = AwarenessTuning.pollIntervalTicks();
        if (Math.floorMod(now + this.phaseSeed, interval) != 0) {
            return false;
        }

        // 已经有活目标就别抢，交给战斗 Goal 与目标选择器
        LivingEntity current = this.zombie.getTarget();
        if (current != null && current.isAlive()) {
            return false;
        }

        LevelAwareness awareness = AwarenessManager.of(this.zombie.level());
        if (awareness == null) {
            return false;
        }
        // 只关心“有实体来源、且能打”的通道；警报只提供位置，不作为攻击目标。
        // requireSource = true：必须跳过无来源的刺激，否则目标生物自己的环境音会把它的受伤音挤掉。
        Stimulus stimulus = awareness.findStrongest(this.zombie.getX(), this.zombie.getY(),
                this.zombie.getZ(), now, AwarenessManager.MASK_SOUND | AwarenessManager.MASK_IMPACT, true);
        if (stimulus == null || stimulus.source == null) {
            return false;
        }
        LivingEntity candidate = this.asLegalTarget(stimulus.source);
        if (candidate == null || !this.canConfirmTarget(candidate)) {
            return false;
        }

        this.sensedTarget = candidate;
        this.sensedGeneration = stimulus.generation;
        this.senseUntil = stimulus.bornTick + stimulus.lifespan;
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        if (AwarenessManager.isTargetDistracted(this.zombie, this.zombie.level().getGameTime())) {
            return false;
        }
        LivingEntity target = this.sensedTarget;
        if (target == null) {
            return false;
        }
        if (!target.isAlive()) {
            return false;
        }
        if (this.zombie.level().getGameTime() >= this.senseUntil) {
            return false;
        }
        return ZombieUtils.isTargetLegal(target);
    }

    @Override
    public void start() {
        this.zombie.setTarget(this.sensedTarget);
        if (AwarenessTuning.debugLog()) {
            LOGGER.info("[ZGR awareness] zombie#{} acquired target {} via awareness | zombie ({}, {}, {})",
                    this.zombie.getId(),
                    this.sensedTarget == null ? "null" : this.sensedTarget.getName().getString(),
                    String.format("%.1f", this.zombie.getX()),
                    String.format("%.1f", this.zombie.getY()),
                    String.format("%.1f", this.zombie.getZ()));
        }
        this.emitAlert();
    }

    @Override
    public void tick() {
        long now = this.zombie.level().getGameTime();
        if (AwarenessManager.shouldInterruptTarget(this.zombie, now)) {
            AwarenessManager.distractTarget(this.zombie, now);
            this.zombie.setTarget(null);
            this.sensedTarget = null;
            this.senseUntil = 0L;
            return;
        }
        // 目标可能被战斗逻辑清掉，这里在感知寿命内持续维持
        if (this.sensedTarget != null && this.zombie.getTarget() != this.sensedTarget) {
            this.zombie.setTarget(this.sensedTarget);
        }
    }

    @Override
    public void stop() {
        this.sensedTarget = null;
        this.senseUntil = 0L;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return false;
    }

    /**
     * 发出警报，让附近同类知道“这边有情况”。
     * <p>
     * 三重限流：单只僵尸冷却、全局每 tick 配额、跳数上限（见 {@link AwarenessManager#emitAlert}）。
     * 没有这三层，僵尸群会互相反复唤醒，形成典型的警报风暴。
     */
    private void emitAlert() {
        long now = this.zombie.level().getGameTime();
        if (now - this.lastAlertTick < AwarenessTuning.alertCooldownTicks()) {
            return;
        }
        this.lastAlertTick = now;
        // 听觉反馈：玩家能听见“它们注意到我了”。跟着警报冷却走，所以不会变成刷屏。
        // 用原版僵尸环境音压低音高 —— 不需要任何外部素材。
        AwarenessManager.playFeedbackSound(this.zombie.level(), this.zombie.getX(),
                this.zombie.getY() + 0.6D, this.zombie.getZ(),
                SoundEvents.ZOMBIE_AMBIENT, 1.0F, 0.7F);
        AwarenessSettings settings = AwarenessTuning.settings();
        AwarenessManager.emitAlert(this.zombie.level(), this.zombie.getX(), this.zombie.getY(),
                this.zombie.getZ(), settings.alertRadius,
                settings.alertStrength, this.zombie, this.sensedGeneration + 1);
    }

    @Nullable
    private LivingEntity asLegalTarget(net.minecraft.world.entity.Entity entity) {
        if (entity == this.zombie || !(entity instanceof LivingEntity living)) {
            return null;
        }
        if (this.zombie instanceof ZombifiedPiglin) {
            // 未进入“猪灵暴怒”阶段时，僵尸猪灵不参与感知追击
            if (!this.piglinAngryMode || !ZombieUtils.zombifiedPiglinAttackableEntity(living)) {
                return null;
            }
        } else if (!ZombieUtils.zombieAttackableEntity(living)) {
            return null;
        }
        return living;
    }

    /**
     * 感知只能提供“去哪里调查”的线索，不能绕过原版的确认条件。
     * 只有在原版跟随范围内且有视线时，才把来源实体升级为真实攻击目标。
     */
    private boolean canConfirmTarget(LivingEntity candidate) {
        Double followRange = ZGRZombieAttributesAPI.getFollowRange(this.zombie);
        if (followRange != null) {
            double visibilityRange = followRange;
            if (candidate instanceof Player player) {
                int brightness = player.level().getMaxLocalRawBrightness(player.blockPosition());
                visibilityRange *= Math.max(0.45D, 0.45D + brightness / 15.0D * 0.85D);
                if (player.isCrouching()) {
                    visibilityRange *= 0.65D;
                }
            }
            if (this.zombie.distanceToSqr(candidate) > visibilityRange * visibilityRange) {
                return false;
            }
        }
        return this.zombie.getSensing().hasLineOfSight(candidate);
    }
}
