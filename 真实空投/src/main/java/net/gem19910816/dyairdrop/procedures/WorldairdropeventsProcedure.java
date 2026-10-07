package net.gem19910816.dyairdrop.procedures;

import javax.annotation.Nullable;
import net.gem19910816.dyairdrop.configuration.AirdropconfigConfiguration;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber
public class WorldairdropeventsProcedure {
   @SubscribeEvent
   public static void onWorldTick(LevelTickEvent.Post event) {
      execute(event, event.getLevel());
   }

   public static void execute(LevelAccessor world) {
      execute(null, world);
   }

   private static void execute(@Nullable Event event, LevelAccessor world) {
      Entity player = null;
      String loottable = "";
      String modid = "";
      String worldlist = "";
      String drifts = "";
      double gap = 0.0;
      double now = 0.0;
      double level = 0.0;
      double coefficient = 0.0;
      double position = 0.0;
      double height = 0.0;
      double drift = 0.0;
      double driftmin = 0.0;
      double driftmax = 0.0;
      if ((Boolean)AirdropconfigConfiguration.ENABLEAIRDROPEVENTS.get() && (world instanceof Level _lvl ? _lvl.dimension() : Level.OVERWORLD) == Level.OVERWORLD) {
         if ((Double)AirdropconfigConfiguration.GAP.get() < 1.0) {
            coefficient = 1.0;
         } else {
            coefficient = Math.round((Double)AirdropconfigConfiguration.GAP.get());
         }

         gap = coefficient * 24000.0;
         now = Math.abs(((Level)world).getDayTime() - 100L);
         level = now / gap;
         if (level >= 1.0 && now % gap == 0.0) {
            RandomworldairdropProcedure.execute(world);
         }
      }
   }
}
