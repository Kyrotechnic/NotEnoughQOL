package me.oringo.oringoclient.mixins.blocks;

import me.oringo.oringoclient.OringoClient;
import me.oringo.oringoclient.mixins.BlockMixin;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(BlockSkull.class)
public abstract class BlockSkullMixin extends BlockMixin {
    @Inject(method = "getCollisionBoundingBox", at = @At("HEAD"), cancellable = true)
    private void onGetCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state, CallbackInfoReturnable<AxisAlignedBB> cir)
    {
        if (OringoClient.secretHitboxes.isToggled() && OringoClient.secretHitboxes.essence.isEnabled())
        {
            switch (worldIn.getBlockState(pos).getValue(BlockSkull.FACING)) {
                default: {
                    this.setBlockBounds(0.25f, 0.0f, 0.25f, 0.75f, 0.5f, 0.75f);
                    break;
                }
                case NORTH: {
                    this.setBlockBounds(0.25f, 0.25f, 0.5f, 0.75f, 0.75f, 1.0f);
                    break;
                }
                case SOUTH: {
                    this.setBlockBounds(0.25f, 0.25f, 0.0f, 0.75f, 0.75f, 0.5f);
                    break;
                }
                case WEST: {
                    this.setBlockBounds(0.5f, 0.25f, 0.25f, 1.0f, 0.75f, 0.75f);
                    break;
                }
                case EAST: {
                    this.setBlockBounds(0.0f, 0.25f, 0.25f, 0.5f, 0.75f, 0.75f);
                }
            }

            AxisAlignedBB collisionBoundingBox = this.getCollisionBoundingBox(worldIn, pos, state);
            this.setBlockBounds(0, 0, 0, 1, 1, 1);
            cir.setReturnValue(collisionBoundingBox);
            cir.cancel();
        }
    }

    @Inject(method = "setBlockBoundsBasedOnState", at = @At("HEAD"), cancellable = true)
    private void onSetBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos, CallbackInfo ci)
    {
        if (OringoClient.secretHitboxes.isToggled() && OringoClient.secretHitboxes.isEssence(pos))
        {
            this.setBlockBounds(0, 0, 0, 1, 1, 1);
            ci.cancel();
        }
    }
}
