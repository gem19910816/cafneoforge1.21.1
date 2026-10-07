package com.chaosz.tarkovstamina.backpack.client;

import com.chaosz.tarkovstamina.TarkovStamina;
import com.chaosz.tarkovstamina.backpack.BackpackNetwork;
import com.chaosz.tarkovstamina.backpack.BackpackRegistration;
import com.chaosz.tarkovstamina.backpack.item.MilitaryBackpackItem;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.lwjgl.glfw.GLFW;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

/**
 * 背包的客户端注册。
 *
 * <p>1.20.1 版靠 {@code DistExecutor.unsafeRunWhenOn(Dist.CLIENT, ...)} 从服务端安全地
 * 分流到这儿。1.21.1 不再需要：本类自己带
 * {@code @EventBusSubscriber(value = Dist.CLIENT)}，专用服务器根本不会加载它
 * —— 那些 {@code net.minecraft.client} 的引用因此一个字节都不会被解析。</p>
 */
@EventBusSubscriber(modid = TarkovStamina.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class BackpackClientRegistration {
    private static KeyMapping openBackpackKey;

    private BackpackClientRegistration() {}

    public static boolean isBackpackKey(int keyCode, int scanCode) {
        return openBackpackKey != null && openBackpackKey.matches(keyCode, scanCode);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        // 客户端 tick 挂在游戏总线上。这里挂而不是再开一个带
        // @EventBusSubscriber 的类，是因为本方法只在客户端跑，天然是客户端专属。
        NeoForge.EVENT_BUS.addListener(BackpackClientRegistration::onClientTick);

        event.enqueueWork(() -> {
            // 菜单界面不在这儿注册：1.21.1 的 MenuScreens.register 已经不可从外部调用，
            // 改走 NeoForge 的 RegisterMenuScreensEvent，见下面那个方法。
            // 只注册自己的渲染器，绝不调用 CuriosRendererRegistry.load()！
            // load() 会实例化所有注册的 renderer（包括 superbwarfare 的 parachute），
            // 缺失模型会直接导致客户端崩溃。
            CuriosRendererRegistry.register(
                    BackpackRegistration.MILITARY_BACKPACK.get(),
                    () -> new BackpackCurioRenderer());
            CuriosRendererRegistry.register(
                    BackpackRegistration.SATCHEL.get(),
                    () -> new SatchelCurioRenderer());
            CuriosRendererRegistry.register(
                    BackpackRegistration.SCHOOL_BAG.get(),
                    () -> new BackpackCurioRenderer());
            CuriosRendererRegistry.register(
                    BackpackRegistration.HIKING_BACKPACK.get(),
                    () -> new BackpackCurioRenderer());
        });
    }

    /** 1.21.1：菜单 → 界面 的绑定改由这个事件承担（MenuScreens.register 已不可外部调用）。 */
    @SubscribeEvent
    public static void onRegisterMenuScreens(RegisterMenuScreensEvent event) {
        event.register(BackpackRegistration.BACKPACK_MENU.get(), BackpackScreen::new);
    }

    @SubscribeEvent
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        openBackpackKey = new KeyMapping(
                "key.caf.backpack.open",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_B,
                "key.category.caf");
        event.register(openBackpackKey);
    }

    private static void onClientTick(ClientTickEvent.Post event) {
        if (openBackpackKey != null && openBackpackKey.consumeClick()) {
            Minecraft mc = Minecraft.getInstance();
            if (mc.screen instanceof BackpackScreen) {
                mc.setScreen(null);
            } else if (mc.screen == null && mc.player != null) {
                if (hasBackpack(mc)) {
                    BackpackNetwork.sendOpenBackpackPacket();
                }
            }
        }
    }

    private static boolean hasBackpack(Minecraft mc) {
        if (mc.player.getMainHandItem().getItem() instanceof MilitaryBackpackItem
                || mc.player.getOffhandItem().getItem() instanceof MilitaryBackpackItem) {
            return true;
        }
        return CuriosApi.getCuriosInventory(mc.player)
                .flatMap(inv -> inv.findFirstCurio(stack -> stack.getItem() instanceof MilitaryBackpackItem))
                .isPresent();
    }
}
