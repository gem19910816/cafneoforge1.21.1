package net.mcreator.gore.procedures;

import java.util.List;
import java.util.Comparator;
import net.mcreator.gore.api.ZombieShoveEvent;
import net.mcreator.gore.api.ZombieShoveResultEvent;
import net.mcreator.gore.configuration.ZombieShoveConfiguration;
import net.mcreator.gore.GoreEditionMod;
import net.mcreator.gore.init.GoreEditionModMobEffects;
import net.mcreator.gore.network.ZombieShovePoseMessage;
import net.neoforged.neoforge.network.PacketDistributor;
import net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket;
import net.minecraft.network.protocol.game.ClientboundRemoveMobEffectPacket;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent.EntityInteractSpecific;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

/**
 * Handles the short area knockdown used by the Gore Edition zombie shove.
 * The original mob stays visible and is temporarily frozen, so its renderer,
 * equipment layers, health, and resource-pack texture remain intact.
 */
@EventBusSubscriber
public final class ZombiePushProcedure {
   private static final String DOWNED_KEY = "gore_downed";
   private static final String PUSH_COOLDOWN_KEY = "gore_push_until";
   private static final String DOWNED_UNTIL_KEY = "gore_downed_until";
   private static final String OLD_NO_AI_KEY = "gore_downed_old_no_ai";
   private static final String STAGGER_KEY = "gore_shove_stagger";
   private static final String SOUND_STAGE_KEY = "gore_shove_sound_stage";
   private static final String BODY_YAW_KEY = "gore_shove_body_yaw";
   private static final String DIRECTION_KEY = "gore_shove_direction";
   private static final String DURATION_KEY = "gore_shove_duration";
   private static final int PUSH_COOLDOWN_TICKS = 18;
   /** Includes the fall, the short hold, and the get-up transition. */
   public static final int DOWNED_TOTAL_TICKS = 84;
   public static final int GET_UP_TICKS = 32;
   public static final int FALL_TICKS = 12;
   public static final int STAGGER_TICKS = 20;
   private static int loggedShoves;
   private static int loggedAttempts;

   private ZombiePushProcedure() {
   }

   @SubscribeEvent(priority = EventPriority.HIGHEST)
   public static void onRightClickEntity(EntityInteract event) {
      InteractionResult result = tryShove(event.getEntity(), event.getHand(), event.getItemStack(), event.getTarget());
      if (result.consumesAction()) {
         event.setCancellationResult(result);
         event.setCanceled(true);
      }
   }

   @SubscribeEvent(priority = EventPriority.HIGHEST)
   public static void onRightClickEntitySpecific(EntityInteractSpecific event) {
      InteractionResult result = tryShove(event.getEntity(), event.getHand(), event.getItemStack(), event.getTarget());
      if (result.consumesAction()) {
         event.setCancellationResult(result);
         event.setCanceled(true);
      }
   }

   private static InteractionResult tryShove(Player player, InteractionHand hand, ItemStack stack, Entity clicked) {
      if (!player.level().isClientSide && hand == InteractionHand.MAIN_HAND
         && isZombieTarget(clicked) && loggedAttempts++ < 5) {
         GoreEditionMod.LOGGER.info("[Gore shove polish-v5] Right-click type={}, item={}, melee={}, spectator={}",
            BuiltInRegistries.ENTITY_TYPE.getKey(clicked.getType()),
            BuiltInRegistries.ITEM.getKey(stack.getItem()), isMeleeOrEmpty(stack), player.isSpectator());
      }
      if (hand != InteractionHand.MAIN_HAND
         || player.isSpectator()
         || !isMeleeOrEmpty(stack)
         || !isZombieTarget(clicked)) {
         return InteractionResult.PASS;
      }

      long now = player.level().getGameTime();
      long cooldown = player.getPersistentData().getLong(PUSH_COOLDOWN_KEY);
      if (cooldown > now && cooldown <= now + PUSH_COOLDOWN_TICKS) {
         return InteractionResult.CONSUME;
      }

      // Minecraft has already sent the entity interaction packet before this
      // client event. SUCCESS gives immediate hand feedback without an attack
      // packet or waiting for the server's knockdown/stagger roll.
      if (player.level().isClientSide) {
         if (nearbyTargets(player, clicked).stream().noneMatch(ZombiePushProcedure::canShoveTarget)) {
            return InteractionResult.CONSUME;
         }
         player.getPersistentData().putLong(PUSH_COOLDOWN_KEY, now + PUSH_COOLDOWN_TICKS);
         return InteractionResult.SUCCESS;
      }
      if (!(player.level() instanceof ServerLevel level)) {
         return InteractionResult.PASS;
      }

      int pushed = pushNearby(level, player, clicked);
      if (pushed > 0) {
         player.getPersistentData().putLong(PUSH_COOLDOWN_KEY, now + PUSH_COOLDOWN_TICKS);
         // Return CONSUME from the server event so the interaction handler
         // does not issue a second swing. This packet is the one authoritative
         // action animation seen by the player and other clients.
         player.swing(InteractionHand.MAIN_HAND, true);
         playSound(level, player, SoundEvents.PLAYER_ATTACK_SWEEP, 0.45F, 0.85F);
         if (loggedShoves++ < 5) {
            GoreEditionMod.LOGGER.info("[Gore shove polish-v5] Pushed {} mobs; clicked type={}",
               pushed, BuiltInRegistries.ENTITY_TYPE.getKey(clicked.getType()));
         }
      }
      return InteractionResult.CONSUME;
   }

   @SubscribeEvent
   public static void onStartTracking(PlayerEvent.StartTracking event) {
      if (event.getEntity() instanceof ServerPlayer player && event.getTarget() instanceof Mob mob) {
         MobEffectInstance marker = mob.getEffect(GoreEditionModMobEffects.ZOMBIE_DOWNED);
         if (marker != null) {
            player.connection.send(new ClientboundUpdateMobEffectPacket(mob.getId(), marker, false));
         }
         MobEffectInstance stagger = mob.getEffect(GoreEditionModMobEffects.ZOMBIE_STAGGERED);
         if (stagger != null) {
            player.connection.send(new ClientboundUpdateMobEffectPacket(mob.getId(), stagger, false));
         }
         if (marker != null || stagger != null) {
            PacketDistributor.sendToPlayer(player, poseMessage(mob,
               marker != null ? marker.getDuration() : stagger.getDuration()));
         }
      }
   }

   @SubscribeEvent
   public static void onEntityTick(EntityTickEvent.Pre event) {
      Entity entity = event.getEntity();
      if (!(entity instanceof Mob mob)
         || !mob.getPersistentData().getBoolean(DOWNED_KEY)
         || !(mob.level() instanceof ServerLevel level)) {
         return;
      }

      long until = mob.getPersistentData().getLong(DOWNED_UNTIL_KEY);
      long now = level.getGameTime();
      boolean stagger = mob.getPersistentData().getBoolean(STAGGER_KEY);
      if (until <= now || !mob.isAlive()
         || !(stagger ? mob.hasEffect(GoreEditionModMobEffects.ZOMBIE_STAGGERED)
            : mob.hasEffect(GoreEditionModMobEffects.ZOMBIE_DOWNED))) {
         clearDowned(mob);
         return;
      }

      // AI resumes only once the visible get-up transition has finished.
      mob.setNoAi(true);
      float frozenYaw = mob.getPersistentData().getFloat(BODY_YAW_KEY);
      mob.yBodyRot = frozenYaw;
      mob.yBodyRotO = frozenYaw;
      mob.setYRot(frozenYaw);
      if (!stagger) {
         long elapsed = DOWNED_TOTAL_TICKS - (until - now);
         int stage = mob.getPersistentData().getInt(SOUND_STAGE_KEY);
         if (stage == 0 && elapsed >= FALL_TICKS && mob.onGround()) {
            playSound(level, mob, SoundEvents.PLAYER_SMALL_FALL, 0.65F, 0.72F);
            mob.getPersistentData().putInt(SOUND_STAGE_KEY, 1);
         }
         if (stage < 2 && elapsed >= DOWNED_TOTAL_TICKS - GET_UP_TICKS + 6) {
            playSound(level, mob, SoundEvents.ZOMBIE_AMBIENT, 0.22F, 0.78F);
            playSound(level, mob, SoundEvents.ARMOR_EQUIP_LEATHER.value(), 0.30F, 0.85F);
            mob.getPersistentData().putInt(SOUND_STAGE_KEY, 2);
         }
      }
   }

   private static int pushNearby(ServerLevel level, Player player, Entity clicked) {
      List<Mob> targets = nearbyTargets(player, clicked);
      int pushed = 0;
      for (Mob target : targets) {
         if (pushed >= ZombieShoveConfiguration.MAX_TARGETS.get()) {
            break;
         }
         if (shoveTarget(level, player, target)) {
            pushed++;
         }
      }
      return pushed;
   }

   private static List<Mob> nearbyTargets(Player player, Entity clicked) {
      double reach = ZombieShoveConfiguration.REACH.get();
      Vec3 look = player.getLookAngle();
      AABB area = player.getBoundingBox().inflate(reach, 1.0D, reach);
      List<Mob> targets = player.level().getEntitiesOfClass(Mob.class, area, entity ->
         isZombieTarget(entity)
            && ZombieShoveRules.inCone(entity.getX() - player.getX(), entity.getZ() - player.getZ(),
               Math.abs(entity.getY() - player.getY()), look.x, look.z, reach,
               ZombieShoveConfiguration.HALF_ANGLE.get())
            && player.hasLineOfSight(entity));
      targets.sort(Comparator.comparingDouble(entity ->
         entity == clicked ? -1.0D : player.distanceToSqr(entity)));

      return targets;
   }

   private static boolean canShoveTarget(Mob target) {
      return !target.isRemoved() && !target.isDeadOrDying()
         && !target.hasEffect(GoreEditionModMobEffects.ZOMBIE_DOWNED)
         && !target.hasEffect(GoreEditionModMobEffects.ZOMBIE_STAGGERED)
         && !target.getPersistentData().getBoolean(DOWNED_KEY);
   }

   private static boolean shoveTarget(ServerLevel level, Player player, Mob target) {
      if (target.getPersistentData().getBoolean(DOWNED_KEY)
         && !target.hasEffect(GoreEditionModMobEffects.ZOMBIE_DOWNED)
         && !target.hasEffect(GoreEditionModMobEffects.ZOMBIE_STAGGERED)) {
         clearDowned(target);
      }
      if (!canShoveTarget(target)) {
         return false;
      }
      double resistance = Math.max(0.0D, Math.min(1.0D,
         target.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE)));
      ZombieShoveEvent event = new ZombieShoveEvent(player, target,
         ZombieShoveConfiguration.DOWNED_CHANCE.get() * (1.0D - resistance),
         ZombieShoveConfiguration.STRENGTH.get() * (1.0D - resistance * 0.65D),
         STAGGER_TICKS);
      if (NeoForge.EVENT_BUS.post(event).isCanceled()) {
         return false;
      }
      boolean downed = ZombieShoveRules.shouldKnockDown(event.getKnockdownChance(), level.random.nextDouble());

      Vec3 away = target.position().subtract(player.position());
      Vec3 horizontal = new Vec3(away.x, 0.0D, away.z);
      if (horizontal.lengthSqr() < 0.0001D) {
         horizontal = player.getLookAngle().multiply(1.0D, 0.0D, 1.0D);
      }
      horizontal = horizontal.normalize().scale(event.getPushStrength() * (downed ? 1.0D : 0.70D));
      int heading = chooseDownedHeading(level, target, horizontal);
      int duration = downed ? DOWNED_TOTAL_TICKS : event.getStaggerTicks();
      float bodyYaw = target.yBodyRot;
      float direction = net.minecraft.util.Mth.wrapDegrees(bodyYaw - heading * (360.0F / 256.0F));

      MobEffectInstance marker = new MobEffectInstance(
         downed ? GoreEditionModMobEffects.ZOMBIE_DOWNED : GoreEditionModMobEffects.ZOMBIE_STAGGERED,
         duration, heading, false, false, false);
      if (!target.addEffect(marker)) {
         return false;
      }
      target.getPersistentData().putBoolean(OLD_NO_AI_KEY, target.isNoAi());
      target.getPersistentData().putBoolean(DOWNED_KEY, true);
      target.getPersistentData().putBoolean(STAGGER_KEY, !downed);
      target.getPersistentData().putInt(SOUND_STAGE_KEY, 0);
      target.getPersistentData().putLong(DOWNED_UNTIL_KEY, level.getGameTime() + duration);
      target.getPersistentData().putFloat(BODY_YAW_KEY, bodyYaw);
      target.getPersistentData().putFloat(DIRECTION_KEY, direction);
      target.getPersistentData().putInt(DURATION_KEY, duration);
      target.setNoAi(true);
      target.getNavigation().stop();
      target.setDeltaMovement(horizontal.x, downed ? 0.06D : 0.015D, horizontal.z);
      target.hurtMarked = true;
      // Non-player effect changes are not automatically sent to spectators of
      // the mob. Send the full duration explicitly to all tracking players.
      level.getChunkSource().broadcastAndSend(target,
         new ClientboundUpdateMobEffectPacket(target.getId(), marker, false));
      PacketDistributor.sendToPlayersTrackingEntity(target, poseMessage(target, duration));
      playSound(level, target, SoundEvents.ZOMBIE_HURT, 0.28F, 0.82F + level.random.nextFloat() * 0.12F);
      NeoForge.EVENT_BUS.post(new ZombieShoveResultEvent(player, target, downed));
      return true;
   }

   private static ZombieShovePoseMessage poseMessage(Mob mob, int remaining) {
      return new ZombieShovePoseMessage(mob.getUUID(),
         mob.getPersistentData().getFloat(BODY_YAW_KEY),
         mob.getPersistentData().getFloat(DIRECTION_KEY),
         mob.getPersistentData().getInt(DURATION_KEY), remaining);
   }

   private static void playSound(ServerLevel level, Entity entity,
      net.minecraft.sounds.SoundEvent sound, float volume, float pitch) {
      float configuredVolume = (float) (volume * ZombieShoveConfiguration.SOUND_VOLUME.get());
      if (configuredVolume > 0.0F) {
         level.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
            sound, SoundSource.HOSTILE, configuredVolume, pitch);
      }
   }

   private static int chooseDownedHeading(ServerLevel level, Mob target, Vec3 away) {
      double preferred = Math.atan2(-away.x, away.z);
      double selected = preferred;
      double length = target.getBbHeight() * 0.95D;
      double width = target.getBbWidth() * 0.55D;
      for (int offset : new int[] {0, 45, -45, 90, -90, 135, -135, 180}) {
         double yaw = preferred + Math.toRadians(offset);
         double dx = -Math.sin(yaw) * length;
         double dz = Math.cos(yaw) * length;
         AABB footprint = new AABB(
            target.getX() + Math.min(0.0D, dx) - width, target.getY() + 0.06D,
            target.getZ() + Math.min(0.0D, dz) - width,
            target.getX() + Math.max(0.0D, dx) + width, target.getY() + 0.55D,
            target.getZ() + Math.max(0.0D, dz) + width);
         if (!level.getBlockCollisions(target, footprint).iterator().hasNext()) {
            selected = yaw;
            break;
         }
      }
      double degrees = (Math.toDegrees(selected) % 360.0D + 360.0D) % 360.0D;
      return ((int) Math.round(degrees * 256.0D / 360.0D)) & 255;
   }

   private static void clearDowned(Mob mob) {
      mob.setNoAi(mob.getPersistentData().getBoolean(OLD_NO_AI_KEY));
      mob.getPersistentData().remove(DOWNED_KEY);
      mob.getPersistentData().remove(DOWNED_UNTIL_KEY);
      mob.getPersistentData().remove(OLD_NO_AI_KEY);
      mob.getPersistentData().remove(STAGGER_KEY);
      mob.getPersistentData().remove(SOUND_STAGE_KEY);
      mob.getPersistentData().remove(BODY_YAW_KEY);
      mob.getPersistentData().remove(DIRECTION_KEY);
      mob.getPersistentData().remove(DURATION_KEY);
      mob.removeEffect(GoreEditionModMobEffects.ZOMBIE_DOWNED);
      mob.removeEffect(GoreEditionModMobEffects.ZOMBIE_STAGGERED);
      if (mob.level() instanceof ServerLevel level) {
         level.getChunkSource().broadcastAndSend(mob,
            new ClientboundRemoveMobEffectPacket(mob.getId(), GoreEditionModMobEffects.ZOMBIE_DOWNED));
         level.getChunkSource().broadcastAndSend(mob,
            new ClientboundRemoveMobEffectPacket(mob.getId(), GoreEditionModMobEffects.ZOMBIE_STAGGERED));
      }
      MobEffectInstance effect = mob.getEffect(MobEffects.MOVEMENT_SLOWDOWN);
      if (effect != null && effect.getAmplifier() == 255) {
         mob.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
         if (mob.level() instanceof ServerLevel level) {
            level.getChunkSource().broadcastAndSend(mob,
               new ClientboundRemoveMobEffectPacket(mob.getId(), MobEffects.MOVEMENT_SLOWDOWN));
         }
      }
   }

   private static boolean isMeleeOrEmpty(ItemStack stack) {
      return stack.isEmpty()
         || stack.is(ItemTags.SWORDS)
         || stack.is(ItemTags.AXES)
         || stack.is(ItemTags.PICKAXES)
         || stack.is(ItemTags.SHOVELS)
         || stack.is(ItemTags.HOES)
         || stack.is(ItemTags.MACE_ENCHANTABLE)
         || stack.getAttributeModifiers().modifiers().stream().anyMatch(entry ->
            entry.attribute().equals(Attributes.ATTACK_DAMAGE) && entry.modifier().amount() > 0.0D);
   }

   private static boolean isZombieTarget(Entity entity) {
      if (!(entity instanceof Mob)) {
         return false;
      }
      if (entity instanceof Zombie || entity instanceof Husk || entity instanceof Drowned) {
         return true;
      }
      ResourceLocation id = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType());
      String path = id.getPath();
      if (path.contains("corpse")) {
         return false;
      }
      return id.getNamespace().equals("gore_edition")
         && (path.contains("zombie") || path.contains("husk") || path.contains("drowned"));
   }
}
