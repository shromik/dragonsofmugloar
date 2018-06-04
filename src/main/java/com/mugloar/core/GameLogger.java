package com.mugloar.core;

import com.google.gson.*;
import com.mugloar.result.GameLog;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.stream.JsonWriter;
import com.mugloar.result.Logs;

public class GameLogger {
    public static String Vinno_Read;
    public static List<String> linststring;

    public void logGame(GameLog newLog){
        Gson builder = new GsonBuilder().setPrettyPrinting().create();
        try {
            JsonParser parser = new JsonParser();
            Object obj = parser.parse(new FileReader("log.json"));

            JsonObject jsonObject = (JsonObject) obj;

            linststring = new ArrayList<String>();
            JsonArray msg = (JsonArray) jsonObject.get("logs");
            Iterator<JsonElement> iterator = msg.iterator();

            String json = builder.toJson(newLog);

            FileWriter file = new FileWriter("log.json", false);
            JsonWriter jw = new JsonWriter(file);
            iterator = msg.iterator();
            Logs logs = new Logs();
            while (iterator.hasNext()) {
                logs.addLog(builder.fromJson(iterator.next().toString(), GameLog.class));
            }
            logs.setTotalGames(((JsonObject) obj).get("totalGames").getAsInt()+1);

            if (newLog.getLogObject().getGameResult().equals("Victory")){
                logs.setVictories(((JsonObject) obj).get("victories").getAsInt()+1);
            } else {
                logs.setVictories(((JsonObject) obj).get("victories").getAsInt());
            }

            logs.addLog(newLog);
            builder.toJson(logs, Logs.class, jw);
            file.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
