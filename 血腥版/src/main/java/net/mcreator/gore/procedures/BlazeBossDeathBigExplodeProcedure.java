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
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class BlazeBossDeathBigExplodeProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         double D = 0.0;
         D = (double)(
               !((entity instanceof LivingEntity _livEntx ? _livEntx.getMaxHealth() : -1.0F) >= 100.0F)
                  ? (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1.0F)
                  : 100.0F
            )
            * (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get();
         if (world instanceof ServerLevel _level) {
            _level.sendParticles(
               (SimpleParticleType)GoreEditionModParticleTypes.BLAZE_DEATH_DUST.get(),
               x,
               y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get(),
               z,
               (int)(
                  !(D > GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get())
                     ? D
                     : (Double)GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get()
               ),
               (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
               (Double)GoreEditionConfigurationFileConfiguration.Y_AREA_SIZE.get(),
               (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
               (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get() * 4.0
            );
         }

         if (world instanceof ServerLevel _level) {
            _level.sendParticles(
               (SimpleParticleType)GoreEditionModParticleTypes.BLAZE_DUST_DROPS.get(),
               x,
               y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get(),
               z,
               (int)(
                  !(D > GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get())
                     ? D * 12.0
                     : (Double)GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get() * 12.0
               ),
               (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
               (Double)GoreEditionConfigurationFileConfiguration.Y_AREA_SIZE.get(),
               (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
               (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get() * 4.0
            );
         }

         if (world instanceof ServerLevel _level) {
            _level.sendParticles(
               (SimpleParticleType)GoreEditionModParticleTypes.BLAZE_PIECES.get(),
               x,
               y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get(),
               z,
               (int)(
                  !(D > GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get())
                     ? D * 12.0
                     : (Double)GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get() * 12.0
               ),
               (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
               (Double)GoreEditionConfigurationFileConfiguration.Y_AREA_SIZE.get(),
               (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
               (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get() * 4.0
            );
         }

         if (world instanceof ServerLevel _level) {
            _level.sendParticles(
               ParticleTypes.LARGE_SMOKE,
               x,
               y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get(),
               z,
               (int)(
                  !(D > GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get())
                     ? D * 12.0
                     : (Double)GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get() * 12.0
               ),
               (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
               (Double)GoreEditionConfigurationFileConfiguration.Y_AREA_SIZE.get(),
               (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
               (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get() * 3.0
            );
         }

         if (world instanceof Level _level) {
            if (!_level.isClientSide()) {
               _level.playSound(
                  null,
                  BlockPos.containing(x, y, z),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.infernal_machine_explode")),
                  SoundSource.AMBIENT,
                  (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_INFERNAL_MACHINE_EXPLODE.get()).doubleValue(),
                  0.5F
               );
            } else {
               _level.playLocalSound(
                  x,
                  y,
                  z,
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.infernal_machine_explode")),
                  SoundSource.AMBIENT,
                  (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_INFERNAL_MACHINE_EXPLODE.get()).doubleValue(),
                  0.5F,
                  false
               );
            }
         }

         if (world instanceof Level _levelx) {
            if (!_levelx.isClientSide()) {
               _levelx.playSound(
                  null,
                  BlockPos.containing(x, y, z),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.metal_death_pieces")),
                  SoundSource.AMBIENT,
                  (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_METAL_DEATH_PIECES.get()).doubleValue(),
                  0.4F
               );
            } else {
               _levelx.playLocalSound(
                  x,
                  y,
                  z,
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.metal_death_pieces")),
                  SoundSource.AMBIENT,
                  (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_METAL_DEATH_PIECES.get()).doubleValue(),
                  0.4F,
                  false
               );
            }
         }
      }
   }
}
