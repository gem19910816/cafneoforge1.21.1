package net.mcreator.gore.entity;

import net.mcreator.gore.procedures.NowindOnEntityTickUpdateProcedure;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.SynchedEntityData.Builder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FollowMobGoal;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.animation.AnimationController.State;
import software.bernie.geckolib.util.GeckoLibUtil;

public class NowindEntity extends Monster implements GeoEntity {
   public static final EntityDataAccessor<Boolean> SHOOT = SynchedEntityData.defineId(NowindEntity.class, EntityDataSerializers.BOOLEAN);
   public static final EntityDataAccessor<String> ANIMATION = SynchedEntityData.defineId(NowindEntity.class, EntityDataSerializers.STRING);
   public static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(NowindEntity.class, EntityDataSerializers.STRING);
   private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
   private boolean swinging;
   private boolean lastloop;
   private long lastSwing;
   public String animationprocedure = "empty";
   String prevAnim = "empty";

   public NowindEntity(EntityType<NowindEntity> type, Level world) {
      super(type, world);
      this.xpReward = 7;
      this.setNoAi(false);
   }

   protected void defineSynchedData(Builder builder) {
      super.defineSynchedData(builder);
      builder.define(SHOOT, false);
      builder.define(ANIMATION, "undefined");
      builder.define(TEXTURE, "nowind");
   }

   public void setTexture(String texture) {
      this.entityData.set(TEXTURE, texture);
   }

   public String getTexture() {
      return (String)this.entityData.get(TEXTURE);
   }

   protected void registerGoals() {
      super.registerGoals();
      this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0, false) {
         protected double getAttackReachSqr(LivingEntity entity) {
            return 2.89;
         }
      });
      this.targetSelector.addGoal(2, new HurtByTargetGoal(this, new Class[0]));
      this.goalSelector.addGoal(3, new FollowMobGoal(this, 1.0, 20.0F, 2.0F));
      this.goalSelector.addGoal(4, new RandomStrollGoal(this, 1.0));
      this.goalSelector.addGoal(5, new LeapAtTargetGoal(this, 0.5F));
      this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
   }

   public SoundEvent getHurtSound(DamageSource ds) {
      return (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:entity.nowind_hurt"));
   }

   public SoundEvent getDeathSound() {
      return (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.generic.death"));
   }

   public boolean hurt(DamageSource source, float amount) {
      if (source.is(DamageTypes.IN_FIRE)) {
         return false;
      } else if (source.is(DamageTypes.FALL)) {
         return false;
      } else if (source.is(DamageTypes.EXPLOSION)) {
         return false;
      } else if (source.is(DamageTypes.WITHER)) {
         return false;
      } else {
         return source.is(DamageTypes.WITHER_SKULL) ? false : super.hurt(source, amount);
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

   public void baseTick() {
      super.baseTick();
      NowindOnEntityTickUpdateProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), this);
      this.refreshDimensions();
   }

   public static void init() {
   }

   public static net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder createAttributes() {
      net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder builder = Mob.createMobAttributes();
      builder = builder.add(Attributes.MOVEMENT_SPEED, 0.27);
      builder = builder.add(Attributes.MAX_HEALTH, 16.0);
      builder = builder.add(Attributes.ARMOR, 0.0);
      builder = builder.add(Attributes.ATTACK_DAMAGE, 6.0);
      builder = builder.add(Attributes.FOLLOW_RANGE, 64.0);
      return builder.add(Attributes.ATTACK_KNOCKBACK, 0.5);
   }

   private PlayState movementPredicate(AnimationState event) {
      if (!this.animationprocedure.equals("empty")) {
         return PlayState.STOP;
      } else {
         return !event.isMoving() && event.getLimbSwingAmount() > -0.15F && event.getLimbSwingAmount() < 0.15F
            ? event.setAndContinue(RawAnimation.begin().thenLoop("idle"))
            : event.setAndContinue(RawAnimation.begin().thenLoop("walk"));
      }
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
