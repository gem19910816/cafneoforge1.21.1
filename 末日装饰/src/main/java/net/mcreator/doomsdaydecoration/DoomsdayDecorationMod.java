package net.mcreator.doomsdaydecoration;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;
import net.mcreator.doomsdaydecoration.block.entity.AcrateBlockEntity;
import net.mcreator.doomsdaydecoration.init.DoomsdayDecorationModBlockEntities;
import net.mcreator.doomsdaydecoration.init.DoomsdayDecorationModBlocks;
import net.mcreator.doomsdaydecoration.init.DoomsdayDecorationModItems;
import net.mcreator.doomsdaydecoration.init.DoomsdayDecorationModSounds;
import net.mcreator.doomsdaydecoration.init.DoomsdayDecorationModTabs;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.util.thread.SidedThreadGroups;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(DoomsdayDecorationMod.MODID)
public class DoomsdayDecorationMod {
    public static final Logger LOGGER = LogManager.getLogger(DoomsdayDecorationMod.class);
    public static final String MODID = "doomsday_decoration";
    private static final Collection<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<AbstractMap.SimpleEntry<Runnable, Integer>>();

    public DoomsdayDecorationMod(IEventBus modEventBus) {
        NeoForge.EVENT_BUS.register(this);
        DoomsdayDecorationModSounds.REGISTRY.register(modEventBus);
        DoomsdayDecorationModBlocks.REGISTRY.register(modEventBus);
        DoomsdayDecorationModBlockEntities.REGISTRY.register(modEventBus);
        DoomsdayDecorationModItems.REGISTRY.register(modEventBus);
        DoomsdayDecorationModTabs.REGISTRY.register(modEventBus);
        modEventBus.addListener(this::registerCapabilities);
    }

    /**
     * The crates expose their inventory to item pipes/hoppers through the NeoForge item handler capability.
     * One sided wrapper per access side, exactly like the original Forge mod did with LazyOptional handlers.
     */
    private void registerCapabilities(RegisterCapabilitiesEvent event) {
        BlockEntityType<AcrateBlockEntity> crateType =
                (BlockEntityType<AcrateBlockEntity>) (BlockEntityType<?>) DoomsdayDecorationModBlockEntities.ACRATE.get();
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, crateType,
                (blockEntity, side) -> side == null ? null : new SidedInvWrapper(blockEntity, side));
    }

    public static void queueServerWork(int tick, Runnable action) {
        if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER) {
            workQueue.add(new AbstractMap.SimpleEntry<Runnable, Integer>(action, tick));
        }
    }

    @SubscribeEvent
    public void tick(ServerTickEvent.Post event) {
        ArrayList<AbstractMap.SimpleEntry<Runnable, Integer>> actions = new ArrayList<AbstractMap.SimpleEntry<Runnable, Integer>>();
        workQueue.forEach(work -> {
            work.setValue((Integer) work.getValue() - 1);
            if ((Integer) work.getValue() == 0) {
                actions.add(work);
            }
        });
        actions.forEach(e -> ((Runnable) e.getKey()).run());
        workQueue.removeAll(actions);
    }
}
