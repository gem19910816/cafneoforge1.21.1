package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.init.GoreEditionModMobEffects;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent.Pre;

@EventBusSubscriber
public class ExplosiveOnEntityTickUpdateProcedure {
   @SubscribeEvent
   public static void onEntityTick(Pre event) {
      execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      execute(null, world, x, y, z, entity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(GoreEditionModMobEffects.EXPLODING)) {
            if (entity.getPersistentData().getDouble("explosive_effect_tick") == 0.0 && world instanceof Level _level && !_level.isClientSide()) {
               double var10002;
               double var10003;
               double var10004;
               int var10006;
               label29: {
                  var10002 = x + (double)Mth.nextInt(RandomSource.create(), -1, 1);
                  var10003 = y + (double)(entity.getBbHeight() / 2.0F);
                  var10004 = z + (double)Mth.nextInt(RandomSource.create(), -1, 1);
                  if (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(GoreEditionModMobEffects.EXPLODING)) {
                     var10006 = _livEnt.getEffect(GoreEditionModMobEffects.EXPLODING).getAmplifier();
                     break label29;
                  }

                  var10006 = 0;
               }

               _level.explode(null, var10002, var10003, var10004, (float)(0.7 + (double)(var10006 / 2)), ExplosionInteraction.NONE);
            }

            entity.getPersistentData().putDouble("explosive_effect_tick", entity.getPersistentData().getDouble("explosive_effect_tick") + 1.0);
            if (entity.getPersistentData().getDouble("explosive_effect_tick") == 2.0) {
               entity.getPersistentData().putDouble("explosive_effect_tick", 0.0);
            }

            return;
         }

         entity.getPersistentData().putDouble("explosive_effect_tick", 0.0);
      }
   }
}
