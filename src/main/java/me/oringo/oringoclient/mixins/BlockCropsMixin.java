//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\lukes\OneDrive\Desktop\deobfer\1.8.9 MAPPINGS"!

//Decompiled by Procyon!

package me.oringo.oringoclient.mixins;

import me.oringo.oringoclient.OringoClient;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.*;
import net.minecraft.block.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(value = { BlockCrops.class }, priority = 9999)
public abstract class BlockCropsMixin extends BlockBush
{
    @Override
    public AxisAlignedBB getSelectedBoundingBox(World worldIn, BlockPos pos) {
        Block block = Minecraft.getMinecraft().theWorld.getBlockState(pos).getBlock();
        if (block.equals(Blocks.brown_mushroom) || block.equals(Blocks.red_mushroom)) {
            setMushroomBoundbox(worldIn, block);
        }
        return super.getSelectedBoundingBox(worldIn, pos);
    }

    @Override
    public MovingObjectPosition collisionRayTrace(World worldIn, BlockPos pos, Vec3 start, Vec3 end) {
        Block block = Minecraft.getMinecraft().theWorld.getBlockState(pos).getBlock();
        if (block.equals(Blocks.brown_mushroom) || block.equals(Blocks.red_mushroom)) {
            setMushroomBoundbox(worldIn, block);
        }
        return super.collisionRayTrace(worldIn, pos, start, end);
    }

    public void setMushroomBoundbox(World worldIn, Block block)
    {
        if (OringoClient.gardenHelper.isToggled() && OringoClient.gardenHelper.fixMushroom.isEnabled())
            block.setBlockBounds(0, 0, 0, 1, 1, 1);
    }
}
