package com.scarasol.itemrarity.init;

import com.scarasol.itemrarity.ItemRarityMod;
import com.scarasol.itemrarity.command.ItemRarityCommand;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;

/**
 * @author Scarasol
 */ // 注意：指令注册事件在 FORGE 宗线上抛出
@EventBusSubscriber(modid = ItemRarityMod.MODID, bus = EventBusSubscriber.Bus.GAME)
public class ItemRarityCommands {

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        ItemRarityCommand.register(event.getDispatcher());
    }
}