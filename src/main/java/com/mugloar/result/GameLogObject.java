package com.mugloar.result;

import com.mugloar.dragons.Dragon;
import com.mugloar.game.Game;

public class GameLogObject {
    private int gameId;
    private String weather;
    private String gameResult;

    public int getGameId() {
        return gameId;
    }

    public GameLogObject setGameId(int gameId) {
        this.gameId = gameId;
        return this;
    }

    public String getWeather() {
        return weather;
    }

    public GameLogObject setWeather(String weather) {
        this.weather = weather;
        return this;
    }

    public String getGameResult() {
        return gameResult;
    }

    public GameLogObject setGameResult(String gameResult) {
        this.gameResult = gameResult;
        return this;
    }

}
