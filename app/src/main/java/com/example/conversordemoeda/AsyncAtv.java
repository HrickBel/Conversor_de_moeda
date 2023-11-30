package com.example.conversordemoeda;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

public class AsyncAtv{

    private static URL  url;
    private static HttpURLConnection HUC;

    public CompletableFuture<String> getCotacao() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                url = new URL("https://economia.awesomeapi.com.br/last/USD");
                HUC = (HttpURLConnection) url.openConnection();
                HUC.setRequestMethod("GET");
                HUC.setRequestProperty("User-Agent", "Mozilla/5.0");
                BufferedReader in = new BufferedReader(new InputStreamReader(HUC.getInputStream(), StandardCharsets.UTF_8));
                String inputLine;
                StringBuilder content = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                in.close();
                HUC.disconnect();

                JSONObject jobj = new JSONObject(content.toString());
                return jobj.getJSONObject("USDBRL").getString("high");
            } catch (Exception e) {
                return e.toString();
            }
        });
    }
}
