//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\lukes\OneDrive\Desktop\deobfer\1.8.9 MAPPINGS"!

//Decompiled by Procyon!

package me.oringo.oringoclient.utils.api;

import java.net.*;
import java.io.*;
import com.google.gson.*;

public class HypixelAPI
{
    public static JsonObject getPlayer(final String uuid, final String apiKey) {
        try {
            final JsonObject d = new JsonParser().parse((Reader)new InputStreamReader(new URL(String.format("https://api.hypixel.net/player?uuid=%s&key=%s", uuid, apiKey)).openStream())).getAsJsonObject();
            if (d.get("player") instanceof JsonNull) {
                return null;
            }
            return d.getAsJsonObject("player");
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public static double getSumoWLR(final JsonObject player) {
        try {
            if (player.getAsJsonObject("stats").getAsJsonObject("Duels").get("sumo_duel_wins").getAsInt() == 0) {
                return 0.0;
            }
            if (player.getAsJsonObject("stats").getAsJsonObject("Duels").get("sumo_duel_losses").getAsInt() == 0) {
                return -1.0;
            }
            return player.getAsJsonObject("stats").getAsJsonObject("Duels").get("sumo_duel_wins").getAsInt() / (double)player.getAsJsonObject("stats").getAsJsonObject("Duels").get("sumo_duel_losses").getAsInt();
        }
        catch (Exception e) {
            return -1.0;
        }
    }
    
    public static String getName(final JsonObject player) {
        try {
            return player.get("displayname").getAsString();
        }
        catch (Exception e) {
            return "";
        }
    }
    
    public static int getSumoWins(final JsonObject player) {
        try {
            return player.getAsJsonObject("stats").getAsJsonObject("Duels").get("sumo_duel_wins").getAsInt();
        }
        catch (Exception e) {
            return 10000000;
        }
    }
    
    public static int getSumoLosses(final JsonObject player) {
        try {
            return player.getAsJsonObject("stats").getAsJsonObject("Duels").get("sumo_duel_losses").getAsInt();
        }
        catch (Exception e) {
            return 0;
        }
    }
    
    public static int getSumoStreak(final JsonObject player) {
        try {
            return player.getAsJsonObject("stats").getAsJsonObject("Duels").get("current_sumo_winstreak").getAsInt();
        }
        catch (Exception e) {
            return 0;
        }
    }
}
