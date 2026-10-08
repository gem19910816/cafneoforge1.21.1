package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.GoreEditionMod;
import net.mcreator.gore.configuration.GoreEditionOtherConfigurationsConfiguration;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber
public class GiveDamageInChatProcedure {
   @SubscribeEvent
   public static void onEntityAttacked(LivingIncomingDamageEvent event) {
      if (event != null && event.getEntity() != null) {
         execute(event, event.getEntity().level(), event.getEntity(), event.getSource().getEntity());
      }
   }

   public static void execute(LevelAccessor world, Entity entity, Entity sourceentity) {
      execute(null, world, entity, sourceentity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, Entity sourceentity) {
      if (entity != null && sourceentity != null && (Boolean)GoreEditionOtherConfigurationsConfiguration.SHOW_DAMAGE_NUMBER.get()) {
         entity.getPersistentData().putDouble("DegugHealthBeforeEntityIsHurt", entity instanceof LivingEntity _livEnt ? (double)_livEnt.getHealth() : -1.0);
         GoreEditionMod.queueServerWork(
            1,
            () -> {
               entity.getPersistentData()
                  .putDouble(
                     "DebugAmount",
                     entity.getPersistentData().getDouble("DegugHealthBeforeEntityIsHurt")
                        - (double)(entity instanceof LivingEntity _livEntx ? _livEntx.getHealth() : -1.0F)
                  );
               if (sourceentity instanceof Player _player && !_player.level().isClientSide()) {
                  _player.displayClientMessage(Component.literal("§cDamage: " + entity.getPersistentData().getDouble("DebugAmount")), true);
               }

               GoreEditionMod.queueServerWork(5, () -> {
                  if (sourceentity instanceof Player _playerx && !_playerx.level().isClientSide()) {
                     _playerx.displayClientMessage(Component.literal("Damage: " + entity.getPersistentData().getDouble("DebugAmount")), true);
                  }
               });
            }
         );
      }
   }
}
