package com.chaosz.tarkovstamina.client;

import com.chaosz.tarkovstamina.TarkovStamina;
import net.minecraft.Util;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;

/**
 * 塔科夫风格体力 HUD
 * <p>
 * 极简：底部细条 + 百分比文字，无面板、无图标、无装饰。
 * 满体力时 3 秒淡出，体力消耗时出现。
 * 条的大小与位置均按屏幕比例计算，切换 GUI 缩放后等比缩放。
 * </p>
 *
 * <p>1.21.1 NeoForge 换掉了整套 HUD 机制：{@code IGuiOverlay} / {@code ForgeGui} 没了，
 * 改成 {@link RegisterGuiLayersEvent} 注册一个 {@code LayeredDraw.Layer}，
 * 渲染签名是 {@code (GuiGraphics, DeltaTracker)} —— 屏幕尺寸从
 * {@code GuiGraphics} 上取，不再由框架作为参数递进来。</p>
 */
@EventBusSubscriber(modid = TarkovStamina.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class StaminaHud {
    private static float displayed = -1.0F;
    private static float opacity;
    private static long fullSince;
    private static long previousFrame;

    private StaminaHud() {
    }

    @SubscribeEvent
    public static void registerOverlay(RegisterGuiLayersEvent event) {
        event.registerAboveAll(
                ResourceLocation.fromNamespaceAndPath(TarkovStamina.MOD_ID, "stamina"),
                StaminaHud::render);
    }

    private static void render(GuiGraphics g, DeltaTracker deltaTracker) {
        Minecraft mc = Minecraft.getInstance();
        if (!ClientStaminaState.received() || mc.player == null || mc.options.hideGui
                || ClientStaminaState.hidden()) {
            return;
        }

        int screenWidth = g.guiWidth();
        int screenHeight = g.guiHeight();

        long now = Util.getMillis();
        float delta = previousFrame == 0L ? 0.016F
                : Mth.clamp((now - previousFrame) / 1000.0F, 0.0F, 0.1F);
        previousFrame = now;

        float target = ClientStaminaState.stamina();
        float max = ClientStaminaState.maximum();
        // 直接使用服务器同步的精确值，不做平滑延迟（保证动画与真实体力一致）
        displayed = target;

        // 只有启用开关时，满体力且无动作才淡出。
        boolean idle = target >= max - 0.01F && ClientStaminaState.cooldown() == 0
                && !ClientStaminaState.sprinting() && !ClientStaminaState.exhausted();
        if (HudConfig.showOnlyWhenChanged() && idle) { if (fullSince == 0L) fullSince = now; }
        else fullSince = 0L;

        float wanted = fullSince != 0L && now - fullSince > 1200L ? 0.0F : 1.0F;
        float fade = wanted > opacity ? 14.0F : 4.5F;
        opacity += (wanted - opacity) * Mth.clamp(delta * fade, 0.0F, 1.0F);
        if (opacity < 0.015F || max <= 0) return;

        float frac = Mth.clamp(displayed / max, 0, 1);
        boolean exhausted = ClientStaminaState.exhausted();
        int color = exhausted ? 0xE47B70 : frac <= 0.25F ? 0xE0B965 : HudConfig.barColor();
        float pulse = exhausted ? 0.65F + 0.35F * (float) Math.sin(now / 100.0) : 1.0F;
        float a = opacity * pulse;

        // 尺寸随屏幕缩放等比变化
        int bw = HudConfig.width(screenWidth);
        int bh = HudConfig.height(screenHeight);

        // 位置：默认左上锚点（红框位置），加上玩家可调的偏移（按屏幕比例缩放）
        int x = HudConfig.anchorX(screenWidth) + HudPositionConfig.offsetX(screenWidth);
        int y = HudConfig.anchorY(screenHeight) + HudPositionConfig.offsetY(screenHeight);

        // 进度条
        g.fill(x - 1, y - 1, x + bw + 1, y + bh + 1, argb(a * 0.6F, 0x000000));
        g.fill(x, y, x + bw, y + bh, argb(a * 0.25F, 0xFFFFFF));
        int fill = Math.round(bw * frac);
        if (fill > 0) {
            g.fill(x, y, x + fill, y + bh, argb(a, color));
            g.fill(x, y, x + fill, y + 1, argb(a * 0.7F, mix(color, 0xFFFFFF, 0.7F)));
        }

        // 百分比文字
        String pct = Math.round(frac * 100) + "%";
        g.drawString(Minecraft.getInstance().font, pct, x + bw + (int)Math.round(bw * 0.05), y - 1,
                argb(a * 0.9F, 0xD7DEDC), false);

        // 标签（体力耗尽时显示）
        if (exhausted) {
            String label = Component.translatable("hud.tarkov_stamina.exhausted").getString();
            g.drawString(Minecraft.getInstance().font, label,
                    x + (bw - Minecraft.getInstance().font.width(label)) / 2,
                    y - (int)Math.round(bh * 3.0), argb(a * 0.8F, 0xFFE0625A), false);
        }
    }

    private static int mix(int rgb, int w, float r) {
        return ((int) (((rgb >> 16) & 0xFF) * r + ((w >> 16) & 0xFF) * (1 - r)) << 16)
             | ((int) (((rgb >> 8) & 0xFF) * r + ((w >> 8) & 0xFF) * (1 - r)) << 8)
             | (int) ((rgb & 0xFF) * r + (w & 0xFF) * (1 - r));
    }

    private static int argb(float a, int rgb) {
        int alpha = Mth.clamp(Math.round(a * 255.0F), 0, 255);
        return (alpha << 24) | (rgb & 0xFFFFFF);
    }
}
