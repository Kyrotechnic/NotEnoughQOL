package me.oringo.oringoclient.mixins.blocks;

import me.oringo.oringoclient.OringoClient;
import me.oringo.oringoclient.mixins.BlockMixin;
import net.minecraft.block.BlockLever;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockLever.class)
public abstract class LeverMixin extends BlockMixin {
    @Inject(method = "setBlockBoundsBasedOnState", at = @At("HEAD"), cancellable = true)
    public void setBounds(IBlockAccess access, BlockPos pos, CallbackInfo ci)
    {
        if (OringoClient.secretHitboxes.lever.isEnabled() && OringoClient.secretHitboxes.isToggled())
        {
            if (pos.getX() >= 58 && pos.getX() <= 62 && pos.getY() >= 133 && pos.getY() <= 136 && pos.getZ() == 142) { return; }

            this.setBlockBounds(0, 0, 0, 1, 1, 1);
            ci.cancel();
        }
    }
}
