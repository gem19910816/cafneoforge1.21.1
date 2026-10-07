package com.gem19910816.selfaid;

import com.gem19910816.selfaid.client.ClientBodyHealthStore;
import com.gem19910816.selfaid.net.BodySyncPayload;
import com.gem19910816.selfaid.registry.ModAttachments;
import com.gem19910816.selfaid.registry.SelfAidConfig;
import com.gem19910816.selfaid.registry.ModCreativeTabs;
import com.gem19910816.selfaid.registry.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.slf4j.Logger;

/**
 * SelfAid：自制“分部位生命值 + 急救物品”玩法模组。
 * 玩法概念参考同类模组（First Aid 等），代码与素材均为原创实现。
 */
@Mod(SelfAidMod.MODID)
public final class SelfAidMod {

    public static final String MODID = "selfaid";
    public static final Logger LOGGER = LogUtils.getLogger();

    public SelfAidMod(IEventBus modEventBus, ModContainer modContainer) {
        ModItems.ITEMS.register(modEventBus);
        ModCreativeTabs.TABS.register(modEventBus);
        ModAttachments.ATTACHMENTS.register(modEventBus);

        modContainer.registerConfig(ModConfig.Type.COMMON, SelfAidConfig.COMMON_SPEC, "selfaid-common.toml");
        modContainer.registerConfig(ModConfig.Type.CLIENT, SelfAidConfig.CLIENT_SPEC, "selfaid-client.toml");

        modEventBus.addListener(SelfAidMod::onRegisterPayloads);
    }

    private static void onRegisterPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");
        registrar.playToClient(BodySyncPayload.TYPE, BodySyncPayload.STREAM_CODEC, (payload, context) -> context
                .enqueueWork(() -> {
                    LocalPlayer player = Minecraft.getInstance().player;
                    if (player != null) {
                        ClientBodyHealthStore.accept(player.getUUID(), payload.parts());
                    }
                }));
    }
}
