package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.entity.TripofobicAcidEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber
public class RemoveTripofobicAcidDamageToAshesNativesProcedure {
   @SubscribeEvent
   public static void onEntityAttacked(LivingIncomingDamageEvent event) {
      if (event != null && event.getEntity() != null) {
         execute(event, event.getEntity(), event.getSource().getDirectEntity());
      }
   }

   public static void execute(Entity entity, Entity immediatesourceentity) {
      execute(null, entity, immediatesourceentity);
   }

   private static void execute(@Nullable Event event, Entity entity, Entity immediatesourceentity) {
      if (entity != null
         && immediatesourceentity != null
         && immediatesourceentity instanceof TripofobicAcidEntity
         && entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("gore_edition:ashes_natives")))
         && event instanceof ICancellableEvent _c) {
         _c.setCanceled(true);
      }
   }
}
