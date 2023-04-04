package io.github.betterclient.modmenu.mixins;

import io.github.betterclient.modmenu.ModMenuUI;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(GuiIngameMenu.class)
public class MixinGuiIngameMenu extends GuiScreen {

    @Inject(method = "drawScreen", at = @At("HEAD"))
    public void draw(int mx, int my, float pt, CallbackInfo ci) {
        this.mc.fontRendererObj.drawString("Mods", 11, height - 25, -1);
        Gui.drawRect(10, height - 30, 10 + this.mc.fontRendererObj.getStringWidth("Mods") + 2, height - 10, new Color(0, 0, 0, 84).getRGB());
    }

    @Override
    protected void mouseReleased(int mx, int my, int button) {
        if(button == 0 && mx >= 10 && mx <= (10 + this.fontRendererObj.getStringWidth("Mods") + 2) && my >= (height - 30) && my <= (height - 10)) {
            this.mc.displayGuiScreen(new ModMenuUI());
        }

        super.mouseReleased(mx, my, button);
    }
}
