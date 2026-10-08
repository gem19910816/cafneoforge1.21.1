package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.network.GoreEditionModVariables;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent.Pre;

@EventBusSubscriber
public class CooldownIProcedure {
   @SubscribeEvent
   public static void onPlayerTick(Pre event) {
      execute(event, event.getEntity());
   }

   public static void execute(Entity entity) {
      execute(null, entity);
   }

   private static void execute(@Nullable Event event, Entity entity) {
      if (entity != null) {
         if (GoreEditionModVariables.getPlayerVariables(entity).cooldown_i_l) {
            double _setval = GoreEditionModVariables.getPlayerVariables(entity).cooldown_i + 1.0;
            GoreEditionModVariables.PlayerVariables capability = GoreEditionModVariables.getPlayerVariables(entity);
            capability.cooldown_i = _setval;
            capability.syncPlayerVariables(entity);
         }

         if (GoreEditionModVariables.getPlayerVariables(entity).cooldown_i == 15.0) {
            GoreEditionModVariables.PlayerVariables capability = GoreEditionModVariables.getPlayerVariables(entity);
            capability.cooldown_i_l = false;
            capability.cooldown_i = 0.0;
            capability.syncPlayerVariables(entity);
         }
      }
   }
}
