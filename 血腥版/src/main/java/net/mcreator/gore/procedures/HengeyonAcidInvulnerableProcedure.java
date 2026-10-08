package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.entity.HengeyonEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber
public class HengeyonAcidInvulnerableProcedure {
   @SubscribeEvent
   public static void onEntityAttacked(LivingIncomingDamageEvent event) {
      if (event != null && event.getEntity() != null) {
         execute(event, event.getSource(), event.getEntity());
      }
   }

   public static void execute(DamageSource damagesource, Entity entity) {
      execute(null, damagesource, entity);
   }

   private static void execute(@Nullable Event event, DamageSource damagesource, Entity entity) {
      if (damagesource != null
         && entity != null
         && entity instanceof HengeyonEntity
         && damagesource.is(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("gore_edition:acid_damage")))
         && event instanceof ICancellableEvent _c) {
         _c.setCanceled(true);
      }
   }
}
