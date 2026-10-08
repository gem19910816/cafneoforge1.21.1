package net.mcreator.gore.procedures;

import net.mcreator.gore.configuration.GoreEditionSoundsConfigurationConfiguration;
import net.mcreator.gore.entity.HeadlessHuskEntity;
import net.mcreator.gore.entity.HeadlessZombieEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class HeadlessZombieOnEntityTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (entity.getPersistentData().getDouble("timer") == 0.0) {
            if (entity instanceof HeadlessZombieEntity) {
               ((HeadlessZombieEntity)entity).setAnimation("zombie_without_head.death_and_rv");
            }

            if (entity instanceof HeadlessHuskEntity) {
               ((HeadlessHuskEntity)entity).setAnimation("zombie_without_head.death_and_rv");
            }

            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
               _entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 280, 254, false, false));
            }

            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
               _entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 280, 254, false, false));
            }

            if (world instanceof Level _level) {
               if (!_level.isClientSide()) {
                  _level.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:dukeplus.gore_bounce")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.DUKEPLUS_GORE_BOUNCE.get()).doubleValue(),
                     1.0F
                  );
               } else {
                  _level.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:dukeplus.gore_bounce")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.DUKEPLUS_GORE_BOUNCE.get()).doubleValue(),
                     1.0F,
                     false
                  );
               }
            }

            if (world instanceof Level _levelx) {
               if (!_levelx.isClientSide()) {
                  _levelx.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.skull_destroy")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_SKULL_DESTROY.get()).doubleValue(),
                     1.0F
                  );
               } else {
                  _levelx.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.skull_destroy")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_SKULL_DESTROY.get()).doubleValue(),
                     1.0F,
                     false
                  );
               }
            }
         }

         entity.getPersistentData().putDouble("timer", entity.getPersistentData().getDouble("timer") + 1.0);
         if (entity.getPersistentData().getDouble("timer") == 80.0) {
            if (entity instanceof HeadlessZombieEntity) {
               ((HeadlessZombieEntity)entity).setAnimation("rv");
            }

            if (entity instanceof HeadlessHuskEntity) {
               ((HeadlessHuskEntity)entity).setAnimation("rv");
            }
         }

         if (entity.getPersistentData().getDouble("timer") == 220.0 && !entity.getPersistentData().getBoolean("resurrection")) {
            entity.hurt(
               new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MOB_ATTACK)),
               (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1.0F) * 2.0F
            );
         }

         GenericToughnessBleedingProcedure.execute(world, x, y, z, entity);
      }
   }
}
