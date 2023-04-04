package io.github.betterclient.modmenu;

import net.minecraft.client.gui.GuiScreen;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

public class ModMenuUI extends GuiScreen {
    @Override
    public void drawScreen(int i, int i1, float v) {
        super.drawBackground(0);

        this.fontRendererObj.drawString("Mod List", width / 2 - (this.fontRendererObj.getStringWidth("Mod List") / 2), 10, -1);
        drawRect(0, 50, width, 60, -1);

        int currentY = 70;
        for (Object obj : ModMenu.mods) {
            String name = "This shouldn't happen, error printed in renderer.log";

            try {
                Field f = obj.getClass().getDeclaredField("name");
                f.setAccessible(true);

                name = (String) f.get(obj);

                name = name.substring(0, name.indexOf(".jar")).replace('-', ' ').replace('_', ' ');
            } catch (Exception e) {
                e.printStackTrace();
            }

            drawRect(width / 2 - (this.fontRendererObj.getStringWidth(name) / 2), currentY, width / 2 + (this.fontRendererObj.getStringWidth(name) / 2), currentY + 10, new Color(0x421601).getRGB());
            this.fontRendererObj.drawString(name, width / 2 - (this.fontRendererObj.getStringWidth(name) / 2), currentY, -1);

            currentY+=10;
        }

        String text = "Open Mod Folder";
        drawRect(10, height - 30, 10 + (this.fontRendererObj.getStringWidth(text)) + 1, height - 10, new Color(0x421601).getRGB());
        this.fontRendererObj.drawString(text, 11, height - 25, -1);
    }

    @Override
    protected void mouseReleased(int mx, int my, int button) {
        String text = "Open Mod Folder";
        if(button == 0 && isHovered(mx, my, 10, height - 30, 10 + (this.fontRendererObj.getStringWidth(text)) + 1, height - 10)) {
            try {
                Desktop.getDesktop().open(new File(System.getProperty("user.home") + "\\.lunarclient\\mods\\"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        super.mouseReleased(mx, my, button);
    }

    public boolean isHovered(int mouseX, int mouseY, int x, int y, int endX, int endY) {
        return (mouseX >= x && mouseY >= y && mouseX < endX && mouseY < endY);
    }
}
