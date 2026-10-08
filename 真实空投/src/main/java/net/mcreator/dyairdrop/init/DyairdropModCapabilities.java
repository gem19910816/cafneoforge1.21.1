package net.mcreator.dyairdrop.init;

import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;

/**
 * Forge exposed the airdrop containers through LazyOptional item handlers attached to each
 * block entity.  NeoForge 1.21.1 has no LazyOptional, so the same per-side
 * {@link SidedInvWrapper} behaviour is registered here instead.
 */
@EventBusSubscriber(
   modid = "dyairdrop",
   bus = Bus.MOD
)
public class DyairdropModCapabilities {
   public DyairdropModCapabilities() {
   }

   @SubscribeEvent
   public static void registerCapabilities(RegisterCapabilitiesEvent event) {
      event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DyairdropModBlockEntities.AIRDROPLARGE.get(),
         (blockEntity, side) -> side == null ? null : new SidedInvWrapper(blockEntity, side));
      event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DyairdropModBlockEntities.AIRDROPMEDICAL.get(),
         (blockEntity, side) -> side == null ? null : new SidedInvWrapper(blockEntity, side));
      event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DyairdropModBlockEntities.AIRDROPWEAPON.get(),
         (blockEntity, side) -> side == null ? null : new SidedInvWrapper(blockEntity, side));
      event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DyairdropModBlockEntities.AIRDROPSMALL.get(),
         (blockEntity, side) -> side == null ? null : new SidedInvWrapper(blockEntity, side));
      event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DyairdropModBlockEntities.SAFE_2.get(),
         (blockEntity, side) -> side == null ? null : new SidedInvWrapper(blockEntity, side));
      event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DyairdropModBlockEntities.SAFE.get(),
         (blockEntity, side) -> side == null ? null : new SidedInvWrapper(blockEntity, side));
      event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DyairdropModBlockEntities.LOCKEDAIRDROPLARGE.get(),
         (blockEntity, side) -> side == null ? null : new SidedInvWrapper(blockEntity, side));
      event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DyairdropModBlockEntities.LOCKEDAIRDROPLARGEOPEN.get(),
         (blockEntity, side) -> side == null ? null : new SidedInvWrapper(blockEntity, side));
      event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DyairdropModBlockEntities.SAFEOPEN.get(),
         (blockEntity, side) -> side == null ? null : new SidedInvWrapper(blockEntity, side));
      event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DyairdropModBlockEntities.LOCKEDAIRDROPWEAPON.get(),
         (blockEntity, side) -> side == null ? null : new SidedInvWrapper(blockEntity, side));
      event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DyairdropModBlockEntities.LOCKEDAIRDROPWEAPONOPEN.get(),
         (blockEntity, side) -> side == null ? null : new SidedInvWrapper(blockEntity, side));
      event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DyairdropModBlockEntities.LOCKEDAIRDROPMEDICAL.get(),
         (blockEntity, side) -> side == null ? null : new SidedInvWrapper(blockEntity, side));
      event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DyairdropModBlockEntities.LOCKEDAIRDROPMEDICALOPEN.get(),
         (blockEntity, side) -> side == null ? null : new SidedInvWrapper(blockEntity, side));
      event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DyairdropModBlockEntities.LOCKEDAIRDROPSMALL.get(),
         (blockEntity, side) -> side == null ? null : new SidedInvWrapper(blockEntity, side));
      event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DyairdropModBlockEntities.LOCKEDAIRDROPSMALLOPEN.get(),
         (blockEntity, side) -> side == null ? null : new SidedInvWrapper(blockEntity, side));
   }
}
