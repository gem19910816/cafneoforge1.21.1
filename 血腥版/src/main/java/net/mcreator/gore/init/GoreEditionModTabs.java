package net.mcreator.gore.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(
   bus = Bus.MOD
)
public class GoreEditionModTabs {
   public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "gore_edition");
   public static final DeferredHolder<CreativeModeTab, CreativeModeTab> GORE_EDITION = REGISTRY.register(
      "gore_edition",
      () -> CreativeModeTab.builder()
            .title(Component.translatable("item_group.gore_edition.gore_edition"))
            .icon(() -> new ItemStack((ItemLike)GoreEditionModItems.GEART.get()))
            .displayItems((parameters, tabData) -> {
               tabData.accept(((Block)GoreEditionModBlocks.CREEPER_GRASS.get()).asItem());
               tabData.accept((ItemLike)GoreEditionModItems.HEADLESS_ZOMBIE_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.SEVEREDLEGS_ZOMBIE_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.DISARMED_ZOMBIE_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.SEVERED_LEGS_AND_ARM_ZOMBIE_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.ZOMBIE_ABOUT_TO_DIE_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.HEADLESS_HUSK_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.SEVEREDLEGS_HUSK_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.DISARMED_HUSK_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.SEVERED_LEGS_AND_ARM_HUSK_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.HUSK_ABOUT_TO_DIE_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.DISMEMBERED_SPIDER_I_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.DISMEMBERED_SPIDER_II_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.DISMEMBERED_SPIDER_III_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.BABY_SPIDER_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.VERTICAL_CUTTED_ZOMBIE_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.HORIZONTALLY_CUTTED_ZOMBIE_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.CRUSHED_ZOMBIE_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.VERTICALCUTTED_HUSK_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.HORIZONTALLY_CUTTED_HUSK_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.CRUSHED_HUSK_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.VERTICALLY_CUTTED_SPIDER_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.HORIZONTALLY_CUTTED_SPIDER_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.EXPLODED_HEAD_SPIDER_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.ZOMBIE_CORPSE_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.ZOMBIE_WITHOUT_LEGS_AND_ARM_CORPSE_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.SEVERED_LEGS_ZOMBIE_CORPSE_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.CREEPER_CORPSE_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.DISARMED_ZOMBIE_CORPSE_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.HEADLESS_ZOMBIE_CORPSE_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.HUSK_CORPSE_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.HUSK_WITHOUT_LEGS_AND_ARM_CORPSE_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.SEVERED_LEGS_HUSK_CORPSE_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.DISARMED_HUSK_CORPSE_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.HEADLESS_HUSK_CORPSE_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.SKELETON_RIBS_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.SKELETON_HEAD_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.SKELETON_PELVIS_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.SKELETON_SPINAL_COLUMN_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.SKELETON_RIGHT_ARM_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.SKELETON_LEFT_ARM_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.SKELETON_LEFT_LEG_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.SKELETON_RIGHT_LEG_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.SKELETON_CORPSE_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.DISMEMBERED_SPIDER_I_CORPSE_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.DISMEMBERED_SPIDER_II_CORPSE_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.DISMEMBERED_SPIDER_III_CORPSE_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.SKELETON_CORPSE_III_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.SKELETON_CORPSE_II_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.SPIDER_CORPSE_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.SEVERED_LEGS_SKELETON_SPAWN_EGG.get());
               tabData.accept((ItemLike)GoreEditionModItems.VERTICALLY_CUTTED_SKELETON_SPAWN_EGG.get());
            })
            .build()
   );
   public static final DeferredHolder<CreativeModeTab, CreativeModeTab> GORE_EDITION_2 = REGISTRY.register(
      "gore_edition_2",
      () -> CreativeModeTab.builder()
            .title(Component.translatable("item_group.gore_edition.gore_edition_2"))
            .icon(() -> new ItemStack((ItemLike)GoreEditionModItems.SPECTRAL_EXECUTIONERS_AXE.get()))
            .displayItems((parameters, tabData) -> {
               tabData.accept((ItemLike)GoreEditionModItems.SPIRAL.get());
               tabData.accept((ItemLike)GoreEditionModItems.NEEDLE_SCYTHE_CORDYCEPS.get());
               tabData.accept((ItemLike)GoreEditionModItems.VOODOO_RABBIT.get());
               tabData.accept((ItemLike)GoreEditionModItems.VITALITY_STEALINGS_FANGS.get());
               tabData.accept((ItemLike)GoreEditionModItems.OBSIDIAN_KNIFE.get());
               tabData.accept((ItemLike)GoreEditionModItems.SPECTRAL_GIFT.get());
               tabData.accept((ItemLike)GoreEditionModItems.AMETHYST_WAVE.get());
               tabData.accept((ItemLike)GoreEditionModItems.NEEDLE.get());
               tabData.accept((ItemLike)GoreEditionModItems.CORDYCEPS_FUNGUS.get());
               tabData.accept((ItemLike)GoreEditionModItems.THROWABLE_WITHER_SKULL.get());
               tabData.accept((ItemLike)GoreEditionModItems.NETHER_GRENADE.get());
               tabData.accept((ItemLike)GoreEditionModItems.ASHES.get());
               tabData.accept((ItemLike)GoreEditionModItems.THIRD_HAND.get());
               tabData.accept((ItemLike)GoreEditionModItems.MUSIC_DISC_INTERNAL_INSANITY.get());
               tabData.accept((ItemLike)GoreEditionModItems.MUSIC_DISC_POISONED.get());
               tabData.accept((ItemLike)GoreEditionModItems.MUSIC_DISC_LIMBO.get());
               tabData.accept((ItemLike)GoreEditionModItems.MUSIC_DISC_THE_XASH.get());
            })
            .build()
   );
   public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DESTRUCTION_BY_GORE_EDITION = REGISTRY.register(
      "destruction_by_gore_edition",
      () -> CreativeModeTab.builder()
            .title(Component.translatable("item_group.gore_edition.destruction_by_gore_edition"))
            .icon(() -> new ItemStack((ItemLike)GoreEditionModItems.SQUITCHGUN.get()))
            .displayItems((parameters, tabData) -> {
               tabData.accept((ItemLike)GoreEditionModItems.SQUITCHGUN.get());
               tabData.accept((ItemLike)GoreEditionModItems.GEART.get());
            })
            .build()
   );

   @SubscribeEvent
   public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
      if (tabData.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
         tabData.accept((ItemLike)GoreEditionModItems.DISARMED_SKELETON_RIGHT_ARM_SPAWN_EGG.get());
         tabData.accept((ItemLike)GoreEditionModItems.DISARMED_SKELETON_LEFT_ARM_SPAWN_EGG.get());
         tabData.accept((ItemLike)GoreEditionModItems.COLLAPSING_SKELETON_SPAWN_EGG.get());
         tabData.accept((ItemLike)GoreEditionModItems.THE_HELL_ZONE_THING_SPAWN_EGG.get());
         tabData.accept((ItemLike)GoreEditionModItems.CRUSHING_SKELETON_SPAWN_EGG.get());
      }
   }
}
