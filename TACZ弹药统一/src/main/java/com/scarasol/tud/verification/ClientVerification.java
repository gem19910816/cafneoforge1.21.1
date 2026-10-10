package com.scarasol.tud.verification;

import com.scarasol.tud.TudMod;
import com.scarasol.tud.client.screen.WheelMenuScreen;
import com.scarasol.tud.init.TudShaders;
import com.scarasol.tud.data.AmmoData;
import com.scarasol.tud.data.MagData;
import com.scarasol.tud.manager.AmmoManager;
import com.scarasol.tud.util.GunAmmoSelection;
import com.scarasol.tud.util.data.DataManager;
import com.tacz.guns.api.TimelessAPI;
import com.tacz.guns.api.item.builder.AmmoItemBuilder;
import com.tacz.guns.api.item.builder.GunItemBuilder;
import com.tacz.guns.api.item.IGun;
import com.tacz.guns.api.item.IAmmo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Screenshot;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import com.scarasol.tud.network.SwitchAmmoPacket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/** Opt-in isolated client smoke test. Never opens or changes the user's worlds. */
@EventBusSubscriber(modid = TudMod.MODID, value = Dist.CLIENT)
public final class ClientVerification {
    private static int stage;
    private static int ticks;
    private static int modelCount;
    private static final List<ItemStack> ammo = new ArrayList<>();
    private static ItemStack gun;
    private static boolean showTooltip;
    private static volatile int returnedAmmo;
    private static volatile boolean serverAccepted;

    @SubscribeEvent
    public static void tick(ClientTickEvent.Post event) {
        if (!Boolean.getBoolean("tacz_unidict.verify")) return;
        Minecraft mc = Minecraft.getInstance();
        try {
            if (++ticks > 2400) throw new AssertionError("client verification timeout stage=" + stage);
            if (stage == 0 && mc.screen instanceof TitleScreen && mc.getOverlay() == null) {
                stage = 1;
                ticks = 0;
                TudMod.LOGGER.info("TUD_PORT_CLIENT_STAGE open isolated world");
                mc.createWorldOpenFlows().openWorld("port-client-smoke", () -> mc.setScreen(new TitleScreen()));
            } else if (stage == 1 && mc.player != null && mc.level != null && mc.screen == null) {
                for (String id : List.of("pistol", "rifle", "sniper", "shot", "barrel", "fuel_tank")) {
                    var location = ResourceLocation.fromNamespaceAndPath(TudMod.MODID, id);
                    var index = TimelessAPI.getClientAmmoIndex(location).orElseThrow();
                    if (index.getAmmoModel() == null) throw new AssertionError("model: " + id);
                    if (index.getModelTextureLocation() == null || index.getSlotTextureLocation() == null) throw new AssertionError("texture: " + id);
                    ammo.add(AmmoItemBuilder.create().setId(location).build());
                    modelCount++;
                }
                gun = GunItemBuilder.create().setId(ResourceLocation.parse("tacz:m4a1")).build(mc.level.registryAccess());
                var gunData = new com.scarasol.tud.data.GunData(ResourceLocation.parse("tacz:m4a1"), List.of(
                        new MagData(ResourceLocation.parse("tacz_unidict:rifle"), null, null, null),
                        new MagData(ResourceLocation.parse("tacz_unidict:sniper"), null, null, null)));
                DataManager.registerModData(gunData);
                DataManager.registerModData(new AmmoData(ResourceLocation.parse("tacz_unidict:rifle"), false));
                DataManager.registerModData(new AmmoData(ResourceLocation.parse("tacz_unidict:sniper"), false));
                var server = mc.getSingleplayerServer();
                server.execute(() -> {
                    var player = server.getPlayerList().getPlayer(mc.player.getUUID());
                    player.setGameMode(GameType.SURVIVAL);
                    player.getInventory().clearContent();
                    player.getInventory().setItem(0, gun.copy());
                    var held = player.getMainHandItem();
                    var iGun = IGun.getIGunOrNull(held);
                    iGun.setCurrentAmmoCount(held, 70);
                    iGun.setBulletInBarrel(held, true);
                    DataManager.registerModData(gunData);
                });
                stage = 2;
                ticks = 0;
            } else if (stage == 2 && ticks > 30) {
                if (TudShaders.WHEEL_MENU_SHADER == null) throw new AssertionError("wheel shader not loaded");
                WheelMenuScreen.setWheelItems(ammo);
                showTooltip = false;
                mc.setScreen(new PreviewScreen());
                stage = 3;
                ticks = 0;
            } else if (stage == 3 && ticks > 30) {
                try (var image = Screenshot.takeScreenshot(mc.getMainRenderTarget())) {
                    image.writeToFile(Path.of("client-wheel-preview.png"));
                }
                showTooltip = true;
                stage = 4;
                ticks = 0;
            } else if (stage == 4 && ticks > 20) {
                try (var image = Screenshot.takeScreenshot(mc.getMainRenderTarget())) {
                    image.writeToFile(Path.of("client-preview.png"));
                }
                mc.setScreen(null);
                PacketDistributor.sendToServer(new SwitchAmmoPacket(-1));
                PacketDistributor.sendToServer(new SwitchAmmoPacket(999));
                stage = 5;
                ticks = 0;
            } else if (stage == 5 && ticks > 25) {
                if (GunAmmoSelection.get(mc.player.getMainHandItem()) != 0
                        || IGun.getIGunOrNull(mc.player.getMainHandItem()).getCurrentAmmoCount(mc.player.getMainHandItem()) != 70)
                    throw new AssertionError("invalid packets must preserve selection and magazine");
                PacketDistributor.sendToServer(new SwitchAmmoPacket(1));
                stage = 6;
                ticks = 0;
            } else if (stage == 6 && ticks > 50) {
                if (GunAmmoSelection.get(mc.player.getMainHandItem()) != 1) throw new AssertionError("server/client selection sync");
                var server = mc.getSingleplayerServer();
                server.execute(() -> {
                    var player = server.getPlayerList().getPlayer(mc.player.getUUID());
                    var held = player.getMainHandItem();
                    var iGun = IGun.getIGunOrNull(held);
                    for (var stack : player.getInventory().items) {
                        var iAmmo = IAmmo.getIAmmoOrNull(stack);
                        if (iAmmo != null && iAmmo.getAmmoId(stack).equals(ResourceLocation.parse("tacz_unidict:rifle")))
                            returnedAmmo += stack.getCount();
                    }
                    serverAccepted = GunAmmoSelection.get(held) == 1 && iGun.getCurrentAmmoCount(held) == 0 && !iGun.hasBulletInBarrel(held);
                });
                stage = 7;
                ticks = 0;
            } else if (stage == 7 && ticks > 20) {
                if (!serverAccepted || returnedAmmo != 71) throw new AssertionError("return magazine and chamber ammo: " + returnedAmmo);
                String result = "TUD_PORT_CLIENT_PASS models=" + modelCount + " wheel=rendered tooltip=rendered payload=roundtrip invalid_packets=rejected returned_ammo=" + returnedAmmo;
                Files.writeString(Path.of("client-verification.txt"), result);
                TudMod.LOGGER.info(result);
                stage = 8;
                mc.stop();
            }
        } catch (Throwable error) {
            TudMod.LOGGER.error("TUD_PORT_CLIENT_FAILED", error);
            try { Files.writeString(Path.of("client-verification.txt"), "FAILED: " + error); } catch (Exception ignored) {}
            stage = 8;
            mc.stop();
        }
    }

    private static final class PreviewScreen extends WheelMenuScreen {
        @Override
        public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
            super.render(graphics, mouseX, mouseY, partialTick);
            for (int i = 0; i < ammo.size(); i++) {
                graphics.pose().pushPose();
                graphics.pose().translate(20 + i * 48, 24, 0);
                graphics.pose().scale(2, 2, 2);
                graphics.renderItem(ammo.get(i), 0, 0);
                graphics.pose().popPose();
            }
            if (showTooltip) graphics.renderTooltip(Minecraft.getInstance().font, gun, 10, 70);
        }
    }
}
