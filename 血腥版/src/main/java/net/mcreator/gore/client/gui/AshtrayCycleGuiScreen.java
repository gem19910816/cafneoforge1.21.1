package net.mcreator.gore.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.HashMap;
import net.mcreator.gore.network.AshtrayCycleGuiButtonMessage;
import net.mcreator.gore.procedures.ButtonDisplayConditionProcedure;
import net.mcreator.gore.world.inventory.AshtrayCycleGuiMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;

public class AshtrayCycleGuiScreen extends AbstractContainerScreen<AshtrayCycleGuiMenu> {
   private static final HashMap<String, Object> guistate = AshtrayCycleGuiMenu.guistate;
   private final Level world;
   private final int x;
   private final int y;
   private final int z;
   private final Player entity;
   Button button_run;

   public AshtrayCycleGuiScreen(AshtrayCycleGuiMenu container, Inventory inventory, Component text) {
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
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      guiGraphics.blit(
         ResourceLocation.parse("gore_edition:textures/screens/ashtray_cycle_gui.png"), this.leftPos + 0, this.topPos + 0, 0.0F, 0.0F, 176, 166, 176, 166
      );
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

   protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
      guiGraphics.drawString(this.font, Component.translatable("gui.gore_edition.ashtray_cycle_gui.label_ashtray_cycle"), 51, 7, -12829636, false);
   }

   public void init() {
      super.init();
      this.button_run = Button.builder(Component.translatable("gui.gore_edition.ashtray_cycle_gui.button_run"), e -> {
            if (ButtonDisplayConditionProcedure.execute(this.world, (double)this.x, (double)this.y, (double)this.z)) {
               PacketDistributor.sendToServer(new AshtrayCycleGuiButtonMessage(0, this.x, this.y, this.z), new CustomPacketPayload[0]);
               AshtrayCycleGuiButtonMessage.handleButtonAction(this.entity, 0, this.x, this.y, this.z);
            }
         })
         .bounds(this.leftPos + 114, this.topPos + 52, 40, 20)
         .build(
            builder -> new Button(builder) {
                  protected void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
                     if (ButtonDisplayConditionProcedure.execute(
                        AshtrayCycleGuiScreen.this.world,
                        (double)AshtrayCycleGuiScreen.this.x,
                        (double)AshtrayCycleGuiScreen.this.y,
                        (double)AshtrayCycleGuiScreen.this.z
                     )) {
                        super.renderWidget(guiGraphics, gx, gy, ticks);
                     }
                  }
               }
         );
      guistate.put("button:button_run", this.button_run);
      this.addRenderableWidget(this.button_run);
   }
}
