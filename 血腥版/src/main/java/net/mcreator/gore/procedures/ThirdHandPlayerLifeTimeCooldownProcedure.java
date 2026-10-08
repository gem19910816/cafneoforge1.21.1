package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.network.GoreEditionModVariables;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent.Pre;

@EventBusSubscriber
public class ThirdHandPlayerLifeTimeCooldownProcedure {
   @SubscribeEvent
   public static void onPlayerTick(Pre event) {
      execute(event, event.getEntity());
   }

   public static void execute(Entity entity) {
      execute(null, entity);
   }

   private static void execute(@Nullable Event event, Entity entity) {
      if (entity != null && GoreEditionModVariables.getPlayerVariables(entity).third_hand_boolean_cooldown) {
         double _setval = GoreEditionModVariables.getPlayerVariables(entity).third_hand_number_cooldown + 1.0;
         GoreEditionModVariables.PlayerVariables capability = GoreEditionModVariables.getPlayerVariables(entity);
         capability.third_hand_number_cooldown = _setval;
         capability.syncPlayerVariables(entity);
         if (GoreEditionModVariables.getPlayerVariables(entity).third_hand_number_cooldown == 10.0) {
            boolean _setvalx = false;
            GoreEditionModVariables.PlayerVariables capabilityx = GoreEditionModVariables.getPlayerVariables(entity);
            capabilityx.third_hand_boolean_cooldown = _setvalx;
            capabilityx.syncPlayerVariables(entity);
            _setval = 0.0;
            capabilityx = GoreEditionModVariables.getPlayerVariables(entity);
            capabilityx.third_hand_number_cooldown = _setval;
            capabilityx.syncPlayerVariables(entity);
         }
      }
   }
}
