package me.oringo.oringoclient.qolfeatures.module.impl.skyblock;

import me.oringo.oringoclient.OringoClient;
import me.oringo.oringoclient.events.PacketReceivedEvent;
import me.oringo.oringoclient.qolfeatures.module.Module;
import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GardenHelper extends Module {
    public BooleanSetting fixMushroom = new BooleanSetting("Mushroom Hitbox", true);
    public BooleanSetting antiDesync = new BooleanSetting("Anti Desync", true);
    public NumberSetting desyncDistance = new NumberSetting("Distance", 3, 1, 10, 0.5);
    public GardenHelper()
    {
        super("Garden Helper", Category.SKYBLOCK);

        this.addSettings(
                fixMushroom,
                antiDesync
        );
    }

    public void desynced()
    {

    }

    @SubscribeEvent
    public void packet(PacketReceivedEvent event)
    {
        if (!isToggled() || OringoClient.mc.theWorld == null || OringoClient.mc.thePlayer == null || !this.antiDesync.isEnabled()) return;

        if (event.packet instanceof S23PacketBlockChange)
        {
            S23PacketBlockChange blockChange = (S23PacketBlockChange) event.packet;

            BlockPos pos = blockChange.getBlockPosition();

            IBlockState oldState = OringoClient.mc.theWorld.getBlockState(pos);
            IBlockState newState = blockChange.getBlockState();

            System.out.println("PREVIOUS STATE >>> " + oldState.getBlock().getRegistryName());

            if (oldState.getBlock() != Blocks.air) return;

            if (newState.getBlock() instanceof BlockCrops)
            {
                BlockCrops crop = (BlockCrops) newState.getBlock();

                int age = newState.getValue(BlockCrops.AGE).intValue();

                if (age > 4)
                {
                    this.desynced();
                }
            }
            else if (newState.getBlock() instanceof BlockBush)
            {
                BlockBush bush = (BlockBush) newState.getBlock();

                if (OringoClient.mc.thePlayer.getDistanceSq(pos) > Math.pow(desyncDistance.getValue(), 2))
                {
                    desynced();
                }
            }
        }
    }
}
