package net.mcreator.gore.procedures;

import net.mcreator.gore.configuration.GoreEditionConfigurationFileConfiguration;
import net.mcreator.gore.configuration.GoreEditionSoundsConfigurationConfiguration;
import net.mcreator.gore.entity.VerticallyCuttedSpiderEntity;
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

public class VerticallyCuttedSpiderOnEntityTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (entity.getPersistentData().getDouble("death_timer") == 0.0 && entity instanceof VerticallyCuttedSpiderEntity) {
            ((VerticallyCuttedSpiderEntity)entity).setAnimation("vertical_cutted.death");
         }

         entity.getPersistentData().putDouble("death_timer", entity.getPersistentData().getDouble("death_timer") + 1.0);
         if (entity.getPersistentData().getDouble("death_timer") == 6.0) {
            if (entity instanceof VerticallyCuttedSpiderEntity animatable) {
               animatable.setTexture("vertica_cutted_spider");
            }

            if (world instanceof ServerLevel _level) {
               _level.sendParticles(
                  (SimpleParticleType)GoreEditionModParticleTypes.SPIDER_BLOOD_DROPS.get(),
                  x,
                  y + 0.4,
                  z,
                  (int)Math.pow((Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get() * 3.0, 4.0),
                  0.2,
                  0.1,
                  0.2,
                  (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get() * 1.6
               );
            }

            if (world instanceof Level _level) {
               if (!_level.isClientSide()) {
                  _level.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore_concentred_damage_in_one_piece_sound")),
                     SoundSource.NEUTRAL,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_CONCENTRED_DAMAGE_SOUND.get()).doubleValue(),
                     0.7F
                  );
               } else {
                  _level.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore_concentred_damage_in_one_piece_sound")),
                     SoundSource.NEUTRAL,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_CONCENTRED_DAMAGE_SOUND.get()).doubleValue(),
                     0.7F,
                     false
                  );
               }
            }

            entity.getPersistentData().putBoolean("asd", true);
         }

         if (entity.getPersistentData().getDouble("death_timer") == 29.0) {
            entity.hurt(
               new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC)),
               (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1.0F) * 2.0F
            );
         }

         if (entity.getPersistentData().getBoolean("asd") && world instanceof ServerLevel _levelx) {
            _levelx.sendParticles(
               (SimpleParticleType)GoreEditionModParticleTypes.SPIDER_BLOOD_DROPS.get(),
               x,
               y + 0.2,
               z,
               (int)Math.pow((Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get() * 3.0, 2.0),
               0.3,
               0.1,
               0.3,
               (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get()
            );
         }
      }
   }
}
