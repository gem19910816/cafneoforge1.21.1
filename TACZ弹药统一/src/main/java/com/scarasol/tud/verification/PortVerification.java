package com.scarasol.tud.verification;

import com.scarasol.tud.TudMod;
import com.scarasol.tud.data.AmmoData;
import com.scarasol.tud.data.MagData;
import com.scarasol.tud.data.TaczGunDataMap;
import com.scarasol.tud.manager.AmmoManager;
import com.scarasol.tud.util.GunAmmoSelection;
import com.scarasol.tud.util.data.DataManager;
import com.scarasol.tud.configuration.CommonConfig;
import com.tacz.guns.api.TimelessAPI;
import com.tacz.guns.api.item.IAmmo;
import com.tacz.guns.api.item.IAmmoBox;
import com.tacz.guns.api.item.IGun;
import com.tacz.guns.api.entity.IGunOperator;
import com.tacz.guns.item.ModernKineticGunScriptAPI;
import com.tacz.guns.entity.EntityKineticBullet;
import com.tacz.guns.resource.modifier.AttachmentCacheProperty;
import com.tacz.guns.util.AttachmentDataUtils;
import com.tacz.guns.api.item.builder.AmmoItemBuilder;
import com.tacz.guns.api.item.builder.GunItemBuilder;
import com.tacz.guns.api.item.gun.AbstractGunItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.entity.EquipmentSlot;
import net.neoforged.neoforge.common.util.FakePlayerFactory;
import com.mojang.authlib.GameProfile;
import net.neoforged.neoforge.items.ItemStackHandler;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

/** Runs only with -PverifyPort, in an isolated development server. */
public final class PortVerification {
    private static int assertions;
    private PortVerification() {}

    public static void run(MinecraftServer server) {
        int guns = 0;
        try {
            Class.forName("com.tacz.guns.item.ModernKineticGunScriptAPI");
            for (var entry : TimelessAPI.getAllCommonGunIndex()) {
                ItemStack stack = GunItemBuilder.create().setId(entry.getKey()).build(server.registryAccess());
                var target = AmmoManager.getAmmo(stack);
                if (target == null) continue;
                check(TimelessAPI.getCommonAmmoIndex(target.getA()).isPresent(), "target index " + entry.getKey());
                ItemStack ammo = AmmoManager.getAmmoItemStack(target);
                check(IAmmo.getIAmmoOrNull(ammo).isAmmoOfGun(stack, ammo), "ammo match " + entry.getKey());
                ItemStack wrong = AmmoItemBuilder.create().setId(ResourceLocation.parse("tacz:9mm")).build();
                check(!IAmmo.getIAmmoOrNull(wrong).isAmmoOfGun(stack, wrong), "reject old caliber " + entry.getKey());
                var inventory = new ItemStackHandler(2);
                ammo.setCount(20);
                inventory.setStackInSlot(0, ammo);
                inventory.setStackInSlot(1, new ItemStack(Items.DIAMOND, 7));
                int count = ((AbstractGunItem) stack.getItem()).findAndExtractInventoryAmmo(inventory, stack, 6);
                check(count == 6 && inventory.getStackInSlot(0).getCount() == 14, "consume exact count " + entry.getKey());
                check(inventory.getStackInSlot(1).getCount() == 7, "preserve unrelated item");
                ItemStack box = new ItemStack(BuiltInRegistries.ITEM.get(ResourceLocation.parse("tacz:ammo_box")));
                IAmmoBox ammoBox = (IAmmoBox) box.getItem();
                ammoBox.setAmmoId(box, target.getA());
                ammoBox.setAmmoCount(box, 20);
                check(ammoBox.isAmmoBoxOfGun(stack, box), "box match " + entry.getKey());
                inventory.setStackInSlot(0, box);
                check(((AbstractGunItem) stack.getItem()).findAndExtractInventoryAmmo(inventory, stack, 6) == 6
                        && ammoBox.getAmmoCount(box) == 14, "box exact extraction " + entry.getKey());
                ammoBox.setAmmoId(box, ResourceLocation.parse("tacz:9mm"));
                check(!ammoBox.isAmmoBoxOfGun(stack, box), "box rejects old caliber " + entry.getKey());
                guns++;
            }
            check(guns > 40, "loaded real default gunpack");
            ItemStack stack = GunItemBuilder.create().setId(ResourceLocation.parse("tacz:m4a1")).build(server.registryAccess());
            var originalTypes = CommonConfig.TYPE_TO_AMMO.get();
            try {
                CommonConfig.TYPE_TO_AMMO.set(List.of("rifle, $minecraft:gold_nugget"));
                AmmoManager.init();
                check(AmmoManager.getAmmo(stack).getB(), "config item-ammo reload");
                var inventory = new ItemStackHandler(3);
                inventory.setStackInSlot(0, new ItemStack(Items.GOLD_NUGGET, 4));
                inventory.setStackInSlot(1, new ItemStack(Items.GOLD_NUGGET, 20));
                inventory.setStackInSlot(2, new ItemStack(Items.DIAMOND, 7));
                var gunItem = (AbstractGunItem) stack.getItem();
                check(gunItem.findAndExtractInventoryAmmo(inventory, stack, 6) == 6, "item ammo count across slots");
                check(inventory.getStackInSlot(0).isEmpty() && inventory.getStackInSlot(1).getCount() == 18,
                        "no over-extraction across slots");
                check(gunItem.findAndExtractInventoryAmmo(inventory, stack, 0) == 0
                        && inventory.getStackInSlot(1).getCount() == 18, "zero requested ammo");
                check(inventory.getStackInSlot(2).getCount() == 7, "item ammo preserves other items");
                CommonConfig.TYPE_TO_AMMO.set(List.of("rifle, tacz_unidict:rifle"));
                AmmoManager.init();
                check(!AmmoManager.getAmmo(stack).getB(), "config mapping replaces cached value");
            } finally {
                CommonConfig.TYPE_TO_AMMO.set(originalTypes);
                AmmoManager.init();
            }
            for (String id : List.of("pistol", "rifle", "sniper", "shot", "barrel", "fuel_tank")) {
                check(server.getRecipeManager().byKey(ResourceLocation.parse("tacz_unidict:ammo/" + id)).isPresent(),
                        "ported recipe path " + id);
            }
            CustomData.update(DataComponents.CUSTOM_DATA, stack, tag -> tag.putString("unrelated_key", "preserved"));
            GunAmmoSelection.set(stack, 1);
            check(GunAmmoSelection.get(stack) == 1, "selection component");
            check(stack.get(DataComponents.CUSTOM_DATA).copyTag().getString("unrelated_key").equals("preserved"), "keep custom data");
            var rifleId = ResourceLocation.parse("tacz_unidict:rifle");
            var sniperId = ResourceLocation.parse("tacz_unidict:sniper");
            AmmoData rifle = new AmmoData(rifleId, false);
            rifle.setDamageAmount(23.0f);
            rifle.setSpeed(333.0f);
            DataManager.registerModData(rifle);
            DataManager.registerModData(new AmmoData(sniperId, false));
            var gunData = new com.scarasol.tud.data.GunData(ResourceLocation.parse("tacz:m4a1"), List.of(
                    new MagData(rifleId, 37, 700, new Integer[]{45, 60, 75}), new MagData(sniperId, 10, null, null)));
            DataManager.registerModData(gunData);
            GunAmmoSelection.set(stack, -1);
            check(gunData.getCurrentAmmo(stack).equals(rifleId), "negative selection fallback");
            GunAmmoSelection.set(stack, 999);
            check(gunData.getCurrentAmmo(stack).equals(rifleId), "overflow selection fallback");
            GunAmmoSelection.set(stack, 0);
            var original = TimelessAPI.getCommonGunIndex(ResourceLocation.parse("tacz:m4a1")).orElseThrow().getGunData();
            var custom = TaczGunDataMap.getCustomGunData(stack, original);
            check(custom != original && custom.getAmmoId().equals(rifleId), "custom gun data");
            check(custom.getAmmoAmount() == 37 && custom.getRoundsPerMinute() == 700, "magazine overrides");
            check(custom.getBulletData().getDamageAmount() == 23.0f && custom.getBulletData().getSpeed() == 333.0f, "bullet overrides");
            check(AttachmentDataUtils.getAmmoCountWithAttachment(stack, original) == 37, "attachment capacity override");
            check(!original.getAmmoId().equals(rifleId), "original data unchanged");
            DataManager.clear(TaczGunDataMap.class);
            check(TaczGunDataMap.getCustomGunData(stack, null) != null, "cache miss in bullet override");
            var shooter = FakePlayerFactory.get(server.overworld(), new GameProfile(
                    UUID.fromString("ae915c24-c5ae-4f1e-9178-5b3ecf916802"), "PortVerifier"));
            shooter.setPos(0, 80, 0);
            shooter.setItemSlot(EquipmentSlot.MAINHAND, stack);
            var operator = IGunOperator.fromLivingEntity(shooter);
            operator.initialData();
            var cache = new AttachmentCacheProperty();
            cache.eval(stack, original);
            operator.updateCacheProperty(cache);
            var gunItem = IGun.getIGunOrNull(stack);
            gunItem.setCurrentAmmoCount(stack, 3);
            gunItem.setBulletInBarrel(stack, true);
            var script = new ModernKineticGunScriptAPI();
            script.setShooter(shooter);
            script.setItemStack(stack);
            script.setDataHolder(operator.getDataHolder());
            script.setPitchSupplier(() -> 0.0f);
            script.setYawSupplier(() -> 0.0f);
            script.shootOnce(true);
            EntityKineticBullet fired = null;
            for (var entity : server.overworld().getAllEntities()) {
                if (entity instanceof EntityKineticBullet bullet && bullet.ownedBy(shooter)) fired = bullet;
            }
            check(fired != null, "real script fires a bullet");
            check(fired.getAmmoId().equals(rifleId) && fired.getPersistentData().getString("TudAmmoId").equals(rifleId.toString()),
                    "bullet uses unified id and effect tag");
            check(Math.abs(fired.getDamage(fired.position()) - 23.0f) < 0.01f, "fired bullet damage override");
            fired.discard();
            GunAmmoSelection.set(stack, 1);
            ItemStack sniper = AmmoItemBuilder.create().setId(sniperId).build();
            check(IAmmo.getIAmmoOrNull(sniper).isAmmoOfGun(stack, sniper), "switched ammo match");
            String message = "TUD_PORT_SERVER_PASS guns=" + guns + " assertions=" + assertions;
            TudMod.LOGGER.info(message);
            Files.writeString(Path.of("server-verification.txt"), message);
        } catch (Throwable error) {
            TudMod.LOGGER.error("TUD_PORT_SERVER_FAILED", error);
            try { Files.writeString(Path.of("server-verification.txt"), "FAILED: " + error); } catch (Exception ignored) {}
        } finally {
            server.execute(() -> server.halt(false));
        }
    }

    private static void check(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
        assertions++;
    }
}
