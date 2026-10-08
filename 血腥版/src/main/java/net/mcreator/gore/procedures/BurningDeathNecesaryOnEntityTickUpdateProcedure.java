package net.mcreator.gore.procedures;

import net.mcreator.gore.configuration.GoreEditionConfigurationFileConfiguration;
import net.mcreator.gore.configuration.GoreEditionSoundsConfigurationConfiguration;
import net.mcreator.gore.init.GoreEditionModParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class BurningDeathNecesaryOnEntityTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (entity.getPersistentData().getDouble("tick1") == 0.0) {
            if (world instanceof Level _level) {
               if (!_level.isClientSide()) {
                  _level.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore_incinerated_sound")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_INCINERATED_SOUND.get()).doubleValue(),
                     1.5F
                  );
               } else {
                  _level.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore_incinerated_sound")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_INCINERATED_SOUND.get()).doubleValue(),
                     1.5F,
                     false
                  );
               }
            }

            if (world instanceof ServerLevel _levelx) {
               _levelx.sendParticles(
                  (SimpleParticleType)GoreEditionModParticleTypes.ASHES_PARTICLES.get(),
                  x,
                  y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get(),
                  z,
                  (int)(
                     Math.pow((Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get() * 3.0, 4.0)
                        * (Double)GoreEditionConfigurationFileConfiguration.FIRE_PARTICLES_REDUCTOR.get()
                  ),
                  0.2,
                  0.5,
                  0.2,
                  (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get()
               );
            }
         }

         entity.getPersistentData().putDouble("tick1", entity.getPersistentData().getDouble("tick1") + 1.0);
         if (entity.getPersistentData().getDouble("tick1") == 5.0) {
            if (world instanceof ServerLevel _levelx) {
               _levelx.sendParticles(
                  ParticleTypes.LARGE_SMOKE,
                  x,
                  y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get(),
                  z,
                  (int)(
                     Math.pow((Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get() * 2.0, 2.0)
                        * (Double)GoreEditionConfigurationFileConfiguration.FIRE_PARTICLES_REDUCTOR.get()
                  ),
                  0.3,
                  0.5,
                  0.3,
                  (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get()
               );
            }

            entity.getPersistentData().putDouble("tick1", 0.0);
         }

         entity.getPersistentData().putDouble("tick2", entity.getPersistentData().getDouble("tick2") + 1.0);
         if (entity.getPersistentData().getDouble("tick2") == 17.0 && !entity.level().isClientSide()) {
            entity.discard();
         }

         if (entity.getPersistentData().getDouble("tick0") == 0.0 && world instanceof ServerLevel _levelx) {
            _levelx.sendParticles(
               ParticleTypes.SMOKE,
               x,
               y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get(),
               z,
               (int)(
                  Math.pow((Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get() * 2.0, 3.0)
                     * (Double)GoreEditionConfigurationFileConfiguration.FIRE_PARTICLES_REDUCTOR.get()
               ),
               0.3,
               0.5,
               0.3,
               (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get()
            );
         }

         entity.getPersistentData().putDouble("tick0", entity.getPersistentData().getDouble("tick0") + 1.0);
         if (entity.getPersistentData().getDouble("tick0") == 2.0) {
            entity.getPersistentData().putDouble("tick0", 0.0);
         }
      }
   }
}
