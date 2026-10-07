package com.gem19910816.selfaid.body;

import java.util.Iterator;
import java.util.Random;

import com.gem19910816.selfaid.SelfAidMod;
import com.gem19910816.selfaid.body.BodyHealth.DeathCause;
import com.gem19910816.selfaid.net.BodySyncPayload;
import com.gem19910816.selfaid.registry.ModAttachments;
import com.gem19910816.selfaid.registry.SelfAidConfig;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

/**
 * 分部位血量的核心玩法层：伤害分配、死亡判定、持续治疗、原版回血分摊、截肢惩罚与数据同步。
 */
@EventBusSubscriber(modid = SelfAidMod.MODID)
public final class BodyHealthEvents {

    private static final Random RANDOM = new Random();

    private static boolean enabled() {
        return SelfAidConfig.ENABLE_SYSTEM.get();
    }

    /** 这些伤害类型是“系统级处决/虚空”，不参与部位分配，也用于我们自己的强制死亡。 */
    private static boolean isBypassSource(DamageSource source) {
        return source.is(DamageTypes.GENERIC_KILL) || source.is(DamageTypes.FELL_OUT_OF_WORLD)
                || source.is(DamageTypes.GENERIC);
    }

    private static boolean appliesTo(Player player) {
        return enabled() && player instanceof ServerPlayer && !player.isCreative() && !player.isSpectator()
                && !player.isDeadOrDying();
    }

    private static void sync(ServerPlayer player) {
        BodyHealth health = player.getData(ModAttachments.BODY_HEALTH.get());
        PacketDistributor.sendToPlayer(player, new BodySyncPayload(health.snapshot()));
    }

    // ---------------------------------------------------------------- 伤害分配

    @SubscribeEvent
    public static void onIncomingDamage(LivingDamageEvent.Pre event) {
        if (!(event.getEntity() instanceof ServerPlayer player) || !appliesTo(player)) {
            return;
        }
        if (isBypassSource(event.getSource())) {
            return;
        }
        // 提前在数据附加里生成默认数据，保证 Post 阶段一定存在
        player.getData(ModAttachments.BODY_HEALTH.get());
    }

    @SubscribeEvent
    public static void onDamagePost(LivingDamageEvent.Post event) {
        if (!(event.getEntity() instanceof ServerPlayer player) || !appliesTo(player)) {
            return;
        }
        DamageSource source = event.getSource();
        if (isBypassSource(source)) {
            return;
        }
        float amount = event.getNewDamage() * SelfAidConfig.DAMAGE_SCALE.get().floatValue();
        if (amount <= 0.0F) {
            return;
        }
        BodyHealth health = player.getData(ModAttachments.BODY_HEALTH.get());
        health.damagePart(pickPart(player, source), amount);
        checkLethal(player, health);
        // 完全没有治疗计划被打断时，清掉针对已空部位的无效计划
        health.activeHeals().removeIf(heal -> health.isPartEmpty(BodyPart.byIndex(heal.partIndex())));
        sync(player);
    }

    private static void checkLethal(ServerPlayer player, BodyHealth health) {
        DeathCause cause = health.lethalCause(SelfAidConfig.HEAD_DEATH.get(), SelfAidConfig.TORSO_DEATH.get());
        if (cause != DeathCause.NONE) {
            health.pendingDeath = cause;
        }
    }

    /** 依据伤害来源挑一个受击部位。 */
    private static BodyPart pickPart(ServerPlayer player, DamageSource source) {
        if (source.is(DamageTypes.FALL) || source.is(DamageTypes.STALAGMITE)) {
            double roll = RANDOM.nextDouble();
            if (roll < 0.25D) {
                return BodyPart.TORSO;
            }
            return RANDOM.nextBoolean() ? BodyPart.LEFT_LEG : BodyPart.RIGHT_LEG;
        }
        Entity direct = source.getDirectEntity();
        if (direct instanceof Projectile projectile && player.getBbHeight() > 0.5D) {
            double relativeY = (projectile.getY() - player.getY()) / player.getBbHeight();
            if (relativeY >= 0.8D) {
                return BodyPart.HEAD;
            }
            if (relativeY >= 0.45D) {
                return BodyPart.TORSO;
            }
            return randomLimb();
        }
        return weightedRandom();
    }

    private static BodyPart randomLimb() {
        BodyPart[] limbs = { BodyPart.LEFT_ARM, BodyPart.RIGHT_ARM, BodyPart.LEFT_LEG, BodyPart.RIGHT_LEG };
        return limbs[RANDOM.nextInt(limbs.length)];
    }

    private static BodyPart weightedRandom() {
        double total = 0.0D;
        for (BodyPart part : BodyPart.VALUES) {
            total += part.maxHealthFraction();
        }
        double roll = RANDOM.nextDouble() * total;
        for (BodyPart part : BodyPart.VALUES) {
            roll -= part.maxHealthFraction();
            if (roll <= 0.0D) {
                return part;
            }
        }
        return BodyPart.TORSO;
    }

    // ---------------------------------------------------------------- 死亡 / 治疗 / tick

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if (!(event.getEntity() instanceof ServerPlayer player) || !enabled()) {
            return;
        }
        BodyHealth health = player.getData(ModAttachments.BODY_HEALTH.get());
        if (player.isDeadOrDying() || player.isCreative() || player.isSpectator()) {
            return;
        }
        health.rescale(player.getMaxHealth());

        // 强制死亡（下个 tick 执行，避免在伤害事件里改状态）
        if (health.pendingDeath != DeathCause.NONE) {
            DeathCause cause = health.pendingDeath;
            health.pendingDeath = DeathCause.NONE;
            player.sendSystemMessage(Component.translatable(switch (cause) {
                case HEAD -> "message.selfaid.head_death";
                case TORSO -> "message.selfaid.torso_death";
                default -> "message.selfaid.all_parts_death";
            }));
            player.invulnerableTime = 0;
            player.hurt(player.damageSources().genericKill(), Float.MAX_VALUE);
            return;
        }

        tickHeals(player, health);
        applyLimbPenalties(player, health);
        reconcile(player, health);
    }

    private static void tickHeals(ServerPlayer player, BodyHealth health) {
        if (health.activeHeals().isEmpty()) {
            return;
        }
        boolean changed = false;
        Iterator<ActiveHeal> iterator = health.activeHeals().iterator();
        while (iterator.hasNext()) {
            ActiveHeal heal = iterator.next().tick();
            float applied = 0.0F;
            if (heal.dueNow()) {
                applied = health.healPartReturnApplied(BodyPart.byIndex(heal.partIndex()), heal.amountPerInterval());
                if (applied > 0.0F) {
                    float newHealth = Math.min(player.getMaxHealth(), player.getHealth() + applied);
                    player.setHealth(newHealth);
                }
            }
            float remaining = heal.remainingAmount() - applied;
            if (remaining <= 0.01F || applied == 0.0F && heal.dueNow()) {
                iterator.remove();
                changed = true;
            } else {
                changed = true;
            }
        }
        if (changed) {
            sync(player);
        }
    }

    private static void applyLimbPenalties(ServerPlayer player, BodyHealth health) {
        if (!SelfAidConfig.LIMB_PENALTIES.get()) {
            return;
        }
        boolean armEmpty = health.isPartEmpty(BodyPart.LEFT_ARM) || health.isPartEmpty(BodyPart.RIGHT_ARM);
        boolean legEmpty = health.isPartEmpty(BodyPart.LEFT_LEG) || health.isPartEmpty(BodyPart.RIGHT_LEG);
        boolean bothLegsEmpty = health.isPartEmpty(BodyPart.LEFT_LEG) && health.isPartEmpty(BodyPart.RIGHT_LEG);
        if (armEmpty) {
            player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, 0, true, false, false));
        }
        if (legEmpty) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, bothLegsEmpty ? 2 : 0, true,
                    false, false));
        }
    }

    /** 各部位之和应约等于玩家原生血量；漂移超过 0.5 时拉回来（吸收其他模组/环境造成的变动）。 */
    private static void reconcile(ServerPlayer player, BodyHealth health) {
        if (player.tickCount % 20 != 0) {
            return;
        }
        float diff = player.getHealth() - health.totalHealth();
        if (diff > 0.5F) {
            health.distributeHeal(diff);
            sync(player);
        } else if (diff < -0.5F) {
            health.takeFromHealthiest(-diff);
            sync(player);
        }
    }

    @SubscribeEvent
    public static void onLivingHeal(LivingHealEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player) || !appliesTo(player)) {
            return;
        }
        if (!SelfAidConfig.NATURAL_REGEN_DISTRIBUTES.get()) {
            return;
        }
        BodyHealth health = player.getData(ModAttachments.BODY_HEALTH.get());
        float amount = event.getAmount();
        if (health.distributeHeal(amount) > 0.0F) {
            sync(player);
        }
    }

    // ---------------------------------------------------------------- 生命周期同步

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            BodyHealth health = player.getData(ModAttachments.BODY_HEALTH.get());
            health.rescale(player.getMaxHealth());
            sync(player);
        }
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            player.getData(ModAttachments.BODY_HEALTH.get()).fill(player.getMaxHealth());
            sync(player);
        }
    }

    @SubscribeEvent
    public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            sync(player);
        }
    }

    private BodyHealthEvents() {
    }
}
