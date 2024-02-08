package me.oringo.oringoclient.qolfeatures.module.impl.dungeons;

import me.oringo.oringoclient.OringoClient;
import me.oringo.oringoclient.qolfeatures.module.Module;
import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.BlockPos;

import java.util.UUID;

public class SecretHitboxes extends Module {
    public BooleanSetting lever = new BooleanSetting("Lever", true);
    public BooleanSetting button = new BooleanSetting("Button", true);
    public BooleanSetting essence = new BooleanSetting("Essence", true);
    public BooleanSetting chests = new BooleanSetting("Chests", true);
    public SecretHitboxes()
    {
        super("Secret Hitboxes", Module.Category.DUNGEONS);

        this.setDescription("Increases hitbox sizes of secrets");

        this.flag = FlagType.SAFE;

        addSettings(
                lever,
                button,
                essence,
                chests
        );
    }

    private long significantBits = UUID.fromString("26bb1a8d-7c66-31c6-82d5-a9c04c94fb02").getMostSignificantBits();

    public boolean isEssence(BlockPos pos)
    {
        return essence.isEnabled() && ((TileEntitySkull) OringoClient.mc.theWorld.getTileEntity(pos)).getPlayerProfile().getId().getMostSignificantBits() == significantBits;
    }
}
