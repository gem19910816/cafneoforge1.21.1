package net.mcreator.gore.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.mcreator.gore.procedures.ZombiePushProcedure;
import net.mcreator.gore.init.GoreEditionModMobEffects;
import software.bernie.geckolib.event.GeoRenderEvent;
import org.joml.Vector3f;
import org.joml.Quaternionf;

/**
 * Applies the shove pose to the original zombie render stack. Because this is
 * done before the renderer draws its model, vanilla armor and all render layers
 * receive the exact same transform as the zombie body.
 */
@EventBusSubscriber(value = Dist.CLIENT)
public final class ZombieDownedRenderHandler {
   private static final java.util.Map<MobEffectInstance, Integer> STAGGER_DURATIONS = new java.util.WeakHashMap<>();

   private ZombieDownedRenderHandler() {
   }

   public static void applyLivingPose(PoseStack stack, LivingEntity entity, float partialTick) {
      if (!isZombieTarget(entity)) {
         return;
      }

      MobEffectInstance effect = entity.getEffect(GoreEditionModMobEffects.ZOMBIE_DOWNED);
      MobEffectInstance stagger = entity.getEffect(GoreEditionModMobEffects.ZOMBIE_STAGGERED);
      ZombieShovePoseClient.Pose state = ZombieShovePoseClient.get(entity);
      if (effect == null && stagger == null && state == null) {
         return;
      }
      if (effect == null) {
         applyStagger(stack, entity, partialTick);
         return;
      }

      float elapsed = ZombiePushProcedure.DOWNED_TOTAL_TICKS - effect.getDuration() + partialTick;
      applyPose(stack, entity, partialTick, elapsed);
   }

   public static void freezeFacing(LivingEntity entity) {
      ZombieShovePoseClient.Pose pose = ZombieShovePoseClient.get(entity);
      if (pose == null && !entity.hasEffect(GoreEditionModMobEffects.ZOMBIE_DOWNED)
         && !entity.hasEffect(GoreEditionModMobEffects.ZOMBIE_STAGGERED)) return;
      if (pose != null) {
         entity.yBodyRot = pose.bodyYaw();
         entity.yBodyRotO = pose.bodyYaw();
      }
   }

   public static Runnable applyHumanoidPose(HumanoidModel<?> model, LivingEntity entity, float partialTick) {
      MobEffectInstance effect = entity.getEffect(GoreEditionModMobEffects.ZOMBIE_DOWNED);
      MobEffectInstance stagger = entity.getEffect(GoreEditionModMobEffects.ZOMBIE_STAGGERED);
      ZombieShovePoseClient.Pose state = ZombieShovePoseClient.get(entity);
      if (!isZombieTarget(entity) || (effect == null && stagger == null && state == null)) {
         return null;
      }
      ZombieAnimationCompat.evaluateBasePose(model, entity);
      ModelPart[] parts = {model.head, model.hat, model.body, model.leftArm,
         model.rightArm, model.leftLeg, model.rightLeg};
      PartPose[] original = new PartPose[parts.length];
      Vector3f[] scales = new Vector3f[parts.length];
      for (int i = 0; i < parts.length; i++) {
         original[i] = parts[i].storePose();
         scales[i] = new Vector3f(parts[i].xScale, parts[i].yScale, parts[i].zScale);
      }
      MobEffectInstance marker = effect == null ? stagger : effect;
      float duration = state != null ? state.duration()
         : effect == null ? staggerDuration(stagger) : ZombiePushProcedure.DOWNED_TOTAL_TICKS;
      float elapsed = state != null ? state.elapsed(entity.level().getGameTime(), partialTick)
         : duration - marker.getDuration() + partialTick;
      boolean downed = effect != null
         || (stagger == null && state != null
            && state.duration() == ZombiePushProcedure.DOWNED_TOTAL_TICKS);
      float seconds = downed ? elapsed / 20.0F : elapsed / duration;
      float direction = state != null ? state.direction()
         : Mth.wrapDegrees(entity.yBodyRot - marker.getAmplifier() * (360.0F / 256.0F));
      float weight = ZombieShoveAnimation.weight(elapsed, duration);
      posePart(model.head, downed, direction, "head", seconds, weight);
      posePart(model.body, downed, direction, "body", seconds, weight);
      posePart(model.leftArm, downed, direction, "leftArm", seconds, weight);
      posePart(model.rightArm, downed, direction, "rightArm", seconds, weight);
      posePart(model.leftLeg, downed, direction, "leftLeg", seconds, weight);
      posePart(model.rightLeg, downed, direction, "rightLeg", seconds, weight);

      // Humanoid parts are flat, unlike the Blockbench reference hierarchy.
      // Carry the upper-body pivots and rotations together around the waist.
      Vector3f torso = rotation(downed, direction, "body", seconds).mul(weight);
      Quaternionf torsoRotation = new Quaternionf().rotationZYX(torso.z, torso.y, torso.x);
      for (ModelPart part : new ModelPart[] {model.body, model.head, model.leftArm, model.rightArm}) {
         Vector3f pivot = new Vector3f(part.x, part.y - 12.0F, part.z);
         torsoRotation.transform(pivot);
         part.x = pivot.x;
         part.y = pivot.y + 12.0F;
         part.z = pivot.z;
         if (part != model.body) {
            Quaternionf local = new Quaternionf().rotationZYX(part.zRot, part.yRot, part.xRot);
            Vector3f combined = new Quaternionf(torsoRotation).mul(local).getEulerAnglesZYX(new Vector3f());
            part.xRot = combined.x;
            part.yRot = combined.y;
            part.zRot = combined.z;
         }
      }
      model.hat.x = Mth.lerp(weight, model.hat.x, model.head.x);
      model.hat.y = Mth.lerp(weight, model.hat.y, model.head.y);
      model.hat.z = Mth.lerp(weight, model.hat.z, model.head.z);
      model.hat.xScale = Mth.lerp(weight, model.hat.xScale, model.head.xScale);
      model.hat.yScale = Mth.lerp(weight, model.hat.yScale, model.head.yScale);
      model.hat.zScale = Mth.lerp(weight, model.hat.zScale, model.head.zScale);
      blend(model.hat, model.head.xRot, model.head.yRot, model.head.zRot, weight);
      return () -> {
         for (int i = 0; i < parts.length; i++) {
            parts[i].loadPose(original[i]);
            parts[i].xScale = scales[i].x;
            parts[i].yScale = scales[i].y;
            parts[i].zScale = scales[i].z;
         }
      };
   }

   private static void blend(ModelPart part, float x, float y, float z, float weight) {
      if (weight <= 0.0F) return;
      Vector3f angles = ZombieShoveAnimation.blendRotation(
         new Vector3f(part.xRot, part.yRot, part.zRot), new Vector3f(x, y, z), weight);
      part.xRot = angles.x;
      part.yRot = angles.y;
      part.zRot = angles.z;
   }

   private static void posePart(ModelPart part, boolean downed, float direction, String bone, float seconds, float weight) {
      PartPose rest = part.getInitialPose();
      part.x = Mth.lerp(weight, part.x, rest.x);
      part.y = Mth.lerp(weight, part.y, rest.y);
      part.z = Mth.lerp(weight, part.z, rest.z);
      part.xScale = Mth.lerp(weight, part.xScale, 1.0F);
      part.yScale = Mth.lerp(weight, part.yScale, 1.0F);
      part.zScale = Mth.lerp(weight, part.zScale, 1.0F);
      Vector3f angles = rotation(downed, direction, bone, seconds);
      blend(part, angles.x, angles.y, angles.z, weight);
   }

   private static Vector3f rotation(boolean downed, float direction, String bone, float seconds) {
      Vector3f frame = ZombieShoveAnimation.directional(downed, direction, bone, "rotation", seconds);
      // Blockbench's root is converted into world space, while child bones
      // cross the model's handedness conversion on both sides of the local
      // quaternion. Using the root conversion for limbs mirrors their yaw
      // and roll during the recovery phase.
      return ZombieShoveAnimation.modelRotation(frame);
   }

   @SubscribeEvent
   public static void onRenderGeoEntity(GeoRenderEvent.Entity.Pre event) {
      if (!(event.getEntity() instanceof LivingEntity entity) || !isGoreZombieTarget(entity)) {
         return;
      }
      ZombieAppearanceClient.capture(entity, geoTexture(event.getRenderer(), entity));
      freezeFacing(entity);

      MobEffectInstance effect = entity.getEffect(GoreEditionModMobEffects.ZOMBIE_DOWNED);
      if (effect == null) {
         applyStagger(event.getPoseStack(), entity, event.getPartialTick());
         return;
      }

      float elapsed = ZombiePushProcedure.DOWNED_TOTAL_TICKS - effect.getDuration() + event.getPartialTick();
      // GeckoLib already encloses its Pre event in its own push/pop pair.
      applyPose(event.getPoseStack(), entity, event.getPartialTick(), elapsed);
   }

   @SuppressWarnings({"rawtypes", "unchecked"})
   private static ResourceLocation geoTexture(software.bernie.geckolib.renderer.GeoEntityRenderer renderer, Entity entity) {
      return renderer.getTextureLocation(entity);
   }

   private static void applyPose(PoseStack poseStack, LivingEntity entity, float partialTick, float elapsed) {
      MobEffectInstance marker = entity.getEffect(GoreEditionModMobEffects.ZOMBIE_DOWNED);
      ZombieShovePoseClient.Pose state = ZombieShovePoseClient.get(entity);
      if (state != null) elapsed = state.elapsed(entity.level().getGameTime(), partialTick);
      float yaw = marker == null ? 0.0F : marker.getAmplifier() * (360.0F / 256.0F);
      float direction = state != null ? state.direction() : Mth.wrapDegrees(entity.yBodyRot - yaw);
      Vector3f root = ZombieShoveAnimation.directional(true, direction, "root", "rotation", elapsed / 20.0F);
      Vector3f position = ZombieShoveAnimation.directional(true, direction, "root", "position", elapsed / 20.0F);
      float scale = entity.getBbHeight() / 1.95F;
      poseStack.translate(position.x * scale / 16.0F, position.y * scale / 16.0F, position.z * scale / 16.0F);
      applyRootRotation(poseStack, root, state != null ? state.bodyYaw() : entity.yBodyRot);
   }

   private static int staggerDuration(MobEffectInstance marker) {
      return STAGGER_DURATIONS.computeIfAbsent(marker, effect ->
         Math.max(10, effect.getDuration()));
   }

   private static void applyStagger(PoseStack stack, LivingEntity entity, float partialTick) {
      MobEffectInstance marker = entity.getEffect(GoreEditionModMobEffects.ZOMBIE_STAGGERED);
      ZombieShovePoseClient.Pose state = ZombieShovePoseClient.get(entity);
      if (marker == null && state == null) {
         return;
      }
      if (marker == null && state.duration() == ZombiePushProcedure.DOWNED_TOTAL_TICKS) {
         applyPose(stack, entity, partialTick,
            state.elapsed(entity.level().getGameTime(), partialTick));
         return;
      }
      float duration = state != null ? state.duration() : staggerDuration(marker);
      float elapsed = state != null ? state.elapsed(entity.level().getGameTime(), partialTick)
         : duration - marker.getDuration() + partialTick;
      float yaw = marker.getAmplifier() * (360.0F / 256.0F);
      float direction = state != null ? state.direction() : Mth.wrapDegrees(entity.yBodyRot - yaw);
      Vector3f root = ZombieShoveAnimation.directional(false, direction, "root", "rotation", elapsed / duration);
      applyRootRotation(stack, root, state != null ? state.bodyYaw() : entity.yBodyRot);
   }

   private static void applyRootRotation(PoseStack stack, Vector3f root, float bodyYaw) {
      // One tilt axis preserves the full fall angle along diagonals and the
      // authored side roll during recovery, without Euler-order distortion.
      float angle = (float) Math.hypot(root.x, root.z);
      if (angle < 0.00001F) return;
      stack.mulPose(Axis.YP.rotationDegrees(-bodyYaw));
      stack.mulPose(new Quaternionf().rotationAxis(angle * Mth.DEG_TO_RAD,
         -root.x / angle, 0.0F, -root.z / angle));
      stack.mulPose(Axis.YP.rotationDegrees(bodyYaw));
   }

   private static boolean isZombieTarget(Entity entity) {
      return entity instanceof Zombie || entity instanceof Husk || entity instanceof Drowned;
   }

   private static boolean isGoreZombieTarget(Entity entity) {
      if (isZombieTarget(entity)) {
         return true;
      }
      ResourceLocation id = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType());
      String path = id.getPath();
      return id.getNamespace().equals("gore_edition")
         && !path.contains("corpse")
         && (path.contains("zombie") || path.contains("husk") || path.contains("drowned"));
   }
}
