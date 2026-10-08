package net.mcreator.dyairdrop.init;

import net.mcreator.dyairdrop.block.display.AirdroplargeDisplayItem;
import net.mcreator.dyairdrop.block.display.AirdropmedicalDisplayItem;
import net.mcreator.dyairdrop.block.display.AirdropweaponDisplayItem;
import net.mcreator.dyairdrop.block.display.LockedairdroplargeDisplayItem;
import net.mcreator.dyairdrop.block.display.LockedairdroplargeopenDisplayItem;
import net.mcreator.dyairdrop.block.display.LockedairdropmedicalDisplayItem;
import net.mcreator.dyairdrop.block.display.LockedairdropmedicalopenDisplayItem;
import net.mcreator.dyairdrop.block.display.LockedairdropweaponDisplayItem;
import net.mcreator.dyairdrop.block.display.LockedairdropweaponopenDisplayItem;
import net.mcreator.dyairdrop.block.display.Safe2DisplayItem;
import net.mcreator.dyairdrop.block.display.SafeDisplayItem;
import net.mcreator.dyairdrop.block.display.SafeopenDisplayItem;
import net.mcreator.dyairdrop.item.Flaregun1Item;
import net.mcreator.dyairdrop.item.Flaregun2Item;
import net.mcreator.dyairdrop.item.Flaregun3Item;
import net.mcreator.dyairdrop.item.Flaregun4Item;
import net.mcreator.dyairdrop.item.Flaregun5Item;
import net.mcreator.dyairdrop.item.FlaregunItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;

public class DyairdropModItems {
   public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(BuiltInRegistries.ITEM, "dyairdrop");
   public static final DeferredHolder<Item, Item> AIRDROPLARGE = REGISTRY.register(
      DyairdropModBlocks.AIRDROPLARGE.getId().getPath(), () -> new AirdroplargeDisplayItem((Block)DyairdropModBlocks.AIRDROPLARGE.get(), new Properties())
   );
   public static final DeferredHolder<Item, Item> AIRDROPMEDICAL = REGISTRY.register(
      DyairdropModBlocks.AIRDROPMEDICAL.getId().getPath(),
      () -> new AirdropmedicalDisplayItem((Block)DyairdropModBlocks.AIRDROPMEDICAL.get(), new Properties())
   );
   public static final DeferredHolder<Item, Item> AIRDROPWEAPON = REGISTRY.register(
      DyairdropModBlocks.AIRDROPWEAPON.getId().getPath(), () -> new AirdropweaponDisplayItem((Block)DyairdropModBlocks.AIRDROPWEAPON.get(), new Properties())
   );
   public static final DeferredHolder<Item, Item> AIRDROPSMALL = block(DyairdropModBlocks.AIRDROPSMALL);
   public static final DeferredHolder<Item, Item> SAFE_2 = REGISTRY.register(
      DyairdropModBlocks.SAFE_2.getId().getPath(), () -> new Safe2DisplayItem((Block)DyairdropModBlocks.SAFE_2.get(), new Properties())
   );
   public static final DeferredHolder<Item, Item> SAFE = REGISTRY.register(
      DyairdropModBlocks.SAFE.getId().getPath(), () -> new SafeDisplayItem((Block)DyairdropModBlocks.SAFE.get(), new Properties())
   );
   public static final DeferredHolder<Item, Item> FLAREGUN1 = REGISTRY.register("flaregun1", () -> new Flaregun1Item());
   public static final DeferredHolder<Item, Item> FLAREGUN2 = REGISTRY.register("flaregun2", () -> new Flaregun2Item());
   public static final DeferredHolder<Item, Item> FLAREGUN3 = REGISTRY.register("flaregun3", () -> new Flaregun3Item());
   public static final DeferredHolder<Item, Item> FLAREGUN4 = REGISTRY.register("flaregun4", () -> new Flaregun4Item());
   public static final DeferredHolder<Item, Item> FLAREGUN5 = REGISTRY.register("flaregun5", () -> new Flaregun5Item());
   public static final DeferredHolder<Item, Item> FLAREGUN0 = REGISTRY.register("flaregun0", () -> new FlaregunItem());
   public static final DeferredHolder<Item, Item> LOCKEDAIRDROPLARGE = REGISTRY.register(
      DyairdropModBlocks.LOCKEDAIRDROPLARGE.getId().getPath(),
      () -> new LockedairdroplargeDisplayItem((Block)DyairdropModBlocks.LOCKEDAIRDROPLARGE.get(), new Properties())
   );
   public static final DeferredHolder<Item, Item> LOCKEDAIRDROPLARGEOPEN = REGISTRY.register(
      DyairdropModBlocks.LOCKEDAIRDROPLARGEOPEN.getId().getPath(),
      () -> new LockedairdroplargeopenDisplayItem((Block)DyairdropModBlocks.LOCKEDAIRDROPLARGEOPEN.get(), new Properties())
   );
   public static final DeferredHolder<Item, Item> SAFEOPEN = REGISTRY.register(
      DyairdropModBlocks.SAFEOPEN.getId().getPath(), () -> new SafeopenDisplayItem((Block)DyairdropModBlocks.SAFEOPEN.get(), new Properties())
   );
   public static final DeferredHolder<Item, Item> LOCKEDAIRDROPWEAPON = REGISTRY.register(
      DyairdropModBlocks.LOCKEDAIRDROPWEAPON.getId().getPath(),
      () -> new LockedairdropweaponDisplayItem((Block)DyairdropModBlocks.LOCKEDAIRDROPWEAPON.get(), new Properties())
   );
   public static final DeferredHolder<Item, Item> LOCKEDAIRDROPMEDICAL = REGISTRY.register(
      DyairdropModBlocks.LOCKEDAIRDROPMEDICAL.getId().getPath(),
      () -> new LockedairdropmedicalDisplayItem((Block)DyairdropModBlocks.LOCKEDAIRDROPMEDICAL.get(), new Properties())
   );
   public static final DeferredHolder<Item, Item> LOCKEDAIRDROPMEDICALOPEN = REGISTRY.register(
      DyairdropModBlocks.LOCKEDAIRDROPMEDICALOPEN.getId().getPath(),
      () -> new LockedairdropmedicalopenDisplayItem((Block)DyairdropModBlocks.LOCKEDAIRDROPMEDICALOPEN.get(), new Properties())
   );
   public static final DeferredHolder<Item, Item> LOCKEDAIRDROPWEAPONOPEN = REGISTRY.register(
      DyairdropModBlocks.LOCKEDAIRDROPWEAPONOPEN.getId().getPath(),
      () -> new LockedairdropweaponopenDisplayItem((Block)DyairdropModBlocks.LOCKEDAIRDROPWEAPONOPEN.get(), new Properties())
   );
   public static final DeferredHolder<Item, Item> LOCKEDAIRDROPSMALL = block(DyairdropModBlocks.LOCKEDAIRDROPSMALL);
   public static final DeferredHolder<Item, Item> LOCKEDAIRDROPSMALLOPEN = block(DyairdropModBlocks.LOCKEDAIRDROPSMALLOPEN);

   public DyairdropModItems() {
   }

   private static DeferredHolder<Item, Item> block(DeferredHolder<Block, Block> block) {
      return REGISTRY.register(block.getId().getPath(), () -> new BlockItem((Block)block.get(), new Properties()));
   }
}
