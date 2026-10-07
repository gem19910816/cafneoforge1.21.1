package net.mcreator.dyairdrop.client.gui;

import net.gem19910816.dyairdrop.network.payload.PanelActionPayload;
import net.gem19910816.dyairdrop.panel.PanelService;
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
import net.mcreator.dyairdrop.world.inventory.PannelMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;

/**
 * 数字密码面板（0~9 + √）的客户端屏幕。
 *
 * <p>重构点：
 * <ul>
 *   <li>按钮**只发动作包**，不再在本地调用服务端 procedure（旧实现在 {@code sendToServer(...)} 之后紧跟
 *       {@code handleButtonAction(...)}，等于客户端与服务端各跑一遍，是冲突与状态回弹的根源）；</li>
 *   <li>不再用静态 {@code guistate} 传递控件；</li>
 *   <li>ESC 交回原版 {@code Screen} 流程，不再硬编码 {@code key == 256}；</li>
 *   <li>亮灯与标签仍读已同步的玩家数据（{@code pw} / {@code showlight} / {@code keyticking}），视觉时机与原版一致。</li>
 * </ul>
 */
public class PannelScreen extends AbstractContainerScreen<PannelMenu> {

    private static final ResourceLocation TEXTURE = ResourceLocation.parse("dyairdrop:textures/screens/panel.png");
    private static final ResourceLocation TEXTURE_CORRECT = ResourceLocation.parse("dyairdrop:textures/screens/correct.png");
    private static final ResourceLocation TEXTURE_WRONG = ResourceLocation.parse("dyairdrop:textures/screens/wrong.png");
    private static final ResourceLocation TEXTURE_TITLE = ResourceLocation.parse("dyairdrop:textures/screens/test3.png");

    private final Level world;
    private final int x;
    private final int y;
    private final int z;
    private final Player entity;

    private EditBox passwordPanel;

    public PannelScreen(PannelMenu container, Inventory inventory, Component text) {
        super(container, inventory, text);
        this.world = container.world;
        this.x = container.x;
        this.y = container.y;
        this.z = container.z;
        this.entity = container.entity;
        this.imageWidth = 201;
        this.imageHeight = 166;
    }

    private void send(int action, String input) {
        PacketDistributor.sendToServer(new PanelActionPayload(action, PanelService.KIND_DIGIT, new BlockPos(this.x, this.y, this.z), input));
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.passwordPanel.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        guiGraphics.blit(TEXTURE, this.leftPos, this.topPos, 0.0F, 0.0F, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
        for (int i = 0; i < 6; i++) {
            if (isDigitCorrect(i)) {
                guiGraphics.blit(TEXTURE_CORRECT, this.leftPos + 25 + i * 14, this.topPos + 39, 0.0F, 0.0F, 8, 8, 8, 8);
            }
            if (isDigitWrong(i)) {
                guiGraphics.blit(TEXTURE_WRONG, this.leftPos + 25 + i * 14, this.topPos + 39, 0.0F, 0.0F, 8, 8, 8, 8);
            }
        }
        guiGraphics.blit(TEXTURE_TITLE, this.leftPos + 17, this.topPos + 15, 0.0F, 0.0F, 167, 22, 167, 22);
    }

    private boolean isDigitCorrect(int index) {
        return switch (index) {
            case 0 -> C1Procedure.execute(this.entity);
            case 1 -> C2Procedure.execute(this.world, this.entity);
            case 2 -> C3Procedure.execute(this.world, this.entity);
            case 3 -> C4Procedure.execute(this.world, this.entity);
            case 4 -> C5Procedure.execute(this.world, this.entity);
            default -> C6Procedure.execute(this.world, this.entity);
        };
    }

    private boolean isDigitWrong(int index) {
        return switch (index) {
            case 0 -> W1Procedure.execute(this.entity);
            case 1 -> W2Procedure.execute(this.world, this.entity);
            case 2 -> W3Procedure.execute(this.world, this.entity);
            case 3 -> W4Procedure.execute(this.world, this.entity);
            case 4 -> W5Procedure.execute(this.world, this.entity);
            default -> W6Procedure.execute(this.world, this.entity);
        };
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.passwordPanel.isFocused() && this.passwordPanel.keyPressed(keyCode, scanCode, modifiers)) {
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        if (AccessgrantedProcedure.execute(this.world, this.x, this.y, this.z, this.entity)) {
            guiGraphics.drawString(this.font, Component.translatable("gui.dyairdrop.panel.label_correct"), 125, 23, -13382656, false);
        }
        if (AccessdeniedProcedure.execute(this.world, this.x, this.y, this.z, this.entity)) {
            guiGraphics.drawString(this.font, Component.translatable("gui.dyairdrop.panel.label_denied"), 129, 22, -3407872, false);
        }
        if (AccessconfirmingProcedure.execute(this.world, this.entity)) {
            guiGraphics.drawString(this.font, Component.translatable("gui.dyairdrop.panel.label_processing"), 124, 22, -1, false);
        }
    }

    @Override
    public void init() {
        super.init();

        this.passwordPanel = new EditBox(this.font, this.leftPos + 19, this.topPos + 17, 94, 18,
                Component.translatable("gui.dyairdrop.panel.password_panel"));
        this.passwordPanel.setMaxLength(64);
        this.addWidget(this.passwordPanel);

        // 数字键盘：1 2 3 / 4 5 6 / 7 8 9 / × 0 ␣
        addKeypadButton(1, 19, 52, 20);
        addKeypadButton(2, 55, 52, 21);
        addKeypadButton(3, 91, 52, 20);
        addKeypadButton(4, 19, 79, 20);
        addKeypadButton(5, 55, 79, 21);
        addKeypadButton(6, 91, 79, 20);
        addKeypadButton(7, 19, 106, 20);
        addKeypadButton(8, 55, 106, 21);
        addKeypadButton(9, 91, 106, 20);

        this.addRenderableWidget(Button.builder(Component.translatable("gui.dyairdrop.panel.button_x"),
                e -> send(PanelService.ACTION_DELETE, "")).bounds(this.leftPos + 19, this.topPos + 133, 20, 20).build());
        addKeypadButton(0, 55, 133, 21);
        this.addRenderableWidget(Button.builder(Component.translatable("gui.dyairdrop.panel.button_empty"),
                e -> send(PanelService.ACTION_CONFIRM, "")).bounds(this.leftPos + 91, this.topPos + 133, 20, 20).build());

        // OP 三个按钮：仅创造模式可见（沿用 OpshowProcedure 的判定）
        addOpButton("gui.dyairdrop.panel.button_save", 84, PanelService.ACTION_TEST);
        addOpButton("gui.dyairdrop.panel.button_pw", 102, PanelService.ACTION_SET_PASSWORD);
        addOpButton("gui.dyairdrop.panel.button_op", 120, PanelService.ACTION_SET_LOOT);
    }

    private void addKeypadButton(int digit, int offsetX, int offsetY, int width) {
        this.addRenderableWidget(Button.builder(Component.translatable("gui.dyairdrop.panel.button_" + digit),
                        e -> send(digit, ""))
                .bounds(this.leftPos + offsetX, this.topPos + offsetY, width, 20)
                .build());
    }

    private void addOpButton(String translationKey, int offsetY, int action) {
        this.addRenderableWidget(Button.builder(Component.translatable(translationKey), e -> {
                    if (OpshowProcedure.execute(this.entity)) {
                        send(action, this.passwordPanel.getValue());
                    }
                })
                .bounds(this.leftPos + 136, this.topPos + offsetY, 54, 20)
                .build(builder -> new Button(builder) {
                    @Override
                    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
                        if (OpshowProcedure.execute(PannelScreen.this.entity)) {
                            super.renderWidget(guiGraphics, mouseX, mouseY, partialTicks);
                        }
                    }
                }));
    }
}
