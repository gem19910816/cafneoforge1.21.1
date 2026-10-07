package net.gem19910816.dyairdrop.init;

import net.gem19910816.dyairdrop.DyairdropMod;
import net.gem19910816.dyairdrop.block.display.AirdroplargeDisplayItem;
import net.gem19910816.dyairdrop.block.display.AirdropmedicalDisplayItem;
import net.gem19910816.dyairdrop.block.display.AirdropweaponDisplayItem;
import net.gem19910816.dyairdrop.block.display.LockedairdroplargeDisplayItem;
import net.gem19910816.dyairdrop.block.display.LockedairdroplargeopenDisplayItem;
import net.gem19910816.dyairdrop.block.display.LockedairdropmedicalDisplayItem;
import net.gem19910816.dyairdrop.block.display.LockedairdropmedicalopenDisplayItem;
import net.gem19910816.dyairdrop.block.display.LockedairdropweaponDisplayItem;
import net.gem19910816.dyairdrop.block.display.LockedairdropweaponopenDisplayItem;
import net.gem19910816.dyairdrop.block.display.Safe2DisplayItem;
import net.gem19910816.dyairdrop.block.display.SafeDisplayItem;
import net.gem19910816.dyairdrop.block.display.SafeopenDisplayItem;
import net.gem19910816.dyairdrop.item.Flaregun1Item;
import net.gem19910816.dyairdrop.item.Flaregun2Item;
import net.gem19910816.dyairdrop.item.Flaregun3Item;
import net.gem19910816.dyairdrop.item.Flaregun4Item;
import net.gem19910816.dyairdrop.item.Flaregun5Item;
import net.gem19910816.dyairdrop.item.FlaregunItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DyairdropModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.createItems(DyairdropMod.MODID);
	public static final DeferredHolder<Item, Item> AIRDROPLARGE = REGISTRY.register(DyairdropModBlocks.AIRDROPLARGE.getId().getPath(),
			() -> new AirdroplargeDisplayItem(DyairdropModBlocks.AIRDROPLARGE.get(), new Item.Properties()));
	public static final DeferredHolder<Item, Item> AIRDROPMEDICAL = REGISTRY.register(DyairdropModBlocks.AIRDROPMEDICAL.getId().getPath(),
			() -> new AirdropmedicalDisplayItem(DyairdropModBlocks.AIRDROPMEDICAL.get(), new Item.Properties()));
	public static final DeferredHolder<Item, Item> AIRDROPWEAPON = REGISTRY.register(DyairdropModBlocks.AIRDROPWEAPON.getId().getPath(),
			() -> new AirdropweaponDisplayItem(DyairdropModBlocks.AIRDROPWEAPON.get(), new Item.Properties()));
	public static final DeferredHolder<Item, Item> AIRDROPSMALL = block(DyairdropModBlocks.AIRDROPSMALL);
	public static final DeferredHolder<Item, Item> SAFE_2 = REGISTRY.register(DyairdropModBlocks.SAFE_2.getId().getPath(),
			() -> new Safe2DisplayItem(DyairdropModBlocks.SAFE_2.get(), new Item.Properties()));
	public static final DeferredHolder<Item, Item> SAFE = REGISTRY.register(DyairdropModBlocks.SAFE.getId().getPath(),
			() -> new SafeDisplayItem(DyairdropModBlocks.SAFE.get(), new Item.Properties()));
	public static final DeferredHolder<Item, Item> FLAREGUN1 = REGISTRY.register("flaregun1", () -> new Flaregun1Item());
	public static final DeferredHolder<Item, Item> FLAREGUN2 = REGISTRY.register("flaregun2", () -> new Flaregun2Item());
	public static final DeferredHolder<Item, Item> FLAREGUN3 = REGISTRY.register("flaregun3", () -> new Flaregun3Item());
	public static final DeferredHolder<Item, Item> FLAREGUN4 = REGISTRY.register("flaregun4", () -> new Flaregun4Item());
	public static final DeferredHolder<Item, Item> FLAREGUN5 = REGISTRY.register("flaregun5", () -> new Flaregun5Item());
	public static final DeferredHolder<Item, Item> FLAREGUN0 = REGISTRY.register("flaregun0", () -> new FlaregunItem());
	public static final DeferredHolder<Item, Item> LOCKEDAIRDROPLARGE = REGISTRY.register(DyairdropModBlocks.LOCKEDAIRDROPLARGE.getId().getPath(),
			() -> new LockedairdroplargeDisplayItem(DyairdropModBlocks.LOCKEDAIRDROPLARGE.get(), new Item.Properties()));
	public static final DeferredHolder<Item, Item> LOCKEDAIRDROPLARGEOPEN = REGISTRY.register(DyairdropModBlocks.LOCKEDAIRDROPLARGEOPEN.getId().getPath(),
			() -> new LockedairdroplargeopenDisplayItem(DyairdropModBlocks.LOCKEDAIRDROPLARGEOPEN.get(), new Item.Properties()));
	public static final DeferredHolder<Item, Item> SAFEOPEN = REGISTRY.register(DyairdropModBlocks.SAFEOPEN.getId().getPath(),
			() -> new SafeopenDisplayItem(DyairdropModBlocks.SAFEOPEN.get(), new Item.Properties()));
	public static final DeferredHolder<Item, Item> LOCKEDAIRDROPWEAPON = REGISTRY.register(DyairdropModBlocks.LOCKEDAIRDROPWEAPON.getId().getPath(),
			() -> new LockedairdropweaponDisplayItem(DyairdropModBlocks.LOCKEDAIRDROPWEAPON.get(), new Item.Properties()));
	public static final DeferredHolder<Item, Item> LOCKEDAIRDROPMEDICAL = REGISTRY.register(DyairdropModBlocks.LOCKEDAIRDROPMEDICAL.getId().getPath(),
			() -> new LockedairdropmedicalDisplayItem(DyairdropModBlocks.LOCKEDAIRDROPMEDICAL.get(), new Item.Properties()));
	public static final DeferredHolder<Item, Item> LOCKEDAIRDROPMEDICALOPEN = REGISTRY.register(DyairdropModBlocks.LOCKEDAIRDROPMEDICALOPEN.getId().getPath(),
			() -> new LockedairdropmedicalopenDisplayItem(DyairdropModBlocks.LOCKEDAIRDROPMEDICALOPEN.get(), new Item.Properties()));
	public static final DeferredHolder<Item, Item> LOCKEDAIRDROPWEAPONOPEN = REGISTRY.register(DyairdropModBlocks.LOCKEDAIRDROPWEAPONOPEN.getId().getPath(),
			() -> new LockedairdropweaponopenDisplayItem(DyairdropModBlocks.LOCKEDAIRDROPWEAPONOPEN.get(), new Item.Properties()));
	public static final DeferredHolder<Item, Item> LOCKEDAIRDROPSMALL = block(DyairdropModBlocks.LOCKEDAIRDROPSMALL);
	public static final DeferredHolder<Item, Item> LOCKEDAIRDROPSMALLOPEN = block(DyairdropModBlocks.LOCKEDAIRDROPSMALLOPEN);

	private static DeferredHolder<Item, Item> block(DeferredHolder<Block, ? extends Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}
}
