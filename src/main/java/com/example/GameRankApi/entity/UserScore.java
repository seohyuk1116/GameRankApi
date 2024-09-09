package com.example.GameRankApi.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_score")
public class UserScore {
    @Id
    @Column(name = "uid", length = 12, nullable = false)
    private String uid;

    @Column(name = "user_name", length = 16, nullable = false, unique = true)
    private String userName;

    @Column(name = "memory_game_score", length = 32)
    private String memoryGameScore;

    @Column(name = "snake_game_score", length = 32)
    private String snakeGameScore;

    @Column(name = "jump_game_score", length = 32)
    private String jumpGameScore;

    @Column(name = "bird_game_score", length = 32)
    private String birdGameScore;

    public UserScore(String uid, String userName) {
        this.uid = uid;
        this.userName = userName;
        this.memoryGameScore = "0";
        this.snakeGameScore = "0";
        this.jumpGameScore = "0";
        this.birdGameScore = "0";
    }
}