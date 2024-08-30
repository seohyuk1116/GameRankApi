package com.example.GameRankApi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "userScore")
public class UserScore {

    @Id
    private Long num;
    private String uid;
    private String userName;
    private String memoryGame;
    private String snakeGame;
    private String jumpGame;
    private String birdGame;

    // Getters and setters
    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMemoryGame() {
        return memoryGame;
    }

    public void setMemoryGame(String memoryGame) {
        this.memoryGame = memoryGame;
    }

    public String getSnakeGame() {
        return snakeGame;
    }

    public void setSnakeGame(String snakeGame) {
        this.snakeGame = snakeGame;
    }

    public String getJumpGame() {
        return jumpGame;
    }

    public void setJumpGame(String jumpGame) {
        this.jumpGame = jumpGame;
    }

    public String getBirdGame() {
        return birdGame;
    }

    public void setBirdGame(String birdGame) {
        this.birdGame = birdGame;
    }
}
