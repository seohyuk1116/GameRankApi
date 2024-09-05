package com.example.GameRankApi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "userScore")
public class UserScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    private String uid;
    private String userName;
    private String memoryGame;
    private String snakeGame;
    private String jumpGame;
    private String birdGame;

    @Column(name = "score_date")
    private LocalDateTime date;

    @PrePersist
    protected void onCreate() {
        date = LocalDateTime.now();
    }
}