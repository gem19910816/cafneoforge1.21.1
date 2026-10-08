package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.configuration.GoreEditionConfigurationFileConfiguration;
import net.mcreator.gore.init.GoreEditionModItems;
import net.mcreator.gore.network.GoreEditionModVariables;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent.Pre;

@EventBusSubscriber
public class OnPlayerTickUpdateProcedure {
   @SubscribeEvent
   public static void onPlayerTick(Pre event) {
      execute(event, event.getEntity());
   }

   public static void execute(Entity entity) {
      execute(null, entity);
   }

   private static void execute(@Nullable Event event, Entity entity) {
      if (entity != null) {
         if (GoreEditionModVariables.getPlayerVariables(entity).blood_in_screen_i_amount > 0.0) {
            double _setval = GoreEditionModVariables.getPlayerVariables(entity).blood_screen_cooldown_i + 1.0;
            GoreEditionModVariables.PlayerVariables capability = GoreEditionModVariables.getPlayerVariables(entity);
            capability.blood_screen_cooldown_i = _setval;
            capability.syncPlayerVariables(entity);
            if (entity instanceof Player _playerHasItem
               && _playerHasItem.getInventory().contains(new ItemStack((ItemLike)GoreEditionModItems.VITALITY_STEALINGS_FANGS.get()))) {
               double _setvalx = GoreEditionModVariables.getPlayerVariables(entity).blood_screen_cooldown_i + 2.0;
               GoreEditionModVariables.PlayerVariables capabilityx = GoreEditionModVariables.getPlayerVariables(entity);
               capabilityx.blood_screen_cooldown_i = _setvalx;
               capabilityx.syncPlayerVariables(entity);
            }
         }

         if (GoreEditionModVariables.getPlayerVariables(entity).blood_screen_cooldown_i
               < (Double)GoreEditionConfigurationFileConfiguration.BLOOD_IN_SCREEN_TICKS.get()
            && (
               !(entity instanceof Player _playerHasItem)
                  || !_playerHasItem.getInventory().contains(new ItemStack((ItemLike)GoreEditionModItems.VITALITY_STEALINGS_FANGS.get()))
            )) {
            double _setval = 0.0;
            GoreEditionModVariables.PlayerVariables capability = GoreEditionModVariables.getPlayerVariables(entity);
            capability.vitality_stealings = _setval;
            capability.syncPlayerVariables(entity);
         }

         if (GoreEditionModVariables.getPlayerVariables(entity).blood_screen_cooldown_i
            >= (Double)GoreEditionConfigurationFileConfiguration.BLOOD_IN_SCREEN_TICKS.get()) {
            double _setval = 0.0;
            GoreEditionModVariables.PlayerVariables capability = GoreEditionModVariables.getPlayerVariables(entity);
            capability.blood_screen_cooldown_i = _setval;
            capability.syncPlayerVariables(entity);
            if (GoreEditionModVariables.getPlayerVariables(entity).blood_in_screen_i_amount > -1.0) {
               _setval = GoreEditionModVariables.getPlayerVariables(entity).blood_in_screen_i_amount - 1.0;
               capability = GoreEditionModVariables.getPlayerVariables(entity);
               capability.blood_in_screen_i_amount = _setval;
               capability.syncPlayerVariables(entity);
               _setval = 1.0;
               capability = GoreEditionModVariables.getPlayerVariables(entity);
               capability.vitality_stealings = _setval;
               capability.syncPlayerVariables(entity);
            }
         }

         if (entity.isInWaterRainOrBubble() && GoreEditionModVariables.getPlayerVariables(entity).blood_in_screen_i_amount > -1.0) {
            double _setval = GoreEditionModVariables.getPlayerVariables(entity).blood_in_screen_i_amount - 1.0;
            GoreEditionModVariables.PlayerVariables capability = GoreEditionModVariables.getPlayerVariables(entity);
            capability.blood_in_screen_i_amount = _setval;
            capability.syncPlayerVariables(entity);
         }
      }
   }
}
