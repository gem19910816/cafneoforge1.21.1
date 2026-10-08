package net.mcreator.gore.procedures;

import net.mcreator.gore.configuration.GoreEditionConfigurationFileConfiguration;
import net.mcreator.gore.configuration.GoreEditionSoundsConfigurationConfiguration;
import net.mcreator.gore.entity.VerticallyCuttedSkeletonEntity;
import net.mcreator.gore.init.GoreEditionModParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class VerticallyCuttedSkeletonOnTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (entity.getPersistentData().getDouble("timer") == 0.0) {
            if (world instanceof Level _level) {
               if (!_level.isClientSide()) {
                  _level.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:dukeplus.squished")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.DUKEPLUS_SQUISHED.get()).doubleValue(),
                     1.0F
                  );
               } else {
                  _level.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:dukeplus.squished")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.DUKEPLUS_SQUISHED.get()).doubleValue(),
                     1.0F,
                     false
                  );
               }
            }

            if (entity instanceof VerticallyCuttedSkeletonEntity) {
               ((VerticallyCuttedSkeletonEntity)entity).setAnimation("death");
            }
         }

         entity.getPersistentData().putDouble("timer", entity.getPersistentData().getDouble("timer") + 1.0);
         if (entity.getPersistentData().getDouble("timer") == 59.0) {
            entity.hurt(
               new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC)),
               (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1.0F) * 2.0F
            );
         }

         entity.getPersistentData().putDouble("aaa_timer", entity.getPersistentData().getDouble("aaa_timer") + 1.0);
         if (entity.getPersistentData().getDouble("aaa_timer") == 1.0) {
            entity.getPersistentData().putBoolean("nmsnfuj", true);
            if (world instanceof Level _levelx) {
               if (!_levelx.isClientSide()) {
                  _levelx.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:dukeplus.squished")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.DUKEPLUS_SQUISHED.get()).doubleValue(),
                     1.0F
                  );
               } else {
                  _levelx.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:dukeplus.squished")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.DUKEPLUS_SQUISHED.get()).doubleValue(),
                     1.0F,
                     false
                  );
               }
            }
         }

         if (entity.getPersistentData().getDouble("aaa_timer") == 6.0) {
            if (world instanceof Level _levelxx) {
               if (!_levelxx.isClientSide()) {
                  _levelxx.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:dukeplus.squished")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.DUKEPLUS_SQUISHED.get()).doubleValue(),
                     1.0F
                  );
               } else {
                  _levelxx.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:dukeplus.squished")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.DUKEPLUS_SQUISHED.get()).doubleValue(),
                     1.0F,
                     false
                  );
               }
            }

            entity.getPersistentData().putBoolean("nmsnfuj", true);
         }

         if (entity.getPersistentData().getDouble("aaa_timer") == 8.0) {
            if (world instanceof Level _levelxxx) {
               if (!_levelxxx.isClientSide()) {
                  _levelxxx.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:dukeplus.squished")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.DUKEPLUS_SQUISHED.get()).doubleValue(),
                     1.0F
                  );
               } else {
                  _levelxxx.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:dukeplus.squished")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.DUKEPLUS_SQUISHED.get()).doubleValue(),
                     1.0F,
                     false
                  );
               }
            }

            entity.getPersistentData().putBoolean("nmsnfuj", true);
         }

         if (entity.getPersistentData().getDouble("aaa_timer") == 12.0) {
            if (world instanceof Level _levelxxxx) {
               if (!_levelxxxx.isClientSide()) {
                  _levelxxxx.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:dukeplus.squished")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.DUKEPLUS_SQUISHED.get()).doubleValue(),
                     1.0F
                  );
               } else {
                  _levelxxxx.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:dukeplus.squished")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.DUKEPLUS_SQUISHED.get()).doubleValue(),
                     1.0F,
                     false
                  );
               }
            }

            entity.getPersistentData().putBoolean("nmsnfuj", true);
         }

         if (entity.getPersistentData().getDouble("aaa_timer") == 30.0) {
            entity.getPersistentData().putBoolean("nmsnfuj", false);
         }

         if (entity.getPersistentData().getBoolean("nmsnfuj")) {
            if (world instanceof ServerLevel _levelxxxxx) {
               _levelxxxxx.sendParticles(
                  (SimpleParticleType)GoreEditionModParticleTypes.SKELETON_HURT_DUST.get(),
                  x,
                  y + 1.0,
                  z,
                  (int)Math.pow((Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get() * 1.5, 2.0),
                  0.0,
                  0.3,
                  0.0,
                  (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get()
               );
            }

            if (world instanceof ServerLevel _levelxxxxx) {
               _levelxxxxx.sendParticles(
                  (SimpleParticleType)GoreEditionModParticleTypes.SKELETON_DUST_DROPS.get(),
                  x,
                  y + 1.0,
                  z,
                  (int)Math.pow((Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get() * 4.0, 2.0),
                  0.0,
                  0.3,
                  0.0,
                  (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get()
               );
            }
         }
      }
   }
}
