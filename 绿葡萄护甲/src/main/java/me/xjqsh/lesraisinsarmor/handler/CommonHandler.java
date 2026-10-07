package me.xjqsh.lesraisinsarmor.handler;

import me.xjqsh.lesraisinsarmor.LesRaisinsArmor;
import me.xjqsh.lesraisinsarmor.network.s2c.ArmorDataMessage;
import me.xjqsh.lesraisinsarmor.resource.ArmorDataManager;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = LesRaisinsArmor.MOD_ID)
public class CommonHandler {
    @SubscribeEvent
    public static void onServerResourceReload(AddReloadListenerEvent event) {
        event.addListener(ArmorDataManager.getInstance());
    }

    @SubscribeEvent
    public static void onDatapackSync(OnDatapackSyncEvent event) {
        if (event.getPlayer() == null) {
            for (ServerPlayer s : event.getPlayerList().getPlayers()) {
                PacketDistributor.sendToPlayer(s, new ArmorDataMessage(ArmorDataManager.getInstance().getNetworkCache()));
            }
        } else {
            PacketDistributor.sendToPlayer(event.getPlayer(),
                    new ArmorDataMessage(ArmorDataManager.getInstance().getNetworkCache()));
        }
    }
}
