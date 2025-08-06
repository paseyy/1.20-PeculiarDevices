package com.pasey.peculiardevices.client.screen;

import com.pasey.peculiardevices.PeculiarDevices;
import com.pasey.peculiardevices.menu.GeoGeneratorMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import javax.annotation.ParametersAreNonnullByDefault;

public class GeoGeneratorScreen extends AbstractContainerScreen<GeoGeneratorMenu> {
    public static final ResourceLocation TEXTURE =
            new ResourceLocation(PeculiarDevices.MODID, "textures/gui/container/geo_generator.png");

    public GeoGeneratorScreen(GeoGeneratorMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);

        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        renderBackground(pGuiGraphics);
        pGuiGraphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);

        renderEnergyBar(pGuiGraphics, this.leftPos + 11, this.topPos + 71, 176, 56);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBg(pGuiGraphics, pPartialTick, pMouseX, pMouseY);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);

        Component energyText =
                Component.literal("Energy: " + menu.getEnergy() + "/" + menu.getMaxEnergy());
        if(isHovering(11, 15, 10, 56, pMouseX, pMouseY)) {
            pGuiGraphics.renderTooltip(font, energyText, pMouseX, pMouseY);
        }
    }

    private void renderEnergyBar(GuiGraphics pGuiGraphics, int targetX, int targetY, int sourceX, int sourceY) {
        if (menu.getEnergy() > 0) {
            int energyBarHeight = (int) (menu.getScaledEnergy() * 56);
            pGuiGraphics.blit(TEXTURE, targetX, targetY - energyBarHeight, sourceX, sourceY - energyBarHeight, 10, energyBarHeight);
        }
    }
}
