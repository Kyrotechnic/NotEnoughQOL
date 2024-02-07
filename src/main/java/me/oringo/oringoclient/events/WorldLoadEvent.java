package me.oringo.oringoclient.events;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraftforge.fml.common.eventhandler.Event;

public class WorldLoadEvent extends Event {
    public WorldClient client;

    public WorldLoadEvent(WorldClient client)
    {
        this.client = client;
    }
}
