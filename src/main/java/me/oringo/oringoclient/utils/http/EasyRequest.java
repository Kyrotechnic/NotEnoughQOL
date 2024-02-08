package me.oringo.oringoclient.utils.http;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EasyRequest {
    public URL url;
    public HttpsURLConnection conn;
    public List<Object> objectList = new ArrayList<>();
    public EasyRequest(String destination)
    {
        try {
            this.url = new URL(destination);
            this.conn = (HttpsURLConnection) url.openConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        conn.setDoOutput(true);
    }

    public EasyRequest setTimeout(int timeout)
    {
        conn.setConnectTimeout(timeout);

        return this;
    }

    public EasyRequest addRequestProperty(String key, String value)
    {
        conn.addRequestProperty(key, value);

        return this;
    }

    public EasyRequest setRequestMethod(String type)
    {
        try {
            conn.setRequestMethod(type);
        } catch (ProtocolException e) {
            e.printStackTrace();
        }

        return this;
    }

    public EasyRequest addDataToSend(Object obj)
    {
        objectList.add(obj);

        return this;
    }

    public void execute()
    {
        try {
            OutputStream stream = conn.getOutputStream();

            for (Object obj : objectList)
            {
                stream.write(obj.toString().getBytes());
            }

            stream.flush();
            stream.close();
            conn.getInputStream().close();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
