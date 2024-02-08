package me.oringo.oringoclient.utils.bazaar;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.oringo.oringoclient.utils.service.MarketService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class Bazaar extends MarketService {
    public HashMap<String, BazaarItem> itemList = new HashMap<>();
    public Bazaar()
    {

    }

    //https://volcaronitee.pythonanywhere.com/bazaar

    public void update()
    {
        StringBuilder result = new StringBuilder();

        itemList.clear();

        try {
            URL url = new URL("https://volcaronitee.pythonanywhere.com/bazaar");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Content-Type", "application/json");

            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String inputLine;

            while ((inputLine = in.readLine()) != null)
            {
                result.append(inputLine);
            }

            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        JsonObject object = new JsonParser().parse(result.toString()).getAsJsonObject();

        Set<Map.Entry<String, JsonElement>> array = object.get("items").getAsJsonObject().entrySet();

        for (Map.Entry<String, JsonElement> item : array)
        {
            BazaarItem bzItem = new BazaarItem();
            JsonArray obj = item.getValue().getAsJsonArray();

            bzItem.id = item.getKey();
            bzItem.instasell = obj.get(0).getAsInt();
            bzItem.instabuy = obj.get(1).getAsInt();


            itemList.put(item.getKey(), bzItem);
        }
    }
}
