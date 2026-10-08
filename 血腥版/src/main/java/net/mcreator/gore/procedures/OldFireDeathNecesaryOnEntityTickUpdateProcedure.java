package net.mcreator.gore.procedures;

import net.mcreator.gore.configuration.GoreEditionConfigurationFileConfiguration;
import net.mcreator.gore.configuration.GoreEditionModeSettingsConfiguration;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class OldFireDeathNecesaryOnEntityTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (entity.getPersistentData().getDouble("tick1") == 0.0) {
            if (world instanceof Level _level) {
               if (!_level.isClientSide()) {
                  _level.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.generic.extinguish_fire")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_INCINERATED_SOUND.get()).doubleValue(),
                     1.0F
                  );
               } else {
                  _level.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.generic.extinguish_fire")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_INCINERATED_SOUND.get()).doubleValue(),
                     1.0F,
                     false
                  );
               }
            }

            if (((String)GoreEditionModeSettingsConfiguration.FDEATH_METHOD.get()).equals("manual")) {
               if (world instanceof ServerLevel _levelx) {
                  _levelx.sendParticles(
                     (SimpleParticleType)GoreEditionModParticleTypes.FIRE_PARTICLE.get(),
                     x,
                     y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get(),
                     z,
                     (int)(
                        Math.pow((Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get() * 4.0, 2.0)
                           * (Double)GoreEditionConfigurationFileConfiguration.FIRE_PARTICLES_REDUCTOR.get()
                     ),
                     0.1,
                     0.4,
                     0.1,
                     (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get() / 3.0
                  );
               }

               if (world instanceof ServerLevel _levelx) {
                  _levelx.sendParticles(
                     (SimpleParticleType)GoreEditionModParticleTypes.FIRE_PARTICLE.get(),
                     x,
                     y,
                     z,
                     (int)(
                        Math.pow((Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get() * 4.0, 2.0)
                           * (Double)GoreEditionConfigurationFileConfiguration.FIRE_PARTICLES_REDUCTOR.get()
                     ),
                     0.2,
                     0.0,
                     0.2,
                     (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get() / 3.0
                  );
               }
            }

            if (((String)GoreEditionModeSettingsConfiguration.FDEATH_METHOD.get()).equals("hitbox")) {
               if (world instanceof ServerLevel _levelx) {
                  _levelx.sendParticles(
                     (SimpleParticleType)GoreEditionModParticleTypes.FIRE_PARTICLE.get(),
                     x,
                     y + entity.getPersistentData().getDouble("ge_the_true_entity_height") / 2.0,
                     z,
                     (int)(
                        Math.pow((Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get() * 4.0, 2.0)
                           * (Double)GoreEditionConfigurationFileConfiguration.FIRE_PARTICLES_REDUCTOR.get()
                     ),
                     entity.getPersistentData().getDouble("ge_the_true_entity_width")
                        / 2.0
                        * 0.5
                        * (Double)GoreEditionConfigurationFileConfiguration.HITBOX_MULTIPLIER_X.get(),
                     entity.getPersistentData().getDouble("ge_the_true_entity_height")
                        / 2.0
                        * 0.5
                        * (Double)GoreEditionConfigurationFileConfiguration.HITBOX_MULTIPLIER_X.get(),
                     entity.getPersistentData().getDouble("ge_the_true_entity_width")
                        / 2.0
                        * 0.5
                        * (Double)GoreEditionConfigurationFileConfiguration.HITBOX_MULTIPLIER_X.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get() / 3.0
                  );
               }

               if (world instanceof ServerLevel _levelx) {
                  _levelx.sendParticles(
                     (SimpleParticleType)GoreEditionModParticleTypes.FIRE_PARTICLE.get(),
                     x,
                     y + entity.getPersistentData().getDouble("ge_the_true_entity_height") / 2.0,
                     z,
                     (int)(
                        Math.pow((Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get() * 4.0, 2.0)
                           * (Double)GoreEditionConfigurationFileConfiguration.FIRE_PARTICLES_REDUCTOR.get()
                     ),
                     entity.getPersistentData().getDouble("ge_the_true_entity_width")
                        / 2.0
                        * 0.5
                        * (Double)GoreEditionConfigurationFileConfiguration.HITBOX_MULTIPLIER_X.get(),
                     entity.getPersistentData().getDouble("ge_the_true_entity_height")
                        / 2.0
                        * 0.5
                        * (Double)GoreEditionConfigurationFileConfiguration.HITBOX_MULTIPLIER_X.get(),
                     entity.getPersistentData().getDouble("ge_the_true_entity_width")
                        / 2.0
                        * 0.5
                        * (Double)GoreEditionConfigurationFileConfiguration.HITBOX_MULTIPLIER_X.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get() / 3.0
                  );
               }
            }
         }

         entity.getPersistentData().putDouble("tick1", entity.getPersistentData().getDouble("tick1") + 1.0);
         if (entity.getPersistentData().getDouble("tick1") == 5.0) {
            entity.getPersistentData().putDouble("tick1", 0.0);
         }

         entity.getPersistentData().putDouble("tick2", entity.getPersistentData().getDouble("tick2") + 1.0);
         if (entity.getPersistentData().getDouble("tick2") == 30.0 && !entity.level().isClientSide()) {
            entity.discard();
         }

         entity.getPersistentData().putDouble("tick0", entity.getPersistentData().getDouble("tick0") + 1.0);
         if (entity.getPersistentData().getDouble("tick0") == 2.0) {
            entity.getPersistentData().putDouble("tick0", 0.0);
         }
      }
   }
}
