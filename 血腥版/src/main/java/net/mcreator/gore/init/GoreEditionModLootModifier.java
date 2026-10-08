package net.mcreator.gore.init;

import com.google.common.base.Suppliers;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.function.Supplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.fml.event.lifecycle.FMLConstructModEvent;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries.Keys;

@EventBusSubscriber(
   modid = "gore_edition",
   bus = Bus.MOD
)
public class GoreEditionModLootModifier {
   public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(
      Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, "gore_edition"
   );
   public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<GoreEditionModLootModifier.GoreEditionModLootTableModifier>> LOOT_MODIFIER = LOOT_MODIFIERS.register(
      "gore_edition_loot_modifier", GoreEditionModLootModifier.GoreEditionModLootTableModifier.CODEC
   );

   @SubscribeEvent
   public static void register(FMLConstructModEvent event) {
      IEventBus bus = ModLoadingContext.get().getActiveContainer().getEventBus();
      event.enqueueWork(() -> LOOT_MODIFIERS.register(bus));
   }

   public static class GoreEditionModLootTableModifier extends LootModifier {
      public static final Supplier<MapCodec<GoreEditionModLootModifier.GoreEditionModLootTableModifier>> CODEC = Suppliers.memoize(
         () -> RecordCodecBuilder.mapCodec(
               instance -> codecStart(instance)
                     .and(ResourceLocation.CODEC.fieldOf("lootTable").forGetter(m -> m.lootTable))
                     .apply(instance, GoreEditionModLootModifier.GoreEditionModLootTableModifier::new)
            )
      );
      private final ResourceLocation lootTable;

      public GoreEditionModLootTableModifier(LootItemCondition[] conditions, ResourceLocation lootTable) {
         super(conditions);
         this.lootTable = lootTable;
      }

      protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
         LootTable _tbl = context.getLevel().getServer().reloadableRegistries().getLootTable(ResourceKey.create(Registries.LOOT_TABLE, this.lootTable));
         _tbl.getRandomItemsRaw(context, generatedLoot::add);
         return generatedLoot;
      }

      public MapCodec<? extends IGlobalLootModifier> codec() {
         return CODEC.get();
      }
   }
}
