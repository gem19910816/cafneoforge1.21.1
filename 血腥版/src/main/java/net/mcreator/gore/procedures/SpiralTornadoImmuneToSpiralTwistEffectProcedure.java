package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.entity.SpiralTornadoEntity;
import net.mcreator.gore.init.GoreEditionModMobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent.Pre;

@EventBusSubscriber
public class SpiralTornadoImmuneToSpiralTwistEffectProcedure {
   @SubscribeEvent
   public static void onEntityTick(Pre event) {
      execute(event, event.getEntity());
   }

   public static void execute(Entity entity) {
      execute(null, entity);
   }

   private static void execute(@Nullable Event event, Entity entity) {
      if (entity != null
         && entity instanceof SpiralTornadoEntity
         && entity instanceof LivingEntity _livEnt1
         && _livEnt1.hasEffect(GoreEditionModMobEffects.SPIRAL_TWIST)
         && entity instanceof LivingEntity _entity) {
         _entity.removeEffect(GoreEditionModMobEffects.SPIRAL_TWIST);
      }
   }
}
