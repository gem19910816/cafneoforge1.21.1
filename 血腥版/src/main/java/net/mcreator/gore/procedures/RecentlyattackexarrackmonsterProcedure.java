package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.entity.FakeExarrackMonsterEntity;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber
public class RecentlyattackexarrackmonsterProcedure {
   @SubscribeEvent
   public static void onEntityAttacked(LivingIncomingDamageEvent event) {
      if (event != null && event.getEntity() != null) {
         execute(event, event.getSource().getEntity());
      }
   }

   public static void execute(Entity sourceentity) {
      execute(null, sourceentity);
   }

   private static void execute(@Nullable Event event, Entity sourceentity) {
      if (sourceentity != null && sourceentity instanceof FakeExarrackMonsterEntity) {
         sourceentity.getPersistentData().putBoolean("recentlyattack", true);
      }
   }
}
