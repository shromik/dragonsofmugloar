package com.mugloar.result;

public class GameLog {
    private GameLogObject logObject;

    public GameLogObject getLogObject(){
        return logObject;
    }

    public GameLog setLogObject(GameLogObject logObject){
        this.logObject = logObject;
        return this;
    }
}
