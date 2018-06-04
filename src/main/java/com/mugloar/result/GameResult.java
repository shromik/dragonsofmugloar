package com.mugloar.result;

public class GameResult {
    private String status;
    private String message;

    public String getStatus(){
        return status;
    }

    public GameResult setStatus(String status){
        this.status = status;
        return this;
    }

    public String getMessage(){
        return message;
    }

    public GameResult setMessage(String message){
        this.message = message;
        return this;
    }

}
