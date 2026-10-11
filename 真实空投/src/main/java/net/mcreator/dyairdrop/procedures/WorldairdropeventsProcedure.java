package net.mcreator.dyairdrop.procedures;

import java.util.Arrays;
import java.util.List;
import net.mcreator.dyairdrop.configuration.AirdropconfigConfiguration;
import net.mcreator.dyairdrop.world.AirdropScheduleData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

@EventBusSubscriber
public class WorldairdropeventsProcedure {
   public WorldairdropeventsProcedure() {
   }

   @SubscribeEvent
   public static void onWorldTick(LevelTickEvent.Post event) {
      if (event.getLevel() instanceof ServerLevel level) {
         execute(level);
      }
   }

   public static void execute(ServerLevel world) {
      if (!(Boolean)AirdropconfigConfiguration.ENABLEAIRDROPEVENTS.get()
         || !world.dimension().equals(Level.OVERWORLD)) {
         return;
      }

      double configuredDays = AirdropconfigConfiguration.GAP.get();
      long daysBetween = Double.isFinite(configuredDays) ? Math.round(configuredDays) : 10L;
      daysBetween = Math.min(Long.MAX_VALUE / 24000L, Math.max(1L, daysBetween));
      long interval = daysBetween * 24000L;
      if (!AirdropScheduleData.get(world).poll(world.getGameTime(), interval)) {
         return;
      }

      List<String> dimensions = Arrays.stream(AirdropconfigConfiguration.AVAILABLEWORLD.get().split(","))
         .map(String::trim).filter(id -> !id.isEmpty()).toList();
      // A global event has one clock. Other dimensions contribute eligible players,
      // and an empty server skips this opportunity without a burst on the next login.
      if (world.getServer().getPlayerList().getPlayers().stream()
         .anyMatch(player -> dimensions.contains(player.level().dimension().location().toString()))) {
         RandomworldairdropProcedure.execute(world);
      }
   }
}
