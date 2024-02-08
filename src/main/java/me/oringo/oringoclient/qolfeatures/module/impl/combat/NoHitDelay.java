//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\lukes\OneDrive\Desktop\deobfer\1.8.9 MAPPINGS"!

//Decompiled by Procyon!

package me.oringo.oringoclient.qolfeatures.module.impl.combat;

import me.oringo.oringoclient.qolfeatures.module.*;

public class NoHitDelay extends Module
{
    public NoHitDelay() {
        super("No hit delay", 0, Category.OTHER);

        this.setDescription("Removes 1.8 hit delay");

        this.flag = FlagType.SAFE;
    }
}
