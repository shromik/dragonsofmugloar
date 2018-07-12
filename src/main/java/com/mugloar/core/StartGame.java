package com.mugloar.core;

import com.google.gson.*;
import com.mugloar.dragons.Dragon;
import com.mugloar.dragons.Dragon_;
import com.mugloar.game.Game;
import com.mugloar.result.GameLog;
import com.mugloar.result.GameResult;
import com.mugloar.result.GameLogObject;
import io.restassured.RestAssured;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;

public class StartGame {
    public Game game;
    public Dragon dragon;
    public Dragon_ dragon_;
    public GameResult gameResult;
    public GameLog log;
    public GameLogObject gameLog;
    public String weather;
    public HashMap<String, String> skillMap;

    private GameLogger logger;
    public static List<String> linststring;

    Gson builder = new GsonBuilder().setPrettyPrinting().create();

    public StartGame(){
        dragon = new Dragon();
        dragon_ = new Dragon_();
        gameLog = new GameLogObject();
        log = new GameLog();
        logger = new GameLogger();

        skillMap = new HashMap<>();

        skillMap.put("armor", "claw");
        skillMap.put("attack", "scale");
        skillMap.put("agility", "wing");
        skillMap.put("endurance", "fire");
    }

    public static void main(String[] args){
        StartGame startGame = new StartGame();

        int nrOfGames;

        if (args.length == 0) nrOfGames = 1;
        else nrOfGames = Integer.valueOf(args[0]);

        for (int i = 0; i < nrOfGames; i++){
            startGame.getGame().getWeather();

            switch (startGame.weather){
                case "NMR":
                    startGame.createDragon();
                    break;
                case "T E":
                    startGame.createDragon(5,5,5,5);
                    break;
                case "HVA":
                    startGame.createDragon(5,10, 5, 0);
                    break;
                case "FUNDEFINEDG":
                    startGame.createDragon(5,5, 5, 5);
                    break;
                default:
                    break;
            }

            startGame
                    .sendDragon()
                    .logger.logGame(
                    startGame.log.setLogObject(
                            startGame.gameLog
                                .setGameId(startGame.game.getGameId())
                                .setWeather(startGame.weather)
                                .setGameResult(startGame.gameResult.getStatus())
                    )
            );
        }

        startGame.getGameStats();
    }

    public Dragon createDragon(int scale, int claw, int wing, int fire){
        dragon_.setClawSharpness(claw)
                .setFireBreath(fire)
                .setScaleThickness(scale)
                .setWingStrength(wing);

        dragon.setDragon(dragon_);

        return dragon;
    }

    public Dragon createDragon(){
        Map<String, Integer> map = new HashMap<>();
        map.put("armor", game.getKnight().getArmor());
        map.put("attack", game.getKnight().getAttack());
        map.put("agility", game.getKnight().getAgility());
        map.put("endurance", game.getKnight().getEndurance());

        Map<String, Integer> sortedMap = map.entrySet()
                .stream().sorted(
                        Map.Entry.comparingByValue())
                .collect(
                        Collectors
                                .toMap(
                                        Map.Entry::getKey, Map.Entry::getValue,(oldValue, newValue) -> oldValue, LinkedHashMap::new)
                );

        int count = 0;

        for(Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();

            switch (count) {
                case 0:
                    setDragonSkill(skillMap.get(key),value);
                    break;
                case 1:
                    setDragonSkill(skillMap.get(key), value - 1);
                    break;
                case 2:
                    setDragonSkill(skillMap.get(key), value - 1);
                    break;
                case 3:
                    setDragonSkill(skillMap.get(key), value + 2);
                    break;
                default:
                    break;
            }

            count++;
        }

        return dragon.setDragon(dragon_);
    }

    private StartGame getGame(){
        RestAssured.baseURI = "http://www.dragonsofmugloar.com/api/game";
        game = given()
                .log().all()
                .get("/")
                .as(Game.class);

        System.out.println(builder.toJson(game));

        return this;
    }

    private StartGame sendDragon(){
        RestAssured.baseURI = "http://www.dragonsofmugloar.com/api/game";
        String json = builder.toJson(dragon);
        System.out.println(json);
        gameResult =  given().log().all().contentType("application/json")
                    .body(json)
                .when()
                    .put(game.getGameId()+"/solution")
                .as(GameResult.class);

        System.out.println(gameResult.getStatus()+"\n"+gameResult.getMessage());

        return this;
    }

    private StartGame getWeather(){
        RestAssured.baseURI = "http://www.dragonsofmugloar.com/weather/api/report/";
        weather = given().log().all()
                .get(game.getGameId().toString())
                .xmlPath()
                .getString("report.code");

        System.out.println(weather);
        return this;
    }

    private StartGame getGameStats(){
        JsonParser parser = new JsonParser();
        Object obj = null;
        try {
            obj = parser.parse(new FileReader("log.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        float victories = ((JsonObject) obj).get("victories").getAsFloat();
        float totalGames = ((JsonObject) obj).get("totalGames").getAsFloat();
        float percentage = (victories/totalGames)*100.0f;

        System.out.println("Victories: "+victories);
        System.out.println("Total games played: "+totalGames);
        System.out.println("Victory %: "+ percentage +"%");
        return this;
    }

    private Dragon_ setDragonSkill(String stat, int value){

        switch (stat) {
            case "claw":
                dragon_.setClawSharpness(value);
                break;
            case "scale":
                dragon_.setScaleThickness(value);
                break;
            case "wing":
                dragon_.setWingStrength(value);
                break;
            case "fire":
                dragon_.setFireBreath(value);
                break;
            default:
                break;
        }

        return dragon_;
    }
}
