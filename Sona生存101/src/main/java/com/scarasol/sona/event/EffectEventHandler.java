package com.scarasol.sona.event;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.scarasol.sona.SonaMod;
import com.scarasol.sona.client.renderer.PositionIndicatorRenderer;
import com.scarasol.sona.configuration.CommonConfig;
import com.scarasol.sona.entity.SoundDecoy;
import com.scarasol.sona.init.SonaMobEffects;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber(modid = SonaMod.MODID)
public class EffectEventHandler {

    public static float tick = 0;

    // NeoForge 1.21 移除了 ZombieEvent.SummonAidEvent，这里通过瞬时压低
    // SPAWN_REINFORCEMENTS_CHANCE 属性（下一 tick 移除）复刻原 DENY 语义，
    // 并在原 ALLOW 分支手动生成一只增援僵尸。见 summonAid / removeSummonAidSuppression。
    private static final ResourceLocation SUMMON_AID_SUPPRESSION_ID = ResourceLocation.fromNamespaceAndPath(SonaMod.MODID, "summon_aid_suppression");

    @SubscribeEvent
    public static void entityTargeting(LivingEvent.LivingVisibilityEvent event) {
        LivingEntity livingEntity = event.getEntity();
        Entity lookingEntity = event.getLookingEntity();
        if (livingEntity == null || lookingEntity == null) {
            return;
        }
        if (lookingEntity instanceof Mob entity_buffer) {
            LivingEntity originalTarget = entity_buffer.getTarget();
            BlockPos targetPos = BlockPos.containing(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ());
            BlockPos mobPos = BlockPos.containing(entity_buffer.getX(), entity_buffer.getY(), entity_buffer.getZ());
            if (SonaEventHooks.shouldCheckNeutrality(entity_buffer, livingEntity)) {
                return;
            }
            double followRange = entity_buffer.getAttributeValue(Attributes.FOLLOW_RANGE);
            MobEffectInstance exposure = livingEntity.getEffect(SonaMobEffects.EXPOSURE);
            if (entity_buffer instanceof Enemy && !(entity_buffer instanceof NeutralMob) && !(livingEntity instanceof SoundDecoy) && exposure != null && (originalTarget == null || !originalTarget.isAlive())) {
                double distance = targetPos.distSqr(mobPos);
                double range = Math.pow(followRange * (exposure.getAmplifier() + 1) * 0.3, 2);
                if (distance < range && !livingEntity.isAlliedTo(entity_buffer)) {
                    if (!(livingEntity instanceof Player player) || !player.getAbilities().instabuild) {
                        entity_buffer.setTarget(livingEntity);
                        return;
                    }
                }
            }
            MobEffectInstance camouflage = livingEntity.getEffect(SonaMobEffects.CAMOUFLAGE);
            if (camouflage != null && !livingEntity.equals(originalTarget)) {
                double distance = targetPos.distSqr(mobPos);
                double range = Math.pow(followRange * (1 / Math.pow(2, camouflage.getAmplifier() + 1)), 2);
                if (distance > range) {
                    event.modifyVisibility(0);
                }
            }
        }
    }

    @SubscribeEvent
    public static void summonAid(LivingDamageEvent.Post event) {
        if (!(event.getEntity() instanceof Zombie zombie) || zombie.level().isClientSide()) {
            return;
        }
        DamageSource source = event.getSource();
        if (source == null || source.isDirect() || !(source.getEntity() instanceof LivingEntity attacker)) {
            return;
        }
        MobEffectInstance camouflage = attacker.getEffect(SonaMobEffects.CAMOUFLAGE);
        if (camouflage == null) {
            return;
        }
        AttributeInstance reinforcements = zombie.getAttribute(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
        if (reinforcements == null) {
            return;
        }
        double distance = zombie.position().distanceTo(attacker.position());
        double followRange = zombie.getAttributeValue(Attributes.FOLLOW_RANGE);
        MobEffectInstance exposure = attacker.getEffect(SonaMobEffects.EXPOSURE);
        boolean allow = false;
        if (exposure != null) {
            double exposureRange = followRange * 0.3 * (exposure.getAmplifier() + 1);
            allow = exposureRange > distance;
        }
        double range = followRange * (1 / Math.pow(2, camouflage.getAmplifier() + 1));
        if (distance > range) {
            // 原 Event.Result.DENY：禁止本次增援召唤
            reinforcements.addTransientModifier(new AttributeModifier(SUMMON_AID_SUPPRESSION_ID, -1, AttributeModifier.Operation.ADD_VALUE));
        } else if (allow) {
            // 原 Event.Result.ALLOW：强制召唤一只增援僵尸（先压掉原版的随机召唤以免重复）
            reinforcements.addTransientModifier(new AttributeModifier(SUMMON_AID_SUPPRESSION_ID, -1, AttributeModifier.Operation.ADD_VALUE));
            if (zombie.level() instanceof ServerLevel serverLevel
                    && serverLevel.getDifficulty() == Difficulty.HARD
                    && serverLevel.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING)) {
                Zombie reinforcement = EntityType.ZOMBIE.create(serverLevel);
                if (reinforcement != null) {
                    reinforcement.moveTo(zombie.getX(), zombie.getY(), zombie.getZ(), zombie.getYRot(), 0);
                    reinforcement.finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(zombie.blockPosition()), MobSpawnType.REINFORCEMENT, null);
                    reinforcement.setTarget(attacker);
                    serverLevel.addFreshEntity(reinforcement);
                }
            }
        }
    }

    @SubscribeEvent
    public static void removeSummonAidSuppression(EntityTickEvent.Post event) {
        if (event.getEntity() instanceof Zombie zombie && !zombie.level().isClientSide()) {
            AttributeInstance reinforcements = zombie.getAttribute(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
            if (reinforcements != null && reinforcements.getModifier(SUMMON_AID_SUPPRESSION_ID) != null) {
                reinforcements.removeModifier(SUMMON_AID_SUPPRESSION_ID);
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void constraintPlayer(InputEvent.MouseButton.Pre event) {
        Player entity = Minecraft.getInstance().player;
        if (entity != null && Minecraft.getInstance().screen == null) {
            if (event.getAction() == 1 && (entity.hasEffect(SonaMobEffects.STUN) || (entity.hasEffect(SonaMobEffects.SLIMINESS) && entity.hasEffect(SonaMobEffects.FROST)))) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onAttacked(LivingDamageEvent.Pre event) {
        LivingEntity target = event.getEntity();
        if (target.hasEffect(SonaMobEffects.STUN)) {
            event.setNewDamage((float) (event.getOriginalDamage() * CommonConfig.STUN_DAMAGE_MULTIPLIER.get()));
        }
    }

    @OnlyIn(Dist.CLIENT)
    private static void renderInfectionOverlay(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
        if (player != null) {
            PositionIndicatorRenderer.renderGuiIndicators(guiGraphics, deltaTracker.getGameTimeDeltaPartialTick(true));
            if (player.hasEffect(SonaMobEffects.INFECTION) || tick > 0) {
                PoseStack poseStack = guiGraphics.pose();
                int width = minecraft.getWindow().getGuiScaledWidth();
                int height = minecraft.getWindow().getGuiScaledHeight();

                RenderSystem.enableBlend();
                RenderSystem.defaultBlendFunc();
                poseStack.pushPose();
                guiGraphics.setColor(1, 1, 1, tick);
                guiGraphics.blit(
                        ResourceLocation.fromNamespaceAndPath(SonaMod.MODID, "textures/screens/effects/infection.png"),
                        0, 0, 0, 0,
                        width, height,
                        width, height
                );
                guiGraphics.setColor(1, 1, 1, 1);
                poseStack.popPose();
                RenderSystem.disableBlend();
            }
            if (!player.hasEffect(SonaMobEffects.INFECTION)) {
                tick = Math.max(0, tick - 0.01f);
            } else {
                tick = Math.min(tick + 0.01f, 1);
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onRenderExposureHints(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_ENTITIES) {
            return;
        }
        PositionIndicatorRenderer.renderWorldHalos(event.getPoseStack(), event.getPartialTick().getGameTimeDeltaPartialTick(true));
    }

    @SubscribeEvent
    public static void onHeal(LivingHealEvent event) {
        LivingEntity livingEntity = event.getEntity();
        MobEffectInstance maim = livingEntity.getEffect(SonaMobEffects.MAIM);
        if (maim != null) {
            float proc = Math.max(1 - 0.15f * (maim.getAmplifier() + 1), 0);
            event.setAmount(proc * event.getAmount());
        }
    }

    @OnlyIn(Dist.CLIENT)
    @EventBusSubscriber(modid = SonaMod.MODID, value = Dist.CLIENT)
    public static class GuiLayerRegistration {
        @SubscribeEvent
        public static void registerLayers(RegisterGuiLayersEvent event) {
            event.registerAboveAll(ResourceLocation.fromNamespaceAndPath(SonaMod.MODID, "infection_overlay"), EffectEventHandler::renderInfectionOverlay);
        }
    }
}
