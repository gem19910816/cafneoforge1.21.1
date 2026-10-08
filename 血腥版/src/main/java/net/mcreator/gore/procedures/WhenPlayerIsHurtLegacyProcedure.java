package net.mcreator.gore.procedures;

import net.mcreator.gore.configuration.GoreEditionModeSettingsConfiguration;
import net.mcreator.gore.network.GoreEditionModVariables;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class WhenPlayerIsHurtLegacyProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, double amount) {
      if (entity != null && (Double)GoreEditionModeSettingsConfiguration.HURT_INTENSITY.get() == 2.0) {
         if (GoreEditionModVariables.getPlayerVariables(entity).player_blood_particles.equals("generic")) {
            GenericHurtLegacyPrProcedure.execute(world, x, y + (double)(entity.getBbHeight() / 2.0F), z, entity, amount);
            LegacyHurtBloodSoundsProcedure.execute(world, entity, amount);
         }

         if (GoreEditionModVariables.getPlayerVariables(entity).player_blood_particles.equals("spider")) {
            SpiderHurtLegacyPrProcedure.execute(world, x, y + (double)(entity.getBbHeight() / 2.0F), z, entity, amount);
            LegacyHurtBloodSoundsProcedure.execute(world, entity, amount);
         }

         if (GoreEditionModVariables.getPlayerVariables(entity).player_blood_particles.equals("ender")) {
            EnderHurtLegacyPrProcedure.execute(world, x, y + (double)(entity.getBbHeight() / 2.0F), z, entity, amount);
            LegacyHurtBloodSoundsProcedure.execute(world, entity, amount);
         }

         if (GoreEditionModVariables.getPlayerVariables(entity).player_blood_particles.equals("spectral")) {
            SpectralHurtLegacyPrProcedure.execute(world, x, y + (double)(entity.getBbHeight() / 2.0F), z, entity, amount);
            LegacyHurtBloodSoundsProcedure.execute(world, entity, amount);
         }

         if (GoreEditionModVariables.getPlayerVariables(entity).player_blood_particles.equals("warden")) {
            WardenHurtLegacyPrProcedure.execute(world, x, y + (double)(entity.getBbHeight() / 2.0F), z, entity, amount);
            LegacyHurtBloodSoundsProcedure.execute(world, entity, amount);
         }

         if (GoreEditionModVariables.getPlayerVariables(entity).player_blood_particles.equals("bee")) {
            BeeHurtLegacyPrProcedure.execute(world, x, y + (double)(entity.getBbHeight() / 2.0F), z, entity, amount);
            LegacyHurtBloodSoundsProcedure.execute(world, entity, amount);
         }

         if (GoreEditionModVariables.getPlayerVariables(entity).player_blood_particles.equals("skeleton")) {
            SkeletonHurtLegacyPrProcedure.execute(world, x, y + (double)(entity.getBbHeight() / 2.0F), z, entity, amount);
            SkeletonHurtDustSoundsProcedure.execute(world, entity, amount);
         }
      }
   }
}
