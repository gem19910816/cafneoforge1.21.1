package com.scarasol.tud.data;

import com.scarasol.tud.api.data.SearchableModData;
import com.scarasol.tud.manager.AmmoManager;
import com.scarasol.tud.util.TaczGunDataOverrideUtil;
import com.scarasol.tud.util.data.DataManager;
import com.tacz.guns.api.item.IGun;
import com.tacz.guns.resource.pojo.data.gun.GunData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

/**
 * @author Scarasol
 */
public record TaczGunDataMap(ResourceLocation gunId, ResourceLocation ammoId, GunData gunData) implements SearchableModData {

    @Nullable
    public static String getGunDataMapId(ItemStack itemStack) {
        IGun iGun = IGun.getIGunOrNull(itemStack);
        if (iGun != null) {
            AmmoData ammoData = AmmoManager.getCurrentAmmoData(itemStack);
            if (ammoData != null && ammoData.getAmmoId() != null) {
                ResourceLocation gunId = iGun.getGunId(itemStack);
                return gunId + " + " + ammoData.getAmmoId();
            }
        }
        return null;
    }

    public static GunData getCustomGunData(ItemStack gunItem, GunData originalGunData) {
        if (originalGunData == null) {
            IGun gun = IGun.getIGunOrNull(gunItem);
            if (gun == null) return null;
            originalGunData = com.tacz.guns.api.TimelessAPI.getCommonGunIndex(gun.getGunId(gunItem))
                    .map(com.tacz.guns.resource.index.CommonGunIndex::getGunData).orElse(null);
            if (originalGunData == null) return null;
        }
        String searchId = getGunDataMapId(gunItem);
        if (searchId != null) {
            TaczGunDataMap gunDataMap = DataManager.getSearchableModData(TaczGunDataMap.class, searchId);
            if (gunDataMap != null) {
                return gunDataMap.gunData;
            } else {
                GunData gunData = TaczGunDataOverrideUtil.buildOverrideGunData(originalGunData, gunItem);
                if (gunData != null) DataManager.registerModData(new TaczGunDataMap(IGun.getIGunOrNull(gunItem).getGunId(gunItem), AmmoManager.getCurrentAmmoData(gunItem).getAmmoId(), gunData));
               return gunData;
            }
        }
        return originalGunData;
    }

    @Override
    public String getId() {
        return gunId.toString() + " + " + ammoId.toString();
    }
}
