package net.mcreator.gore.procedures;

import net.mcreator.gore.configuration.GoreEditionModeSettingsConfiguration;
import net.mcreator.gore.network.GoreEditionModVariables;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class WhenPlayerDiesLegacyProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null && (Double)GoreEditionModeSettingsConfiguration.HURT_INTENSITY.get() == 2.0) {
         if (GoreEditionModVariables.getPlayerVariables(entity).player_blood_particles.equals("generic")) {
            GenericDeathLegacyPrProcedure.execute(world, x, y + (double)(entity.getBbHeight() / 2.0F), z, entity);
            LegacyDeathBloodSoundsProcedure.execute(world, entity);
         }

         if (GoreEditionModVariables.getPlayerVariables(entity).player_blood_particles.equals("spider")) {
            SpiderDeathLegacyPrProcedure.execute(world, x, y + (double)(entity.getBbHeight() / 2.0F), z, entity);
            LegacyDeathBloodSoundsProcedure.execute(world, entity);
         }

         if (GoreEditionModVariables.getPlayerVariables(entity).player_blood_particles.equals("ender")) {
            EnderDeathLegacyPrProcedure.execute(world, x, y + (double)(entity.getBbHeight() / 2.0F), z, entity);
            LegacyDeathBloodSoundsProcedure.execute(world, entity);
         }

         if (GoreEditionModVariables.getPlayerVariables(entity).player_blood_particles.equals("spectral")) {
            SpectralDeathLegacyPrProcedure.execute(world, x, y + (double)(entity.getBbHeight() / 2.0F), z, entity);
            LegacyDeathBloodSoundsProcedure.execute(world, entity);
         }

         if (GoreEditionModVariables.getPlayerVariables(entity).player_blood_particles.equals("warden")) {
            WardenDeathLegacyPrProcedure.execute(world, x, y + (double)(entity.getBbHeight() / 2.0F), z, entity);
            LegacyDeathBloodSoundsProcedure.execute(world, entity);
         }

         if (GoreEditionModVariables.getPlayerVariables(entity).player_blood_particles.equals("bee")) {
            BeeDeathLegacyprProcedure.execute(world, x, y + (double)(entity.getBbHeight() / 2.0F), z, entity);
            LegacyDeathBloodSoundsProcedure.execute(world, entity);
         }

         if (GoreEditionModVariables.getPlayerVariables(entity).player_blood_particles.equals("skeleton")) {
            SkeletonDeathLegacyPrProcedure.execute(world, x, y + (double)(entity.getBbHeight() / 2.0F), z, entity);
         }
      }
   }
}
