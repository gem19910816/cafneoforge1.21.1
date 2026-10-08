package net.mcreator.dyairdrop.init;

import net.mcreator.dyairdrop.block.entity.AirdroplargeTileEntity;
import net.mcreator.dyairdrop.block.entity.AirdropmedicalTileEntity;
import net.mcreator.dyairdrop.block.entity.AirdropsmallBlockEntity;
import net.mcreator.dyairdrop.block.entity.AirdropweaponTileEntity;
import net.mcreator.dyairdrop.block.entity.LockedairdroplargeTileEntity;
import net.mcreator.dyairdrop.block.entity.LockedairdroplargeopenTileEntity;
import net.mcreator.dyairdrop.block.entity.LockedairdropmedicalTileEntity;
import net.mcreator.dyairdrop.block.entity.LockedairdropmedicalopenTileEntity;
import net.mcreator.dyairdrop.block.entity.LockedairdropsmallBlockEntity;
import net.mcreator.dyairdrop.block.entity.LockedairdropsmallopenBlockEntity;
import net.mcreator.dyairdrop.block.entity.LockedairdropweaponTileEntity;
import net.mcreator.dyairdrop.block.entity.LockedairdropweaponopenTileEntity;
import net.mcreator.dyairdrop.block.entity.Safe2TileEntity;
import net.mcreator.dyairdrop.block.entity.SafeTileEntity;
import net.mcreator.dyairdrop.block.entity.SafeopenTileEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityType.BlockEntitySupplier;
import net.minecraft.world.level.block.entity.BlockEntityType.Builder;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DyairdropModBlockEntities {
   public static final DeferredRegister<BlockEntityType<?>> REGISTRY =
      DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, "dyairdrop");
   public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AirdroplargeTileEntity>> AIRDROPLARGE = REGISTRY.register(
      "airdroplarge",
      () -> Builder.of(AirdroplargeTileEntity::new, new Block[]{(Block)DyairdropModBlocks.AIRDROPLARGE.get()}).build(null));
   public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AirdropmedicalTileEntity>> AIRDROPMEDICAL = REGISTRY.register(
      "airdropmedical",
      () -> Builder.of(AirdropmedicalTileEntity::new, new Block[]{(Block)DyairdropModBlocks.AIRDROPMEDICAL.get()}).build(null));
   public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AirdropweaponTileEntity>> AIRDROPWEAPON = REGISTRY.register(
      "airdropweapon",
      () -> Builder.of(AirdropweaponTileEntity::new, new Block[]{(Block)DyairdropModBlocks.AIRDROPWEAPON.get()}).build(null));
   public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AirdropsmallBlockEntity>> AIRDROPSMALL = register(
      "airdropsmall", DyairdropModBlocks.AIRDROPSMALL, AirdropsmallBlockEntity::new);
   public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<Safe2TileEntity>> SAFE_2 = REGISTRY.register(
      "safe_2", () -> Builder.of(Safe2TileEntity::new, new Block[]{(Block)DyairdropModBlocks.SAFE_2.get()}).build(null));
   public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SafeTileEntity>> SAFE = REGISTRY.register(
      "safe", () -> Builder.of(SafeTileEntity::new, new Block[]{(Block)DyairdropModBlocks.SAFE.get()}).build(null));
   public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LockedairdroplargeTileEntity>> LOCKEDAIRDROPLARGE = REGISTRY.register(
      "lockedairdroplarge",
      () -> Builder.of(LockedairdroplargeTileEntity::new, new Block[]{(Block)DyairdropModBlocks.LOCKEDAIRDROPLARGE.get()}).build(null));
   public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LockedairdroplargeopenTileEntity>> LOCKEDAIRDROPLARGEOPEN = REGISTRY.register(
      "lockedairdroplargeopen",
      () -> Builder.of(LockedairdroplargeopenTileEntity::new, new Block[]{(Block)DyairdropModBlocks.LOCKEDAIRDROPLARGEOPEN.get()}).build(null));
   public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SafeopenTileEntity>> SAFEOPEN = REGISTRY.register(
      "safeopen", () -> Builder.of(SafeopenTileEntity::new, new Block[]{(Block)DyairdropModBlocks.SAFEOPEN.get()}).build(null));
   public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LockedairdropweaponTileEntity>> LOCKEDAIRDROPWEAPON = REGISTRY.register(
      "lockedairdropweapon",
      () -> Builder.of(LockedairdropweaponTileEntity::new, new Block[]{(Block)DyairdropModBlocks.LOCKEDAIRDROPWEAPON.get()}).build(null));
   public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LockedairdropmedicalTileEntity>> LOCKEDAIRDROPMEDICAL = REGISTRY.register(
      "lockedairdropmedical",
      () -> Builder.of(LockedairdropmedicalTileEntity::new, new Block[]{(Block)DyairdropModBlocks.LOCKEDAIRDROPMEDICAL.get()}).build(null));
   public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LockedairdropmedicalopenTileEntity>> LOCKEDAIRDROPMEDICALOPEN = REGISTRY.register(
      "lockedairdropmedicalopen",
      () -> Builder.of(LockedairdropmedicalopenTileEntity::new, new Block[]{(Block)DyairdropModBlocks.LOCKEDAIRDROPMEDICALOPEN.get()}).build(null));
   public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LockedairdropweaponopenTileEntity>> LOCKEDAIRDROPWEAPONOPEN = REGISTRY.register(
      "lockedairdropweaponopen",
      () -> Builder.of(LockedairdropweaponopenTileEntity::new, new Block[]{(Block)DyairdropModBlocks.LOCKEDAIRDROPWEAPONOPEN.get()}).build(null));
   public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LockedairdropsmallBlockEntity>> LOCKEDAIRDROPSMALL = register(
      "lockedairdropsmall", DyairdropModBlocks.LOCKEDAIRDROPSMALL, LockedairdropsmallBlockEntity::new);
   public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LockedairdropsmallopenBlockEntity>> LOCKEDAIRDROPSMALLOPEN = register(
      "lockedairdropsmallopen", DyairdropModBlocks.LOCKEDAIRDROPSMALLOPEN, LockedairdropsmallopenBlockEntity::new);

   public DyairdropModBlockEntities() {
   }

   private static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>> register(
      String registryname, DeferredHolder<Block, Block> block, BlockEntitySupplier<T> supplier) {
      return REGISTRY.register(registryname, () -> Builder.of(supplier, new Block[]{(Block)block.get()}).build(null));
   }
}
