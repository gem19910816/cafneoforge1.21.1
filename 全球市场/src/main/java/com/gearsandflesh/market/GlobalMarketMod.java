package com.gearsandflesh.market;

import com.gearsandflesh.market.block.MarketTerminalBlock;
import com.gearsandflesh.market.config.MarketConfig;
import com.gearsandflesh.market.network.MarketNetwork;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

import java.util.function.Supplier;

@Mod(MarketConstants.MOD_ID)
public final class GlobalMarketMod {
    public static final Logger LOGGER = LogUtils.getLogger();

    /** The terminal block intentionally has NO item form: it cannot appear in
     *  the creative inventory and /give cannot produce it. Admins place it
     *  with /setblock. */
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MarketConstants.MOD_ID);

    public static final Supplier<Block> MARKET_TERMINAL = BLOCKS.register("market_terminal",
            () -> new MarketTerminalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)
                    .strength(5.0F, 1200.0F)
                    .requiresCorrectToolForDrops()
                    .noLootTable()));

    /** Built-in currency, registered as caf:money so the website, KubeJS-era
     *  data and existing scripts all agree on one id. Registered from this mod
     *  because the 1.21.1 pack has no KubeJS registration for it.
     *  It is intentionally not added to any creative tab: the only faucet is
     *  wallet withdrawal on the website. */
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems("caf");

    public static final Supplier<Item> MONEY = ITEMS.register("money",
            () -> new Item(new Item.Properties().stacksTo(64)));

    public GlobalMarketMod(IEventBus modEventBus, ModContainer modContainer) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        modEventBus.addListener(MarketNetwork::register);
        modContainer.registerConfig(ModConfig.Type.COMMON, MarketConfig.SPEC);
    }
}
