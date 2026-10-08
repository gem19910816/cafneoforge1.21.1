package net.mcreator.gore.procedures;

import net.mcreator.gore.configuration.GoreEditionConfigurationFileConfiguration;
import net.mcreator.gore.configuration.GoreEditionSoundsConfigurationConfiguration;
import net.mcreator.gore.entity.DisarmedHuskEntity;
import net.mcreator.gore.entity.DisarmedZombieEntity;
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

public class DisarmedZombieOnEntityTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (entity.getPersistentData().getDouble("timer") == 0.0) {
            for (int index0 = 0; index0 < 53; index0++) {
               if (entity instanceof DisarmedZombieEntity) {
                  ((DisarmedZombieEntity)entity).setAnimation("zombie_without_arms.death_and_rv");
               }

               if (entity instanceof DisarmedHuskEntity) {
                  ((DisarmedHuskEntity)entity).setAnimation("zombie_without_arms.death_and_rv");
               }
            }

            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
               _entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 240, 254, false, false));
            }

            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
               _entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 240, 254, false, false));
            }

            if (world instanceof Level _level) {
               if (!_level.isClientSide()) {
                  _level.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore_disarmed_zombie_ambient")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_DISARMED_ZOMBIE_AMBIENT.get()).doubleValue(),
                     1.0F
                  );
               } else {
                  _level.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore_disarmed_zombie_ambient")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_DISARMED_ZOMBIE_AMBIENT.get()).doubleValue(),
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
         }

         entity.getPersistentData().putDouble("timer", entity.getPersistentData().getDouble("timer") + 1.0);
         if (entity.getPersistentData().getDouble("timer") == 40.0) {
            if (entity instanceof DisarmedZombieEntity) {
               ((DisarmedZombieEntity)entity).setAnimation("rv");
            }

            if (entity instanceof DisarmedHuskEntity) {
               ((DisarmedHuskEntity)entity).setAnimation("rv");
            }
         }

         if (entity.getPersistentData().getDouble("timer") == 180.0 && !entity.getPersistentData().getBoolean("resurrection")) {
            entity.hurt(
               new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MOB_ATTACK)),
               (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1.0F) * 2.0F
            );
         }

         if (entity.getPersistentData().getDouble("timer") == 310.0) {
            entity.getPersistentData().putBoolean("aaaa", true);
         }

         if (entity.getPersistentData().getBoolean("aaaa")) {
            GenericToughnessBleedingProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity);
         }

         if (!entity.getPersistentData().getBoolean("AASFASDF")) {
            if (world instanceof ServerLevel _levelxx) {
               _levelxx.sendParticles(
                  (SimpleParticleType)GoreEditionModParticleTypes.GENERIC_BLOOD_DROPS.get(),
                  x,
                  y + 1.4,
                  z,
                  (int)((Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get() * 4.0),
                  0.1,
                  0.2,
                  0.1,
                  (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get()
               );
            }

            if (world instanceof ServerLevel _levelxx) {
               _levelxx.sendParticles(
                  (SimpleParticleType)GoreEditionModParticleTypes.GENERIC_SKELETON_PIECES.get(),
                  x,
                  y + 1.4,
                  z,
                  (int)((Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get() * 2.0),
                  0.1,
                  0.2,
                  0.1,
                  (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get()
               );
            }
         }

         entity.getPersistentData().putDouble("AAATIMERSANGRE", entity.getPersistentData().getDouble("timer") + 1.0);
         if (entity.getPersistentData().getDouble("AAATIMERSANGRE") == 17.0) {
            entity.getPersistentData().putBoolean("AASFASDF", true);
         }
      }
   }
}
