package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber
public class DontHaveACorpseProcedureProcedure {
   @SubscribeEvent
   public static void onEntityAttacked(LivingIncomingDamageEvent event) {
      if (event != null && event.getEntity() != null) {
         execute(event, event.getSource(), event.getEntity(), (double)event.getAmount());
      }
   }

   public static void execute(DamageSource damagesource, Entity entity, double amount) {
      execute(null, damagesource, entity, amount);
   }

   private static void execute(@Nullable Event event, DamageSource damagesource, Entity entity, double amount) {
      if (damagesource != null && entity != null) {
         double type = 0.0;
         if (amount >= (double)(entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1.0F)
            && (damagesource.is(DamageTypes.EXPLOSION) || damagesource.is(DamageTypes.PLAYER_EXPLOSION))) {
            entity.getPersistentData().putBoolean("have_a_corpse", false);
         }
      }
   }
}
