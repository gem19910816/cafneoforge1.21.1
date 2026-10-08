package net.mcreator.gore.procedures;

import net.mcreator.gore.GoreEditionMod;
import net.mcreator.gore.configuration.GoreEditionConfigurationFileConfiguration;
import net.mcreator.gore.init.GoreEditionModParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelAccessor;

public class WardenHurtLegacyPrProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, double amount) {
      if (entity != null) {
         entity.getPersistentData().putDouble("HealthBeforeEntityIsHurt", entity instanceof LivingEntity _livEnt ? (double)_livEnt.getHealth() : -1.0);
         GoreEditionMod.queueServerWork(
            1,
            () -> {
               if (entity.isAlive()) {
                  entity.getPersistentData()
                     .putDouble(
                        "HurtParticleValue",
                        Math.pow(
                              (
                                    entity.getPersistentData().getDouble("HealthBeforeEntityIsHurt")
                                       - (double)(entity instanceof LivingEntity _livEntx ? _livEntx.getHealth() : -1.0F)
                                 )
                                 * 0.7,
                              (Double)GoreEditionConfigurationFileConfiguration.EASE_IN_HURT_AMOUNT.get()
                           )
                           * (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get()
                           * 1.3
                     );
               } else {
                  entity.getPersistentData()
                     .putDouble(
                        "HurtParticleValue",
                        Math.pow(amount * 0.7, (Double)GoreEditionConfigurationFileConfiguration.EASE_IN_HURT_AMOUNT.get())
                           * (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get()
                           * 1.3
                     );
               }

               if (world instanceof ServerLevel _level) {
                  _level.sendParticles(
                     (SimpleParticleType)GoreEditionModParticleTypes.WARDEN_BLOOD_DROPS.get(),
                     x,
                     y,
                     z,
                     (int)(
                        !(entity.getPersistentData().getDouble("HurtParticleValue") > GoreEditionConfigurationFileConfiguration.MAX_HURT_PARTICLES_AMOUNT.get())
                           ? entity.getPersistentData().getDouble("HurtParticleValue")
                           : (Double)GoreEditionConfigurationFileConfiguration.MAX_HURT_PARTICLES_AMOUNT.get()
                     ),
                     (double)(entity.getBbWidth() / 2.0F) * 0.5 * (Double)GoreEditionConfigurationFileConfiguration.HITBOX_MULTIPLIER_X.get(),
                     (double)(entity.getBbHeight() / 2.0F) * 0.5 * (Double)GoreEditionConfigurationFileConfiguration.HITBOX_MULTIPLIER_Y.get(),
                     (double)(entity.getBbWidth() / 2.0F) * 0.5 * (Double)GoreEditionConfigurationFileConfiguration.HITBOX_MULTIPLIER_X.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get() / 1.5
                  );
               }

               if (world instanceof ServerLevel _level) {
                  _level.sendParticles(
                     (SimpleParticleType)GoreEditionModParticleTypes.WARDEN_LEGACY_HURT_BLOOD.get(),
                     x,
                     y,
                     z,
                     (int)(
                        !(entity.getPersistentData().getDouble("HurtParticleValue") > GoreEditionConfigurationFileConfiguration.MAX_HURT_PARTICLES_AMOUNT.get())
                           ? entity.getPersistentData().getDouble("HurtParticleValue")
                           : (Double)GoreEditionConfigurationFileConfiguration.MAX_HURT_PARTICLES_AMOUNT.get()
                     ),
                     (double)(entity.getBbWidth() / 2.0F) * 0.5 * (Double)GoreEditionConfigurationFileConfiguration.HITBOX_MULTIPLIER_X.get(),
                     (double)(entity.getBbHeight() / 2.0F) * 0.5 * (Double)GoreEditionConfigurationFileConfiguration.HITBOX_MULTIPLIER_Y.get(),
                     (double)(entity.getBbWidth() / 2.0F) * 0.5 * (Double)GoreEditionConfigurationFileConfiguration.HITBOX_MULTIPLIER_X.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get() * 2.0
                  );
               }
            }
         );
      }
   }
}
