package net.mcreator.gore.entity;

import net.mcreator.gore.procedures.DismemberedSpiderIIIOnEntityTickUpdateProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class DismemberedSpiderIIIEntity extends Monster {
   public DismemberedSpiderIIIEntity(EntityType<DismemberedSpiderIIIEntity> type, Level world) {
      super(type, world);
      this.xpReward = 0;
      this.setNoAi(false);
   }

   protected void registerGoals() {
      super.registerGoals();
      this.goalSelector.addGoal(1, new RandomStrollGoal(this, 1.0));
      this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
      this.goalSelector.addGoal(3, new FloatGoal(this));
   }

   public SoundEvent getAmbientSound() {
      return (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.spider.ambient"));
   }

   public void playStepSound(BlockPos pos, BlockState blockIn) {
      this.playSound((SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.spider.step")), 0.15F, 1.0F);
   }

   public SoundEvent getHurtSound(DamageSource ds) {
      return (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.spider.hurt"));
   }

   public SoundEvent getDeathSound() {
      return (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.spider.death"));
   }

   public void baseTick() {
      super.baseTick();
      DismemberedSpiderIIIOnEntityTickUpdateProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), this);
   }

   public static void init() {
   }

   public static Builder createAttributes() {
      Builder builder = Mob.createMobAttributes();
      builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
      builder = builder.add(Attributes.MAX_HEALTH, 16.0);
      builder = builder.add(Attributes.ARMOR, 0.0);
      builder = builder.add(Attributes.ATTACK_DAMAGE, 4.0);
      return builder.add(Attributes.FOLLOW_RANGE, 16.0);
   }
}
