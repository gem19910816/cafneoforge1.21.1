package net.mcreator.dyairdrop.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.HashMap;
import net.mcreator.dyairdrop.network.PannelREButtonMessage;
import net.mcreator.dyairdrop.procedures.Light1Procedure;
import net.mcreator.dyairdrop.procedures.Light2Procedure;
import net.mcreator.dyairdrop.procedures.Light3Procedure;
import net.mcreator.dyairdrop.procedures.Light4Procedure;
import net.mcreator.dyairdrop.procedures.Light5Procedure;
import net.mcreator.dyairdrop.procedures.Light6Procedure;
import net.mcreator.dyairdrop.procedures.Wrong1Procedure;
import net.mcreator.dyairdrop.procedures.Wrong2Procedure;
import net.mcreator.dyairdrop.procedures.Wrong3Procedure;
import net.mcreator.dyairdrop.procedures.Wrong4Procedure;
import net.mcreator.dyairdrop.procedures.Wrong5Procedure;
import net.mcreator.dyairdrop.procedures.Wrong6Procedure;
import net.mcreator.dyairdrop.world.inventory.PannelREMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class PannelREScreen extends AbstractContainerScreen<PannelREMenu> {
   private static final HashMap<String, Object> guistate = PannelREMenu.guistate;
   private final Level world;
   private final int x;
   private final int y;
   private final int z;
   private final Player entity;
   TextureButton imagebutton_a1;
   TextureButton imagebutton_b1;
   TextureButton imagebutton_c1;
   TextureButton imagebutton_d1;
   TextureButton imagebutton_e1;
   TextureButton imagebutton_f1;
   TextureButton imagebutton_check;

   public PannelREScreen(PannelREMenu container, Inventory inventory, Component text) {
      super(container, inventory, text);
      this.world = container.world;
      this.x = container.x;
      this.y = container.y;
      this.z = container.z;
      this.entity = container.entity;
      this.imageWidth = 198;
      this.imageHeight = 166;
   }

   public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
      this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
      super.render(guiGraphics, mouseX, mouseY, partialTicks);
      this.renderTooltip(guiGraphics, mouseX, mouseY);
   }

   protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      guiGraphics.blit(
         ResourceLocation.parse("dyairdrop:textures/screens/pannelre.png"), this.leftPos + 1, this.topPos + -1, 0.0F, 0.0F, 197, 165, 197, 165
      );
      guiGraphics.blit(ResourceLocation.parse("dyairdrop:textures/screens/dark.png"), this.leftPos + 73, this.topPos + 31, 0.0F, 0.0F, 19, 20, 19, 20);
      if (Wrong1Procedure.execute(this.entity)) {
         guiGraphics.blit(ResourceLocation.parse("dyairdrop:textures/screens/red.png"), this.leftPos + 73, this.topPos + 31, 0.0F, 0.0F, 19, 20, 19, 20);
      }

      if (Light1Procedure.execute(this.entity)) {
         guiGraphics.blit(ResourceLocation.parse("dyairdrop:textures/screens/green.png"), this.leftPos + 73, this.topPos + 31, 0.0F, 0.0F, 19, 20, 19, 20);
      }

      guiGraphics.blit(ResourceLocation.parse("dyairdrop:textures/screens/dark.png"), this.leftPos + 73, this.topPos + 61, 0.0F, 0.0F, 19, 20, 19, 20);
      if (Light2Procedure.execute(this.entity)) {
         guiGraphics.blit(ResourceLocation.parse("dyairdrop:textures/screens/green.png"), this.leftPos + 73, this.topPos + 61, 0.0F, 0.0F, 19, 20, 19, 20);
      }

      if (Wrong2Procedure.execute(this.entity)) {
         guiGraphics.blit(ResourceLocation.parse("dyairdrop:textures/screens/red.png"), this.leftPos + 73, this.topPos + 61, 0.0F, 0.0F, 19, 20, 19, 20);
      }

      guiGraphics.blit(ResourceLocation.parse("dyairdrop:textures/screens/dark.png"), this.leftPos + 73, this.topPos + 90, 0.0F, 0.0F, 19, 20, 19, 20);
      if (Wrong3Procedure.execute(this.entity)) {
         guiGraphics.blit(ResourceLocation.parse("dyairdrop:textures/screens/red.png"), this.leftPos + 73, this.topPos + 90, 0.0F, 0.0F, 19, 20, 19, 20);
      }

      if (Light3Procedure.execute(this.entity)) {
         guiGraphics.blit(ResourceLocation.parse("dyairdrop:textures/screens/green.png"), this.leftPos + 73, this.topPos + 90, 0.0F, 0.0F, 19, 20, 19, 20);
      }

      guiGraphics.blit(ResourceLocation.parse("dyairdrop:textures/screens/dark.png"), this.leftPos + 141, this.topPos + 31, 0.0F, 0.0F, 19, 20, 19, 20);
      if (Light4Procedure.execute(this.entity)) {
         guiGraphics.blit(
            ResourceLocation.parse("dyairdrop:textures/screens/green.png"), this.leftPos + 141, this.topPos + 31, 0.0F, 0.0F, 19, 20, 19, 20
         );
      }

      if (Wrong4Procedure.execute(this.entity)) {
         guiGraphics.blit(ResourceLocation.parse("dyairdrop:textures/screens/red.png"), this.leftPos + 141, this.topPos + 31, 0.0F, 0.0F, 19, 20, 19, 20);
      }

      guiGraphics.blit(ResourceLocation.parse("dyairdrop:textures/screens/dark.png"), this.leftPos + 141, this.topPos + 61, 0.0F, 0.0F, 19, 20, 19, 20);
      if (Light5Procedure.execute(this.entity)) {
         guiGraphics.blit(
            ResourceLocation.parse("dyairdrop:textures/screens/green.png"), this.leftPos + 141, this.topPos + 61, 0.0F, 0.0F, 19, 20, 19, 20
         );
      }

      if (Wrong5Procedure.execute(this.entity)) {
         guiGraphics.blit(ResourceLocation.parse("dyairdrop:textures/screens/red.png"), this.leftPos + 141, this.topPos + 61, 0.0F, 0.0F, 19, 20, 19, 20);
      }

      guiGraphics.blit(ResourceLocation.parse("dyairdrop:textures/screens/dark.png"), this.leftPos + 141, this.topPos + 89, 0.0F, 0.0F, 19, 20, 19, 20);
      if (Wrong6Procedure.execute(this.entity)) {
         guiGraphics.blit(ResourceLocation.parse("dyairdrop:textures/screens/red.png"), this.leftPos + 141, this.topPos + 89, 0.0F, 0.0F, 19, 20, 19, 20);
      }

      if (Light6Procedure.execute(this.entity)) {
         guiGraphics.blit(
            ResourceLocation.parse("dyairdrop:textures/screens/green.png"), this.leftPos + 141, this.topPos + 89, 0.0F, 0.0F, 19, 20, 19, 20
         );
      }

      RenderSystem.disableBlend();
   }

   public boolean keyPressed(int key, int b, int c) {
      if (key == 256) {
         this.minecraft.player.closeContainer();
         return true;
      } else {
         return super.keyPressed(key, b, c);
      }
   }

   public void containerTick() {
      super.containerTick();
   }

   protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
   }

   public void onClose() {
      super.onClose();
   }

   public void init() {
      super.init();
      this.imagebutton_a1 = new TextureButton(
         this.leftPos + 31, this.topPos + 29, 39, 22, 0, 0, 22, ResourceLocation.parse("dyairdrop:textures/screens/atlas/imagebutton_a1.png"), 39, 44, e -> {
            net.neoforged.neoforge.network.PacketDistributor.sendToServer(new PannelREButtonMessage(0, this.x, this.y, this.z));
            PannelREButtonMessage.handleButtonAction(this.entity, 0, this.x, this.y, this.z);
         }
      );
      guistate.put("button:imagebutton_a1", this.imagebutton_a1);
      this.addRenderableWidget(this.imagebutton_a1);
      this.imagebutton_b1 = new TextureButton(
         this.leftPos + 31, this.topPos + 59, 39, 22, 0, 0, 22, ResourceLocation.parse("dyairdrop:textures/screens/atlas/imagebutton_b1.png"), 39, 44, e -> {
            net.neoforged.neoforge.network.PacketDistributor.sendToServer(new PannelREButtonMessage(1, this.x, this.y, this.z));
            PannelREButtonMessage.handleButtonAction(this.entity, 1, this.x, this.y, this.z);
         }
      );
      guistate.put("button:imagebutton_b1", this.imagebutton_b1);
      this.addRenderableWidget(this.imagebutton_b1);
      this.imagebutton_c1 = new TextureButton(
         this.leftPos + 31, this.topPos + 88, 39, 22, 0, 0, 22, ResourceLocation.parse("dyairdrop:textures/screens/atlas/imagebutton_c1.png"), 39, 44, e -> {
            net.neoforged.neoforge.network.PacketDistributor.sendToServer(new PannelREButtonMessage(2, this.x, this.y, this.z));
            PannelREButtonMessage.handleButtonAction(this.entity, 2, this.x, this.y, this.z);
         }
      );
      guistate.put("button:imagebutton_c1", this.imagebutton_c1);
      this.addRenderableWidget(this.imagebutton_c1);
      this.imagebutton_d1 = new TextureButton(
         this.leftPos + 99, this.topPos + 29, 39, 22, 0, 0, 22, ResourceLocation.parse("dyairdrop:textures/screens/atlas/imagebutton_d1.png"), 39, 44, e -> {
            net.neoforged.neoforge.network.PacketDistributor.sendToServer(new PannelREButtonMessage(3, this.x, this.y, this.z));
            PannelREButtonMessage.handleButtonAction(this.entity, 3, this.x, this.y, this.z);
         }
      );
      guistate.put("button:imagebutton_d1", this.imagebutton_d1);
      this.addRenderableWidget(this.imagebutton_d1);
      this.imagebutton_e1 = new TextureButton(
         this.leftPos + 99, this.topPos + 59, 39, 22, 0, 0, 22, ResourceLocation.parse("dyairdrop:textures/screens/atlas/imagebutton_e1.png"), 39, 44, e -> {
            net.neoforged.neoforge.network.PacketDistributor.sendToServer(new PannelREButtonMessage(4, this.x, this.y, this.z));
            PannelREButtonMessage.handleButtonAction(this.entity, 4, this.x, this.y, this.z);
         }
      );
      guistate.put("button:imagebutton_e1", this.imagebutton_e1);
      this.addRenderableWidget(this.imagebutton_e1);
      this.imagebutton_f1 = new TextureButton(
         this.leftPos + 99, this.topPos + 88, 39, 22, 0, 0, 22, ResourceLocation.parse("dyairdrop:textures/screens/atlas/imagebutton_f1.png"), 39, 44, e -> {
            net.neoforged.neoforge.network.PacketDistributor.sendToServer(new PannelREButtonMessage(5, this.x, this.y, this.z));
            PannelREButtonMessage.handleButtonAction(this.entity, 5, this.x, this.y, this.z);
         }
      );
      guistate.put("button:imagebutton_f1", this.imagebutton_f1);
      this.addRenderableWidget(this.imagebutton_f1);
      this.imagebutton_check = new TextureButton(
         this.leftPos + 106,
         this.topPos + 114,
         42,
         24,
         0,
         0,
         24,
         ResourceLocation.parse("dyairdrop:textures/screens/atlas/imagebutton_check.png"),
         42,
         48,
         e -> {
            net.neoforged.neoforge.network.PacketDistributor.sendToServer(new PannelREButtonMessage(6, this.x, this.y, this.z));
            PannelREButtonMessage.handleButtonAction(this.entity, 6, this.x, this.y, this.z);
         }
      );
      guistate.put("button:imagebutton_check", this.imagebutton_check);
      this.addRenderableWidget(this.imagebutton_check);
   }
}
