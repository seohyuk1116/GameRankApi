// UserScore.java
package com.example.GameRankApi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "userScore")
public class UserScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    private String uid;
    private String userName;

    @Column(name = "memory_game")
    private String memoryGame;

    @Column(name = "snake_game")
    private String snakeGame;

    @Column(name = "jump_game")
    private String jumpGame;

    @Column(name = "bird_game")
    private String birdGame;

    // Getters and Setters
    // ...
}
