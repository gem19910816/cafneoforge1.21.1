package net.mcreator.gore.procedures;

import net.mcreator.gore.configuration.GoreEditionConfigurationFileConfiguration;
import net.mcreator.gore.init.GoreEditionModParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelAccessor;

public class BeeDeathLegacyprProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         double death_variable = 0.0;
         death_variable = (double)((entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1.0F) * 7.0F * 5.0F)
            * (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get()
            * 1.3;
         if (world instanceof ServerLevel _level) {
            _level.sendParticles(
               (SimpleParticleType)GoreEditionModParticleTypes.BEE_LEGACY_GORE.get(),
               x,
               y,
               z,
               (int)(
                  !(death_variable > GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get())
                     ? death_variable
                     : (Double)GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get()
               ),
               (double)(entity.getBbWidth() / 2.0F) * (Double)GoreEditionConfigurationFileConfiguration.HITBOX_MULTIPLIER_X.get(),
               (double)(entity.getBbHeight() / 2.0F) * (Double)GoreEditionConfigurationFileConfiguration.HITBOX_MULTIPLIER_Y.get(),
               (double)(entity.getBbWidth() / 2.0F) * (Double)GoreEditionConfigurationFileConfiguration.HITBOX_MULTIPLIER_X.get(),
               (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get() / 2.0
            );
         }

         if (world instanceof ServerLevel _level) {
            _level.sendParticles(
               (SimpleParticleType)GoreEditionModParticleTypes.BEE_DEATH_BLOOD.get(),
               x,
               y,
               z,
               (int)(
                  !(death_variable > GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get())
                     ? death_variable / 3.0
                     : (Double)GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get() / 3.0
               ),
               (double)(entity.getBbWidth() / 2.0F) * (Double)GoreEditionConfigurationFileConfiguration.HITBOX_MULTIPLIER_X.get(),
               (double)(entity.getBbHeight() / 2.0F) * (Double)GoreEditionConfigurationFileConfiguration.HITBOX_MULTIPLIER_Y.get(),
               (double)(entity.getBbWidth() / 2.0F) * (Double)GoreEditionConfigurationFileConfiguration.HITBOX_MULTIPLIER_X.get(),
               (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get() * 2.0
            );
         }

         if (world instanceof ServerLevel _level) {
            _level.sendParticles(
               (SimpleParticleType)GoreEditionModParticleTypes.BEE_MEATS.get(),
               x,
               y,
               z,
               (int)(
                  !(death_variable > GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get())
                     ? death_variable / 1.5
                     : (Double)GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get() / 3.0
               ),
               (double)(entity.getBbWidth() / 2.0F) * (Double)GoreEditionConfigurationFileConfiguration.HITBOX_MULTIPLIER_X.get(),
               (double)(entity.getBbHeight() / 2.0F) * (Double)GoreEditionConfigurationFileConfiguration.HITBOX_MULTIPLIER_Y.get(),
               (double)(entity.getBbWidth() / 2.0F) * (Double)GoreEditionConfigurationFileConfiguration.HITBOX_MULTIPLIER_X.get(),
               (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get() * 2.0
            );
         }
      }
   }
}
