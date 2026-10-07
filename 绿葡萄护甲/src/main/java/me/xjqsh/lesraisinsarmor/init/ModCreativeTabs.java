package me.xjqsh.lesraisinsarmor.init;

import me.xjqsh.lesraisinsarmor.LesRaisinsArmor;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@SuppressWarnings("all")
public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LesRaisinsArmor.MOD_ID);
    public static final ResourceLocation icon = ResourceLocation.fromNamespaceAndPath(LesRaisinsArmor.MOD_ID, "chemical_protective_chestplate");
    public static DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN = TABS.register("other", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.tab.lrarmor"))
            .icon(() -> BuiltInRegistries.ITEM.get(icon).getDefaultInstance())
            .displayItems((parameters, output) -> {
                for (DeferredHolder<Item, ? extends Item> item : ModItems.REGISTER.getEntries()) {
                    output.accept(item.get().getDefaultInstance());
                }
            }).build());
}
