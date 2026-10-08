package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber
public class AcidEntityDieProcedure {
   @SubscribeEvent
   public static void onEntityDeath(LivingDeathEvent event) {
      if (event != null && event.getEntity() != null) {
         execute(event, event.getEntity());
      }
   }

   public static void execute(Entity entity) {
      execute(null, entity);
   }

   private static void execute(@Nullable Event event, Entity entity) {
      if (entity != null) {
         entity.getPersistentData().putBoolean("recently_exposed_to_acid", false);
         entity.getPersistentData().putDouble("recently_exposed_to_acid_tag_remove_tick", 0.0);
         entity.getPersistentData().putDouble("acid_pendulum", 0.0);
         entity.getPersistentData().putDouble("acid_time_of_potency", 0.0);
      }
   }
}
