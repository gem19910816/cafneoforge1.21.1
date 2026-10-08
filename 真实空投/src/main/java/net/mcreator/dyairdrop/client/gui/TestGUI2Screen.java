package net.mcreator.dyairdrop.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.HashMap;
import net.mcreator.dyairdrop.network.TestGUI2ButtonMessage;
import net.mcreator.dyairdrop.procedures.AccessconfirmingProcedure;
import net.mcreator.dyairdrop.procedures.AccessdeniedProcedure;
import net.mcreator.dyairdrop.procedures.AccessgrantedProcedure;
import net.mcreator.dyairdrop.procedures.C1Procedure;
import net.mcreator.dyairdrop.procedures.C2Procedure;
import net.mcreator.dyairdrop.procedures.C3Procedure;
import net.mcreator.dyairdrop.procedures.C4Procedure;
import net.mcreator.dyairdrop.procedures.C5Procedure;
import net.mcreator.dyairdrop.procedures.C6Procedure;
import net.mcreator.dyairdrop.procedures.OpshowProcedure;
import net.mcreator.dyairdrop.procedures.W1Procedure;
import net.mcreator.dyairdrop.procedures.W2Procedure;
import net.mcreator.dyairdrop.procedures.W3Procedure;
import net.mcreator.dyairdrop.procedures.W4Procedure;
import net.mcreator.dyairdrop.procedures.W5Procedure;
import net.mcreator.dyairdrop.procedures.W6Procedure;
import net.mcreator.dyairdrop.world.inventory.TestGUI2Menu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class TestGUI2Screen extends AbstractContainerScreen<TestGUI2Menu> {
   private static final HashMap<String, Object> guistate = TestGUI2Menu.guistate;
   private final Level world;
   private final int x;
   private final int y;
   private final int z;
   private final Player entity;
   EditBox password_panel;
   Button button_2;
   Button button_1;
   Button button_3;
   Button button_4;
   Button button_5;
   Button button_6;
   Button button_7;
   Button button_8;
   Button button_9;
   Button button_x;
   Button button_0;
   Button button_empty;
   Button button_op;
   Button button_pw;
   Button button_tpw;
   private static final ResourceLocation texture = ResourceLocation.parse("dyairdrop:textures/screens/test_gui_2.png");

   public TestGUI2Screen(TestGUI2Menu container, Inventory inventory, Component text) {
      super(container, inventory, text);
      this.world = container.world;
      this.x = container.x;
      this.y = container.y;
      this.z = container.z;
      this.entity = container.entity;
      this.imageWidth = 201;
      this.imageHeight = 166;
   }

   public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
      this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
      super.render(guiGraphics, mouseX, mouseY, partialTicks);
      this.password_panel.render(guiGraphics, mouseX, mouseY, partialTicks);
      this.renderTooltip(guiGraphics, mouseX, mouseY);
   }

   protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      guiGraphics.blit(texture, this.leftPos, this.topPos, 0.0F, 0.0F, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
      if (C1Procedure.execute(this.entity)) {
         guiGraphics.blit(ResourceLocation.parse("dyairdrop:textures/screens/correct.png"), this.leftPos + 25, this.topPos + 39, 0.0F, 0.0F, 8, 8, 8, 8);
      }

      if (C2Procedure.execute(this.world, this.entity)) {
         guiGraphics.blit(ResourceLocation.parse("dyairdrop:textures/screens/correct.png"), this.leftPos + 39, this.topPos + 39, 0.0F, 0.0F, 8, 8, 8, 8);
      }

      if (C3Procedure.execute(this.world, this.entity)) {
         guiGraphics.blit(ResourceLocation.parse("dyairdrop:textures/screens/correct.png"), this.leftPos + 53, this.topPos + 39, 0.0F, 0.0F, 8, 8, 8, 8);
      }

      if (C4Procedure.execute(this.world, this.entity)) {
         guiGraphics.blit(ResourceLocation.parse("dyairdrop:textures/screens/correct.png"), this.leftPos + 67, this.topPos + 39, 0.0F, 0.0F, 8, 8, 8, 8);
      }

      if (C5Procedure.execute(this.world, this.entity)) {
         guiGraphics.blit(ResourceLocation.parse("dyairdrop:textures/screens/correct.png"), this.leftPos + 81, this.topPos + 39, 0.0F, 0.0F, 8, 8, 8, 8);
      }

      if (C6Procedure.execute(this.world, this.entity)) {
         guiGraphics.blit(ResourceLocation.parse("dyairdrop:textures/screens/correct.png"), this.leftPos + 95, this.topPos + 39, 0.0F, 0.0F, 8, 8, 8, 8);
      }

      if (W1Procedure.execute(this.entity)) {
         guiGraphics.blit(ResourceLocation.parse("dyairdrop:textures/screens/wrong.png"), this.leftPos + 25, this.topPos + 39, 0.0F, 0.0F, 8, 8, 8, 8);
      }

      if (W2Procedure.execute(this.world, this.entity)) {
         guiGraphics.blit(ResourceLocation.parse("dyairdrop:textures/screens/wrong.png"), this.leftPos + 39, this.topPos + 39, 0.0F, 0.0F, 8, 8, 8, 8);
      }

      if (W3Procedure.execute(this.world, this.entity)) {
         guiGraphics.blit(ResourceLocation.parse("dyairdrop:textures/screens/wrong.png"), this.leftPos + 53, this.topPos + 39, 0.0F, 0.0F, 8, 8, 8, 8);
      }

      if (W4Procedure.execute(this.world, this.entity)) {
         guiGraphics.blit(ResourceLocation.parse("dyairdrop:textures/screens/wrong.png"), this.leftPos + 67, this.topPos + 39, 0.0F, 0.0F, 8, 8, 8, 8);
      }

      if (W5Procedure.execute(this.world, this.entity)) {
         guiGraphics.blit(ResourceLocation.parse("dyairdrop:textures/screens/wrong.png"), this.leftPos + 81, this.topPos + 39, 0.0F, 0.0F, 8, 8, 8, 8);
      }

      if (W6Procedure.execute(this.world, this.entity)) {
         guiGraphics.blit(ResourceLocation.parse("dyairdrop:textures/screens/wrong.png"), this.leftPos + 95, this.topPos + 39, 0.0F, 0.0F, 8, 8, 8, 8);
      }

      guiGraphics.blit(ResourceLocation.parse("dyairdrop:textures/screens/test3.png"), this.leftPos + 17, this.topPos + 15, 0.0F, 0.0F, 167, 22, 167, 22);
      RenderSystem.disableBlend();
   }

   public boolean keyPressed(int key, int b, int c) {
      if (key == 256) {
         this.minecraft.player.closeContainer();
         return true;
      } else {
         return this.password_panel.isFocused() ? this.password_panel.keyPressed(key, b, c) : super.keyPressed(key, b, c);
      }
   }

   public void containerTick() {
      super.containerTick();
   }

   protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
      if (AccessgrantedProcedure.execute(this.world, (double)this.x, (double)this.y, (double)this.z, this.entity)) {
         guiGraphics.drawString(this.font, Component.translatable("gui.dyairdrop.test_gui_2.label_correct"), 125, 23, -13382656, false);
      }

      if (AccessdeniedProcedure.execute(this.world, (double)this.x, (double)this.y, (double)this.z, this.entity)) {
         guiGraphics.drawString(this.font, Component.translatable("gui.dyairdrop.test_gui_2.label_denied"), 129, 22, -3407872, false);
      }

      if (AccessconfirmingProcedure.execute(this.world, this.entity)) {
         guiGraphics.drawString(this.font, Component.translatable("gui.dyairdrop.test_gui_2.label_processing"), 124, 22, -1, false);
      }
   }

   public void onClose() {
      super.onClose();
   }

   public void init() {
      super.init();
      this.password_panel = new EditBox(
         this.font, this.leftPos + 19, this.topPos + 17, 94, 18, Component.translatable("gui.dyairdrop.test_gui_2.password_panel")
      );
      this.password_panel.setMaxLength(32767);
      guistate.put("text:password_panel", this.password_panel);
      this.addWidget(this.password_panel);
      this.button_2 = Button.builder(Component.translatable("gui.dyairdrop.test_gui_2.button_2"), e -> {
         net.neoforged.neoforge.network.PacketDistributor.sendToServer(new TestGUI2ButtonMessage(0, this.x, this.y, this.z, this.password_panel != null ? this.password_panel.getValue() : ""));
         TestGUI2ButtonMessage.handleButtonAction(this.entity, 0, this.x, this.y, this.z);
      }).bounds(this.leftPos + 55, this.topPos + 52, 21, 20).build();
      guistate.put("button:button_2", this.button_2);
      this.addRenderableWidget(this.button_2);
      this.button_1 = Button.builder(Component.translatable("gui.dyairdrop.test_gui_2.button_1"), e -> {
         net.neoforged.neoforge.network.PacketDistributor.sendToServer(new TestGUI2ButtonMessage(1, this.x, this.y, this.z, this.password_panel != null ? this.password_panel.getValue() : ""));
         TestGUI2ButtonMessage.handleButtonAction(this.entity, 1, this.x, this.y, this.z);
      }).bounds(this.leftPos + 19, this.topPos + 52, 20, 20).build();
      guistate.put("button:button_1", this.button_1);
      this.addRenderableWidget(this.button_1);
      this.button_3 = Button.builder(Component.translatable("gui.dyairdrop.test_gui_2.button_3"), e -> {
         net.neoforged.neoforge.network.PacketDistributor.sendToServer(new TestGUI2ButtonMessage(2, this.x, this.y, this.z, this.password_panel != null ? this.password_panel.getValue() : ""));
         TestGUI2ButtonMessage.handleButtonAction(this.entity, 2, this.x, this.y, this.z);
      }).bounds(this.leftPos + 91, this.topPos + 52, 20, 20).build();
      guistate.put("button:button_3", this.button_3);
      this.addRenderableWidget(this.button_3);
      this.button_4 = Button.builder(Component.translatable("gui.dyairdrop.test_gui_2.button_4"), e -> {
         net.neoforged.neoforge.network.PacketDistributor.sendToServer(new TestGUI2ButtonMessage(3, this.x, this.y, this.z, this.password_panel != null ? this.password_panel.getValue() : ""));
         TestGUI2ButtonMessage.handleButtonAction(this.entity, 3, this.x, this.y, this.z);
      }).bounds(this.leftPos + 19, this.topPos + 79, 20, 20).build();
      guistate.put("button:button_4", this.button_4);
      this.addRenderableWidget(this.button_4);
      this.button_5 = Button.builder(Component.translatable("gui.dyairdrop.test_gui_2.button_5"), e -> {
         net.neoforged.neoforge.network.PacketDistributor.sendToServer(new TestGUI2ButtonMessage(4, this.x, this.y, this.z, this.password_panel != null ? this.password_panel.getValue() : ""));
         TestGUI2ButtonMessage.handleButtonAction(this.entity, 4, this.x, this.y, this.z);
      }).bounds(this.leftPos + 55, this.topPos + 79, 21, 20).build();
      guistate.put("button:button_5", this.button_5);
      this.addRenderableWidget(this.button_5);
      this.button_6 = Button.builder(Component.translatable("gui.dyairdrop.test_gui_2.button_6"), e -> {
         net.neoforged.neoforge.network.PacketDistributor.sendToServer(new TestGUI2ButtonMessage(5, this.x, this.y, this.z, this.password_panel != null ? this.password_panel.getValue() : ""));
         TestGUI2ButtonMessage.handleButtonAction(this.entity, 5, this.x, this.y, this.z);
      }).bounds(this.leftPos + 91, this.topPos + 79, 20, 20).build();
      guistate.put("button:button_6", this.button_6);
      this.addRenderableWidget(this.button_6);
      this.button_7 = Button.builder(Component.translatable("gui.dyairdrop.test_gui_2.button_7"), e -> {
         net.neoforged.neoforge.network.PacketDistributor.sendToServer(new TestGUI2ButtonMessage(6, this.x, this.y, this.z, this.password_panel != null ? this.password_panel.getValue() : ""));
         TestGUI2ButtonMessage.handleButtonAction(this.entity, 6, this.x, this.y, this.z);
      }).bounds(this.leftPos + 19, this.topPos + 106, 20, 20).build();
      guistate.put("button:button_7", this.button_7);
      this.addRenderableWidget(this.button_7);
      this.button_8 = Button.builder(Component.translatable("gui.dyairdrop.test_gui_2.button_8"), e -> {
         net.neoforged.neoforge.network.PacketDistributor.sendToServer(new TestGUI2ButtonMessage(7, this.x, this.y, this.z, this.password_panel != null ? this.password_panel.getValue() : ""));
         TestGUI2ButtonMessage.handleButtonAction(this.entity, 7, this.x, this.y, this.z);
      }).bounds(this.leftPos + 55, this.topPos + 106, 21, 20).build();
      guistate.put("button:button_8", this.button_8);
      this.addRenderableWidget(this.button_8);
      this.button_9 = Button.builder(Component.translatable("gui.dyairdrop.test_gui_2.button_9"), e -> {
         net.neoforged.neoforge.network.PacketDistributor.sendToServer(new TestGUI2ButtonMessage(8, this.x, this.y, this.z, this.password_panel != null ? this.password_panel.getValue() : ""));
         TestGUI2ButtonMessage.handleButtonAction(this.entity, 8, this.x, this.y, this.z);
      }).bounds(this.leftPos + 91, this.topPos + 106, 20, 20).build();
      guistate.put("button:button_9", this.button_9);
      this.addRenderableWidget(this.button_9);
      this.button_x = Button.builder(Component.translatable("gui.dyairdrop.test_gui_2.button_x"), e -> {
         net.neoforged.neoforge.network.PacketDistributor.sendToServer(new TestGUI2ButtonMessage(9, this.x, this.y, this.z, this.password_panel != null ? this.password_panel.getValue() : ""));
         TestGUI2ButtonMessage.handleButtonAction(this.entity, 9, this.x, this.y, this.z);
      }).bounds(this.leftPos + 19, this.topPos + 133, 20, 20).build();
      guistate.put("button:button_x", this.button_x);
      this.addRenderableWidget(this.button_x);
      this.button_0 = Button.builder(Component.translatable("gui.dyairdrop.test_gui_2.button_0"), e -> {
         net.neoforged.neoforge.network.PacketDistributor.sendToServer(new TestGUI2ButtonMessage(10, this.x, this.y, this.z, this.password_panel != null ? this.password_panel.getValue() : ""));
         TestGUI2ButtonMessage.handleButtonAction(this.entity, 10, this.x, this.y, this.z);
      }).bounds(this.leftPos + 55, this.topPos + 133, 21, 20).build();
      guistate.put("button:button_0", this.button_0);
      this.addRenderableWidget(this.button_0);
      this.button_empty = Button.builder(Component.translatable("gui.dyairdrop.test_gui_2.button_empty"), e -> {
         net.neoforged.neoforge.network.PacketDistributor.sendToServer(new TestGUI2ButtonMessage(11, this.x, this.y, this.z, this.password_panel != null ? this.password_panel.getValue() : ""));
         TestGUI2ButtonMessage.handleButtonAction(this.entity, 11, this.x, this.y, this.z);
      }).bounds(this.leftPos + 91, this.topPos + 133, 20, 20).build();
      guistate.put("button:button_empty", this.button_empty);
      this.addRenderableWidget(this.button_empty);
      this.button_op = Button.builder(Component.translatable("gui.dyairdrop.test_gui_2.button_op"), e -> {
         if (OpshowProcedure.execute(this.entity)) {
            net.neoforged.neoforge.network.PacketDistributor.sendToServer(new TestGUI2ButtonMessage(12, this.x, this.y, this.z, this.password_panel != null ? this.password_panel.getValue() : ""));
            TestGUI2ButtonMessage.handleButtonAction(this.entity, 12, this.x, this.y, this.z);
         }
      }).bounds(this.leftPos + 154, this.topPos + 120, 27, 20).build(builder -> new Button(builder) {
            public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
               if (OpshowProcedure.execute(TestGUI2Screen.this.entity)) {
                  super.render(guiGraphics, gx, gy, ticks);
               }
            }
         });
      guistate.put("button:button_op", this.button_op);
      this.addRenderableWidget(this.button_op);
      this.button_pw = Button.builder(Component.translatable("gui.dyairdrop.test_gui_2.button_pw"), e -> {
         if (OpshowProcedure.execute(this.entity)) {
            net.neoforged.neoforge.network.PacketDistributor.sendToServer(new TestGUI2ButtonMessage(13, this.x, this.y, this.z, this.password_panel != null ? this.password_panel.getValue() : ""));
            TestGUI2ButtonMessage.handleButtonAction(this.entity, 13, this.x, this.y, this.z);
         }
      }).bounds(this.leftPos + 154, this.topPos + 102, 18, 20).build(builder -> new Button(builder) {
            public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
               if (OpshowProcedure.execute(TestGUI2Screen.this.entity)) {
                  super.render(guiGraphics, gx, gy, ticks);
               }
            }
         });
      guistate.put("button:button_pw", this.button_pw);
      this.addRenderableWidget(this.button_pw);
      this.button_tpw = Button.builder(Component.translatable("gui.dyairdrop.test_gui_2.button_tpw"), e -> {
         if (OpshowProcedure.execute(this.entity)) {
            net.neoforged.neoforge.network.PacketDistributor.sendToServer(new TestGUI2ButtonMessage(14, this.x, this.y, this.z, this.password_panel != null ? this.password_panel.getValue() : ""));
            TestGUI2ButtonMessage.handleButtonAction(this.entity, 14, this.x, this.y, this.z);
         }
      }).bounds(this.leftPos + 154, this.topPos + 84, 19, 20).build(builder -> new Button(builder) {
            public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
               if (OpshowProcedure.execute(TestGUI2Screen.this.entity)) {
                  super.render(guiGraphics, gx, gy, ticks);
               }
            }
         });
      guistate.put("button:button_tpw", this.button_tpw);
      this.addRenderableWidget(this.button_tpw);
   }
}
