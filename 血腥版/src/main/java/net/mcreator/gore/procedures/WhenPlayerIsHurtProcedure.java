package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.configuration.GoreEditionModeSettingsConfiguration;
import net.mcreator.gore.network.GoreEditionModVariables;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber
public class WhenPlayerIsHurtProcedure {
   @SubscribeEvent
   public static void onEntityAttacked(LivingIncomingDamageEvent event) {
      if (event != null && event.getEntity() != null) {
         execute(
            event,
            event.getEntity().level(),
            event.getEntity().getX(),
            event.getEntity().getY(),
            event.getEntity().getZ(),
            event.getEntity(),
            (double)event.getAmount()
         );
      }
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, double amount) {
      execute(null, world, x, y, z, entity, amount);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity, double amount) {
      if (entity != null
         && (Double)GoreEditionModeSettingsConfiguration.HURT_INTENSITY.get() == 1.0
         && (entity instanceof Player || entity instanceof ServerPlayer)) {
         if (GoreEditionModVariables.getPlayerVariables(entity).player_blood_particles.equals("generic")) {
            PlayerClassicHurtBloodProcedure.execute(world, x, y, z, entity, amount);
         }

         if (GoreEditionModVariables.getPlayerVariables(entity).player_blood_particles.equals("spider")) {
            SpiderPlayerClassicHurtBloodProcedure.execute(world, x, y, z, entity, amount);
         }

         if (GoreEditionModVariables.getPlayerVariables(entity).player_blood_particles.equals("ender")) {
            EnderPlayerHurtBloodProcedure.execute(world, x, y, z, entity, amount);
         }

         if (GoreEditionModVariables.getPlayerVariables(entity).player_blood_particles.equals("spectral")) {
            SpectralPlayerHurtBloodProcedure.execute(world, x, y, z, entity, amount);
         }

         if (GoreEditionModVariables.getPlayerVariables(entity).player_blood_particles.equals("warden")) {
            WardenPlayerClassicHurtBloodProcedure.execute(world, x, y, z, entity, amount);
         }

         if (GoreEditionModVariables.getPlayerVariables(entity).player_blood_particles.equals("bee")) {
            BeePlayerClassicHurtProcedure.execute(world, x, y, z, entity, amount);
         }

         if (GoreEditionModVariables.getPlayerVariables(entity).player_blood_particles.equals("skeleton")) {
            SkeletonPlayerClassicDustProcedure.execute(world, x, y, z, entity, amount);
         }

         if (GoreEditionModVariables.getPlayerVariables(entity).player_blood_particles.equals("wither")) {
            WitherSkeletonPlayerClassicDustProcedure.execute(world, x, y, z, entity, amount);
         }

         if (GoreEditionModVariables.getPlayerVariables(entity).player_blood_particles.equals("blaze")) {
            BlazePlayerClassicHurtProcedure.execute(world, x, y, z, entity, amount);
         }

         if (GoreEditionModVariables.getPlayerVariables(entity).player_blood_particles.equals("slime")) {
            SlimePlayerClassicHurtProcedure.execute(world, x, y, z, entity, amount);
         }

         if (GoreEditionModVariables.getPlayerVariables(entity).player_blood_particles.equals("magma_cube")) {
            MagmaCubePlayerClassicHurtProcedure.execute(world, x, y, z, entity, amount);
         }

         if (GoreEditionModVariables.getPlayerVariables(entity).player_blood_particles.equals("iron_golem")) {
            IronGolemPlayerClassicHurtProcedure.execute(world, x, y, z, entity, amount);
         }

         if (GoreEditionModVariables.getPlayerVariables(entity).player_blood_particles.equals("guardian")) {
            GuardianPlayerClassicHurtProcedure.execute(world, x, y, z, entity, amount);
         }

         if (GoreEditionModVariables.getPlayerVariables(entity).player_blood_particles.equals("elder_guardian")) {
            ElderGuardianPlayerClassicHurtProcedure.execute(world, x, y, z, entity, amount);
         }
      }
   }
}
