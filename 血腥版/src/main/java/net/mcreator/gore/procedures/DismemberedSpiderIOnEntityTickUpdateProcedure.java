package net.mcreator.gore.procedures;

import net.mcreator.gore.configuration.GoreEditionConfigurationFileConfiguration;
import net.mcreator.gore.configuration.GoreEditionSoundsConfigurationConfiguration;
import net.mcreator.gore.entity.DismemberedSpiderIEntity;
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
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class DismemberedSpiderIOnEntityTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (entity.getPersistentData().getDouble("tick1") == 0.0) {
            for (int index0 = 0; index0 < 23; index0++) {
               if (entity instanceof DismemberedSpiderIEntity) {
                  ((DismemberedSpiderIEntity)entity).setAnimation("ds.death");
               }
            }

            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
               _entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 162, 254, false, false));
            }

            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
               _entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 162, 254, false, false));
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

            if (world instanceof ServerLevel _levelx) {
               _levelx.sendParticles(
                  (SimpleParticleType)GoreEditionModParticleTypes.SPIDER_BLOOD_DROPS.get(),
                  x,
                  y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get() / 2.3,
                  z,
                  (int)((Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get() * 5.0),
                  0.0,
                  0.0,
                  0.0,
                  (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get()
               );
            }
         }

         entity.getPersistentData().putDouble("tick1", entity.getPersistentData().getDouble("tick1") + 1.0);
         if (entity.getPersistentData().getDouble("tick1") == 2.0) {
            if (world instanceof Level _levelx) {
               if (!_levelx.isClientSide()) {
                  _levelx.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:dukeplus.gore_bounce")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.DUKEPLUS_GORE_BOUNCE.get()).doubleValue(),
                     1.0F
                  );
               } else {
                  _levelx.playLocalSound(
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

            if (world instanceof ServerLevel _levelxx) {
               _levelxx.sendParticles(
                  (SimpleParticleType)GoreEditionModParticleTypes.SPIDER_BLOOD_DROPS.get(),
                  x,
                  y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get() / 2.3,
                  z,
                  (int)((Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get() * 5.0),
                  0.0,
                  0.0,
                  0.0,
                  (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get()
               );
            }
         }

         if (entity.getPersistentData().getDouble("tick1") == 4.0) {
            if (world instanceof Level _levelxx) {
               if (!_levelxx.isClientSide()) {
                  _levelxx.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:dukeplus.gore_bounce")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.DUKEPLUS_GORE_BOUNCE.get()).doubleValue(),
                     1.0F
                  );
               } else {
                  _levelxx.playLocalSound(
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

            if (world instanceof ServerLevel _levelxxx) {
               _levelxxx.sendParticles(
                  (SimpleParticleType)GoreEditionModParticleTypes.SPIDER_BLOOD_DROPS.get(),
                  x,
                  y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get() / 2.3,
                  z,
                  (int)((Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get() * 5.0),
                  0.0,
                  0.0,
                  0.0,
                  (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get()
               );
            }
         }

         if (entity.getPersistentData().getDouble("tick1") == 6.0) {
            if (world instanceof Level _levelxxx) {
               if (!_levelxxx.isClientSide()) {
                  _levelxxx.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:dukeplus.gore_bounce")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.DUKEPLUS_GORE_BOUNCE.get()).doubleValue(),
                     1.0F
                  );
               } else {
                  _levelxxx.playLocalSound(
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

            if (world instanceof ServerLevel _levelxxxx) {
               _levelxxxx.sendParticles(
                  (SimpleParticleType)GoreEditionModParticleTypes.SPIDER_BLOOD_DROPS.get(),
                  x,
                  y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get() / 2.3,
                  z,
                  (int)((Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get() * 5.0),
                  0.0,
                  0.0,
                  0.0,
                  (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get()
               );
            }
         }

         if (entity.getPersistentData().getDouble("tick1") == 8.0) {
            if (world instanceof Level _levelxxxx) {
               if (!_levelxxxx.isClientSide()) {
                  _levelxxxx.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:dukeplus.gore_bounce")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.DUKEPLUS_GORE_BOUNCE.get()).doubleValue(),
                     1.0F
                  );
               } else {
                  _levelxxxx.playLocalSound(
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

            if (world instanceof ServerLevel _levelxxxxx) {
               _levelxxxxx.sendParticles(
                  (SimpleParticleType)GoreEditionModParticleTypes.SPIDER_BLOOD_DROPS.get(),
                  x,
                  y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get() / 2.3,
                  z,
                  (int)((Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get() * 5.0),
                  0.0,
                  0.0,
                  0.0,
                  (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get()
               );
            }
         }

         if (entity.getPersistentData().getDouble("tick1") == 10.0) {
            if (world instanceof Level _levelxxxxx) {
               if (!_levelxxxxx.isClientSide()) {
                  _levelxxxxx.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:dukeplus.gore_bounce")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.DUKEPLUS_GORE_BOUNCE.get()).doubleValue(),
                     1.0F
                  );
               } else {
                  _levelxxxxx.playLocalSound(
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

            if (world instanceof ServerLevel _levelxxxxxx) {
               _levelxxxxxx.sendParticles(
                  (SimpleParticleType)GoreEditionModParticleTypes.SPIDER_BLOOD_DROPS.get(),
                  x,
                  y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get() / 2.3,
                  z,
                  (int)((Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get() * 5.0),
                  0.0,
                  0.0,
                  0.0,
                  (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get()
               );
            }
         }

         if (entity.getPersistentData().getDouble("tick1") == 12.0) {
            if (world instanceof Level _levelxxxxxx) {
               if (!_levelxxxxxx.isClientSide()) {
                  _levelxxxxxx.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:dukeplus.gore_bounce")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.DUKEPLUS_GORE_BOUNCE.get()).doubleValue(),
                     1.0F
                  );
               } else {
                  _levelxxxxxx.playLocalSound(
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

            if (world instanceof ServerLevel _levelxxxxxxx) {
               _levelxxxxxxx.sendParticles(
                  (SimpleParticleType)GoreEditionModParticleTypes.SPIDER_BLOOD_DROPS.get(),
                  x,
                  y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get() / 2.3,
                  z,
                  (int)((Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get() * 5.0),
                  0.0,
                  0.0,
                  0.0,
                  (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get()
               );
            }
         }

         if (entity.getPersistentData().getDouble("tick1") == 14.0) {
            if (world instanceof Level _levelxxxxxxx) {
               if (!_levelxxxxxxx.isClientSide()) {
                  _levelxxxxxxx.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:dukeplus.gore_bounce")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.DUKEPLUS_GORE_BOUNCE.get()).doubleValue(),
                     1.0F
                  );
               } else {
                  _levelxxxxxxx.playLocalSound(
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

            if (world instanceof ServerLevel _levelxxxxxxxx) {
               _levelxxxxxxxx.sendParticles(
                  (SimpleParticleType)GoreEditionModParticleTypes.SPIDER_BLOOD_DROPS.get(),
                  x,
                  y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get() / 2.3,
                  z,
                  (int)((Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get() * 5.0),
                  0.0,
                  0.0,
                  0.0,
                  (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get()
               );
            }
         }

         if (entity.getPersistentData().getDouble("tick1") == 16.0) {
            if (world instanceof Level _levelxxxxxxxx) {
               if (!_levelxxxxxxxx.isClientSide()) {
                  _levelxxxxxxxx.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:dukeplus.gore_bounce")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.DUKEPLUS_GORE_BOUNCE.get()).doubleValue(),
                     1.0F
                  );
               } else {
                  _levelxxxxxxxx.playLocalSound(
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

            if (world instanceof ServerLevel _levelxxxxxxxxx) {
               _levelxxxxxxxxx.sendParticles(
                  (SimpleParticleType)GoreEditionModParticleTypes.SPIDER_BLOOD_DROPS.get(),
                  x,
                  y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get() / 2.3,
                  z,
                  (int)((Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get() * 5.0),
                  0.0,
                  0.0,
                  0.0,
                  (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get()
               );
            }
         }

         if (entity.getPersistentData().getDouble("tick1") == 18.0) {
            if (world instanceof Level _levelxxxxxxxxx) {
               if (!_levelxxxxxxxxx.isClientSide()) {
                  _levelxxxxxxxxx.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:dukeplus.gore_bounce")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.DUKEPLUS_GORE_BOUNCE.get()).doubleValue(),
                     1.0F
                  );
               } else {
                  _levelxxxxxxxxx.playLocalSound(
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

            if (world instanceof ServerLevel _levelxxxxxxxxxx) {
               _levelxxxxxxxxxx.sendParticles(
                  (SimpleParticleType)GoreEditionModParticleTypes.SPIDER_BLOOD_DROPS.get(),
                  x,
                  y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get() / 2.3,
                  z,
                  (int)((Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get() * 5.0),
                  0.0,
                  0.0,
                  0.0,
                  (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get()
               );
            }
         }

         if (entity.getPersistentData().getDouble("tick1") == 20.0) {
            if (world instanceof Level _levelxxxxxxxxxx) {
               if (!_levelxxxxxxxxxx.isClientSide()) {
                  _levelxxxxxxxxxx.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:dukeplus.gore_bounce")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.DUKEPLUS_GORE_BOUNCE.get()).doubleValue(),
                     0.2F
                  );
               } else {
                  _levelxxxxxxxxxx.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:dukeplus.gore_bounce")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.DUKEPLUS_GORE_BOUNCE.get()).doubleValue(),
                     0.2F,
                     false
                  );
               }
            }

            if (world instanceof ServerLevel _levelxxxxxxxxxxx) {
               _levelxxxxxxxxxxx.sendParticles(
                  (SimpleParticleType)GoreEditionModParticleTypes.SPIDER_BLOOD_DROPS.get(),
                  x,
                  y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get() / 2.3,
                  z,
                  (int)((Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get() * 25.0),
                  0.0,
                  0.0,
                  0.0,
                  (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get() * 1.6
               );
            }
         }

         if (entity.getPersistentData().getDouble("tick1") == 100.0 && !entity.getPersistentData().getBoolean("resurrection")) {
            entity.hurt(
               new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC)),
               (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1.0F) * 2.0F
            );
         }
      }
   }
}
