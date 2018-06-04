package com.mugloar.core;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.mugloar.dragons.Dragon;
import com.mugloar.dragons.Dragon_;
import com.mugloar.game.Game;
import com.mugloar.result.GameLog;
import com.mugloar.result.GameResult;
import com.mugloar.result.GameLogObject;
import com.mugloar.result.Logs;
import io.restassured.RestAssured;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;

public class StartGame {
    private Game game;
    private Dragon dragon;
    private Dragon_ dragon_;
    private GameResult gameResult;
    private GameLog log;
    private GameLogObject gameLog;
    private String weather;

    private GameLogger logger;
    public static List<String> linststring;

    Gson builder = new GsonBuilder().setPrettyPrinting().create();

    StartGame(){
        dragon = new Dragon();
        dragon_ = new Dragon_();
        gameLog = new GameLogObject();
        log = new GameLog();
        logger = new GameLogger();
    }

    public static void main(String[] args){
        StartGame startGame = new StartGame();

        /*startGame
                .getGame()
                .sendDragon()
                .logger.logGame(
                        startGame.log.setLogObject(
                                startGame.gameLog
                                    .setDragon(startGame.dragon)
                                    .setGame(startGame.game)
                                    .setGameResult(startGame.gameResult)

                        )
        );*/

        int nrOfGames;

        if (args.length == 0) nrOfGames = 1;
        else nrOfGames = Integer.valueOf(args[0]);

        for (int i = 0; i < nrOfGames; i++){
            startGame.getGame().getWeather();

            switch (startGame.weather){
                case "NMR":
                    startGame.createDragon(
                            startGame.game.getKnight().getAttack()-1,
                            startGame.game.getKnight().getArmor()+2,
                            startGame.game.getKnight().getAgility()-1,
                            startGame.game.getKnight().getEndurance()
                    );
                    break;
                case "T E":
                    startGame.createDragon(5,5,5,5);
                    break;
                case "HVA":
                    startGame.createDragon(10,10, 0, 0);
                    break;
                case "FUNDEFINEDG":
                    startGame.createDragon(0,15, 5, 0);
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

    private Dragon_ createDragon(int scale, int claw, int wing, int fire){
        dragon_.setClawSharpness(claw)
                .setFireBreath(fire)
                .setScaleThickness(scale)
                .setWingStrength(wing);

        dragon.setDragon(dragon_);

        return dragon_;
    }

    private StartGame getGame(){
        RestAssured.baseURI = "http://www.dragonsofmugloar.com/api/game";
        game = get("/").as(Game.class);

        System.out.println(builder.toJson(game));
        return this;
    }

    private StartGame sendDragon(){
        RestAssured.baseURI = "http://www.dragonsofmugloar.com/api/game";
        String json = builder.toJson(dragon_);
        System.out.println(json);
        gameResult =  given()
                    .body(json)
                .when()
                    .put(game.getGameId()+"/solution")
                .as(GameResult.class);

        System.out.println(gameResult.getStatus()+"\n"+gameResult.getMessage());

        return this;
    }

    private StartGame getWeather(){
        RestAssured.baseURI = "http://www.dragonsofmugloar.com/weather/api/report/";
        weather = given()
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

        JsonObject jsonObject = (JsonObject) obj;
        linststring = new ArrayList<String>();
        JsonArray msg = (JsonArray) jsonObject.get("logs");

        float victories = ((JsonObject) obj).get("victories").getAsFloat();
        float totalGames = ((JsonObject) obj).get("totalGames").getAsFloat();
        float percentage = (victories/totalGames)*100.0f;

        System.out.println("Victories: "+victories);
        System.out.println("Total games played: "+totalGames);
        System.out.println("Victory %: "+ percentage +"%");
        return this;
    }


}
