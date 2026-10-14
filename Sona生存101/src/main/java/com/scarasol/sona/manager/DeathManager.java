package com.scarasol.sona.manager;

import com.scarasol.sona.SonaMod;
import com.scarasol.sona.configuration.CommonConfig;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.*;

@EventBusSubscriber(modid = SonaMod.MODID)
public class DeathManager {
    private static final Map<UUID, List<Tuple<ItemStack, Integer>>> storedVanillaInventories = new HashMap<>();

    public static void KeepItem(Player player){
        Inventory inventory = player.getInventory();
        Level level = player.level();
        if (!level.isClientSide()){
            List<Tuple<ItemStack, Integer>> vanillaInventoryItem = new ArrayList<>();
            for(int i = 0; i <= 40; ++i) {
                ItemStack item = inventory.getItem(i);
                ResourceLocation key = BuiltInRegistries.ITEM.getKey(item.getItem());
                if ((CommonConfig.ARMOR_KEEP.get() && i >= 36 && i <= 39) || (key != null && CommonConfig.findIndex(key.toString(), CommonConfig.KEEP_WHITELIST.get()) != -1)) {
                    vanillaInventoryItem.add(new Tuple<>(item, i));
                    inventory.removeItem(item);
                }
            }
            storedVanillaInventories.put(player.getUUID(), vanillaInventoryItem);
        }
    }

    public static void respawnItem(Player player){
        List<Tuple<ItemStack, Integer>> list = storedVanillaInventories.remove(player.getUUID());
        if (list != null) {
            for (Tuple<ItemStack, Integer> tuple : list){
                player.getInventory().setItem(tuple.getB(), tuple.getA());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        storedVanillaInventories.remove(event.getEntity().getUUID());
    }
}
