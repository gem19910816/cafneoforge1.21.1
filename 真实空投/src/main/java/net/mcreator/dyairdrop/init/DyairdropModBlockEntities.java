package net.mcreator.dyairdrop.init;

import net.mcreator.dyairdrop.DyairdropMod;
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
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DyairdropModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(net.minecraft.core.registries.Registries.BLOCK_ENTITY_TYPE, DyairdropMod.MODID);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AirdroplargeTileEntity>> AIRDROPLARGE = REGISTRY.register("airdroplarge",
			() -> BlockEntityType.Builder.of(AirdroplargeTileEntity::new, DyairdropModBlocks.AIRDROPLARGE.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AirdropmedicalTileEntity>> AIRDROPMEDICAL = REGISTRY.register("airdropmedical",
			() -> BlockEntityType.Builder.of(AirdropmedicalTileEntity::new, DyairdropModBlocks.AIRDROPMEDICAL.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AirdropweaponTileEntity>> AIRDROPWEAPON = REGISTRY.register("airdropweapon",
			() -> BlockEntityType.Builder.of(AirdropweaponTileEntity::new, DyairdropModBlocks.AIRDROPWEAPON.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AirdropsmallBlockEntity>> AIRDROPSMALL = register("airdropsmall", DyairdropModBlocks.AIRDROPSMALL, AirdropsmallBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<Safe2TileEntity>> SAFE_2 = REGISTRY.register("safe_2",
			() -> BlockEntityType.Builder.of(Safe2TileEntity::new, DyairdropModBlocks.SAFE_2.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SafeTileEntity>> SAFE = REGISTRY.register("safe",
			() -> BlockEntityType.Builder.of(SafeTileEntity::new, DyairdropModBlocks.SAFE.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LockedairdroplargeTileEntity>> LOCKEDAIRDROPLARGE = REGISTRY.register("lockedairdroplarge",
			() -> BlockEntityType.Builder.of(LockedairdroplargeTileEntity::new, DyairdropModBlocks.LOCKEDAIRDROPLARGE.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LockedairdroplargeopenTileEntity>> LOCKEDAIRDROPLARGEOPEN = REGISTRY.register("lockedairdroplargeopen",
			() -> BlockEntityType.Builder.of(LockedairdroplargeopenTileEntity::new, DyairdropModBlocks.LOCKEDAIRDROPLARGEOPEN.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SafeopenTileEntity>> SAFEOPEN = REGISTRY.register("safeopen",
			() -> BlockEntityType.Builder.of(SafeopenTileEntity::new, DyairdropModBlocks.SAFEOPEN.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LockedairdropweaponTileEntity>> LOCKEDAIRDROPWEAPON = REGISTRY.register("lockedairdropweapon",
			() -> BlockEntityType.Builder.of(LockedairdropweaponTileEntity::new, DyairdropModBlocks.LOCKEDAIRDROPWEAPON.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LockedairdropmedicalTileEntity>> LOCKEDAIRDROPMEDICAL = REGISTRY.register("lockedairdropmedical",
			() -> BlockEntityType.Builder.of(LockedairdropmedicalTileEntity::new, DyairdropModBlocks.LOCKEDAIRDROPMEDICAL.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LockedairdropmedicalopenTileEntity>> LOCKEDAIRDROPMEDICALOPEN = REGISTRY.register("lockedairdropmedicalopen",
			() -> BlockEntityType.Builder.of(LockedairdropmedicalopenTileEntity::new, DyairdropModBlocks.LOCKEDAIRDROPMEDICALOPEN.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LockedairdropweaponopenTileEntity>> LOCKEDAIRDROPWEAPONOPEN = REGISTRY.register("lockedairdropweaponopen",
			() -> BlockEntityType.Builder.of(LockedairdropweaponopenTileEntity::new, DyairdropModBlocks.LOCKEDAIRDROPWEAPONOPEN.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LockedairdropsmallBlockEntity>> LOCKEDAIRDROPSMALL = register(
			"lockedairdropsmall", DyairdropModBlocks.LOCKEDAIRDROPSMALL, LockedairdropsmallBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LockedairdropsmallopenBlockEntity>> LOCKEDAIRDROPSMALLOPEN = register(
			"lockedairdropsmallopen", DyairdropModBlocks.LOCKEDAIRDROPSMALLOPEN, LockedairdropsmallopenBlockEntity::new);

	private static <T extends net.minecraft.world.level.block.entity.BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>> register(
			String registryname, DeferredHolder<net.minecraft.world.level.block.Block, ? extends net.minecraft.world.level.block.Block> block, net.minecraft.world.level.block.entity.BlockEntityType.BlockEntitySupplier<? extends T> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of((net.minecraft.world.level.block.entity.BlockEntityType.BlockEntitySupplier<T>) supplier, block.get()).build(null));
	}
}
