package io.github.betterclient.modmenu;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.weavemc.loader.WeaveMod;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

public class ModMenuUI extends GuiScreen {
    public String getModName(WeaveMod mod) {
        if(mod.getName().contains(System.getProperty("user.home")))
            return mod.getName().replaceAll(System.getProperty("user.home") + File.separator + ".weave" + File.separator + "mods" + File.separator, "");
        else
            return mod.getName();
    }

    @Override
    public void drawScreen(int i, int i1, float v) {
        super.drawDefaultBackground();

        this.fontRendererObj.drawString("Mod List", width / 2 - (this.fontRendererObj.getStringWidth("Mod List") / 2), 10, -1);
        drawRect(0, 50, width, 60, -1);

        int maxHeight = ModMenu.mods.size() * 16 + 70;
        int maxWidth = 20;
        for (WeaveMod mod : ModMenu.mods) {
            if(this.fontRendererObj.getStringWidth(getModName(mod)) / 2 > maxWidth) maxWidth = this.fontRendererObj.getStringWidth(getModName(mod)) / 2;
        }
        maxWidth+=5;
        drawRect(width / 2 - maxWidth, 65, width / 2 + maxWidth, maxHeight, new Color(255, 255, 255, 80).getRGB());

        int currentY = 70;
        for (WeaveMod obj : ModMenu.mods) {
            String name = getModName(obj);
            drawRect(width / 2 - (this.fontRendererObj.getStringWidth(name) / 2) - 2, currentY - 2, width / 2 + 2 +(this.fontRendererObj.getStringWidth(name) / 2), currentY + 12, new Color(0, 0, 0, 84).getRGB());
            this.fontRendererObj.drawString(name, width / 2 - (this.fontRendererObj.getStringWidth(name) / 2), currentY, -1);
            currentY+=16;
        }

        super.drawScreen(i, i1, v);
    }

    @Override
    public void initGui() {
        if(Desktop.getDesktop().isSupported(Desktop.Action.BROWSE_FILE_DIR))
            this.buttonList.add(new GuiButton(69420, 10, height - 30, (this.fontRendererObj.getStringWidth("Open Mod Menu")) + 1, 20, "Open Mod Menu"));
        else
            System.out.println("not allowed to open folders, disabling mods folder button");
    }

    @Override
    protected void actionPerformed(GuiButton guiButton) {
        if(guiButton.id == 69420) {
            Desktop.getDesktop().browseFileDirectory(new File(System.getProperty("user.home") + File.separator + ".weave" + File.separator + "mods" + File.separator));
        }

        super.actionPerformed(guiButton);
    }

    public boolean isHovered(int mouseX, int mouseY, int x, int y, int endX, int endY) {
        return (mouseX >= x && mouseY >= y && mouseX < endX && mouseY < endY);
    }
}
