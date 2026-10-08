package net.mcreator.gore.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class BabySpiderEntity extends Spider {
   public BabySpiderEntity(EntityType<BabySpiderEntity> type, Level world) {
      super(type, world);
      this.xpReward = 3;
      this.setNoAi(false);
      this.refreshDimensions();
   }

   protected void registerGoals() {
      super.registerGoals();
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

   public static void init() {
   }

   public static Builder createAttributes() {
      Builder builder = Mob.createMobAttributes();
      builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
      builder = builder.add(Attributes.MAX_HEALTH, 10.0);
      builder = builder.add(Attributes.ARMOR, 0.0);
      builder = builder.add(Attributes.ATTACK_DAMAGE, 1.0);
      return builder.add(Attributes.FOLLOW_RANGE, 16.0);
   }
}
