package net.mcreator.gore.procedures;

import net.mcreator.gore.configuration.GoreEditionConfigurationFileConfiguration;
import net.mcreator.gore.configuration.GoreEditionSoundsConfigurationConfiguration;
import net.mcreator.gore.init.GoreEditionModParticleTypes;
import net.mcreator.gore.network.GoreEditionModVariables;
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

public class GuardianPlayerClassicHurtProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, double amount) {
      if (entity != null) {
         double H = 0.0;
         double B = 0.0;
         H = (double)(
               (
                     !((entity instanceof LivingEntity _livEntxx ? _livEntxx.getMaxHealth() : -1.0F) >= 100.0F)
                        ? (entity instanceof LivingEntity _livEntx ? _livEntx.getMaxHealth() : -1.0F)
                        : 100.0F
                  )
                  - (entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1.0F)
            )
            * amount
            * (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get();
         B = (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get()
            * (Double)GoreEditionConfigurationFileConfiguration.BRUTAL_HURT_PARTICLES_MULTIPLICATOR.get();
         if (world instanceof ServerLevel _level) {
            _level.sendParticles(
               (SimpleParticleType)GoreEditionModParticleTypes.GUARDIAN_HURT_DUST.get(),
               x,
               y + GoreEditionModVariables.getPlayerVariables(entity).center_y_player,
               z,
               (int)(
                  !(H > GoreEditionConfigurationFileConfiguration.MAX_HURT_PARTICLES_AMOUNT.get())
                     ? H
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
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.skeleton_hurt_sound")),
                  SoundSource.AMBIENT,
                  (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_SKELETON_HURT_SOUND.get()).doubleValue(),
                  1.0F
               );
            } else {
               _level.playLocalSound(
                  x,
                  y,
                  z,
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.skeleton_hurt_sound")),
                  SoundSource.AMBIENT,
                  (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_SKELETON_HURT_SOUND.get()).doubleValue(),
                  1.0F,
                  false
               );
            }
         }

         if ((Boolean)GoreEditionConfigurationFileConfiguration.RIBS_HURT_HALF.get()
            && (entity instanceof LivingEntity _livEntxxxx ? _livEntxxxx.getHealth() : -1.0F)
               <= (entity instanceof LivingEntity _livEntxxx ? _livEntxxx.getMaxHealth() : -1.0F) / 2.0F) {
            if (world instanceof ServerLevel _levelx) {
               _levelx.sendParticles(
                  (SimpleParticleType)GoreEditionModParticleTypes.GUARDIAN_50_DUST.get(),
                  x,
                  y + GoreEditionModVariables.getPlayerVariables(entity).center_y_player,
                  z,
                  (int)(
                     !(H > GoreEditionConfigurationFileConfiguration.MAX_HURT_PARTICLES_AMOUNT.get())
                        ? H / 4.0
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
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.skeleton_50_sound")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_SKELETON_50_SOUND.get()).doubleValue(),
                     1.0F
                  );
               } else {
                  _levelx.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.skeleton_50_sound")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_SKELETON_50_SOUND.get()).doubleValue(),
                     1.0F,
                     false
                  );
               }
            }
         }
      }
   }
}
