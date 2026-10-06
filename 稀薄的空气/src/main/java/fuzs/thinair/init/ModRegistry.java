package fuzs.thinair.init;

import com.mojang.serialization.Codec;
import fuzs.puzzleslib.api.capability.v3.CapabilityController;
import fuzs.puzzleslib.api.capability.v3.data.LevelChunkCapabilityKey;
import fuzs.puzzleslib.api.core.v1.ModLoaderEnvironment;
import fuzs.puzzleslib.api.init.v3.registry.RegistryManager;
import fuzs.puzzleslib.api.init.v3.tags.BoundTagFactory;
import fuzs.thinair.ThinAir;
import fuzs.thinair.advancements.criterion.BreatheAirTrigger;
import fuzs.thinair.advancements.criterion.SignalifyTorchTrigger;
import fuzs.thinair.advancements.criterion.UsedSoulfireTrigger;
import fuzs.thinair.capability.AirBubblePositionsCapability;
import fuzs.thinair.neoforge.world.item.AirBladderNeoForgeItem;
import fuzs.thinair.world.item.SoulfireBottleItem;
import fuzs.thinair.world.level.block.SafetyLanternBlock;
import fuzs.thinair.world.level.block.SignalTorchBlock;
import fuzs.thinair.world.level.block.WallSignalTorchBlock;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.Util;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ModRegistry {
    static final RegistryManager REGISTRY = RegistryManager.from(ThinAir.MOD_ID);
    public static final Holder.Reference<ArmorMaterial> RESPIRATOR_ARMOR_MATERIAL = REGISTRY.registerArmorMaterial("respirator", () -> new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                for (Map.Entry<ArmorItem.Type, Integer> entry : Map.of(ArmorItem.Type.BOOTS, 1,
                                ArmorItem.Type.LEGGINGS, 1,
                                ArmorItem.Type.CHESTPLATE, 1,
                                ArmorItem.Type.HELMET, 1,
                                ArmorItem.Type.BODY, 1
                ).entrySet()) {
                    map.put(entry.getKey(), entry.getValue());
                }
            }),
            0,
            SoundEvents.ARMOR_EQUIP_LEATHER,
            () -> Ingredient.of(Items.CHARCOAL),
            List.of(new ArmorMaterial.Layer(ThinAir.id("respirator"))),
            0.0F,
            0.0F
    ));

    public static final Holder.Reference<Block> SIGNAL_TORCH_BLOCK = REGISTRY.registerBlock("signal_torch", () -> new SignalTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH)));
    public static final Holder.Reference<Block> WALL_SIGNAL_TORCH_BLOCK = REGISTRY.registerBlock("wall_signal_torch", () -> new WallSignalTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WALL_TORCH).dropsLike(SIGNAL_TORCH_BLOCK.value())));
    public static final Holder.Reference<Block> SAFETY_LANTERN_BLOCK = REGISTRY.registerBlock("safety_lantern", () -> new SafetyLanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).lightLevel(state -> state.getValue(SafetyLanternBlock.AIR_QUALITY).getLightLevel())));
    public static final Holder.Reference<Item> RESPIRATOR_ITEM;
    public static final Holder.Reference<Item> AIR_BLADDER_ITEM = REGISTRY.registerItem("air_bladder", () -> new AirBladderNeoForgeItem(new Item.Properties().durability(327)));
    public static final Holder.Reference<Item> REINFORCED_AIR_BLADDER_ITEM = REGISTRY.registerItem("reinforced_air_bladder", () -> new AirBladderNeoForgeItem(new Item.Properties().durability(1962)));
    public static final Holder.Reference<Item> SOULFIRE_BOTTLE_ITEM = REGISTRY.registerItem("soulfire_bottle", () -> new SoulfireBottleItem(new Item.Properties()));
    public static final Holder.Reference<Item> SAFETY_LANTERN_ITEM = REGISTRY.registerBlockItem(SAFETY_LANTERN_BLOCK);
    public static final Holder.Reference<BreatheAirTrigger> BREATHE_AIR_TRIGGER = REGISTRY.register(Registries.TRIGGER_TYPE, "breathe_air", () -> new BreatheAirTrigger());
    public static final Holder.Reference<SignalifyTorchTrigger> SIGNALIFY_TORCH_TRIGGER = REGISTRY.register(Registries.TRIGGER_TYPE, "signalify_torch", () -> new SignalifyTorchTrigger());
    public static final Holder.Reference<UsedSoulfireTrigger> USED_SOULFIRE_TRIGGER = REGISTRY.register(Registries.TRIGGER_TYPE, "used_soulfire", () -> new UsedSoulfireTrigger());
    public static final Holder.Reference<DataComponentType<Integer>> AIR_QUALITY_LEVEL_COMPONENT = REGISTRY.registerDataComponentType("air_quality_level",
            builder -> builder.persistent(Codec.INT).networkSynchronized(ByteBufCodecs.VAR_INT)
    );

    static final BoundTagFactory TAGS = BoundTagFactory.make(ThinAir.MOD_ID);
    public static final TagKey<Item> AIR_REFILLER_ITEM_TAG = TAGS.registerItemTag("air_refiller");
    public static final TagKey<EntityType<?>> AIR_QUALITY_SENSITIVE_ENTITY_TYPE_TAG = TAGS.registerEntityTypeTag("air_quality_sensitive");

    public static final ResourceKey<LootTable> SOULFIRE_BOTTLE_BURIED_LOOT_TABLE = REGISTRY.registerLootTable("chest/inject/soulfire_bottle_buried");
    public static final ResourceKey<LootTable> SOULFIRE_BOTTLE_SHIPWRECK_LOOT_TABLE = REGISTRY.registerLootTable("chest/inject/soulfire_bottle_shipwreck");
    public static final ResourceKey<LootTable> SOULFIRE_BOTTLE_BIG_RUIN_LOOT_TABLE = REGISTRY.registerLootTable("chest/inject/soulfire_bottle_big_ruin");
    public static final ResourceKey<LootTable> SOULFIRE_BOTTLE_SMALL_RUIN_LOOT_TABLE = REGISTRY.registerLootTable("chest/inject/soulfire_bottle_small_ruin");
    public static final ResourceKey<LootTable> SAFETY_LANTERN_DUNGEON_LOOT_TABLE = REGISTRY.registerLootTable("chest/inject/safety_lantern_dungeon");
    public static final ResourceKey<LootTable> SAFETY_LANTERN_MINESHAFT_LOOT_TABLE = REGISTRY.registerLootTable("chest/inject/safety_lantern_mineshaft");
    public static final ResourceKey<LootTable> SAFETY_LANTERN_STRONGHOLD_LOOT_TABLE = REGISTRY.registerLootTable("chest/inject/safety_lantern_stronghold");

    static final CapabilityController CAPABILITIES = CapabilityController.from(ThinAir.MOD_ID);
    public static final LevelChunkCapabilityKey<AirBubblePositionsCapability> AIR_BUBBLE_POSITIONS_CAPABILITY = CAPABILITIES.registerLevelChunkCapability("air_bubble_positions", AirBubblePositionsCapability.class, AirBubblePositionsCapability::new);

    static {
        if (ModLoaderEnvironment.INSTANCE.isModLoaded("curios") || ModLoaderEnvironment.INSTANCE.isModLoaded("trinkets")) {
            RESPIRATOR_ITEM = REGISTRY.registerItem("respirator", () -> new Item(new Item.Properties().durability(77)));
        } else {
            RESPIRATOR_ITEM = REGISTRY.registerItem("respirator", () -> new ArmorItem(RESPIRATOR_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Properties().durability(77)));
        }
    }

    public static void touch() {

    }
}
