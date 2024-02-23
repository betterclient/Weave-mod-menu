package io.github.betterclient.modmenu;

import net.weavemc.loader.WeaveLoader;
import net.weavemc.loader.WeaveMod;
import net.weavemc.loader.api.ModInitializer;

import java.lang.reflect.Field;
import java.util.List;

public class ModMenu implements ModInitializer {
    public static List<WeaveMod> mods;

    @Override
    public void preInit() {
        mods = WeaveLoader.INSTANCE.getMods();
    }
}