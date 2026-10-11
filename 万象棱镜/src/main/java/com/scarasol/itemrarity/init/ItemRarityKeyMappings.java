package com.scarasol.itemrarity.init;

import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

/**
 * @author Scarasol
 */
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class ItemRarityKeyMappings {

    public static final KeyMapping EDIT_SWITCH = new KeyMapping("key.item_rarity.edit_switch", GLFW.GLFW_KEY_SPACE, "key.categories.item_rarity");



    @SubscribeEvent
    public static void registerKeyBindings(RegisterKeyMappingsEvent event) {
        event.register(EDIT_SWITCH);

    }


}
