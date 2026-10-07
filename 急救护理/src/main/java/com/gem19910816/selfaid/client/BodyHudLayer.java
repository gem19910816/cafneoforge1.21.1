package com.gem19910816.selfaid.client;

import com.gem19910816.selfaid.SelfAidMod;
import com.gem19910816.selfaid.body.BodyPart;
import com.gem19910816.selfaid.registry.SelfAidConfig;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

/**
 * 身体血量 HUD：使用取自 FirstAid（GPL-3.0）的 simple_health.png 贴图，
 * 按 ichttt/FirstAid PlayerModelRenderer 的相同坐标绘制小人身体，
 * 每个部位按血量比例取 6 档颜色状态（满血绿 / >75% / >50% / >25% / 红 / 空灰）。
 */
public final class BodyHudLayer {

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(SelfAidMod.MODID,
            "textures/gui/body_health.png");
    /**
     * FirstAid 是 1.12 代码，drawTexturedModalRect 按 256x256 假想贴图算 UV，
     * 实际 png 是 128x128 —— 所以这里保持它的原始坐标、把贴图尺寸参数填 256（等价于坐标除以 2）。
     */
    private static final int TEX_SIZE = 256;
    private static final int COLOR_STEP = 32;

    /** 与 FirstAid PlayerModelRenderer 相同的部位 UV 布局（32x64 基准）。 */
    private record PartLayout(BodyPart part, int x, int y, int u, int v, int w, int h) {
    }

    private static final PartLayout[] LAYOUT = {
            new PartLayout(BodyPart.HEAD, 8, 0, 8, 0, 16, 16),
            new PartLayout(BodyPart.TORSO, 8, 16, 8, 16, 16, 24),
            new PartLayout(BodyPart.LEFT_ARM, 0, 16, 0, 16, 8, 24),
            new PartLayout(BodyPart.RIGHT_ARM, 24, 16, 24, 16, 8, 24),
            new PartLayout(BodyPart.LEFT_LEG, 8, 40, 8, 40, 8, 16),
            new PartLayout(BodyPart.RIGHT_LEG, 16, 40, 16, 40, 8, 16),
            // 脚部并进同侧腿的状态
            new PartLayout(BodyPart.LEFT_LEG, 8, 56, 8, 56, 8, 8),
            new PartLayout(BodyPart.RIGHT_LEG, 16, 56, 16, 56, 8, 8),
    };

    public static void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer player = minecraft.player;
        if (player == null || player.isCreative() || player.isSpectator()) {
            return;
        }
        if (!SelfAidConfig.SHOW_HUD.get()) {
            return;
        }
        float[] parts = ClientBodyHealthStore.partsOf(player.getUUID());
        float maxHealth = player.getMaxHealth();
        float scale = SelfAidConfig.HUD_SCALE.get().floatValue();
        int x0 = SelfAidConfig.HUD_X.get();
        int y0 = SelfAidConfig.HUD_Y.get();

        for (PartLayout layout : LAYOUT) {
            float ratio = ratio(parts, layout.part(), maxHealth);
            int state = stateOf(ratio);
            int renderW = Math.round(layout.w() * scale);
            int renderH = Math.round(layout.h() * scale);
            guiGraphics.blit(TEXTURE,
                    x0 + Math.round(layout.x() * scale), y0 + Math.round(layout.y() * scale),
                    renderW, renderH,
                    layout.u() + COLOR_STEP * state, layout.v(),
                    layout.w(), layout.h(), TEX_SIZE, TEX_SIZE);
        }
    }

    /** 与 FirstAid getState 相同的 6 档状态：0 满血、1 >75%、2 >50%、3 >25%、4 <=25%、5 空。 */
    private static int stateOf(float ratio) {
        if (ratio <= 0.001F) {
            return 5;
        }
        if (ratio >= 1.0F) {
            return 0;
        }
        if (ratio > 0.75F) {
            return 1;
        }
        if (ratio > 0.5F) {
            return 2;
        }
        if (ratio > 0.25F) {
            return 3;
        }
        return 4;
    }

    private static float ratio(float[] parts, BodyPart part, float maxHealth) {
        float partMax = part.maxHealthFor(maxHealth);
        if (partMax <= 0.0F) {
            return 1.0F;
        }
        return Mth.clamp(parts[part.ordinal()] / partMax, 0.0F, 1.0F);
    }

    private BodyHudLayer() {
    }
}
