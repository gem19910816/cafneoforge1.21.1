package net.mcreator.gore.procedures;

import net.mcreator.gore.configuration.GoreEditionConfigurationFileConfiguration;
import net.mcreator.gore.configuration.GoreEditionSoundsConfigurationConfiguration;
import net.mcreator.gore.entity.ExplodedHeadSpiderEntity;
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

public class ExplodedHeadSpiderOnEntityTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (entity.getPersistentData().getDouble("death_timer") == 0.0 && entity instanceof ExplodedHeadSpiderEntity) {
            ((ExplodedHeadSpiderEntity)entity).setAnimation("death");
         }

         entity.getPersistentData().putDouble("death_timer", entity.getPersistentData().getDouble("death_timer") + 1.0);
         if (entity.getPersistentData().getDouble("death_timer") == 6.0) {
            if (world instanceof ServerLevel _level) {
               _level.sendParticles(
                  (SimpleParticleType)GoreEditionModParticleTypes.SPIDER_BLOOD_DROPS.get(),
                  entity.getX() + entity.getLookAngle().x * 0.5,
                  y + 0.4,
                  entity.getZ() + entity.getLookAngle().z * 0.5,
                  (int)Math.pow((Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get() * 3.0, 4.0),
                  0.0,
                  0.0,
                  0.0,
                  (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get() * 1.6
               );
            }

            if (world instanceof ServerLevel _level) {
               _level.sendParticles(
                  (SimpleParticleType)GoreEditionModParticleTypes.SPIDER_MEATS.get(),
                  entity.getX() + entity.getLookAngle().x * 0.5,
                  y + 0.4,
                  entity.getZ() + entity.getLookAngle().z * 0.5,
                  (int)Math.pow((Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get() * 3.0, 2.0),
                  0.0,
                  0.0,
                  0.0,
                  (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get() * 1.6
               );
            }

            if (world instanceof Level _level) {
               if (!_level.isClientSide()) {
                  _level.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore_big_blood_splash")),
                     SoundSource.NEUTRAL,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_BIG_BLOOD_SPLASH.get()).doubleValue(),
                     0.7F
                  );
               } else {
                  _level.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore_big_blood_splash")),
                     SoundSource.NEUTRAL,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_BIG_BLOOD_SPLASH.get()).doubleValue(),
                     0.7F,
                     false
                  );
               }
            }

            entity.getPersistentData().putBoolean("asd", true);
            if (entity instanceof ExplodedHeadSpiderEntity animatable) {
               animatable.setTexture("spider_without_head");
            }
         }

         if (entity.getPersistentData().getDouble("death_timer") == 39.0) {
            entity.hurt(
               new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC)),
               (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1.0F) * 2.0F
            );
         }

         if (entity.getPersistentData().getBoolean("asd") && world instanceof ServerLevel _levelx) {
            _levelx.sendParticles(
               (SimpleParticleType)GoreEditionModParticleTypes.SPIDER_BLOOD_DROPS.get(),
               entity.getX() + entity.getLookAngle().x * 0.3,
               y + 0.2,
               entity.getZ() + entity.getLookAngle().z * 0.3,
               (int)Math.pow((Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get() * 3.0, 2.0),
               0.0,
               0.0,
               0.0,
               (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get()
            );
         }
      }
   }
}
