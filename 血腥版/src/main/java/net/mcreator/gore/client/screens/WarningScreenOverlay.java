package net.mcreator.gore.client.screens;

import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;
import com.mojang.blaze3d.systems.RenderSystem;
import net.mcreator.gore.procedures.WarningSCreenFinalFrameProcedure;
import net.mcreator.gore.procedures.WarningScreenDisplayOverlayIngameProcedure;
import net.mcreator.gore.procedures.WarningScreenFrame0Procedure;
import net.mcreator.gore.procedures.WarningScreenFrame1Procedure;
import net.mcreator.gore.procedures.WarningScreenFrame2Procedure;
import net.mcreator.gore.procedures.WarningScreenFrame3Procedure;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiEvent.Pre;

@EventBusSubscriber({Dist.CLIENT})
public class WarningScreenOverlay {
   @SubscribeEvent(
      priority = EventPriority.LOWEST
   )
   public static void eventHandler(Pre event) {
      int w = event.getGuiGraphics().guiWidth();
      int h = event.getGuiGraphics().guiHeight();
      Level world = null;
      double x = 0.0;
      double y = 0.0;
      double z = 0.0;
      Player entity = Minecraft.getInstance().player;
      if (entity != null) {
         world = entity.level();
         x = entity.getX();
         y = entity.getY();
         z = entity.getZ();
      }

      RenderSystem.disableDepthTest();
      RenderSystem.depthMask(false);
      RenderSystem.enableBlend();
      RenderSystem.setShader(GameRenderer::getPositionTexShader);
      RenderSystem.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      if (WarningScreenDisplayOverlayIngameProcedure.execute(entity)) {
         if (WarningScreenFrame0Procedure.execute(entity)) {
            event.getGuiGraphics()
               .blit(ResourceLocation.parse("gore_edition:textures/screens/warning_screen_0f.png"), w / 2 + -213, h / 2 + -120, 0.0F, 0.0F, 427, 240, 427, 240);
         }

         if (WarningScreenFrame1Procedure.execute(entity)) {
            event.getGuiGraphics()
               .blit(ResourceLocation.parse("gore_edition:textures/screens/warning_screen_1f.png"), w / 2 + -213, h / 2 + -120, 0.0F, 0.0F, 427, 240, 427, 240);
         }

         if (WarningScreenFrame2Procedure.execute(entity)) {
            event.getGuiGraphics()
               .blit(ResourceLocation.parse("gore_edition:textures/screens/warning_screen_2f.png"), w / 2 + -213, h / 2 + -120, 0.0F, 0.0F, 427, 240, 427, 240);
         }

         if (WarningScreenFrame3Procedure.execute(entity)) {
            event.getGuiGraphics()
               .blit(ResourceLocation.parse("gore_edition:textures/screens/warning_screen_3f.png"), w / 2 + -213, h / 2 + -120, 0.0F, 0.0F, 427, 240, 427, 240);
         }

         if (WarningSCreenFinalFrameProcedure.execute(entity)) {
            event.getGuiGraphics()
               .blit(ResourceLocation.parse("gore_edition:textures/screens/warning_screen_ff.png"), w / 2 + -213, h / 2 + -120, 0.0F, 0.0F, 427, 240, 427, 240);
         }
      }

      RenderSystem.depthMask(true);
      RenderSystem.defaultBlendFunc();
      RenderSystem.enableDepthTest();
      RenderSystem.disableBlend();
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
   }
}
