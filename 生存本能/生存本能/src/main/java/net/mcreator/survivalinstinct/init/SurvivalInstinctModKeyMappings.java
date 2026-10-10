package net.mcreator.survivalinstinct.init;

import net.mcreator.survivalinstinct.network.ExoSuitDashMessage;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = "survival_instinct", bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class SurvivalInstinctModKeyMappings {
    public static final KeyMapping EXO_SUIT_DASH = new KeyMapping("key.survival_instinct.exo_suit_dash", 88, "key.categories.movement");
    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) { event.register(EXO_SUIT_DASH); }

    @EventBusSubscriber(modid = "survival_instinct", value = Dist.CLIENT)
    public static final class KeyEventListener {
        @SubscribeEvent
        public static void tick(ClientTickEvent.Post event) {
            Minecraft client = Minecraft.getInstance();
            while (EXO_SUIT_DASH.consumeClick()) {
                if (client.player != null && client.screen == null && client.getConnection() != null) PacketDistributor.sendToServer(new ExoSuitDashMessage());
            }
        }
    }
}
