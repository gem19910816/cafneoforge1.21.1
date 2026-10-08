package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.network.GoreEditionModVariables;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent.Pre;

@EventBusSubscriber
public class LivingEntityWithHellCursedMechanicsProcedure {
   @SubscribeEvent
   public static void onEntityTick(Pre event) {
      execute(event, event.getEntity());
   }

   public static void execute(Entity entity) {
      execute(null, entity);
   }

   private static void execute(@Nullable Event event, Entity entity) {
      if (entity != null) {
         if (!(entity instanceof Player) && !(entity instanceof ServerPlayer)) {
            if (entity.getPersistentData().getDouble("hell_cursed") > 0.0 && entity.getPersistentData().getDouble("hell_cursed") != 10.0) {
               entity.getPersistentData().putDouble("hell_cursed_downgrade_timer", entity.getPersistentData().getDouble("hell_cursed_downgrade_timer") + 1.0);
               if (entity.getPersistentData().getDouble("hell_cursed_downgrade_timer") == 40.0) {
                  entity.getPersistentData().putDouble("hell_cursed_downgrade_timer", 0.0);
                  entity.getPersistentData().putDouble("hell_cursed", entity.getPersistentData().getDouble("hell_cursed") - 1.0);
               }
            } else if (entity.getPersistentData().getDouble("hell_cursed") == 0.0) {
               entity.getPersistentData().putDouble("hell_cursed_downgrade_timer", 0.0);
            }
         } else if (GoreEditionModVariables.getPlayerVariables(entity).hell_cursed_for_player > 0.0
            && GoreEditionModVariables.getPlayerVariables(entity).hell_cursed_for_player != 10.0) {
            double _setval = GoreEditionModVariables.getPlayerVariables(entity).hell_cursed_downgrade_timer_for_player + 1.0;
            GoreEditionModVariables.PlayerVariables capability = GoreEditionModVariables.getPlayerVariables(entity);
            capability.hell_cursed_downgrade_timer_for_player = _setval;
            capability.syncPlayerVariables(entity);
            if (GoreEditionModVariables.getPlayerVariables(entity).hell_cursed_downgrade_timer_for_player == 40.0) {
               _setval = 0.0;
               capability = GoreEditionModVariables.getPlayerVariables(entity);
               capability.hell_cursed_downgrade_timer_for_player = _setval;
               capability.syncPlayerVariables(entity);
               _setval = GoreEditionModVariables.getPlayerVariables(entity).hell_cursed_for_player - 1.0;
               capability = GoreEditionModVariables.getPlayerVariables(entity);
               capability.hell_cursed_for_player = _setval;
               capability.syncPlayerVariables(entity);
            }
         } else if (GoreEditionModVariables.getPlayerVariables(entity).hell_cursed_for_player == 0.0) {
            double _setval = 0.0;
            GoreEditionModVariables.PlayerVariables capability = GoreEditionModVariables.getPlayerVariables(entity);
            capability.hell_cursed_downgrade_timer_for_player = _setval;
            capability.syncPlayerVariables(entity);
         }
      }
   }
}
