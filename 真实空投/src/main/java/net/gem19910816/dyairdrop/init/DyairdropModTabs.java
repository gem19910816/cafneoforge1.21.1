package net.gem19910816.dyairdrop.init;

import net.gem19910816.dyairdrop.DyairdropMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DyairdropModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DyairdropMod.MODID);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DYAIRDROP = REGISTRY.register("dyairdrop",
			() -> CreativeModeTab.builder()
					.title(Component.translatable("item_group.dyairdrop.dyairdrop"))
					.icon(() -> new ItemStack(DyairdropModBlocks.AIRDROPLARGE.get()))
					.displayItems((parameters, tabData) -> {
						tabData.accept(DyairdropModBlocks.AIRDROPLARGE.get());
						tabData.accept(DyairdropModBlocks.AIRDROPMEDICAL.get());
						tabData.accept(DyairdropModBlocks.AIRDROPWEAPON.get());
						tabData.accept(DyairdropModBlocks.AIRDROPSMALL.get());
						tabData.accept(DyairdropModBlocks.SAFE_2.get());
						tabData.accept(DyairdropModBlocks.SAFE.get());
						tabData.accept(DyairdropModItems.FLAREGUN1.get());
						tabData.accept(DyairdropModItems.FLAREGUN2.get());
						tabData.accept(DyairdropModItems.FLAREGUN3.get());
						tabData.accept(DyairdropModItems.FLAREGUN4.get());
						tabData.accept(DyairdropModItems.FLAREGUN5.get());
						tabData.accept(DyairdropModBlocks.LOCKEDAIRDROPSMALLOPEN.get());
					})
					.build());
}
