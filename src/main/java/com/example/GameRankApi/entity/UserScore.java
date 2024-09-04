package com.example.GameRankApi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "userScore")
public class UserScore {

    // Getters and setters
    @Id
    private String uid;
    private String userName;
    private String memoryGame;
    private String snakeGame;
    private String jumpGame;
    private String birdGame;

}
