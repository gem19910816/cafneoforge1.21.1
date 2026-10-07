package net.gem19910816.dyairdrop.client.gui;

import net.gem19910816.dyairdrop.core.Vars;

import net.gem19910816.dyairdrop.network.payload.PanelActionPayload;
import net.gem19910816.dyairdrop.panel.PanelService;
import net.gem19910816.dyairdrop.network.DyairdropModVariables;
import net.gem19910816.dyairdrop.core.GameModes;
import net.gem19910816.dyairdrop.world.inventory.PannelMenu;
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

    // 每帧缓存一次展示状态（见 refreshPanelState），避免逐帧重复读玩家数据与方块实体 NBT
    private boolean submitted;
    private String digitResult = "";
    private String enteredPassword = "";
    private String blockKey = "";
    private double keyTicking;
    private long elapsedTicks;

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
        refreshPanelState();
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.passwordPanel.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    /**
     * 每帧只读一次玩家数据与方块实体的密码，避免原实现「每帧调用 15 个 procedure、
     * 每个都 getData/getPersistentData 一遍」的重复开销。
     */
    private void refreshPanelState() {
        DyairdropModVariables.PlayerVariables vars = Vars.of(this.entity);
        this.submitted = vars.showlight == 1.0;
        this.digitResult = this.submitted ? vars.pw : "";
        this.enteredPassword = vars.password;
        this.keyTicking = vars.keyticking;
        this.blockKey = "";
        if (this.world.getBlockEntity(new BlockPos(this.x, this.y, this.z)) instanceof net.minecraft.world.level.block.entity.BlockEntity be) {
            this.blockKey = be.getPersistentData().getString("key");
        }
        this.elapsedTicks = (long) (this.world.getDayTime() - this.keyTicking);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        guiGraphics.blit(TEXTURE, this.leftPos, this.topPos, 0.0F, 0.0F, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
        for (int i = 0; i < 6; i++) {
            if (isDigitCorrect(i)) {
                guiGraphics.blit(TEXTURE_CORRECT, this.leftPos + 25 + i * 14, this.topPos + 39, 0.0F, 0.0F, 8, 8, 8, 8);
            } else if (isDigitWrong(i)) {
                guiGraphics.blit(TEXTURE_WRONG, this.leftPos + 25 + i * 14, this.topPos + 39, 0.0F, 0.0F, 8, 8, 8, 8);
            }
        }
        guiGraphics.blit(TEXTURE_TITLE, this.leftPos + 17, this.topPos + 15, 0.0F, 0.0F, 167, 22, 167, 22);
    }

    private boolean isDigitCorrect(int index) {
        return this.submitted && index < this.digitResult.length() && this.digitResult.charAt(index) == '1';
    }

    private boolean isDigitWrong(int index) {
        return this.submitted && index < this.digitResult.length() && this.digitResult.charAt(index) == '0';
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
        if (!this.submitted || this.digitResult.length() != 6) {
            return;
        }
        if (this.elapsedTicks < 41) {
            guiGraphics.drawString(this.font, Component.translatable("gui.dyairdrop.panel.label_processing"), 124, 22, -1, false);
        } else if (this.enteredPassword.equals(this.blockKey)) {
            guiGraphics.drawString(this.font, Component.translatable("gui.dyairdrop.panel.label_correct"), 125, 23, -13382656, false);
        } else {
            guiGraphics.drawString(this.font, Component.translatable("gui.dyairdrop.panel.label_denied"), 129, 22, -3407872, false);
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

        // OP 三个按钮：仅创造模式可见
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
                    if (GameModes.isCreative(this.entity)) {
                        send(action, this.passwordPanel.getValue());
                    }
                })
                .bounds(this.leftPos + 136, this.topPos + offsetY, 54, 20)
                .build(builder -> new Button(builder) {
                    @Override
                    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
                        if (GameModes.isCreative(PannelScreen.this.entity)) {
                            super.renderWidget(guiGraphics, mouseX, mouseY, partialTicks);
                        }
                    }
                }));
    }
}
