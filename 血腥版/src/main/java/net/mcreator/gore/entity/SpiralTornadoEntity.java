package net.mcreator.gore.entity;

import net.mcreator.gore.init.GoreEditionModEntities;
import net.mcreator.gore.procedures.SpiralTornadoOnEntityTickUpdateProcedure;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.neoforged.neoforge.event.EventHooks;

public class SpiralTornadoEntity extends TamableAnimal {
   public SpiralTornadoEntity(EntityType<SpiralTornadoEntity> type, Level world) {
      super(type, world);
      this.xpReward = 0;
      this.setNoAi(false);
   }

   protected void registerGoals() {
      super.registerGoals();
      this.targetSelector.addGoal(1, new OwnerHurtTargetGoal(this));
      this.goalSelector.addGoal(2, new OwnerHurtByTargetGoal(this));
      this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.0, false) {
         protected double getAttackReachSqr(LivingEntity entity) {
            return (double)(this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth());
         }
      });
      this.goalSelector.addGoal(4, new FollowOwnerGoal(this, 0.8, 10.0F, 2.0F));
   }

   public double getMyRidingOffset() {
      return -0.35;
   }

   public SoundEvent getAmbientSound() {
      return (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:entity.spiral_tornado.idle"));
   }

   public SoundEvent getHurtSound(DamageSource ds) {
      return (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:entity.spiral_tornado.hurt"));
   }

   public SoundEvent getDeathSound() {
      return (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:entity.spiral_tornado.die"));
   }

   public boolean hurt(DamageSource damagesource, float amount) {
      if (damagesource.getDirectEntity() instanceof ThrownPotion || damagesource.getDirectEntity() instanceof AreaEffectCloud) {
         return false;
      } else if (damagesource.is(DamageTypes.FALL)) {
         return false;
      } else if (damagesource.is(DamageTypes.CACTUS)) {
         return false;
      } else if (damagesource.is(DamageTypes.LIGHTNING_BOLT)) {
         return false;
      } else {
         return damagesource.is(DamageTypes.FALLING_ANVIL) ? false : super.hurt(damagesource, amount);
      }
   }

   public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {
      ItemStack itemstack = sourceentity.getItemInHand(hand);
      InteractionResult retval = InteractionResult.sidedSuccess(this.level().isClientSide());
      Item item = itemstack.getItem();
      if (itemstack.getItem() instanceof SpawnEggItem) {
         retval = super.mobInteract(sourceentity, hand);
      } else if (this.level().isClientSide()) {
         retval = (!this.isTame() || !this.isOwnedBy(sourceentity)) && !this.isFood(itemstack)
            ? InteractionResult.PASS
            : InteractionResult.sidedSuccess(this.level().isClientSide());
      } else if (this.isTame()) {
         if (this.isOwnedBy(sourceentity)) {
            if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
               this.usePlayerItem(sourceentity, hand, itemstack);
               this.heal((float)item.getFoodProperties(itemstack, this).nutrition());
               retval = InteractionResult.sidedSuccess(this.level().isClientSide());
            } else if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
               this.usePlayerItem(sourceentity, hand, itemstack);
               this.heal(4.0F);
               retval = InteractionResult.sidedSuccess(this.level().isClientSide());
            } else {
               retval = super.mobInteract(sourceentity, hand);
            }
         }
      } else if (this.isFood(itemstack)) {
         this.usePlayerItem(sourceentity, hand, itemstack);
         if (this.random.nextInt(3) == 0 && !EventHooks.onAnimalTame(this, sourceentity)) {
            this.tame(sourceentity);
            this.level().broadcastEntityEvent(this, (byte)7);
         } else {
            this.level().broadcastEntityEvent(this, (byte)6);
         }

         this.setPersistenceRequired();
         retval = InteractionResult.sidedSuccess(this.level().isClientSide());
      } else {
         retval = super.mobInteract(sourceentity, hand);
         if (retval == InteractionResult.SUCCESS || retval == InteractionResult.CONSUME) {
            this.setPersistenceRequired();
         }
      }

      return retval;
   }

   public void baseTick() {
      super.baseTick();
      SpiralTornadoOnEntityTickUpdateProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), this);
   }

   public AgeableMob getBreedOffspring(ServerLevel serverWorld, AgeableMob ageable) {
      SpiralTornadoEntity retval = (SpiralTornadoEntity)((EntityType)GoreEditionModEntities.SPIRAL_TORNADO.get()).create(serverWorld);
      retval.finalizeSpawn(serverWorld, serverWorld.getCurrentDifficultyAt(retval.blockPosition()), MobSpawnType.BREEDING, null);
      return retval;
   }

   public boolean isFood(ItemStack stack) {
      return Ingredient.of().test(stack);
   }

   public static void init() {
      GoreEditionModEntities.SPAWN_PLACEMENTS
         .add(
            new GoreEditionModEntities.SpawnEntry(
               (EntityType)GoreEditionModEntities.SPIRAL_TORNADO.get(),
               SpawnPlacementTypes.ON_GROUND,
               Types.MOTION_BLOCKING_NO_LEAVES,
               (entityType, world, reason, pos, random) -> world.getDifficulty() != Difficulty.PEACEFUL
                     && Monster.isDarkEnoughToSpawn(world, pos, random)
                     && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)
            )
         );
   }

   public static Builder createAttributes() {
      Builder builder = Mob.createMobAttributes();
      builder = builder.add(Attributes.MOVEMENT_SPEED, 0.37);
      builder = builder.add(Attributes.MAX_HEALTH, 5.0);
      builder = builder.add(Attributes.ARMOR, 0.0);
      builder = builder.add(Attributes.ATTACK_DAMAGE, 0.0);
      return builder.add(Attributes.FOLLOW_RANGE, 16.0);
   }
}
