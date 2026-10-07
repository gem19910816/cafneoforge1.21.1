package net.mcreator.dyairdrop.client.gui;


import com.mojang.blaze3d.systems.RenderSystem;
import net.mcreator.dyairdrop.world.inventory.AirdropGUIMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class AirdropGUIScreen extends AbstractContainerScreen<AirdropGUIMenu> {
   private final Level world;
   private final int x;
   private final int y;
   private final int z;
   private final Player entity;
   private static final ResourceLocation texture = ResourceLocation.parse("dyairdrop:textures/screens/airdrop_gui.png");

   public AirdropGUIScreen(AirdropGUIMenu container, Inventory inventory, Component text) {
      super(container, inventory, text);
      this.world = container.world;
      this.x = container.x;
      this.y = container.y;
      this.z = container.z;
      this.entity = container.entity;
      this.imageWidth = 176;
      this.imageHeight = 166;
   }

   public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
      this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
      super.render(guiGraphics, mouseX, mouseY, partialTicks);
      this.renderTooltip(guiGraphics, mouseX, mouseY);
   }

   protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      guiGraphics.blit(texture, this.leftPos, this.topPos, 0.0F, 0.0F, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
      RenderSystem.disableBlend();
   }

   public boolean keyPressed(int key, int b, int c) {
      // ESC 交回原版 Screen 流程（原实现硬编码 key==256 并直接 closeContainer，会和其它模组抢 ESC 处理）
      return super.keyPressed(key, b, c);
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
   }
}
