package net.mcreator.dyairdrop.init;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class DyairdropModCapabilities {
	@SubscribeEvent
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		registerChest(event, DyairdropModBlockEntities.AIRDROPLARGE.get());
		registerChest(event, DyairdropModBlockEntities.AIRDROPMEDICAL.get());
		registerChest(event, DyairdropModBlockEntities.AIRDROPWEAPON.get());
		registerChest(event, DyairdropModBlockEntities.AIRDROPSMALL.get());
		registerChest(event, DyairdropModBlockEntities.SAFE_2.get());
		registerChest(event, DyairdropModBlockEntities.SAFE.get());
		registerChest(event, DyairdropModBlockEntities.LOCKEDAIRDROPLARGE.get());
		registerChest(event, DyairdropModBlockEntities.LOCKEDAIRDROPLARGEOPEN.get());
		registerChest(event, DyairdropModBlockEntities.SAFEOPEN.get());
		registerChest(event, DyairdropModBlockEntities.LOCKEDAIRDROPWEAPON.get());
		registerChest(event, DyairdropModBlockEntities.LOCKEDAIRDROPMEDICAL.get());
		registerChest(event, DyairdropModBlockEntities.LOCKEDAIRDROPMEDICALOPEN.get());
		registerChest(event, DyairdropModBlockEntities.LOCKEDAIRDROPWEAPONOPEN.get());
		registerChest(event, DyairdropModBlockEntities.LOCKEDAIRDROPSMALL.get());
		registerChest(event, DyairdropModBlockEntities.LOCKEDAIRDROPSMALLOPEN.get());
	}

	private static <T extends net.minecraft.world.level.block.entity.BlockEntity & net.minecraft.world.WorldlyContainer> void registerChest(
			RegisterCapabilitiesEvent event, net.minecraft.world.level.block.entity.BlockEntityType<T> type) {
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, type, (be, side) -> side == null ? null : new SidedInvWrapper(be, side));
	}
}
