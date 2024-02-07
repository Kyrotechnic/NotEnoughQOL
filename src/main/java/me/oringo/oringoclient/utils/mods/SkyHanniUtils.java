package me.oringo.oringoclient.utils.mods;

import me.oringo.oringoclient.utils.ReflectionUtils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

import java.util.HashMap;

public class SkyHanniUtils {
    public static HashMap<Block, Object> CROP_TYPES = new HashMap<>();

    static
    {
        Class<?> cropTypeClass = null;
        try {
            cropTypeClass = Class.forName("at.hannibal2.skyhanni.features.garden.CropType");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        CROP_TYPES.put(Blocks.wheat, ReflectionUtils.getStaticField(cropTypeClass, "WHEAT"));
        CROP_TYPES.put(Blocks.carrots, ReflectionUtils.getStaticField(cropTypeClass, "CARROT"));
        CROP_TYPES.put(Blocks.potatoes, ReflectionUtils.getStaticField(cropTypeClass, "POTATO"));
        CROP_TYPES.put(Blocks.nether_wart, ReflectionUtils.getStaticField(cropTypeClass, "NETHER_WART"));
        CROP_TYPES.put(Blocks.pumpkin, ReflectionUtils.getStaticField(cropTypeClass, "PUMPKIN"));
        CROP_TYPES.put(Blocks.melon_block, ReflectionUtils.getStaticField(cropTypeClass, "MELON"));
        CROP_TYPES.put(Blocks.cocoa, ReflectionUtils.getStaticField(cropTypeClass, "COCOA_BEANS"));
        CROP_TYPES.put(Blocks.reeds, ReflectionUtils.getStaticField(cropTypeClass, "SUGAR_CANE"));
        CROP_TYPES.put(Blocks.cactus, ReflectionUtils.getStaticField(cropTypeClass, "CACTUS"));
        CROP_TYPES.put(Blocks.brown_mushroom, ReflectionUtils.getStaticField(cropTypeClass, "MUSHROOM"));
        CROP_TYPES.put(Blocks.red_mushroom, ReflectionUtils.getStaticField(cropTypeClass, "MUSHROOM"));
    }

    public static Object getCropType(IBlockState state)
    {
        return CROP_TYPES.get(state.getBlock());
    }

    public static Object getClickType(int mouse) throws Exception {
        Class<?> clickTypeClass = Class.forName("at.hannibal2.skyhanni.data.ClickType");

        if (mouse == 0)
        {
            return clickTypeClass.getField("LEFT_CLICK").get(null);
        }

        return clickTypeClass.getField("RIGHT_CLICK").get(null);
    }
}
