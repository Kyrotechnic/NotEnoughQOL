package me.oringo.oringoclient.utils;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;

import java.util.HashMap;

public class ModUtils {
    public static boolean isModLoaded(String modid)
    {
        return Loader.isModLoaded(modid);
    }

    public static void loadPresentMods()
    {
        skyhanni = isModLoaded("skyhanni");
    }

    public static boolean skyhanni;
}
