//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\lukes\OneDrive\Desktop\deobfer\1.8.9 MAPPINGS"!

//Decompiled by Procyon!

package me.oringo.oringoclient.qolfeatures;

import me.oringo.oringoclient.utils.EntityUtils;
import net.minecraft.util.*;
import net.minecraftforge.client.event.*;
import me.oringo.oringoclient.*;
import net.minecraft.client.gui.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.client.*;
import javax.swing.*;
import com.google.gson.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.net.*;
import java.io.*;
import java.lang.reflect.*;
import java.nio.charset.StandardCharsets;

public class LoginWithSession
{
    private Session original;
    
    public LoginWithSession() {
        this.original = null;
    }
    
    @SubscribeEvent
    public void onGuiCreate(final GuiScreenEvent.InitGuiEvent.Post event) {
        if (OringoClient.devMode && event.gui instanceof GuiMainMenu) {
            event.buttonList.add(new GuiButton(2137, 5, 5, 100, 20, "Login"));
            event.buttonList.add(new GuiButton(21370, 115, 5, 100, 20, "Restore"));
        }
    }
    
    @SubscribeEvent
    public void onClick(final GuiScreenEvent.ActionPerformedEvent.Post event) throws Exception {
        if (event.gui instanceof GuiMainMenu) {
            if (event.button.id == 2137) {
                if (this.original == null) {
                    this.original = Minecraft.getMinecraft().getSession();
                }
                try {
                    final Field session = Minecraft.class.getDeclaredField("field_71449_j");
                    session.setAccessible(true);

                    String sessionLocal = getClipboard();

                    CloseableHttpClient client = HttpClients.createDefault();

                    HttpGet request = new HttpGet("https://api.minecraftservices.com/minecraft/profile");

                    request.setHeader("Authorization", "Bearer " + sessionLocal);
                    CloseableHttpResponse response = client.execute(request);
                    String json = org.apache.http.util.EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);

                    JsonObject object = new JsonParser().parse(json).getAsJsonObject();

                    String name = object.get("name").getAsString();
                    String uuid = object.get("id").getAsString();

                    session.set(Minecraft.getMinecraft(), new Session(name, uuid, sessionLocal, "mojang"));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (event.button.id == 21370) {
                final Field session = Minecraft.class.getDeclaredField("session");
                session.setAccessible(true);

                if (this.original == null)
                {
                    return;
                }

                session.set(Minecraft.getMinecraft(), this.original);
            }
        }
    }

    public String getClipboard()
    {
        Transferable transferable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents((Object)null);

        if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.stringFlavor))
        {
            try {
                return (String)transferable.getTransferData(DataFlavor.stringFlavor);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return null;
    }
}
