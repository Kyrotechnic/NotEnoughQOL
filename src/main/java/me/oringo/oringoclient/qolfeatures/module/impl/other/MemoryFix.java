package me.oringo.oringoclient.qolfeatures.module.impl.other;

import me.oringo.oringoclient.events.WorldLoadEvent;
import me.oringo.oringoclient.qolfeatures.module.Module;

public class MemoryFix extends Module {
    public MemoryFix()
    {
        super("Memory Fix", Category.OTHER);

        this.setDescription("Fixes rampant memory usage");

        this.flag = FlagType.SAFE;
    }
}
