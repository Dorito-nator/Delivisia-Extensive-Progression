package net.Rockbudy.dermod.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.Rockbudy.dermod.DERMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class TransendanceAnvilScreen extends AbstractContainerScreen<TransendanceAnvilMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(DERMod.MODID, "textures/gui/trancendence_anvil_gui_temp.png");

    public TransendanceAnvilScreen(TransendanceAnvilMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        renderProgressArrow(guiGraphics, x, y);
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            guiGraphics.blit(TEXTURE, x + 85, y + 30, 178, 13, 8, menu.getScaledProgress());
            guiGraphics.blit(TEXTURE, x + 85, y + 106,186, 38, 8, -menu.getScaledProgress());
            guiGraphics.blit(TEXTURE, x + 50, y + 63, 180, 49, menu.getScaledProgress(), 8);
            guiGraphics.blit(TEXTURE, x + 127,y + 65, 203, 49, -menu.getScaledProgress(), 8);
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
