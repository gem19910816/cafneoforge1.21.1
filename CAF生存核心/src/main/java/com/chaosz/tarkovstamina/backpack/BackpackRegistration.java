package com.chaosz.tarkovstamina.backpack;

import com.chaosz.tarkovstamina.backpack.item.MilitaryBackpackItem;
import com.chaosz.tarkovstamina.backpack.menu.BackpackMenu;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * CAF 军用背包注册（namespace: caf）
 *
 * <p>1.21.1：{@code ForgeRegistries} → {@code BuiltInRegistries}，
 * {@code IForgeMenuType} → {@link IMenuTypeExtension}，
 * {@code RegistryObject} → {@link DeferredHolder}。</p>
 *
 * <p>客户端的 Screen / 按键 / 渲染器注册从这儿挪走了：原来靠
 * {@code DistExecutor.unsafeRunWhenOn} 手动分流，现在
 * {@code BackpackClientRegistration} 自己带 {@code @EventBusSubscriber(Dist.CLIENT)}，
 * 专用服务器根本不会加载那个类。</p>
 */
public final class BackpackRegistration {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(BuiltInRegistries.ITEM, CafBackpack.NAMESPACE);
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(BuiltInRegistries.MENU, CafBackpack.NAMESPACE);

    public static final DeferredHolder<Item, Item> MILITARY_BACKPACK =
            ITEMS.register(CafBackpack.ITEM_ID,
                    () -> new MilitaryBackpackItem(new Item.Properties().stacksTo(1), 12, 9));

    public static final DeferredHolder<Item, Item> SATCHEL =
            ITEMS.register("satchel",
                    () -> new MilitaryBackpackItem(new Item.Properties().stacksTo(1), 9, 3));

    public static final DeferredHolder<Item, Item> SCHOOL_BAG =
            ITEMS.register("school_bag",
                    () -> new MilitaryBackpackItem(new Item.Properties().stacksTo(1), 9, 4));

    public static final DeferredHolder<Item, Item> HIKING_BACKPACK =
            ITEMS.register("hiking_backpack",
                    () -> new MilitaryBackpackItem(new Item.Properties().stacksTo(1), 9, 5));

    public static final DeferredHolder<MenuType<?>, MenuType<BackpackMenu>> BACKPACK_MENU =
            MENUS.register(CafBackpack.MENU_ID,
                    () -> IMenuTypeExtension.create(BackpackMenu::new));

    private BackpackRegistration() {
    }

    /** 在 TarkovStamina 构造中调用 */
    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
        MENUS.register(modEventBus);
        // 背包的两个数据包在 network.StaminaNetwork#register 里统一登记，
        // 这里不再单独注册一遍通道。
    }
}
