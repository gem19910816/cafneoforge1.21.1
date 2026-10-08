package net.mcreator.dyairdrop;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import net.mcreator.dyairdrop.init.DyairdropModBlockEntities;
import net.mcreator.dyairdrop.init.DyairdropModBlocks;
import net.mcreator.dyairdrop.init.DyairdropModConfigs;
import net.mcreator.dyairdrop.init.DyairdropModEntities;
import net.mcreator.dyairdrop.init.DyairdropModItems;
import net.mcreator.dyairdrop.init.DyairdropModMenus;
import net.mcreator.dyairdrop.init.DyairdropModParticleTypes;
import net.mcreator.dyairdrop.init.DyairdropModSounds;
import net.mcreator.dyairdrop.init.DyairdropModTabs;
import net.mcreator.dyairdrop.network.DyairdropModVariables;
import net.mcreator.dyairdrop.network.PannelButtonMessage;
import net.mcreator.dyairdrop.network.PannelRE2ButtonMessage;
import net.mcreator.dyairdrop.network.PannelREButtonMessage;
import net.mcreator.dyairdrop.network.TestGUI2ButtonMessage;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("dyairdrop")
public class DyairdropMod {
   public static final Logger LOGGER = LogManager.getLogger(DyairdropMod.class);
   public static final String MODID = "dyairdrop";
   private static final Collection<SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();

   public DyairdropMod(IEventBus modEventBus, ModContainer modContainer) {
      DyairdropModSounds.REGISTRY.register(modEventBus);
      DyairdropModBlocks.REGISTRY.register(modEventBus);
      DyairdropModBlockEntities.REGISTRY.register(modEventBus);
      DyairdropModItems.REGISTRY.register(modEventBus);
      DyairdropModEntities.REGISTRY.register(modEventBus);
      DyairdropModTabs.REGISTRY.register(modEventBus);
      DyairdropModParticleTypes.REGISTRY.register(modEventBus);
      DyairdropModMenus.REGISTRY.register(modEventBus);
      DyairdropModVariables.REGISTRY.register(modEventBus);
      DyairdropModConfigs.register(modContainer);
      modEventBus.addListener(DyairdropMod::registerPackets);
      NeoForge.EVENT_BUS.addListener(DyairdropMod::onServerTick);
   }

   private static void registerPackets(RegisterPayloadHandlersEvent event) {
      PayloadRegistrar registrar = event.registrar("1");
      registrar.playToServer(PannelButtonMessage.TYPE, PannelButtonMessage.STREAM_CODEC, PannelButtonMessage::handle);
      registrar.playToServer(PannelREButtonMessage.TYPE, PannelREButtonMessage.STREAM_CODEC, PannelREButtonMessage::handle);
      registrar.playToServer(PannelRE2ButtonMessage.TYPE, PannelRE2ButtonMessage.STREAM_CODEC, PannelRE2ButtonMessage::handle);
      registrar.playToServer(TestGUI2ButtonMessage.TYPE, TestGUI2ButtonMessage.STREAM_CODEC, TestGUI2ButtonMessage::handle);
      registrar.playToClient(
         DyairdropModVariables.PlayerVariablesSyncMessage.TYPE,
         DyairdropModVariables.PlayerVariablesSyncMessage.STREAM_CODEC,
         DyairdropModVariables.PlayerVariablesSyncMessage::handle);
      registrar.playToClient(
         DyairdropModVariables.SavedDataSyncMessage.TYPE,
         DyairdropModVariables.SavedDataSyncMessage.STREAM_CODEC,
         DyairdropModVariables.SavedDataSyncMessage::handle);
   }

   public static void queueServerWork(int tick, Runnable action) {
      workQueue.add(new SimpleEntry<>(action, tick));
   }

   private static void onServerTick(ServerTickEvent.Post event) {
      List<SimpleEntry<Runnable, Integer>> actions = new ArrayList<>();
      workQueue.forEach(work -> {
         work.setValue(work.getValue() - 1);
         if (work.getValue() == 0) {
            actions.add(work);
         }
      });
      actions.forEach(e -> e.getKey().run());
      workQueue.removeAll(actions);
   }
}
