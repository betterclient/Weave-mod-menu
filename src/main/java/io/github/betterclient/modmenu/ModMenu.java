package io.github.betterclient.modmenu;

import club.maxstats.weave.loader.WeaveLoader;
import club.maxstats.weave.loader.api.ModInitializer;

import java.lang.reflect.Field;
import java.util.List;

public class ModMenu implements ModInitializer {
    public static List<?> mods;

    @Override
    public void init() {
        try {
            Field f = WeaveLoader.class.getDeclaredField("mods");
            f.setAccessible(true);

            Object ff = f.get(WeaveLoader.INSTANCE);

            if(ff instanceof List) mods = (List<?>) f.get(WeaveLoader.INSTANCE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}