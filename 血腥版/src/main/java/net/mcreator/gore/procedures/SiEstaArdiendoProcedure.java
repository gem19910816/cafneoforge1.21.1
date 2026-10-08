package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.configuration.GoreEditionConfigurationFileConfiguration;
import net.mcreator.gore.configuration.GoreEditionModeSettingsConfiguration;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent.Pre;

@EventBusSubscriber
public class SiEstaArdiendoProcedure {
   @SubscribeEvent
   public static void onEntityTick(Pre event) {
      execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      execute(null, world, x, y, z, entity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null && (Boolean)GoreEditionModeSettingsConfiguration.BURNING.get() && entity.isOnFire()) {
         if (entity.getPersistentData().getDouble("tick") == 0.0 && world instanceof ServerLevel _level) {
            _level.sendParticles(
               ParticleTypes.SMOKE,
               x,
               y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get(),
               z,
               (int)Math.pow((Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get() * 2.0, 2.0),
               0.1,
               0.4,
               0.1,
               (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get() / 3.0
            );
         }

         entity.getPersistentData().putDouble("tick", entity.getPersistentData().getDouble("tick") + 1.0);
         if (entity.getPersistentData().getDouble("tick") == 2.0) {
            entity.getPersistentData().putDouble("tick", 0.0);
         }
      }
   }
}
