package net.mcreator.gore.procedures;

import net.mcreator.gore.configuration.GoreEditionConfigurationFileConfiguration;
import net.mcreator.gore.configuration.GoreEditionSoundsConfigurationConfiguration;
import net.mcreator.gore.init.GoreEditionModParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class GenericHurtBloodConfigProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, double amount) {
      if (entity != null) {
         double amount_variable = 0.0;
         double brutal_amount_variable = 0.0;
         amount_variable = (double)(
               (
                     !((entity instanceof LivingEntity _livEntxx ? _livEntxx.getMaxHealth() : -1.0F) >= 100.0F)
                        ? (entity instanceof LivingEntity _livEntx ? _livEntx.getMaxHealth() : -1.0F)
                        : 100.0F
                  )
                  - (entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1.0F)
            )
            * amount
            * (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get();
         brutal_amount_variable = (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get()
            * (Double)GoreEditionConfigurationFileConfiguration.BRUTAL_HURT_PARTICLES_MULTIPLICATOR.get();
         if (world instanceof ServerLevel _level) {
            _level.sendParticles(
               (SimpleParticleType)GoreEditionModParticleTypes.GENERIC_HURT_BLOOD.get(),
               x,
               y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get(),
               z,
               (int)(
                  !(amount_variable > GoreEditionConfigurationFileConfiguration.MAX_HURT_PARTICLES_AMOUNT.get())
                     ? amount_variable
                     : (Double)GoreEditionConfigurationFileConfiguration.MAX_HURT_PARTICLES_AMOUNT.get()
               ),
               (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
               (Double)GoreEditionConfigurationFileConfiguration.Y_AREA_SIZE.get(),
               (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
               (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get()
            );
         }

         if (world instanceof Level _level) {
            if (!_level.isClientSide()) {
               _level.playSound(
                  null,
                  BlockPos.containing(x, y, z),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore_hurt_sound")),
                  SoundSource.AMBIENT,
                  (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_HURT_SOUND.get()).doubleValue(),
                  1.0F
               );
            } else {
               _level.playLocalSound(
                  x,
                  y,
                  z,
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore_hurt_sound")),
                  SoundSource.AMBIENT,
                  (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_HURT_SOUND.get()).doubleValue(),
                  1.0F,
                  false
               );
            }
         }

         if ((entity instanceof LivingEntity _livEntxxxx ? _livEntxxxx.getHealth() : -1.0F)
               <= (entity instanceof LivingEntity _livEntxxx ? _livEntxxx.getMaxHealth() : -1.0F) / 2.0F
            && (Boolean)GoreEditionConfigurationFileConfiguration.RIBS_HURT_HALF.get()) {
            if (world instanceof ServerLevel _levelx) {
               _levelx.sendParticles(
                  (SimpleParticleType)GoreEditionModParticleTypes.GENERIC_50_BLOOD.get(),
                  x,
                  y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get(),
                  z,
                  (int)(
                     !(amount_variable > GoreEditionConfigurationFileConfiguration.MAX_HURT_PARTICLES_AMOUNT.get())
                        ? amount_variable / 4.0
                        : (Double)GoreEditionConfigurationFileConfiguration.MAX_HURT_PARTICLES_AMOUNT.get() / 4.0
                  ),
                  (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                  (Double)GoreEditionConfigurationFileConfiguration.Y_AREA_SIZE.get(),
                  (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                  (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get()
               );
            }

            if (world instanceof Level _levelx) {
               if (!_levelx.isClientSide()) {
                  _levelx.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore_50_sound")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_50_SOUND.get()).doubleValue(),
                     1.0F
                  );
               } else {
                  _levelx.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore_50_sound")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_50_SOUND.get()).doubleValue(),
                     1.0F,
                     false
                  );
               }
            }
         }
      }
   }
}
