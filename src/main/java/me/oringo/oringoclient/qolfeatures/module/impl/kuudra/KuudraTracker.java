package me.oringo.oringoclient.qolfeatures.module.impl.kuudra;

import me.oringo.oringoclient.qolfeatures.module.Module;

public class KuudraTracker extends Module {
    public KuudraTracker()
    {
        super("Kuudra Tracker", Category.KUUDRA);

        this.setDescription("Tracks kuudra profit and run times");

        this.flag = FlagType.SAFE;
    }
}
