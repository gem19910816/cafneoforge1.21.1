package com.chaosz.tarkovstamina.backpack.client;

import com.chaosz.tarkovstamina.backpack.menu.BackpackMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

/** CAF backpack screen. Military backpack keeps the original wide 12x9 layout;
 *  smaller packs use a self-drawn vanilla-style slot texture (no vanilla offset quirks). */
public class BackpackScreen extends AbstractContainerScreen<BackpackMenu> {
    private static final ResourceLocation CONTAINER_TEXTURE =
            ResourceLocation.withDefaultNamespace("textures/gui/container/generic_54.png");

    public BackpackScreen(BackpackMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        int rows = menu.getRows();
        if (menu.getColumns() == BackpackMenu.MILITARY_COLUMNS) {
            this.imageWidth = 238;
            this.imageHeight = 24 + 9 * 18 + 96;
            this.inventoryLabelY = 24 + 9 * 18 + 6;
        } else {
            this.imageWidth = 176;
            // 与原版 generic_54 布局一致: 17px 标题+rows*18 格子 + 96px 玩家背包区
            this.imageHeight = 17 + rows * 18 + 96;
            this.inventoryLabelY = 17 + rows * 18 + 6;
        }
    }

    /**
     * 1.21 起，容器界面的「鼠标悬停提示」不再由基类负责。
     *
     * <p>1.20.1 的 {@code AbstractContainerScreen.render()} 结尾自己会调
     * {@code this.renderTooltip(...)}，子类白拿；<b>1.21 把那一行挪出了基类</b>，
     * 改成由每个具体界面在自己的 {@code render()} 里调 —— 原版的
     * {@code ShulkerBoxScreen}、{@code ContainerScreen}、{@code InventoryScreen}、
     * {@code HopperScreen}…… 无一例外都写着 {@code super.render(...);}
     * 紧跟 {@code this.renderTooltip(...);}，而 {@code AbstractContainerScreen}
     * 里 {@code renderTooltip} 只剩定义、没有任何调用点。</p>
     *
     * <p>漏掉它的表现：物品正常显示、格子高亮也正常，但鼠标悬停上去
     * <b>什么提示都不弹</b>。同一个人打开普通背包有提示、打开这个背包没有，
     * 差别就在这一处。</p>
     */
    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
        super.render(g, mouseX, mouseY, partialTick);
        this.renderTooltip(g, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics g, float partialTicks, int mouseX, int mouseY) {
        int x = this.leftPos;
        int y = this.topPos;

        if (this.menu.getColumns() == BackpackMenu.MILITARY_COLUMNS) {
            // 军用背包: 12x9 宽版，标题栏加高到 24px（原版 17px 偏窄），格子区从 y=24 开始。
            int rows = this.menu.getRows();
            // 标题栏（宽版: 左7 + 中间重复 + 右7）
            blitWideStrip(g, x + 8, y + 7, 0, 24);
            // 背包格子区
            for (int row = 0; row < rows; row++) {
                boolean last = (row == rows - 1);
                blitWideStrip(g, x + 8, y + 24 + row * 18, last ? 107 : 17, 18);
            }
            // 玩家背包区。
            //
            // 【一次画完 96 像素高，别拆成几段】
            //
            // 原版 ContainerScreen 是 `blit(..., 0, 126, 176, 96)` 一次画完的，
            // 源 y 126..221 正好 96 行。上游这里拆成了三段（高 73 + 18 + 7 = 98），
            // 等于把 96 行源纹理塞进 98 行目标里 —— 底图被整体拉长 2 像素，
            // 越往下偏得越多。快捷栏在最底下，于是：
            //   · 底图的格子被推低 2 像素，而玩家槽位的白色框选画在真实槽位
            //     （y+200 / y+258）上 —— 两者对不上，就是你看到的"框选移位"；
            //   · 三段之间的接缝还把源 y=197 那行深色分隔线多画了一次，落进
            //     快捷栏格子内部 —— 就是你看到的那条横穿快捷栏的黑线。
            //
            // 起点 x 用 35 而不是 36：底图里的槽格在源 x=8，要让 src 8 落在真实
            // 玩家槽位 x+43 上，原点必须是 x+35（43-8）。原版相对偏移就是 8。
            int playerX = x + 35;
            int playerY = y + 24 + rows * 18;
            g.blit(CONTAINER_TEXTURE, playerX, playerY, 0, 126, 176, 96, 256, 256);
            return;
        }

        // 小背包：与军用背包完全一致的原版 generic_54 像素，只是行数更少。
        // 原版布局: 标题栏 0..17, 格子区 17..125 (6行18px), 玩家背包区 126..222。
        int rows = this.menu.getRows();
        g.blit(CONTAINER_TEXTURE, x, y, 0, 0, 176, 17, 256, 256);          // 标题栏
        for (int row = 0; row < rows; row++) {
            boolean last = (row == rows - 1);
            // 前 N-1 行用普通行纹理(17..35)，最后一行用末行纹理(107..125) 带下边框
            g.blit(CONTAINER_TEXTURE, x, y + 17 + row * 18, 0, last ? 107 : 17, 176, 18, 256, 256);
        }
        g.blit(CONTAINER_TEXTURE, x, y + 17 + rows * 18, 0, 126, 176, 96, 256, 256);  // 玩家背包区
    }

    private void blitWideStrip(GuiGraphics g, int x, int y, int sourceY, int height) {
        g.blit(CONTAINER_TEXTURE, x, y, 0, sourceY, 7, height, 256, 256);
        g.blit(CONTAINER_TEXTURE, x + 7, y, 7, sourceY, 162, height, 256, 256);
        g.blit(CONTAINER_TEXTURE, x + 169, y, 7, sourceY, 54, height, 256, 256);
        g.blit(CONTAINER_TEXTURE, x + 223, y, 169, sourceY, 7, height, 256, 256);
    }

    @Override
    protected void renderLabels(GuiGraphics g, int mouseX, int mouseY) {
        boolean military = this.menu.getColumns() == BackpackMenu.MILITARY_COLUMNS;
        int titleX = military ? 15 : 8;
        int titleY = military ? 13 : 6;
        g.drawString(this.font, this.title, titleX, titleY, 0x404040, false);
        int inventoryLabelX = military ? 43 : 8;
        g.drawString(this.font, Component.translatable("container.inventory"), inventoryLabelX,
                this.inventoryLabelY, 0x404040, false);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (BackpackClientRegistration.isBackpackKey(keyCode, scanCode)) {
            this.minecraft.setScreen(null);
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
