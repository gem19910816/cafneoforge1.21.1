package net.gem19910816.dyairdrop.init;

import net.gem19910816.dyairdrop.DyairdropMod;
import net.gem19910816.dyairdrop.block.AirdroplargeBlock;
import net.gem19910816.dyairdrop.block.AirdropmedicalBlock;
import net.gem19910816.dyairdrop.block.AirdropsmallBlock;
import net.gem19910816.dyairdrop.block.AirdropweaponBlock;
import net.gem19910816.dyairdrop.block.LockedairdroplargeBlock;
import net.gem19910816.dyairdrop.block.LockedairdroplargeopenBlock;
import net.gem19910816.dyairdrop.block.LockedairdropmedicalBlock;
import net.gem19910816.dyairdrop.block.LockedairdropmedicalopenBlock;
import net.gem19910816.dyairdrop.block.LockedairdropsmallBlock;
import net.gem19910816.dyairdrop.block.LockedairdropsmallopenBlock;
import net.gem19910816.dyairdrop.block.LockedairdropweaponBlock;
import net.gem19910816.dyairdrop.block.LockedairdropweaponopenBlock;
import net.gem19910816.dyairdrop.block.Safe2Block;
import net.gem19910816.dyairdrop.block.SafeBlock;
import net.gem19910816.dyairdrop.block.SafeopenBlock;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DyairdropModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.createBlocks(DyairdropMod.MODID);
	public static final DeferredHolder<Block, Block> AIRDROPLARGE = REGISTRY.register("airdroplarge", () -> new AirdroplargeBlock());
	public static final DeferredHolder<Block, Block> AIRDROPMEDICAL = REGISTRY.register("airdropmedical", () -> new AirdropmedicalBlock());
	public static final DeferredHolder<Block, Block> AIRDROPWEAPON = REGISTRY.register("airdropweapon", () -> new AirdropweaponBlock());
	public static final DeferredHolder<Block, Block> AIRDROPSMALL = REGISTRY.register("airdropsmall", () -> new AirdropsmallBlock());
	public static final DeferredHolder<Block, Block> SAFE_2 = REGISTRY.register("safe_2", () -> new Safe2Block());
	public static final DeferredHolder<Block, Block> SAFE = REGISTRY.register("safe", () -> new SafeBlock());
	public static final DeferredHolder<Block, Block> LOCKEDAIRDROPLARGE = REGISTRY.register("lockedairdroplarge", () -> new LockedairdroplargeBlock());
	public static final DeferredHolder<Block, Block> LOCKEDAIRDROPLARGEOPEN = REGISTRY.register("lockedairdroplargeopen", () -> new LockedairdroplargeopenBlock());
	public static final DeferredHolder<Block, Block> SAFEOPEN = REGISTRY.register("safeopen", () -> new SafeopenBlock());
	public static final DeferredHolder<Block, Block> LOCKEDAIRDROPWEAPON = REGISTRY.register("lockedairdropweapon", () -> new LockedairdropweaponBlock());
	public static final DeferredHolder<Block, Block> LOCKEDAIRDROPMEDICAL = REGISTRY.register("lockedairdropmedical", () -> new LockedairdropmedicalBlock());
	public static final DeferredHolder<Block, Block> LOCKEDAIRDROPMEDICALOPEN = REGISTRY.register("lockedairdropmedicalopen", () -> new LockedairdropmedicalopenBlock());
	public static final DeferredHolder<Block, Block> LOCKEDAIRDROPWEAPONOPEN = REGISTRY.register("lockedairdropweaponopen", () -> new LockedairdropweaponopenBlock());
	public static final DeferredHolder<Block, Block> LOCKEDAIRDROPSMALL = REGISTRY.register("lockedairdropsmall", () -> new LockedairdropsmallBlock());
	public static final DeferredHolder<Block, Block> LOCKEDAIRDROPSMALLOPEN = REGISTRY.register("lockedairdropsmallopen", () -> new LockedairdropsmallopenBlock());
}
