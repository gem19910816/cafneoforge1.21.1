package net.mcreator.dyairdrop.client.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

/**
 * Replacement for the removed 1.20.1 {@code ImageButton(int x, int y, int w, int h, int u, int v, int vDiff,
 * ResourceLocation texture, int texWidth, int texHeight, Button.OnPress onPress)} constructor.
 * Reproduces the exact 1.20.1 ImageButton blit semantics (normal / hovered / disabled texture rows).
 */
public class TextureButton extends Button {
   private final int u;
   private final int v;
   private final int vDiff;
   private final ResourceLocation texture;
   private final int texWidth;
   private final int texHeight;

   public TextureButton(int x, int y, int w, int h, int u, int v, int vDiff, ResourceLocation texture, int texWidth, int texHeight, Button.OnPress onPress) {
      super(x, y, w, h, Component.empty(), onPress, DEFAULT_NARRATION);
      this.u = u;
      this.v = v;
      this.vDiff = vDiff;
      this.texture = texture;
      this.texWidth = texWidth;
      this.texHeight = texHeight;
   }

   @Override
   protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
      int vOffset = !this.active ? this.vDiff * 2 : (this.isHoveredOrFocused() ? this.vDiff : 0);
      guiGraphics.blit(this.texture, this.getX(), this.getY(), (float)this.u, (float)(this.v + vOffset), this.getWidth(), this.getHeight(), this.texWidth, this.texHeight);
   }
}
