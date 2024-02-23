package io.github.betterclient.modmenu.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(GuiScreen.class)
public class MixinGuiScreen {
    @Shadow protected List<GuiButton> buttonList;

    @Shadow protected Minecraft mc;

    @Shadow protected List<GuiLabel> labelList;

    /**
     * @author betterclient
     * @reason fuck lunar branding
     */
    @Overwrite
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        for (GuiButton guiButton : this.buttonList) {
            guiButton.drawButton(this.mc, mouseX, mouseX);
        }

        for (GuiLabel guiButton : this.labelList) {
            guiButton.drawLabel(this.mc, mouseX, mouseX);
        }
    }
}
