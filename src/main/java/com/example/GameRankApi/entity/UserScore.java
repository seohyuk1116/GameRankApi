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

    @Column(name = "memory_game_score")
    private String memoryGameScore;

    @Column(name = "snake_game_score")
    private String snakeGameScore;

    @Column(name = "jump_game_score")
    private String jumpGameScore;

    @Column(name = "bird_game_score")
    private String birdGameScore;

    public UserScore(String uid) {
        this.uid = uid;
    }
}