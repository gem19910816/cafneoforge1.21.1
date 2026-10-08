package net.mcreator.gore.entity;

import net.mcreator.gore.procedures.DeformityForAshesOnEntityTickUpdateProcedure;
import net.mcreator.gore.procedures.DeformityForAshesStopAttackingProcedure;
import net.mcreator.gore.procedures.DeformtyForAshesDontMessAroundConditionProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.SynchedEntityData.Builder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.animation.AnimationController.State;
import software.bernie.geckolib.util.GeckoLibUtil;

public class DeformityForAshesEntity extends Monster implements GeoEntity {
   public static final EntityDataAccessor<Boolean> SHOOT = SynchedEntityData.defineId(DeformityForAshesEntity.class, EntityDataSerializers.BOOLEAN);
   public static final EntityDataAccessor<String> ANIMATION = SynchedEntityData.defineId(DeformityForAshesEntity.class, EntityDataSerializers.STRING);
   public static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(DeformityForAshesEntity.class, EntityDataSerializers.STRING);
   private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
   private boolean swinging;
   private boolean lastloop;
   private long lastSwing;
   public String animationprocedure = "empty";
   String prevAnim = "empty";

   public DeformityForAshesEntity(EntityType<DeformityForAshesEntity> type, Level world) {
      super(type, world);
      this.xpReward = 0;
      this.setNoAi(false);
      this.setPersistenceRequired();
   }

   protected void defineSynchedData(Builder builder) {
      super.defineSynchedData(builder);
      builder.define(SHOOT, false);
      builder.define(ANIMATION, "undefined");
      builder.define(TEXTURE, "ashes_deformity");
   }

   public void setTexture(String texture) {
      this.entityData.set(TEXTURE, texture);
   }

   public String getTexture() {
      return (String)this.entityData.get(TEXTURE);
   }

   protected void registerGoals() {
      super.registerGoals();
      this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.4, true) {
         protected double getAttackReachSqr(LivingEntity entity) {
            return 25.0;
         }

         public boolean canUse() {
            double x = DeformityForAshesEntity.this.getX();
            double y = DeformityForAshesEntity.this.getY();
            double z = DeformityForAshesEntity.this.getZ();
            Entity entity = DeformityForAshesEntity.this;
            Level world = DeformityForAshesEntity.this.level();
            return super.canUse() && DeformityForAshesStopAttackingProcedure.execute(entity);
         }

         public boolean canContinueToUse() {
            double x = DeformityForAshesEntity.this.getX();
            double y = DeformityForAshesEntity.this.getY();
            double z = DeformityForAshesEntity.this.getZ();
            Entity entity = DeformityForAshesEntity.this;
            Level world = DeformityForAshesEntity.this.level();
            return super.canContinueToUse() && DeformityForAshesStopAttackingProcedure.execute(entity);
         }
      });
      this.goalSelector.addGoal(2, new RandomStrollGoal(this, 2.0) {
         public boolean canUse() {
            double x = DeformityForAshesEntity.this.getX();
            double y = DeformityForAshesEntity.this.getY();
            double z = DeformityForAshesEntity.this.getZ();
            Entity entity = DeformityForAshesEntity.this;
            Level world = DeformityForAshesEntity.this.level();
            return super.canUse() && DeformtyForAshesDontMessAroundConditionProcedure.execute(entity);
         }

         public boolean canContinueToUse() {
            double x = DeformityForAshesEntity.this.getX();
            double y = DeformityForAshesEntity.this.getY();
            double z = DeformityForAshesEntity.this.getZ();
            Entity entity = DeformityForAshesEntity.this;
            Level world = DeformityForAshesEntity.this.level();
            return super.canContinueToUse() && DeformtyForAshesDontMessAroundConditionProcedure.execute(entity);
         }
      });
      this.goalSelector.addGoal(3, new RandomLookAroundGoal(this) {
         public boolean canUse() {
            double x = DeformityForAshesEntity.this.getX();
            double y = DeformityForAshesEntity.this.getY();
            double z = DeformityForAshesEntity.this.getZ();
            Entity entity = DeformityForAshesEntity.this;
            Level world = DeformityForAshesEntity.this.level();
            return super.canUse() && DeformtyForAshesDontMessAroundConditionProcedure.execute(entity);
         }

         public boolean canContinueToUse() {
            double x = DeformityForAshesEntity.this.getX();
            double y = DeformityForAshesEntity.this.getY();
            double z = DeformityForAshesEntity.this.getZ();
            Entity entity = DeformityForAshesEntity.this;
            Level world = DeformityForAshesEntity.this.level();
            return super.canContinueToUse() && DeformtyForAshesDontMessAroundConditionProcedure.execute(entity);
         }
      });
      this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, ServerPlayer.class, false, false));
      this.targetSelector.addGoal(5, new NearestAttackableTargetGoal(this, Player.class, false, false));
   }

   public boolean removeWhenFarAway(double distanceToClosestPlayer) {
      return false;
   }

   public void playStepSound(BlockPos pos, BlockState blockIn) {
      this.playSound((SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:entity.flesh_eater.steps")), 0.15F, 1.0F);
   }

   public SoundEvent getHurtSound(DamageSource ds) {
      return (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:entity.deformity.hurt"));
   }

   public SoundEvent getDeathSound() {
      return (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:entity.deformity.die"));
   }

   public boolean hurt(DamageSource source, float amount) {
      if (source.is(DamageTypes.FALL)) {
         return false;
      } else if (source.is(DamageTypes.CACTUS)) {
         return false;
      } else if (source.is(DamageTypes.DROWN)) {
         return false;
      } else if (source.is(DamageTypes.FALLING_ANVIL)) {
         return false;
      } else if (source.is(DamageTypes.DRAGON_BREATH)) {
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
      DeformityForAshesOnEntityTickUpdateProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), this);
      this.refreshDimensions();
   }

   public static void init() {
   }

   public static net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder createAttributes() {
      net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder builder = Mob.createMobAttributes();
      builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
      builder = builder.add(Attributes.MAX_HEALTH, 80.0);
      builder = builder.add(Attributes.ARMOR, 0.0);
      builder = builder.add(Attributes.ATTACK_DAMAGE, 11.0);
      builder = builder.add(Attributes.FOLLOW_RANGE, 128.0);
      builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 0.4);
      return builder.add(Attributes.ATTACK_KNOCKBACK, 40.0);
   }

   private PlayState movementPredicate(AnimationState event) {
      if (this.animationprocedure.equals("empty")) {
         if (!event.isMoving() && event.getLimbSwingAmount() > -0.15F && event.getLimbSwingAmount() < 0.15F) {
            return this.isSprinting() ? event.setAndContinue(RawAnimation.begin().thenLoop("")) : event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
         } else {
            return event.setAndContinue(RawAnimation.begin().thenLoop("walk"));
         }
      } else {
         return PlayState.STOP;
      }
   }

   private PlayState attackingPredicate(AnimationState event) {
      double d1 = this.getX() - this.xOld;
      double d0 = this.getZ() - this.zOld;
      float velocity = (float)Math.sqrt(d1 * d1 + d0 * d0);
      if (this.getAttackAnim(event.getPartialTick()) > 0.0F && !this.swinging) {
         this.swinging = true;
         this.lastSwing = this.level().getGameTime();
      }

      if (this.swinging && this.lastSwing + 7L <= this.level().getGameTime()) {
         this.swinging = false;
      }

      if (this.swinging && event.getController().getAnimationState() == State.STOPPED) {
         event.getController().forceAnimationReset();
         return event.setAndContinue(RawAnimation.begin().thenPlay("attack"));
      } else {
         return PlayState.CONTINUE;
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
      data.add(new AnimationController[]{new AnimationController(this, "attacking", 4, this::attackingPredicate)});
      data.add(new AnimationController[]{new AnimationController(this, "procedure", 4, this::procedurePredicate)});
   }

   public AnimatableInstanceCache getAnimatableInstanceCache() {
      return this.cache;
   }
}
