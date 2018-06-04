
package com.mugloar.game;

public class Game {
    private Integer gameId;
    private Knight knight;

    public Integer getGameId() {
        return gameId;
    }

    public Game setGameId(Integer gameId) {
        this.gameId = gameId;
        return this;
    }

    public Knight getKnight() {
        return knight;
    }

    public Game setKnight(Knight knight) {
        this.knight = knight;
        return this;
    }

}
