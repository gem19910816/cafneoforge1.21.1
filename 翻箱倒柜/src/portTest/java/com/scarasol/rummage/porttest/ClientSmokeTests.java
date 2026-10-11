package com.scarasol.rummage.porttest;

import com.scarasol.itemrarity.client.RarityImageButton;
import com.scarasol.itemrarity.data.RarityGrade;
import com.scarasol.itemrarity.data.RarityManager;
import com.scarasol.itemrarity.event.ClientEventHandler;
import com.scarasol.rummage.manager.ClientRummageManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Screenshot;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RenderFrameEvent;
import org.lwjgl.glfw.GLFW;

import java.nio.file.Files;
import java.nio.file.Path;

@EventBusSubscriber(modid = "rummage", value = Dist.CLIENT)
public final class ClientSmokeTests {
    private static int ticks;
    private static int readyTicks;
    private static int stage;
    private static String capture;
    private static boolean sawNetworkMask;

    @SubscribeEvent
    public static void tick(ClientTickEvent.Post event) {
        if (!Boolean.getBoolean("rummage.portClientTests")) return;
        Minecraft mc = Minecraft.getInstance();
        ticks++;
        mc.options.pauseOnLostFocus = false;
        if (ticks == 1) GLFW.glfwIconifyWindow(mc.getWindow().getWindow());
        if (ticks > 6000) {
            finish("FAIL: timed out waiting for client world");
            return;
        }
        if (mc.player == null || mc.level == null || mc.gameMode == null) return;
        try {
            readyTicks++;
            if (stage == 0 && readyTicks > 40) {
                RarityGrade grade = new RarityGrade("port_client", 3,
                        ResourceLocation.fromNamespaceAndPath("item_rarity", "screen/grade/rarity_3.png"),
                        "#FF00FF", 0.8F);
                grade.add(ResourceLocation.withDefaultNamespace("oak_planks"));
                RarityManager.registerRarityData(grade);
                mc.gameMode.setLocalMode(GameType.CREATIVE);
                mc.player.setPermissionLevel(2);
                mc.player.getInventory().setItem(0, new ItemStack(Items.OAK_PLANKS, 16));
                mc.setScreen(new CreativeModeInventoryScreen(mc.player, mc.level.enabledFeatures(), true));
                stage = 1;
                readyTicks = 0;
            } else if (stage == 1 && readyTicks > 30) {
                if (!(mc.screen instanceof CreativeModeInventoryScreen screen)) {
                    throw new AssertionError("Creative screen did not open");
                }
                var buttons = screen.children().stream().filter(RarityImageButton.class::isInstance)
                        .map(RarityImageButton.class::cast).toList();
                if (buttons.size() != 2) throw new AssertionError("Missing rarity buttons");
                buttons.stream().filter(button -> button.visible).findFirst().orElseThrow().onPress();
                if (!ClientEventHandler.IS_EDIT) throw new AssertionError("Edit button does not toggle editing");
                capture = "creative-edit.png";
                stage = 2;
                readyTicks = 0;
            } else if (stage == 2 && readyTicks > 30) {
                SimpleContainer inventory = new SimpleContainer(27);
                inventory.setItem(0, new ItemStack(Items.DIAMOND, 4));
                inventory.setItem(1, new ItemStack(Items.GOLD_INGOT, 8));
                inventory.setItem(2, new ItemStack(Items.OAK_PLANKS, 16));
                ChestMenu menu = ChestMenu.threeRows(1, mc.player.getInventory(), inventory);
                mc.player.containerMenu = menu;
                mc.setScreen(new ContainerScreen(menu, mc.player.getInventory(), Component.literal("Port Test")));
                ClientRummageManager.MASKED_MENU_SLOTS.set(0);
                ClientRummageManager.currentRummageSlot = 0;
                ClientRummageManager.rummageStartTime = System.currentTimeMillis();
                ClientRummageManager.rummageTotalTimeMs = 5000;
                stage = 3;
                readyTicks = 0;
            } else if (stage == 3 && readyTicks > 20) {
                capture = "chest-mask.png";
                stage = 4;
                readyTicks = 0;
            } else if (stage == 4 && readyTicks > 10) {
                mc.setScreen(null);
                ClientRummageManager.clear();
                var server = mc.getSingleplayerServer();
                var uuid = mc.player.getUUID();
                server.execute(() -> {
                    var player = server.getPlayerList().getPlayer(uuid);
                    player.setGameMode(GameType.SURVIVAL);
                    var pos = player.blockPosition().offset(2, 0, 0);
                    player.serverLevel().setBlockAndUpdate(pos, net.minecraft.world.level.block.Blocks.CHEST.defaultBlockState());
                    var chest = (net.minecraft.world.level.block.entity.ChestBlockEntity) player.serverLevel().getBlockEntity(pos);
                    chest.setItem(0, new ItemStack(Items.DIAMOND));
                    ((com.scarasol.rummage.api.mixin.IRummageable) chest).setNeedRummage(true);
                    player.openMenu(chest);
                });
                stage = 5;
                readyTicks = 0;
            } else if (stage == 5) {
                if (ClientRummageManager.shouldMask(0)) sawNetworkMask = true;
                if (sawNetworkMask && !ClientRummageManager.shouldMask(0) && readyTicks > 20) {
                    finish("PASS: client world, creative edit buttons, rarity rendering, server-to-client mask and timed unlock");
                } else if (readyTicks > 200) {
                    throw new AssertionError("Server-to-client mask/unlock cycle did not complete; mask seen=" + sawNetworkMask);
                }
            }
        } catch (Throwable error) {
            finish("FAIL: " + error);
            error.printStackTrace();
        }
    }

    @SubscribeEvent
    public static void render(RenderFrameEvent.Post event) {
        if (capture == null) return;
        String name = capture;
        capture = null;
        try (var image = Screenshot.takeScreenshot(Minecraft.getInstance().getMainRenderTarget())) {
            Path dir = Path.of("port-test-screenshots");
            Files.createDirectories(dir);
            image.writeToFile(dir.resolve(name));
        } catch (Exception error) {
            finish("FAIL screenshot: " + error);
        }
    }

    private static void finish(String result) {
        System.setProperty("rummage.portClientTests", "false");
        try {
            Files.writeString(Path.of("port-client-test-results.txt"), result + System.lineSeparator());
        } catch (Exception error) {
            error.printStackTrace();
        }
        Minecraft.getInstance().stop();
    }
}
