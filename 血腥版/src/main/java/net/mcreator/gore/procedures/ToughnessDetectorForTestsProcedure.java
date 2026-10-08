package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.configuration.GoreEditionOtherConfigurationsConfiguration;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent.Pre;

@EventBusSubscriber
public class ToughnessDetectorForTestsProcedure {
   @SubscribeEvent
   public static void onEntityTick(Pre event) {
      execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      execute(null, world, x, y, z, entity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null
         && (Boolean)GoreEditionOtherConfigurationsConfiguration.TOUGHNESS_VISIBLE.get()
         && entity.getPersistentData().getBoolean("toughness")
         && world instanceof ServerLevel _level) {
         _level.sendParticles(ParticleTypes.ENCHANTED_HIT, x, y + 1.0, z, 1, 0.2, 0.5, 0.2, 0.3);
      }
   }
}
