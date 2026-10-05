package net.mcreator.dyairdrop;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;
import java.util.concurrent.ConcurrentLinkedQueue;

import net.mcreator.dyairdrop.init.DyairdropModBlockEntities;
import net.mcreator.dyairdrop.init.DyairdropModCapabilities;
import net.mcreator.dyairdrop.init.DyairdropModBlocks;
import net.mcreator.dyairdrop.init.DyairdropModConfigs;
import net.mcreator.dyairdrop.init.DyairdropModEntities;
import net.mcreator.dyairdrop.init.DyairdropModItems;
import net.mcreator.dyairdrop.init.DyairdropModMenus;
import net.mcreator.dyairdrop.init.DyairdropModParticleTypes;
import net.mcreator.dyairdrop.init.DyairdropModSounds;
import net.mcreator.dyairdrop.init.DyairdropModTabs;
import net.mcreator.dyairdrop.network.DyairdropModVariables;
import net.mcreator.dyairdrop.network.NetworkSetup;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(DyairdropMod.MODID)
public class DyairdropMod {
	public static final Logger LOGGER = LogManager.getLogger(DyairdropMod.class);
	public static final String MODID = "dyairdrop";
	private static final Collection<SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();

	public DyairdropMod(IEventBus modEventBus, ModContainer modContainer) {
		NeoForge.EVENT_BUS.register(this);
		DyairdropModSounds.REGISTRY.register(modEventBus);
		DyairdropModBlocks.REGISTRY.register(modEventBus);
		DyairdropModBlockEntities.REGISTRY.register(modEventBus);
		DyairdropModItems.REGISTRY.register(modEventBus);
		DyairdropModEntities.REGISTRY.register(modEventBus);
		DyairdropModTabs.REGISTRY.register(modEventBus);
		DyairdropModParticleTypes.REGISTRY.register(modEventBus);
		DyairdropModMenus.REGISTRY.register(modEventBus);
		DyairdropModVariables.ATTACHMENT_TYPES.register(modEventBus);
		DyairdropModConfigs.register(modContainer);
		NeoForge.EVENT_BUS.register(DyairdropModVariables.EventBusVariableHandlers.class);
	}

	public static void queueServerWork(int tick, Runnable action) {
		workQueue.add(new SimpleEntry<>(action, tick));
	}

	@SubscribeEvent
	public void tick(ServerTickEvent.Post event) {
		List<SimpleEntry<Runnable, Integer>> actions = new ArrayList<>();
		workQueue.forEach(work -> {
			work.setValue(work.getValue() - 1);
			if (work.getValue() == 0) {
				actions.add((SimpleEntry<Runnable, Integer>) work);
			}
		});
		actions.forEach(e -> e.getKey().run());
		workQueue.removeAll(actions);
	}
}
