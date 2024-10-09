package com.pasey.peculiardevices.client.screen;

import com.pasey.peculiardevices.PeculiarDevices;
import com.pasey.peculiardevices.menu.VibratoryMillMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import javax.annotation.ParametersAreNonnullByDefault;

public class VibratoryMillMenuScreen extends AbstractContainerScreen<VibratoryMillMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(PeculiarDevices.MODID, "textures/gui/container/vibratory_mill.png");

    public VibratoryMillMenuScreen(VibratoryMillMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);

        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        renderBackground(pGuiGraphics);
        pGuiGraphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);

        renderProgressArrow(pGuiGraphics, this.leftPos + 79, this.topPos + 35, 176, 0);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderBg(pGuiGraphics, pPartialTick, pMouseX, pMouseY);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

    private void renderProgressArrow(GuiGraphics pGuiGraphics, int targetX, int targetY, int sourceX, int sourceY) {
        if(menu.isCrafting()) {
            pGuiGraphics.blit(TEXTURE, targetX, targetY, sourceX, sourceY, menu.getProgressArrowSize(), 17);
        }
    }
}
