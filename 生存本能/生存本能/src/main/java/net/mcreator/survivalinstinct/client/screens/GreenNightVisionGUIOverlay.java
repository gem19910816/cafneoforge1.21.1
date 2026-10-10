package net.mcreator.survivalinstinct.client.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import net.mcreator.survivalinstinct.procedures.GreenNightVisionGUIDisplayOverlayIngameProcedure;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

@EventBusSubscriber(modid = "survival_instinct", value = Dist.CLIENT)
public final class GreenNightVisionGUIOverlay {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(
            "survival_instinct", "textures/screens/night_vision_hunter.png");

    @SubscribeEvent
    public static void register(RegisterGuiLayersEvent event) {
        event.registerAbove(VanillaGuiLayers.CAMERA_OVERLAYS,
                ResourceLocation.fromNamespaceAndPath("survival_instinct", "night_vision"), (graphics, delta) -> {
            Minecraft minecraft = Minecraft.getInstance();
            if (!minecraft.options.getCameraType().isFirstPerson()
                    || !GreenNightVisionGUIDisplayOverlayIngameProcedure.execute(minecraft.player)) return;
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            graphics.blit(TEXTURE, 0, 0, graphics.guiWidth(), graphics.guiHeight(), 0, 0, 960, 540, 960, 540);
            RenderSystem.disableBlend();
        });
    }
}
