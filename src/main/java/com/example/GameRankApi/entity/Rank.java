package com.example.GameRankApi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity

@Getter
@Setter
@Table(name = "ranks")
public class Rank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userName", nullable = false, length = 16)
    private String userName;

    @Column(name = "memory_game", length = 32)
    private String memoryGame;

    @Column(name = "snake_game", length = 32)
    private String snakeGame;

    @Column(name = "jump_game", length = 32)
    private String jumpGame;

    @Column(name = "bird_game", length = 32)
    private String birdGame;

    @Column(name = "DATE")
    private LocalDateTime date;

    @PrePersist
    protected void onCreate() {
        date = LocalDateTime.now();
    }
}