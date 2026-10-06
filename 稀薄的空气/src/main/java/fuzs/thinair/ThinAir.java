package fuzs.thinair;

import fuzs.puzzleslib.api.core.v1.ContentRegistrationFlags;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.api.core.v1.context.CreativeModeTabContext;
import fuzs.puzzleslib.api.event.v1.entity.living.LivingBreathEvents;
import fuzs.puzzleslib.api.event.v1.entity.living.LivingHurtCallback;
import fuzs.puzzleslib.api.event.v1.entity.player.PlayerInteractEvents;
import fuzs.puzzleslib.api.event.v1.level.ServerChunkEvents;
import fuzs.puzzleslib.api.event.v1.level.ServerLevelEvents;
import fuzs.puzzleslib.api.event.v1.level.ServerLevelTickEvents;
import fuzs.puzzleslib.api.event.v1.server.LootTableLoadEvents;
import fuzs.puzzleslib.api.item.v2.CreativeModeTabConfigurator;
import fuzs.puzzleslib.api.network.v3.NetworkHandler;
import fuzs.thinair.handler.AirBubbleTracker;
import fuzs.thinair.handler.DrownedAttackHandler;
import fuzs.thinair.handler.TickAirHandler;
import fuzs.thinair.init.ModRegistry;
import fuzs.thinair.network.ClientboundChunkAirQualityMessage;
import fuzs.thinair.world.level.block.SignalTorchBlock;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;
import java.util.function.IntPredicate;

public class ThinAir implements ModConstructor {
    public static final String MOD_ID = "thinair";
    public static final String MOD_NAME = "Thin Air";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static final NetworkHandler NETWORK = NetworkHandler.builder(MOD_ID).registerClientbound(ClientboundChunkAirQualityMessage.class);

    @Override
    public void onConstructMod() {
        ModRegistry.touch();
        registerHandlers();
    }

    private static void registerHandlers() {
        LootTableLoadEvents.MODIFY.register((ResourceLocation identifier, Consumer<LootPool> addPool, IntPredicate removePool) -> {
            injectLootPool(identifier, addPool, BuiltInLootTables.BURIED_TREASURE.location(), ModRegistry.SOULFIRE_BOTTLE_BURIED_LOOT_TABLE);
            injectLootPool(identifier, addPool, BuiltInLootTables.SHIPWRECK_TREASURE.location(), ModRegistry.SOULFIRE_BOTTLE_SHIPWRECK_LOOT_TABLE);
            injectLootPool(identifier, addPool, BuiltInLootTables.UNDERWATER_RUIN_BIG.location(), ModRegistry.SOULFIRE_BOTTLE_BIG_RUIN_LOOT_TABLE);
            injectLootPool(identifier, addPool, BuiltInLootTables.UNDERWATER_RUIN_SMALL.location(), ModRegistry.SOULFIRE_BOTTLE_SMALL_RUIN_LOOT_TABLE);
            injectLootPool(identifier, addPool, BuiltInLootTables.SIMPLE_DUNGEON.location(), ModRegistry.SAFETY_LANTERN_DUNGEON_LOOT_TABLE);
            injectLootPool(identifier, addPool, BuiltInLootTables.ABANDONED_MINESHAFT.location(), ModRegistry.SAFETY_LANTERN_MINESHAFT_LOOT_TABLE);
            injectLootPool(identifier, addPool, BuiltInLootTables.STRONGHOLD_CORRIDOR.location(), ModRegistry.SAFETY_LANTERN_STRONGHOLD_LOOT_TABLE);
        });
        PlayerInteractEvents.USE_BLOCK.register(SignalTorchBlock::onUseBlock);
        ServerChunkEvents.LOAD.register(AirBubbleTracker::onChunkLoad);
        ServerChunkEvents.UNLOAD.register(AirBubbleTracker::onChunkUnload);
        ServerLevelEvents.UNLOAD.register(AirBubbleTracker::onLevelUnload);
        ServerLevelTickEvents.END.register(AirBubbleTracker::onEndLevelTick);
        LivingHurtCallback.EVENT.register(DrownedAttackHandler::onLivingHurt);
        LivingBreathEvents.BREATHE.register(TickAirHandler::onLivingBreathe);
        ServerChunkEvents.WATCH.register(AirBubbleTracker::onChunkWatch);
    }

    private static void injectLootPool(ResourceLocation identifier, Consumer<LootPool> addPool, ResourceLocation builtInLootTable, ResourceKey<LootTable> injectedLootTable) {
        if (identifier.equals(builtInLootTable)) {
            addPool.accept(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(NestedLootTable.lootTableReference(injectedLootTable)).build());
        }
    }

    @Override
    public void onRegisterCreativeModeTabs(CreativeModeTabContext context) {
        context.registerCreativeModeTab(CreativeModeTabConfigurator.from(MOD_ID).icon(() -> new ItemStack(ModRegistry.AIR_BLADDER_ITEM.value())).displayItems((itemDisplayParameters, output) -> {
            output.accept(ModRegistry.RESPIRATOR_ITEM.value());
            output.accept(ModRegistry.AIR_BLADDER_ITEM.value());
            output.accept(ModRegistry.REINFORCED_AIR_BLADDER_ITEM.value());
            output.accept(ModRegistry.SOULFIRE_BOTTLE_ITEM.value());
            output.accept(ModRegistry.SAFETY_LANTERN_ITEM.value());
        }));
    }

    @Override
    public ContentRegistrationFlags[] getContentRegistrationFlags() {
        return new ContentRegistrationFlags[]{ContentRegistrationFlags.COPY_RECIPES};
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
