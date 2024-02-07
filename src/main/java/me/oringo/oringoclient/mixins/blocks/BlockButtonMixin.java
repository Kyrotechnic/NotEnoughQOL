package me.oringo.oringoclient.mixins.blocks;

import me.oringo.oringoclient.OringoClient;
import me.oringo.oringoclient.mixins.BlockMixin;
import me.oringo.oringoclient.qolfeatures.module.impl.dungeons.SecretHitboxes;
import net.minecraft.block.BlockButton;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockButton.class)
public abstract class BlockButtonMixin extends BlockMixin {

    @Inject(method = "updateBlockBounds", at = @At("HEAD"), cancellable = true)
    private void onUpdateBlockBounds(IBlockState state, CallbackInfo ci)
    {
        if (OringoClient.secretHitboxes.isToggled() && OringoClient.secretHitboxes.button.isEnabled())
        {
            EnumFacing enumfacing = state.getValue(BlockButton.FACING);
            boolean flag = state.getValue(BlockButton.POWERED);
            float f2 = (flag ? 1 : 2) / 16.0f;

            switch (enumfacing) {
                case EAST:
                    this.setBlockBounds(0.0f, 0.0f, 0.0f, f2, 1.0f, 1.0f);
                    break;

                case WEST:
                    this.setBlockBounds(1.0f - f2, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                    break;

                case SOUTH:
                    this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, f2);
                    break;

                case NORTH:
                    this.setBlockBounds(0.0f, 0.0f, 1.0f - f2, 1.0f, 1.0f, 1.0f);
                    break;

                case UP:
                    this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.0f + f2, 1.0f);
                    break;

                case DOWN:
                    this.setBlockBounds(0.0f, 1.0f - f2, 0.0f, 1.0f, 1.0f, 1.0f);
                    break;
            }
            ci.cancel();
        }
    }
}
