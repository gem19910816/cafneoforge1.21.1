package net.mcreator.gore.entity;

import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.SynchedEntityData.Builder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.Goal.Flag;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.animation.AnimationController.State;
import software.bernie.geckolib.util.GeckoLibUtil;

public class HandOfAcidEntity extends Monster implements GeoEntity {
   public static final EntityDataAccessor<Boolean> SHOOT = SynchedEntityData.defineId(HandOfAcidEntity.class, EntityDataSerializers.BOOLEAN);
   public static final EntityDataAccessor<String> ANIMATION = SynchedEntityData.defineId(HandOfAcidEntity.class, EntityDataSerializers.STRING);
   public static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(HandOfAcidEntity.class, EntityDataSerializers.STRING);
   private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
   private boolean swinging;
   private boolean lastloop;
   private long lastSwing;
   public String animationprocedure = "empty";
   String prevAnim = "empty";

   public HandOfAcidEntity(EntityType<HandOfAcidEntity> type, Level world) {
      super(type, world);
      this.xpReward = 0;
      this.setNoAi(false);
      this.setPersistenceRequired();
      this.moveControl = new FlyingMoveControl(this, 10, true);
   }

   protected void defineSynchedData(Builder builder) {
      super.defineSynchedData(builder);
      builder.define(SHOOT, false);
      builder.define(ANIMATION, "undefined");
      builder.define(TEXTURE, "hengeyon_hand");
   }

   public void setTexture(String texture) {
      this.entityData.set(TEXTURE, texture);
   }

   public String getTexture() {
      return (String)this.entityData.get(TEXTURE);
   }

   protected PathNavigation createNavigation(Level world) {
      return new FlyingPathNavigation(this, world);
   }

   protected void registerGoals() {
      super.registerGoals();
      this.goalSelector
         .addGoal(
            1,
            new Goal() {
               {
                  this.setFlags(EnumSet.of(Flag.MOVE));
               }

               public boolean canUse() {
                  return HandOfAcidEntity.this.getTarget() != null && !HandOfAcidEntity.this.getMoveControl().hasWanted();
               }

               public boolean canContinueToUse() {
                  return HandOfAcidEntity.this.getMoveControl().hasWanted()
                     && HandOfAcidEntity.this.getTarget() != null
                     && HandOfAcidEntity.this.getTarget().isAlive();
               }

               public void start() {
                  LivingEntity livingentity = HandOfAcidEntity.this.getTarget();
                  Vec3 vec3d = livingentity.getEyePosition(1.0F);
                  HandOfAcidEntity.this.moveControl.setWantedPosition(vec3d.x, vec3d.y, vec3d.z, 2.0);
               }

               public void tick() {
                  LivingEntity livingentity = HandOfAcidEntity.this.getTarget();
                  if (HandOfAcidEntity.this.getBoundingBox().intersects(livingentity.getBoundingBox())) {
                     HandOfAcidEntity.this.doHurtTarget(livingentity);
                  } else {
                     double d0 = HandOfAcidEntity.this.distanceToSqr(livingentity);
                     if (d0 < 16.0) {
                        Vec3 vec3d = livingentity.getEyePosition(1.0F);
                        HandOfAcidEntity.this.moveControl.setWantedPosition(vec3d.x, vec3d.y, vec3d.z, 2.0);
                     }
                  }
               }
            }
         );
      this.goalSelector.addGoal(2, new RandomStrollGoal(this, 2.0, 20) {
         protected Vec3 getPosition() {
            RandomSource random = HandOfAcidEntity.this.getRandom();
            double dir_x = HandOfAcidEntity.this.getX() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double dir_y = HandOfAcidEntity.this.getY() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double dir_z = HandOfAcidEntity.this.getZ() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            return new Vec3(dir_x, dir_y, dir_z);
         }

         public boolean canContinueToUse() {
            double x = HandOfAcidEntity.this.getX();
            double y = HandOfAcidEntity.this.getY();
            double z = HandOfAcidEntity.this.getZ();
            Entity entity = HandOfAcidEntity.this;
            Level world = HandOfAcidEntity.this.level();
            return super.canContinueToUse();
         }
      });
      this.targetSelector.addGoal(3, new HurtByTargetGoal(this, new Class[0]));
      this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
   }

   public boolean removeWhenFarAway(double distanceToClosestPlayer) {
      return false;
   }

   public SoundEvent getHurtSound(DamageSource ds) {
      return (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.generic.hurt"));
   }

   public SoundEvent getDeathSound() {
      return (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.generic.death"));
   }

   public boolean causeFallDamage(float l, float d, DamageSource source) {
      return false;
   }

   public boolean hurt(DamageSource source, float amount) {
      if (source.is(DamageTypes.IN_FIRE)) {
         return false;
      } else if (source.is(DamageTypes.FALL)) {
         return false;
      } else {
         return source.is(DamageTypes.LIGHTNING_BOLT) ? false : super.hurt(source, amount);
      }
   }

   public void addAdditionalSaveData(CompoundTag compound) {
      super.addAdditionalSaveData(compound);
      compound.putString("Texture", this.getTexture());
   }

   public void readAdditionalSaveData(CompoundTag compound) {
      super.readAdditionalSaveData(compound);
      if (compound.contains("Texture")) {
         this.setTexture(compound.getString("Texture"));
      }
   }

   public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {
      ItemStack itemstack = sourceentity.getItemInHand(hand);
      InteractionResult retval = InteractionResult.sidedSuccess(this.level().isClientSide());
      super.mobInteract(sourceentity, hand);
      sourceentity.startRiding(this);
      return retval;
   }

   public void baseTick() {
      super.baseTick();
      this.refreshDimensions();
   }

   public boolean isPushable() {
      return false;
   }

   protected void doPush(Entity entityIn) {
   }

   protected void pushEntities() {
   }

   protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
   }

   public void setNoGravity(boolean ignored) {
      super.setNoGravity(true);
   }

   public void aiStep() {
      super.aiStep();
      this.setNoGravity(true);
   }

   public static void init() {
   }

   public static net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder createAttributes() {
      net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder builder = Mob.createMobAttributes();
      builder = builder.add(Attributes.MOVEMENT_SPEED, 0.1);
      builder = builder.add(Attributes.MAX_HEALTH, 10.0);
      builder = builder.add(Attributes.ARMOR, 3.0);
      builder = builder.add(Attributes.ATTACK_DAMAGE, 9.0);
      builder = builder.add(Attributes.FOLLOW_RANGE, 32.0);
      return builder.add(Attributes.FLYING_SPEED, 0.1);
   }

   private PlayState movementPredicate(AnimationState event) {
      return this.animationprocedure.equals("empty") ? event.setAndContinue(RawAnimation.begin().thenLoop("idle")) : PlayState.STOP;
   }

   private PlayState procedurePredicate(AnimationState event) {
      if (!this.animationprocedure.equals(this.prevAnim) && !this.animationprocedure.equals("empty")) {
         this.prevAnim = this.animationprocedure;
         event.getController().forceAnimationReset();
         event.getController().setAnimation(RawAnimation.begin().thenPlay(this.animationprocedure));
         return PlayState.CONTINUE;
      } else {
         if (!this.animationprocedure.equals("empty") && event.getController().getAnimationState() == State.STOPPED) {
            this.prevAnim = this.animationprocedure;
            event.getController().setAnimation(RawAnimation.begin().thenPlay(this.animationprocedure));
            if (event.getController().getAnimationState() == State.STOPPED) {
               this.animationprocedure = "empty";
               event.getController().forceAnimationReset();
            }
         } else if (this.animationprocedure.equals("empty")) {
            this.prevAnim = "empty";
            return PlayState.STOP;
         }

         return PlayState.CONTINUE;
      }
   }

   protected void tickDeath() {
      this.deathTime++;
      if (this.deathTime == 20) {
         this.remove(RemovalReason.KILLED);
         this.dropExperience(null);
      }
   }

   public String getSyncedAnimation() {
      return (String)this.entityData.get(ANIMATION);
   }

   public void setAnimation(String animation) {
      this.entityData.set(ANIMATION, animation);
   }

   public void registerControllers(ControllerRegistrar data) {
      data.add(new AnimationController[]{new AnimationController(this, "movement", 4, this::movementPredicate)});
      data.add(new AnimationController[]{new AnimationController(this, "procedure", 4, this::procedurePredicate)});
   }

   public AnimatableInstanceCache getAnimatableInstanceCache() {
      return this.cache;
   }
}
