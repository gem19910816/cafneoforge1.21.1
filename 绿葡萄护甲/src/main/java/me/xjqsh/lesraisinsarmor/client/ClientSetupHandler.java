package me.xjqsh.lesraisinsarmor.client;

import me.xjqsh.lesraisinsarmor.LesRaisinsArmor;
import me.xjqsh.lesraisinsarmor.client.resource.ArmorRenderConfigManager;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT, modid = LesRaisinsArmor.MOD_ID)
public class ClientSetupHandler {
    @SubscribeEvent
    public static void onAddResourceListener(RegisterClientReloadListenersEvent event){
        event.registerReloadListener(ArmorRenderConfigManager.getInstance());
    }
}
