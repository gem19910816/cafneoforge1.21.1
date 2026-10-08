package net.mcreator.dyairdrop.init;

import net.mcreator.dyairdrop.block.AirdroplargeBlock;
import net.mcreator.dyairdrop.block.AirdropmedicalBlock;
import net.mcreator.dyairdrop.block.AirdropsmallBlock;
import net.mcreator.dyairdrop.block.AirdropweaponBlock;
import net.mcreator.dyairdrop.block.LockedairdroplargeBlock;
import net.mcreator.dyairdrop.block.LockedairdroplargeopenBlock;
import net.mcreator.dyairdrop.block.LockedairdropmedicalBlock;
import net.mcreator.dyairdrop.block.LockedairdropmedicalopenBlock;
import net.mcreator.dyairdrop.block.LockedairdropsmallBlock;
import net.mcreator.dyairdrop.block.LockedairdropsmallopenBlock;
import net.mcreator.dyairdrop.block.LockedairdropweaponBlock;
import net.mcreator.dyairdrop.block.LockedairdropweaponopenBlock;
import net.mcreator.dyairdrop.block.Safe2Block;
import net.mcreator.dyairdrop.block.SafeBlock;
import net.mcreator.dyairdrop.block.SafeopenBlock;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;

public class DyairdropModBlocks {
   public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(BuiltInRegistries.BLOCK, "dyairdrop");
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

   public DyairdropModBlocks() {
   }
}
