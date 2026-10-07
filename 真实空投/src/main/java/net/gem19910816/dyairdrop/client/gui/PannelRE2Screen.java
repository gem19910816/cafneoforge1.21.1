package net.gem19910816.dyairdrop.client.gui;

import net.gem19910816.dyairdrop.panel.LetterPanel;


import com.mojang.blaze3d.systems.RenderSystem;
import net.gem19910816.dyairdrop.world.inventory.PannelRE2Menu;
import net.gem19910816.dyairdrop.network.payload.PanelActionPayload;
import net.gem19910816.dyairdrop.panel.PanelService;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;

public class PannelRE2Screen extends AbstractContainerScreen<PannelRE2Menu> {
   private static final int[] INDICATOR_X = {73, 73, 73, 141, 141, 141};
   private static final int[] INDICATOR_Y = {31, 61, 90, 31, 61, 89};
   private static final ResourceLocation TEXTURE_PANEL = ResourceLocation.parse("dyairdrop:textures/screens/pannelre.png");
   private static final ResourceLocation TEXTURE_DARK = ResourceLocation.parse("dyairdrop:textures/screens/dark.png");
   private static final ResourceLocation TEXTURE_GREEN = ResourceLocation.parse("dyairdrop:textures/screens/green.png");
   private static final ResourceLocation TEXTURE_RED = ResourceLocation.parse("dyairdrop:textures/screens/red.png");
   private final Level world;
   private final int x;
   private final int y;
   private final int z;
   private final Player entity;
   Button imagebutton_a1;
   Button imagebutton_b1;
   Button imagebutton_c1;
   Button imagebutton_d1;
   Button imagebutton_e1;
   Button imagebutton_f1;
   Button imagebutton_check;

   public PannelRE2Screen(PannelRE2Menu container, Inventory inventory, Component text) {
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
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      guiGraphics.blit(TEXTURE_PANEL, this.leftPos + 1, this.topPos + -1, 0.0F, 0.0F, 197, 165, 197, 165
      );
      for (int slot = 0; slot < INDICATOR_X.length; slot++) {
         int slotX = this.leftPos + INDICATOR_X[slot];
         int slotY = this.topPos + INDICATOR_Y[slot];
         guiGraphics.blit(TEXTURE_DARK, slotX, slotY, 0.0F, 0.0F, 19, 20, 19, 20);
         if (LetterPanel.isWrong(this.entity, slot)) {
            guiGraphics.blit(TEXTURE_RED, slotX, slotY, 0.0F, 0.0F, 19, 20, 19, 20);
         }
         if (LetterPanel.isLit(this.entity, slot)) {
            guiGraphics.blit(TEXTURE_GREEN, slotX, slotY, 0.0F, 0.0F, 19, 20, 19, 20);
         }
      }

      RenderSystem.disableBlend();
   }

   public boolean keyPressed(int key, int b, int c) {
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
      this.imagebutton_a1 = Button.builder(Component.empty(), e -> {
            PacketDistributor.sendToServer(new PanelActionPayload(0, PanelService.KIND_LETTER_RE2, new net.minecraft.core.BlockPos(this.x, this.y, this.z), ""));
         }).bounds(this.leftPos + 31, this.topPos + 29, 39, 22).build(builder -> new Button(builder) {
            @Override
            public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
               guiGraphics.blit(ResourceLocation.parse("dyairdrop:textures/screens/atlas/imagebutton_a1.png"), this.getX(), this.getY(), 0.0F, this.isHoveredOrFocused() ? 22.0F : 0.0F, 39, 22, 39, 44);
            }
         });
      this.addRenderableWidget(this.imagebutton_a1);
      this.imagebutton_b1 = Button.builder(Component.empty(), e -> {
            PacketDistributor.sendToServer(new PanelActionPayload(1, PanelService.KIND_LETTER_RE2, new net.minecraft.core.BlockPos(this.x, this.y, this.z), ""));
         }).bounds(this.leftPos + 31, this.topPos + 59, 39, 22).build(builder -> new Button(builder) {
            @Override
            public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
               guiGraphics.blit(ResourceLocation.parse("dyairdrop:textures/screens/atlas/imagebutton_b1.png"), this.getX(), this.getY(), 0.0F, this.isHoveredOrFocused() ? 22.0F : 0.0F, 39, 22, 39, 44);
            }
         });
      this.addRenderableWidget(this.imagebutton_b1);
      this.imagebutton_c1 = Button.builder(Component.empty(), e -> {
            PacketDistributor.sendToServer(new PanelActionPayload(2, PanelService.KIND_LETTER_RE2, new net.minecraft.core.BlockPos(this.x, this.y, this.z), ""));
         }).bounds(this.leftPos + 31, this.topPos + 88, 39, 22).build(builder -> new Button(builder) {
            @Override
            public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
               guiGraphics.blit(ResourceLocation.parse("dyairdrop:textures/screens/atlas/imagebutton_c1.png"), this.getX(), this.getY(), 0.0F, this.isHoveredOrFocused() ? 22.0F : 0.0F, 39, 22, 39, 44);
            }
         });
      this.addRenderableWidget(this.imagebutton_c1);
      this.imagebutton_d1 = Button.builder(Component.empty(), e -> {
            PacketDistributor.sendToServer(new PanelActionPayload(3, PanelService.KIND_LETTER_RE2, new net.minecraft.core.BlockPos(this.x, this.y, this.z), ""));
         }).bounds(this.leftPos + 99, this.topPos + 29, 39, 22).build(builder -> new Button(builder) {
            @Override
            public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
               guiGraphics.blit(ResourceLocation.parse("dyairdrop:textures/screens/atlas/imagebutton_d1.png"), this.getX(), this.getY(), 0.0F, this.isHoveredOrFocused() ? 22.0F : 0.0F, 39, 22, 39, 44);
            }
         });
      this.addRenderableWidget(this.imagebutton_d1);
      this.imagebutton_e1 = Button.builder(Component.empty(), e -> {
            PacketDistributor.sendToServer(new PanelActionPayload(4, PanelService.KIND_LETTER_RE2, new net.minecraft.core.BlockPos(this.x, this.y, this.z), ""));
         }).bounds(this.leftPos + 99, this.topPos + 59, 39, 22).build(builder -> new Button(builder) {
            @Override
            public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
               guiGraphics.blit(ResourceLocation.parse("dyairdrop:textures/screens/atlas/imagebutton_e1.png"), this.getX(), this.getY(), 0.0F, this.isHoveredOrFocused() ? 22.0F : 0.0F, 39, 22, 39, 44);
            }
         });
      this.addRenderableWidget(this.imagebutton_e1);
      this.imagebutton_f1 = Button.builder(Component.empty(), e -> {
            PacketDistributor.sendToServer(new PanelActionPayload(5, PanelService.KIND_LETTER_RE2, new net.minecraft.core.BlockPos(this.x, this.y, this.z), ""));
         }).bounds(this.leftPos + 99, this.topPos + 88, 39, 22).build(builder -> new Button(builder) {
            @Override
            public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
               guiGraphics.blit(ResourceLocation.parse("dyairdrop:textures/screens/atlas/imagebutton_f1.png"), this.getX(), this.getY(), 0.0F, this.isHoveredOrFocused() ? 22.0F : 0.0F, 39, 22, 39, 44);
            }
         });
      this.addRenderableWidget(this.imagebutton_f1);
      this.imagebutton_check = Button.builder(Component.empty(), e -> {
            PacketDistributor.sendToServer(new PanelActionPayload(6, PanelService.KIND_LETTER_RE2, new net.minecraft.core.BlockPos(this.x, this.y, this.z), ""));
         }).bounds(this.leftPos + 106, this.topPos + 114, 42, 24).build(builder -> new Button(builder) {
            @Override
            public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
               guiGraphics.blit(ResourceLocation.parse("dyairdrop:textures/screens/atlas/imagebutton_check.png"), this.getX(), this.getY(), 0.0F, this.isHoveredOrFocused() ? 24.0F : 0.0F, 42, 24, 42, 48);
            }
         });
      this.addRenderableWidget(this.imagebutton_check);
   }
}
