package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent.EntityInteract;

@EventBusSubscriber
public class OwnerOfCordycepsRightClickProcedure {
   @SubscribeEvent
   public static void onRightClickEntity(EntityInteract event) {
      if (event.getHand() == event.getEntity().getUsedItemHand()) {
         execute(event, event.getTarget(), event.getEntity());
      }
   }

   public static void execute(Entity entity, Entity sourceentity) {
      execute(null, entity, sourceentity);
   }

   private static void execute(@Nullable Event event, Entity entity, Entity sourceentity) {
      if (entity != null
         && sourceentity != null
         && sourceentity == (entity instanceof TamableAnimal _tamEnt ? _tamEnt.getOwner() : null)
         && entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_cordyceps")))) {
         if (!sourceentity.isShiftKeyDown()) {
            if (entity.getPersistentData().getDouble("orders") != 2.0) {
               entity.getPersistentData().putDouble("orders", entity.getPersistentData().getDouble("orders") + 1.0);
            } else {
               entity.getPersistentData().putDouble("orders", 0.0);
            }
         } else if (entity.getPersistentData().getBoolean("cattack")) {
            entity.getPersistentData().putBoolean("cattack", false);
         } else {
            entity.getPersistentData().putBoolean("cattack", true);
         }

         if (!entity.getPersistentData().getBoolean("cattack")) {
            if (entity.getPersistentData().getDouble("orders") == 0.0 && sourceentity instanceof Player _player && !_player.level().isClientSide()) {
               _player.displayClientMessage(Component.literal("Walk"), true);
            }

            if (entity.getPersistentData().getDouble("orders") == 1.0 && sourceentity instanceof Player _player && !_player.level().isClientSide()) {
               _player.displayClientMessage(Component.literal("Stop"), true);
            }

            if (entity.getPersistentData().getDouble("orders") == 2.0 && sourceentity instanceof Player _player && !_player.level().isClientSide()) {
               _player.displayClientMessage(Component.literal("Follow"), true);
            }
         }

         if (entity.getPersistentData().getBoolean("cattack")) {
            if (entity.getPersistentData().getDouble("orders") == 0.0 && sourceentity instanceof Player _player && !_player.level().isClientSide()) {
               _player.displayClientMessage(Component.literal("Walk, don't attack aggressors"), true);
            }

            if (entity.getPersistentData().getDouble("orders") == 1.0 && sourceentity instanceof Player _player && !_player.level().isClientSide()) {
               _player.displayClientMessage(Component.literal("Stop, don't attack aggressors"), true);
            }

            if (entity.getPersistentData().getDouble("orders") == 2.0 && sourceentity instanceof Player _player && !_player.level().isClientSide()) {
               _player.displayClientMessage(Component.literal("Follow, don't attack aggressors"), true);
            }
         }
      }
   }
}
