package com.mugloar.result;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class Logs {
    @Expose
    List<GameLog> logs = new ArrayList<>();
    private int totalGames;
    private int victories;

    public int getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(int totalGames) {
        this.totalGames = totalGames;
    }

    public int getVictories() {
        return victories;
    }

    public void setVictories(int victories) {
        this.victories = victories;
    }

    public List<GameLog> getLogs() {
        return logs;
    }

    public void addLog(GameLog log) {
        this.logs.add(log);
    }
}
