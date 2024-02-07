package me.oringo.oringoclient.qolfeatures.module.impl.render;

import me.oringo.oringoclient.qolfeatures.module.Module;
import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
import me.oringo.oringoclient.ui.hud.impl.HudComponent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Hud extends Module {
    public BooleanSetting bps = new BooleanSetting("BPS", true);

    private HudComponent component;
    public Hud(HudComponent component)
    {
        super("Hud", Category.RENDER);

        this.addSettings(
                bps
        );

        this.component = component;
    }

    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Post event) {
        if (event.type != RenderGameOverlayEvent.ElementType.CROSSHAIRS) return;

        int yOffset = 0;


    }
}
