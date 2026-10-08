package net.mcreator.gore.init;

import net.mcreator.gore.item.AmethystWaveItem;
import net.mcreator.gore.item.AshesOfTheHelLAshesItem;
import net.mcreator.gore.item.CollapseItem;
import net.mcreator.gore.item.CordycepsFungusItem;
import net.mcreator.gore.item.CordycepsScytheItem;
import net.mcreator.gore.item.ExarrackSwordItem;
import net.mcreator.gore.item.ExecutionersAxItem;
import net.mcreator.gore.item.GeartItem;
import net.mcreator.gore.item.GrenadeOfAcidItem;
import net.mcreator.gore.item.GrenadeOfGreekFireItem;
import net.mcreator.gore.item.ItemForTextureOfACIDGRENADEEEEItem;
import net.mcreator.gore.item.LightingOrbItem;
import net.mcreator.gore.item.MagmaGrenadeItem;
import net.mcreator.gore.item.MusicDiscAshedItem;
import net.mcreator.gore.item.MusicDiscInternalInsanityItem;
import net.mcreator.gore.item.MusicDiscShopItem;
import net.mcreator.gore.item.NeedleItem;
import net.mcreator.gore.item.ObsidianKnifeItem;
import net.mcreator.gore.item.SpectralExecutionerAxeGiftItem;
import net.mcreator.gore.item.SpinesawItem;
import net.mcreator.gore.item.SpiralItem;
import net.mcreator.gore.item.SquitchgunItem;
import net.mcreator.gore.item.SwordOfAshesItem;
import net.mcreator.gore.item.TextureOfAshesBlackHoleItem;
import net.mcreator.gore.item.TextureOfSquitchgunProjectileItem;
import net.mcreator.gore.item.ThirdHandItem;
import net.mcreator.gore.item.ThrowableWitherSkullItem;
import net.mcreator.gore.item.TpacItem;
import net.mcreator.gore.item.VitalitystealingsItem;
import net.mcreator.gore.item.VoodooRabbitItem;
import net.mcreator.gore.procedures.VoodooRabbitPropertyValueProviderProcedure;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(
   bus = Bus.MOD,
   value = {Dist.CLIENT}
)
public class GoreEditionModItems {
   public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(BuiltInRegistries.ITEM, "gore_edition");
   public static final DeferredHolder<Item, Item> HEADLESS_ZOMBIE_SPAWN_EGG = REGISTRY.register(
      "headless_zombie_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.HEADLESS_ZOMBIE, -16734835, -8683445, new Properties())
   );
   public static final DeferredHolder<Item, Item> HORIZONTALLY_CUTTED_ZOMBIE_SPAWN_EGG = REGISTRY.register(
      "horizontally_cutted_zombie_spawn_egg",
      () -> new DeferredSpawnEggItem(GoreEditionModEntities.HORIZONTALLY_CUTTED_ZOMBIE, -15168415, -8364726, new Properties())
   );
   public static final DeferredHolder<Item, Item> VERTICAL_CUTTED_ZOMBIE_SPAWN_EGG = REGISTRY.register(
      "vertical_cutted_zombie_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.VERTICAL_CUTTED_ZOMBIE, -15168415, -8364726, new Properties())
   );
   public static final DeferredHolder<Item, Item> CRUSHED_ZOMBIE_SPAWN_EGG = REGISTRY.register(
      "crushed_zombie_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.CRUSHED_ZOMBIE, -15168415, -8364726, new Properties())
   );
   public static final DeferredHolder<Item, Item> SEVEREDLEGS_ZOMBIE_SPAWN_EGG = REGISTRY.register(
      "severedlegs_zombie_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.SEVEREDLEGS_ZOMBIE, -16734835, -8683445, new Properties())
   );
   public static final DeferredHolder<Item, Item> DISARMED_ZOMBIE_SPAWN_EGG = REGISTRY.register(
      "disarmed_zombie_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.DISARMED_ZOMBIE, -16734835, -8683445, new Properties())
   );
   public static final DeferredHolder<Item, Item> SEVERED_LEGS_AND_ARM_ZOMBIE_SPAWN_EGG = REGISTRY.register(
      "severed_legs_and_arm_zombie_spawn_egg",
      () -> new DeferredSpawnEggItem(GoreEditionModEntities.SEVERED_LEGS_AND_ARM_ZOMBIE, -16734835, -8683445, new Properties())
   );
   public static final DeferredHolder<Item, Item> HORIZONTALLY_CUTTED_SPIDER_SPAWN_EGG = REGISTRY.register(
      "horizontally_cutted_spider_spawn_egg",
      () -> new DeferredSpawnEggItem(GoreEditionModEntities.HORIZONTALLY_CUTTED_SPIDER, -14737128, -8639991, new Properties())
   );
   public static final DeferredHolder<Item, Item> VERTICALLY_CUTTED_SPIDER_SPAWN_EGG = REGISTRY.register(
      "vertically_cutted_spider_spawn_egg",
      () -> new DeferredSpawnEggItem(GoreEditionModEntities.VERTICALLY_CUTTED_SPIDER, -14737128, -8639991, new Properties())
   );
   public static final DeferredHolder<Item, Item> EXPLODED_HEAD_SPIDER_SPAWN_EGG = REGISTRY.register(
      "exploded_head_spider_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.EXPLODED_HEAD_SPIDER, -14737128, -8639991, new Properties())
   );
   public static final DeferredHolder<Item, Item> BABY_SPIDER_SPAWN_EGG = REGISTRY.register(
      "baby_spider_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.BABY_SPIDER, -13357785, -5763570, new Properties())
   );
   public static final DeferredHolder<Item, Item> ZOMBIE_ABOUT_TO_DIE_SPAWN_EGG = REGISTRY.register(
      "zombie_about_to_die_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.ZOMBIE_ABOUT_TO_DIE, -16734835, -8683445, new Properties())
   );
   public static final DeferredHolder<Item, Item> HEADLESS_HUSK_SPAWN_EGG = REGISTRY.register(
      "headless_husk_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.HEADLESS_HUSK, -11121340, -1661819, new Properties())
   );
   public static final DeferredHolder<Item, Item> HORIZONTALLY_CUTTED_HUSK_SPAWN_EGG = REGISTRY.register(
      "horizontally_cutted_husk_spawn_egg",
      () -> new DeferredSpawnEggItem(GoreEditionModEntities.HORIZONTALLY_CUTTED_HUSK, -12633549, -3565430, new Properties())
   );
   public static final DeferredHolder<Item, Item> VERTICALCUTTED_HUSK_SPAWN_EGG = REGISTRY.register(
      "verticalcutted_husk_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.VERTICALCUTTED_HUSK, -12633549, -3565430, new Properties())
   );
   public static final DeferredHolder<Item, Item> CRUSHED_HUSK_SPAWN_EGG = REGISTRY.register(
      "crushed_husk_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.CRUSHED_HUSK, -12633549, -3565430, new Properties())
   );
   public static final DeferredHolder<Item, Item> SEVEREDLEGS_HUSK_SPAWN_EGG = REGISTRY.register(
      "severedlegs_husk_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.SEVEREDLEGS_HUSK, -11121340, -1661819, new Properties())
   );
   public static final DeferredHolder<Item, Item> SEVERED_LEGS_AND_ARM_HUSK_SPAWN_EGG = REGISTRY.register(
      "severed_legs_and_arm_husk_spawn_egg",
      () -> new DeferredSpawnEggItem(GoreEditionModEntities.SEVERED_LEGS_AND_ARM_HUSK, -11121340, -1661819, new Properties())
   );
   public static final DeferredHolder<Item, Item> HUSK_ABOUT_TO_DIE_SPAWN_EGG = REGISTRY.register(
      "husk_about_to_die_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.HUSK_ABOUT_TO_DIE, -11121340, -1661819, new Properties())
   );
   public static final DeferredHolder<Item, Item> DISARMED_HUSK_SPAWN_EGG = REGISTRY.register(
      "disarmed_husk_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.DISARMED_HUSK, -11121340, -1661819, new Properties())
   );
   public static final DeferredHolder<Item, Item> DISMEMBERED_SPIDER_I_SPAWN_EGG = REGISTRY.register(
      "dismembered_spider_i_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.DISMEMBERED_SPIDER_I, -13356505, -5759987, new Properties())
   );
   public static final DeferredHolder<Item, Item> NEEDLE = REGISTRY.register("needle", () -> new NeedleItem());
   public static final DeferredHolder<Item, Item> VOODOO_RABBIT = REGISTRY.register("voodoo_rabbit", () -> new VoodooRabbitItem());
   public static final DeferredHolder<Item, Item> ZOMBIE_CORPSE_SPAWN_EGG = REGISTRY.register(
      "zombie_corpse_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.ZOMBIE_CORPSE, -16777216, -16777216, new Properties())
   );
   public static final DeferredHolder<Item, Item> ZOMBIE_WITHOUT_LEGS_AND_ARM_CORPSE_SPAWN_EGG = REGISTRY.register(
      "zombie_without_legs_and_arm_corpse_spawn_egg",
      () -> new DeferredSpawnEggItem(GoreEditionModEntities.ZOMBIE_WITHOUT_LEGS_AND_ARM_CORPSE, -16777216, -16777216, new Properties())
   );
   public static final DeferredHolder<Item, Item> SEVERED_LEGS_ZOMBIE_CORPSE_SPAWN_EGG = REGISTRY.register(
      "severed_legs_zombie_corpse_spawn_egg",
      () -> new DeferredSpawnEggItem(GoreEditionModEntities.SEVERED_LEGS_ZOMBIE_CORPSE, -16777216, -16777216, new Properties())
   );
   public static final DeferredHolder<Item, Item> CREEPER_CORPSE_SPAWN_EGG = REGISTRY.register(
      "creeper_corpse_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.CREEPER_CORPSE, -16777216, -16777216, new Properties())
   );
   public static final DeferredHolder<Item, Item> DISMEMBERED_SPIDER_II_SPAWN_EGG = REGISTRY.register(
      "dismembered_spider_ii_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.DISMEMBERED_SPIDER_II, -13356505, -5759987, new Properties())
   );
   public static final DeferredHolder<Item, Item> SPECTRAL_EXECUTIONERS_AXE = REGISTRY.register("spectral_executioners_axe", () -> new ExecutionersAxItem());
   public static final DeferredHolder<Item, Item> EXARRACK = block(GoreEditionModBlocks.EXARRACK);
   public static final DeferredHolder<Item, Item> DISARMED_ZOMBIE_CORPSE_SPAWN_EGG = REGISTRY.register(
      "disarmed_zombie_corpse_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.DISARMED_ZOMBIE_CORPSE, -16777216, -16777216, new Properties())
   );
   public static final DeferredHolder<Item, Item> SPECTRAL_GIFT = REGISTRY.register("spectral_gift", () -> new SpectralExecutionerAxeGiftItem());
   public static final DeferredHolder<Item, Item> GEART = REGISTRY.register("geart", () -> new GeartItem());
   public static final DeferredHolder<Item, Item> GRENADE_OF_ASHES_ACID = REGISTRY.register("grenade_of_ashes_acid", () -> new GrenadeOfAcidItem());
   public static final DeferredHolder<Item, Item> ASHES = REGISTRY.register("ashes", () -> new AshesOfTheHelLAshesItem());
   public static final DeferredHolder<Item, Item> ITEM_FOR_TEXTURE_OF_ACIDGRENADEEEE = REGISTRY.register(
      "item_for_texture_of_acidgrenadeeee", () -> new ItemForTextureOfACIDGRENADEEEEItem()
   );
   public static final DeferredHolder<Item, Item> ACID_BLOCK_TICK = block(GoreEditionModBlocks.ACID_BLOCK_TICK);
   public static final DeferredHolder<Item, Item> HEADLESS_ZOMBIE_CORPSE_SPAWN_EGG = REGISTRY.register(
      "headless_zombie_corpse_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.HEADLESS_ZOMBIE_CORPSE, -16777216, -16777216, new Properties())
   );
   public static final DeferredHolder<Item, Item> HUSK_CORPSE_SPAWN_EGG = REGISTRY.register(
      "husk_corpse_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.HUSK_CORPSE, -16777216, -16777216, new Properties())
   );
   public static final DeferredHolder<Item, Item> HUSK_WITHOUT_LEGS_AND_ARM_CORPSE_SPAWN_EGG = REGISTRY.register(
      "husk_without_legs_and_arm_corpse_spawn_egg",
      () -> new DeferredSpawnEggItem(GoreEditionModEntities.HUSK_WITHOUT_LEGS_AND_ARM_CORPSE, -16777216, -16777216, new Properties())
   );
   public static final DeferredHolder<Item, Item> SEVERED_LEGS_HUSK_CORPSE_SPAWN_EGG = REGISTRY.register(
      "severed_legs_husk_corpse_spawn_egg",
      () -> new DeferredSpawnEggItem(GoreEditionModEntities.SEVERED_LEGS_HUSK_CORPSE, -16777216, -16777216, new Properties())
   );
   public static final DeferredHolder<Item, Item> DISARMED_HUSK_CORPSE_SPAWN_EGG = REGISTRY.register(
      "disarmed_husk_corpse_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.DISARMED_HUSK_CORPSE, -16777216, -16777216, new Properties())
   );
   public static final DeferredHolder<Item, Item> HEADLESS_HUSK_CORPSE_SPAWN_EGG = REGISTRY.register(
      "headless_husk_corpse_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.HEADLESS_HUSK_CORPSE, -16777216, -16777216, new Properties())
   );
   public static final DeferredHolder<Item, Item> DISMEMBERED_SPIDER_III_SPAWN_EGG = REGISTRY.register(
      "dismembered_spider_iii_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.DISMEMBERED_SPIDER_III, -13356505, -5759987, new Properties())
   );
   public static final DeferredHolder<Item, Item> SKELETON_HEAD_SPAWN_EGG = REGISTRY.register(
      "skeleton_head_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.SKELETON_HEAD, -4079167, -11974327, new Properties())
   );
   public static final DeferredHolder<Item, Item> SKELETON_RIBS_SPAWN_EGG = REGISTRY.register(
      "skeleton_ribs_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.SKELETON_RIBS, -4079167, -11974327, new Properties())
   );
   public static final DeferredHolder<Item, Item> SKELETON_PELVIS_SPAWN_EGG = REGISTRY.register(
      "skeleton_pelvis_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.SKELETON_PELVIS, -4079167, -11974327, new Properties())
   );
   public static final DeferredHolder<Item, Item> SKELETON_SPINAL_COLUMN_SPAWN_EGG = REGISTRY.register(
      "skeleton_spinal_column_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.SKELETON_SPINAL_COLUMN, -4079167, -11974327, new Properties())
   );
   public static final DeferredHolder<Item, Item> SKELETON_RIGHT_ARM_SPAWN_EGG = REGISTRY.register(
      "skeleton_right_arm_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.SKELETON_RIGHT_ARM, -4079167, -11974327, new Properties())
   );
   public static final DeferredHolder<Item, Item> SKELETON_LEFT_ARM_SPAWN_EGG = REGISTRY.register(
      "skeleton_left_arm_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.SKELETON_LEFT_ARM, -4079167, -11974327, new Properties())
   );
   public static final DeferredHolder<Item, Item> SKELETON_LEFT_LEG_SPAWN_EGG = REGISTRY.register(
      "skeleton_left_leg_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.SKELETON_LEFT_LEG, -4079167, -11974327, new Properties())
   );
   public static final DeferredHolder<Item, Item> SKELETON_RIGHT_LEG_SPAWN_EGG = REGISTRY.register(
      "skeleton_right_leg_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.SKELETON_RIGHT_LEG, -4079167, -11974327, new Properties())
   );
   public static final DeferredHolder<Item, Item> CREEPER_GRASS = block(GoreEditionModBlocks.CREEPER_GRASS);
   public static final DeferredHolder<Item, Item> OBSIDIAN_KNIFE = REGISTRY.register("obsidian_knife", () -> new ObsidianKnifeItem());
   public static final DeferredHolder<Item, Item> DISARMED_SKELETON_RIGHT_ARM_SPAWN_EGG = REGISTRY.register(
      "disarmed_skeleton_right_arm_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.DISARMED_SKELETON_RIGHT_ARM, -1, -1, new Properties())
   );
   public static final DeferredHolder<Item, Item> DISARMED_SKELETON_LEFT_ARM_SPAWN_EGG = REGISTRY.register(
      "disarmed_skeleton_left_arm_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.DISARMED_SKELETON_LEFT_ARM, -1, -1, new Properties())
   );
   public static final DeferredHolder<Item, Item> SKELETON_CORPSE_SPAWN_EGG = REGISTRY.register(
      "skeleton_corpse_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.SKELETON_CORPSE, -16777216, -16777216, new Properties())
   );
   public static final DeferredHolder<Item, Item> SKELETON_CORPSE_III_SPAWN_EGG = REGISTRY.register(
      "skeleton_corpse_iii_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.SKELETON_CORPSE_III, -16777216, -16777216, new Properties())
   );
   public static final DeferredHolder<Item, Item> SKELETON_CORPSE_II_SPAWN_EGG = REGISTRY.register(
      "skeleton_corpse_ii_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.SKELETON_CORPSE_II, -16777216, -16777216, new Properties())
   );
   public static final DeferredHolder<Item, Item> GRENADE_OF_GREEK_FIRE = REGISTRY.register("grenade_of_greek_fire", () -> new GrenadeOfGreekFireItem());
   public static final DeferredHolder<Item, Item> DISMEMBERED_SPIDER_I_CORPSE_SPAWN_EGG = REGISTRY.register(
      "dismembered_spider_i_corpse_spawn_egg",
      () -> new DeferredSpawnEggItem(GoreEditionModEntities.DISMEMBERED_SPIDER_I_CORPSE, -16777216, -16777216, new Properties())
   );
   public static final DeferredHolder<Item, Item> DISMEMBERED_SPIDER_II_CORPSE_SPAWN_EGG = REGISTRY.register(
      "dismembered_spider_ii_corpse_spawn_egg",
      () -> new DeferredSpawnEggItem(GoreEditionModEntities.DISMEMBERED_SPIDER_II_CORPSE, -16777216, -16777216, new Properties())
   );
   public static final DeferredHolder<Item, Item> DISMEMBERED_SPIDER_III_CORPSE_SPAWN_EGG = REGISTRY.register(
      "dismembered_spider_iii_corpse_spawn_egg",
      () -> new DeferredSpawnEggItem(GoreEditionModEntities.DISMEMBERED_SPIDER_III_CORPSE, -16777216, -16777216, new Properties())
   );
   public static final DeferredHolder<Item, Item> COLLAPSING_SKELETON_SPAWN_EGG = REGISTRY.register(
      "collapsing_skeleton_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.COLLAPSING_SKELETON, -1, -1, new Properties())
   );
   public static final DeferredHolder<Item, Item> TEXTURE_OF_ACID_ASHES = REGISTRY.register("texture_of_acid_ashes", () -> new TpacItem());
   public static final DeferredHolder<Item, Item> LIGHTNING_ORB = REGISTRY.register("lightning_orb", () -> new LightingOrbItem());
   public static final DeferredHolder<Item, Item> NEEDLE_SCYTHE_CORDYCEPS = REGISTRY.register("needle_scythe_cordyceps", () -> new CordycepsScytheItem());
   public static final DeferredHolder<Item, Item> SPIRAL = REGISTRY.register("spiral", () -> new SpiralItem());
   public static final DeferredHolder<Item, Item> EXARRACK_ART_DOWN_LEFT = block(GoreEditionModBlocks.EXARRACK_ART_DOWN_LEFT);
   public static final DeferredHolder<Item, Item> EXARRACK_ART_DOWN_CENTER = block(GoreEditionModBlocks.EXARRACK_ART_DOWN_CENTER);
   public static final DeferredHolder<Item, Item> EXARRACK_ART_DOWN_RIGHT = block(GoreEditionModBlocks.EXARRACK_ART_DOWN_RIGHT);
   public static final DeferredHolder<Item, Item> EXARRACK_ART_TOP_LEFT = block(GoreEditionModBlocks.EXARRACK_ART_TOP_LEFT);
   public static final DeferredHolder<Item, Item> EXARRACK_ART_TOP_CENTER = block(GoreEditionModBlocks.EXARRACK_ART_TOP_CENTER);
   public static final DeferredHolder<Item, Item> EXARRACK_ART_TOP_RIGHT = block(GoreEditionModBlocks.EXARRACK_ART_TOP_RIGHT);
   public static final DeferredHolder<Item, Item> SPIDER_CORPSE_SPAWN_EGG = REGISTRY.register(
      "spider_corpse_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.SPIDER_CORPSE, -16777216, -16777216, new Properties())
   );
   public static final DeferredHolder<Item, Item> CORDYCEPS_FUNGUS = REGISTRY.register("cordyceps_fungus", () -> new CordycepsFungusItem());
   public static final DeferredHolder<Item, Item> EXARRACK_SWORD = REGISTRY.register("exarrack_sword", () -> new ExarrackSwordItem());
   public static final DeferredHolder<Item, Item> SWORD_OF_ASHES = REGISTRY.register("sword_of_ashes", () -> new SwordOfAshesItem());
   public static final DeferredHolder<Item, Item> MUSIC_DISC_LIMBO = REGISTRY.register("music_disc_limbo", () -> new MusicDiscShopItem());
   public static final DeferredHolder<Item, Item> THE_HELL_ZONE_THING_SPAWN_EGG = REGISTRY.register(
      "the_hell_zone_thing_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.THE_HELL_ZONE_THING, -1, -1, new Properties())
   );
   public static final DeferredHolder<Item, Item> VITALITY_STEALINGS_FANGS = REGISTRY.register("vitality_stealings_fangs", () -> new VitalitystealingsItem());
   public static final DeferredHolder<Item, Item> NETHER_GRENADE = REGISTRY.register("nether_grenade", () -> new MagmaGrenadeItem());
   public static final DeferredHolder<Item, Item> MUSIC_DISC_INTERNAL_INSANITY = REGISTRY.register(
      "music_disc_internal_insanity", () -> new MusicDiscInternalInsanityItem()
   );
   public static final DeferredHolder<Item, Item> THROWABLE_WITHER_SKULL = REGISTRY.register("throwable_wither_skull", () -> new ThrowableWitherSkullItem());
   public static final DeferredHolder<Item, Item> MUSIC_DISC_POISONED = REGISTRY.register("music_disc_poisoned", () -> new CollapseItem());
   public static final DeferredHolder<Item, Item> THIRD_HAND = REGISTRY.register("third_hand", () -> new ThirdHandItem());
   public static final DeferredHolder<Item, Item> ASHES_FLOWER = block(GoreEditionModBlocks.ASHES_FLOWER);
   public static final DeferredHolder<Item, Item> CRUSHING_SKELETON_SPAWN_EGG = REGISTRY.register(
      "crushing_skeleton_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.CRUSHING_SKELETON, -1, -1, new Properties())
   );
   public static final DeferredHolder<Item, Item> SEVERED_LEGS_SKELETON_SPAWN_EGG = REGISTRY.register(
      "severed_legs_skeleton_spawn_egg", () -> new DeferredSpawnEggItem(GoreEditionModEntities.SEVERED_LEGS_SKELETON, -1, -1, new Properties())
   );
   public static final DeferredHolder<Item, Item> EXARRACK_MONSTER_HEAD = block(GoreEditionModBlocks.EXARRACK_MONSTER_HEAD);
   public static final DeferredHolder<Item, Item> MUSIC_DISC_THE_XASH = REGISTRY.register("music_disc_the_xash", () -> new MusicDiscAshedItem());
   public static final DeferredHolder<Item, Item> ASHTRAY_CYCLE = block(GoreEditionModBlocks.ASHTRAY_CYCLE);
   public static final DeferredHolder<Item, Item> VERTICALLY_CUTTED_SKELETON_SPAWN_EGG = REGISTRY.register(
      "vertically_cutted_skeleton_spawn_egg",
      () -> new DeferredSpawnEggItem(GoreEditionModEntities.VERTICALLY_CUTTED_SKELETON, -15168415, -8364726, new Properties())
   );
   public static final DeferredHolder<Item, Item> ASH_SAND = block(GoreEditionModBlocks.ASH_SAND);
   public static final DeferredHolder<Item, Item> ASHED_UNKNOWN_SKULL = block(GoreEditionModBlocks.ASHED_UNKNOWN_SKULL);
   public static final DeferredHolder<Item, Item> SQUITCHGUN = REGISTRY.register("squitchgun", () -> new SquitchgunItem());
   public static final DeferredHolder<Item, Item> TEXTURE_OF_SQUITCHGUN_PROJECTILE = REGISTRY.register(
      "texture_of_squitchgun_projectile", () -> new TextureOfSquitchgunProjectileItem()
   );
   public static final DeferredHolder<Item, Item> TEXTURE_OF_ASHES_BLACK_HOLE = REGISTRY.register(
      "texture_of_ashes_black_hole", () -> new TextureOfAshesBlackHoleItem()
   );
   public static final DeferredHolder<Item, Item> AMETHYST_WAVE = REGISTRY.register("amethyst_wave", () -> new AmethystWaveItem());
   public static final DeferredHolder<Item, Item> SPINESAW = REGISTRY.register("spinesaw", () -> new SpinesawItem());

   private static DeferredHolder<Item, Item> block(DeferredHolder<Block, Block> block) {
      return REGISTRY.register(block.getId().getPath(), () -> new BlockItem((Block)block.get(), new Properties()));
   }

   @SubscribeEvent
   public static void clientLoad(FMLClientSetupEvent event) {
      event.enqueueWork(
         () -> {
            ItemProperties.register(
               (Item)VOODOO_RABBIT.get(),
               ResourceLocation.parse("gore_edition:voodoo_rabbit_blood"),
               (itemStackToRender, clientWorld, entity, itemEntityId) -> (float)VoodooRabbitPropertyValueProviderProcedure.execute(itemStackToRender)
            );
            ItemProperties.register(
               (Item)SPECTRAL_GIFT.get(),
               ResourceLocation.parse("gore_edition:spectral_gift_texture"),
               (itemStackToRender, clientWorld, entity, itemEntityId) -> (float)VoodooRabbitPropertyValueProviderProcedure.execute(itemStackToRender)
            );
            ItemProperties.register(
               (Item)GRENADE_OF_ASHES_ACID.get(),
               ResourceLocation.parse("gore_edition:grenade_of_ashes_acid_gh"),
               (itemStackToRender, clientWorld, entity, itemEntityId) -> (float)VoodooRabbitPropertyValueProviderProcedure.execute(itemStackToRender)
            );
         }
      );
   }
}
