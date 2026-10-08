package net.mcreator.dyairdrop.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class DyairdropModTabs {
   public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "dyairdrop");
   public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DYAIRDROP = REGISTRY.register(
      "dyairdrop",
      () -> CreativeModeTab.builder()
            .title(Component.translatable("item_group.dyairdrop.dyairdrop"))
            .icon(() -> new ItemStack((ItemLike)DyairdropModBlocks.AIRDROPLARGE.get()))
            .displayItems((parameters, tabData) -> {
               tabData.accept(((Block)DyairdropModBlocks.AIRDROPLARGE.get()).asItem());
               tabData.accept(((Block)DyairdropModBlocks.AIRDROPMEDICAL.get()).asItem());
               tabData.accept(((Block)DyairdropModBlocks.AIRDROPWEAPON.get()).asItem());
               tabData.accept(((Block)DyairdropModBlocks.AIRDROPSMALL.get()).asItem());
               tabData.accept(((Block)DyairdropModBlocks.SAFE_2.get()).asItem());
               tabData.accept(((Block)DyairdropModBlocks.SAFE.get()).asItem());
               tabData.accept((ItemLike)DyairdropModItems.FLAREGUN1.get());
               tabData.accept((ItemLike)DyairdropModItems.FLAREGUN2.get());
               tabData.accept((ItemLike)DyairdropModItems.FLAREGUN3.get());
               tabData.accept((ItemLike)DyairdropModItems.FLAREGUN4.get());
               tabData.accept((ItemLike)DyairdropModItems.FLAREGUN5.get());
               tabData.accept(((Block)DyairdropModBlocks.LOCKEDAIRDROPSMALLOPEN.get()).asItem());
            })
            .build()
   );

   public DyairdropModTabs() {
   }
}
