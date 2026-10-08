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

public class WitherSkeletonBossDeathBigExplodeProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         double D = 0.0;
         double U = 0.0;
         D = (double)(
               !((entity instanceof LivingEntity _livEntx ? _livEntx.getMaxHealth() : -1.0F) >= 100.0F)
                  ? (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1.0F)
                  : 100.0F
            )
            * (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get()
            * (Double)GoreEditionConfigurationFileConfiguration.BONES_PARTICLES_MULTIPLICATOR.get();
         U = (double)(
               !((entity instanceof LivingEntity _livEntxxx ? _livEntxxx.getMaxHealth() : -1.0F) >= 100.0F)
                  ? (entity instanceof LivingEntity _livEntxx ? _livEntxx.getMaxHealth() : -1.0F)
                  : 100.0F
            )
            * (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get()
            * 5.0;
         if (world instanceof ServerLevel _level) {
            _level.sendParticles(
               (SimpleParticleType)GoreEditionModParticleTypes.WITHER_SKELETON_PIECES.get(),
               entity.getX(),
               entity.getY() + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get(),
               entity.getZ(),
               (int)(
                  !(D > GoreEditionConfigurationFileConfiguration.MAX_BONES_PARTICLES_AMOUNT.get())
                     ? D * 5.0
                     : (Double)GoreEditionConfigurationFileConfiguration.MAX_BONES_PARTICLES_AMOUNT.get() * 5.0
               ),
               (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
               (Double)GoreEditionConfigurationFileConfiguration.Y_AREA_SIZE.get(),
               (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
               (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get()
                  * (Double)GoreEditionConfigurationFileConfiguration.BONES_PARTICLES_SPEED_MULTIPLICATOR.get()
                  * 7.0
            );
         }

         if (world instanceof ServerLevel _level) {
            _level.sendParticles(
               (SimpleParticleType)GoreEditionModParticleTypes.WITHER_SKELETON_DUST_DROPS.get(),
               entity.getX(),
               entity.getY() + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get(),
               entity.getZ(),
               (int)(
                  !(U > GoreEditionConfigurationFileConfiguration.MAX_DEATH_DUST_PARTICLES_AMOUNT.get())
                     ? U * 2.0
                     : (Double)GoreEditionConfigurationFileConfiguration.MAX_DEATH_DUST_PARTICLES_AMOUNT.get() * 2.0
               ),
               (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
               (Double)GoreEditionConfigurationFileConfiguration.Y_AREA_SIZE.get(),
               (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
               (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get() * 4.0
            );
         }

         if (world instanceof Level _level) {
            if (!_level.isClientSide()) {
               _level.playSound(
                  null,
                  BlockPos.containing(x, y, z),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.skeleton_death_pieces_sound")),
                  SoundSource.AMBIENT,
                  (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_SKELETON_DEATH_PIECES_SOUND.get()).doubleValue(),
                  0.4F
               );
            } else {
               _level.playLocalSound(
                  x,
                  y,
                  z,
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.skeleton_death_pieces_sound")),
                  SoundSource.AMBIENT,
                  (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_SKELETON_DEATH_PIECES_SOUND.get()).doubleValue(),
                  0.4F,
                  false
               );
            }
         }
      }
   }
}
