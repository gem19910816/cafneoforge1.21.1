package com.scarasol.itemrarity.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public final class RarityImageButton extends Button {
    private final ResourceLocation texture;

    public RarityImageButton(int width, int height, ResourceLocation texture, OnPress onPress) {
        super(0, 0, width, height, Component.empty(), onPress, DEFAULT_NARRATION);
        this.texture = texture;
    }

    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        graphics.blit(texture, getX(), getY(), 0, isHoveredOrFocused() ? height : 0,
                width, height, width, height * 2);
    }
}
