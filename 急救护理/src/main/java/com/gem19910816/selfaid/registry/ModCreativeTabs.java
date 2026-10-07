package com.gem19910816.selfaid.registry;

import com.gem19910816.selfaid.SelfAidMod;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, SelfAidMod.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SELF_AID_TAB = TABS.register("selfaid",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.selfaid"))
                    .icon(() -> new ItemStack(ModItems.FIRST_AID_KIT.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.BANDAGE.get());
                        output.accept(ModItems.PLASTER.get());
                        output.accept(ModItems.MORPHINE.get());
                        output.accept(ModItems.FIRST_AID_KIT.get());
                    })
                    .build());

    private ModCreativeTabs() {
    }
}
