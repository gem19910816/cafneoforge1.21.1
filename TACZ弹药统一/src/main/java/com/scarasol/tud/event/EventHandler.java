package com.scarasol.tud.event;

import com.scarasol.tud.TudMod;
import com.scarasol.tud.configuration.CommonConfig;
import com.scarasol.tud.data.*;
import com.scarasol.tud.manager.AmmoManager;
import com.scarasol.tud.util.data.DataManager;
import com.scarasol.tud.util.io.ModGson;
import com.tacz.guns.api.event.common.EntityHurtByGunEvent;
import com.tacz.guns.crafting.GunSmithTableRecipe;
import com.tacz.guns.entity.EntityKineticBullet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.client.event.RecipesUpdatedEvent;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import net.neoforged.neoforge.event.TagsUpdatedEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLPaths;
import net.minecraft.core.registries.BuiltInRegistries;

import javax.annotation.Nullable;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Scarasol
 */
@net.neoforged.fml.common.EventBusSubscriber(modid = TudMod.MODID)
public class EventHandler {


    private static final int DEFAULT_DURATION_TICKS = 200;

    @SubscribeEvent
    public static void onEntityHurtByGunPost(EntityHurtByGunEvent.Pre event) {
        if (event.getLogicalSide().isClient()) {
            return;
        }

        Entity hurt = event.getHurtEntity();
        if (hurt == null) {
            return;
        }

        Entity bullet = event.getBullet();
        String ammoId = bullet.getPersistentData().getString("TudAmmoId");

        AmmoData ammoData = DataManager.getSearchableModData(AmmoData.class, ammoId);
        if (ammoData == null) {
            return;
        }

        String modifierId = ammoData.getModifierId();
        if (modifierId == null) {
            return;
        }
        ModifierData modifierData = DataManager.getSearchableModData(ModifierData.class, modifierId);
        if (modifierData == null) {
            return;
        }
        event.setBaseAmount((float) (event.getBaseAmount() * modifierData.getModifier(hurt)));
    }


    @SubscribeEvent
    public static void onEntityHurtByGunPost(EntityHurtByGunEvent.Post event) {
        if (event.getLogicalSide().isClient()) {
            return;
        }

        Entity hurt = event.getHurtEntity();
        if (!(hurt instanceof LivingEntity living)) {
            return;
        }
        Entity bullet = event.getBullet();
        String ammoId = bullet.getPersistentData().getString("TudAmmoId");

        AmmoData ammoData = DataManager.getSearchableModData(AmmoData.class, ammoId);
        if (ammoData == null) {
            return;
        }

        List<EffectData> list = ammoData.getEffectDataList();
        if (list == null || list.isEmpty()) {
            return;
        }

        applyEffects(living, list);
    }

    private static void applyEffects(LivingEntity target, List<EffectData> list) {
        for (EffectData data : list) {
            if (data == null || data.resourceLocation() == null) {
                continue;
            }

            ResourceLocation effectId = data.resourceLocation();
            var effect = BuiltInRegistries.MOB_EFFECT.getHolder(effectId);
            if (effect.isEmpty()) {
                continue;
            }

            int amplifier = data.amplifier() == null ? 0 : Math.max(0, data.amplifier());
            int duration = data.duration() == null ? DEFAULT_DURATION_TICKS : Math.max(1, data.duration() * 20);

            target.addEffect(new MobEffectInstance(effect.get(), duration, amplifier));
        }
    }

    @SubscribeEvent
    public static void loadData(OnDatapackSyncEvent event) {
        if (event.getPlayer() != null) {
            return;
        }
        try {
            DataManager.clear(GunData.class);
            DataManager.clear(AmmoData.class);
            DataManager.clear(TaczGunDataMap.class);
            DataManager.clear(ModifierData.class);
            ModGson.INSTANCE.loadAll(FMLPaths.CONFIGDIR.get().resolve(TudMod.MODID).resolve("modifier_data"));
            ModGson.INSTANCE.loadAll(FMLPaths.CONFIGDIR.get().resolve(TudMod.MODID).resolve("gun_data"));
            ModGson.INSTANCE.loadAll(FMLPaths.CONFIGDIR.get().resolve(TudMod.MODID).resolve("ammo_data"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SubscribeEvent
    public static void loadData(ServerStartedEvent event) {
        try {
            DataManager.clear(GunData.class);
            DataManager.clear(AmmoData.class);
            DataManager.clear(TaczGunDataMap.class);
            DataManager.clear(ModifierData.class);
            ModGson.INSTANCE.loadAll(FMLPaths.CONFIGDIR.get().resolve(TudMod.MODID).resolve("modifier_data"));
            ModGson.INSTANCE.loadAll(FMLPaths.CONFIGDIR.get().resolve(TudMod.MODID).resolve("gun_data"));
            ModGson.INSTANCE.loadAll(FMLPaths.CONFIGDIR.get().resolve(TudMod.MODID).resolve("ammo_data"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Boolean.getBoolean("tacz_unidict.verify") && event.getServer().isDedicatedServer()) {
            com.scarasol.tud.verification.PortVerification.run(event.getServer());
        }
    }
}
