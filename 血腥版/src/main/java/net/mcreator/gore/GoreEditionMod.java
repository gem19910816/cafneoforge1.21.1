package net.mcreator.gore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;
import java.util.concurrent.ConcurrentLinkedQueue;
import net.mcreator.gore.init.GoreEditionModBlockEntities;
import net.mcreator.gore.init.GoreEditionModBlocks;
import net.mcreator.gore.init.GoreEditionModEntities;
import net.mcreator.gore.init.GoreEditionModFluidTypes;
import net.mcreator.gore.init.GoreEditionModFluids;
import net.mcreator.gore.init.GoreEditionModItems;
import net.mcreator.gore.init.GoreEditionModMenus;
import net.mcreator.gore.init.GoreEditionModMobEffects;
import net.mcreator.gore.init.GoreEditionModParticleTypes;
import net.mcreator.gore.init.GoreEditionModSounds;
import net.mcreator.gore.init.GoreEditionModTabs;
import net.mcreator.gore.network.GoreEditionModVariables;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.util.thread.SidedThreadGroups;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.ServerTickEvent.Post;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("gore_edition")
public class GoreEditionMod {
   public static final Logger LOGGER = LogManager.getLogger(GoreEditionMod.class);
   public static final String MODID = "gore_edition";
   private static final Collection<SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();

   public GoreEditionMod() {
      NeoForge.EVENT_BUS.register(this);
      IEventBus bus = ModLoadingContext.get().getActiveContainer().getEventBus();
      GoreEditionModSounds.REGISTRY.register(bus);
      GoreEditionModBlocks.REGISTRY.register(bus);
      GoreEditionModBlockEntities.REGISTRY.register(bus);
      GoreEditionModItems.REGISTRY.register(bus);
      GoreEditionModEntities.REGISTRY.register(bus);
      GoreEditionModTabs.REGISTRY.register(bus);
      GoreEditionModMobEffects.REGISTRY.register(bus);
      GoreEditionModParticleTypes.REGISTRY.register(bus);
      GoreEditionModMenus.REGISTRY.register(bus);
      GoreEditionModFluids.REGISTRY.register(bus);
      GoreEditionModFluidTypes.REGISTRY.register(bus);
      GoreEditionModVariables.ATTACHMENT_TYPES.register(bus);
   }

   public static void queueServerWork(int tick, Runnable action) {
      if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER) {
         workQueue.add(new SimpleEntry<>(action, tick));
      }
   }

   @SubscribeEvent
   public void tick(Post event) {
      List<SimpleEntry<Runnable, Integer>> actions = new ArrayList<>();
      workQueue.forEach(work -> {
         work.setValue(work.getValue() - 1);
         if (work.getValue() == 0) {
            actions.add((SimpleEntry<Runnable, Integer>)work);
         }
      });
      actions.forEach(e -> e.getKey().run());
      workQueue.removeAll(actions);
   }
}
