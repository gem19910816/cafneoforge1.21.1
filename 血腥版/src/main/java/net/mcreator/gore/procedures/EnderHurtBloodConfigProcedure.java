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

public class EnderHurtBloodConfigProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, double amount) {
      if (entity != null) {
         double hurt = 0.0;
         double brutalowo = 0.0;
         hurt = (double)(
               (
                     !((entity instanceof LivingEntity _livEntxx ? _livEntxx.getMaxHealth() : -1.0F) >= 100.0F)
                        ? (entity instanceof LivingEntity _livEntx ? _livEntx.getMaxHealth() : -1.0F)
                        : 100.0F
                  )
                  - (entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1.0F)
            )
            * amount
            * (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get();
         brutalowo = (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get()
            * (Double)GoreEditionConfigurationFileConfiguration.BRUTAL_HURT_PARTICLES_MULTIPLICATOR.get();
         if (world instanceof ServerLevel _level) {
            _level.sendParticles(
               (SimpleParticleType)GoreEditionModParticleTypes.ENDER_HURT_BLOOD.get(),
               x,
               y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get(),
               z,
               (int)(
                  !(hurt > GoreEditionConfigurationFileConfiguration.MAX_HURT_PARTICLES_AMOUNT.get())
                     ? hurt
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
                  (SimpleParticleType)GoreEditionModParticleTypes.ENDER_50_BLOOD.get(),
                  x,
                  y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get(),
                  z,
                  (int)(
                     !(hurt > GoreEditionConfigurationFileConfiguration.MAX_HURT_PARTICLES_AMOUNT.get())
                        ? hurt / 4.0
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
                     3.0F,
                     1.0F
                  );
               } else {
                  _levelx.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore_50_sound")),
                     SoundSource.AMBIENT,
                     3.0F,
                     1.0F,
                     false
                  );
               }
            }
         }
      }
   }
}
