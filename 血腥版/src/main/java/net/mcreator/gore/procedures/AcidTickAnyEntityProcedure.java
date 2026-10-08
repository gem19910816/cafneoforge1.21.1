package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent.Pre;

@EventBusSubscriber
public class AcidTickAnyEntityProcedure {
   @SubscribeEvent
   public static void onEntityTick(Pre event) {
      execute(event, event.getEntity());
   }

   public static void execute(Entity entity) {
      execute(null, entity);
   }

   private static void execute(@Nullable Event event, Entity entity) {
      if (entity != null) {
         if (!entity.getPersistentData().getBoolean("recently_exposed_to_acid")) {
            if (entity.getPersistentData().getDouble("acid_time_of_potency") != 0.0) {
               entity.getPersistentData().putDouble("acid_pendulum", entity.getPersistentData().getDouble("acid_pendulum") + 1.0);
            }

            if (entity.getPersistentData().getDouble("acid_time_of_potency") < 0.0) {
               entity.getPersistentData().putDouble("acid_time_of_potency", 0.0);
               entity.getPersistentData().putDouble("acid_pendulum", 0.0);
            }

            if (entity.getPersistentData().getDouble("acid_pendulum") >= 20.0) {
               entity.getPersistentData().putDouble("acid_time_of_potency", entity.getPersistentData().getDouble("acid_time_of_potency") - 15.0);
               entity.getPersistentData().putDouble("acid_pendulum", 0.0);
            }
         }

         if (entity.getPersistentData().getBoolean("recently_exposed_to_acid")) {
            entity.getPersistentData()
               .putDouble("recently_exposed_to_acid_tag_remove_tick", entity.getPersistentData().getDouble("recently_exposed_to_acid_tag_remove_tick") + 1.0);
         }

         if (entity.getPersistentData().getDouble("recently_exposed_to_acid_tag_remove_tick") >= 60.0) {
            entity.getPersistentData().putBoolean("recently_exposed_to_acid", false);
            entity.getPersistentData().putDouble("recently_exposed_to_acid_tag_remove_tick", 0.0);
         }
      }
   }
}
