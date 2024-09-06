package com.example.GameRankApi.repository;

import com.example.GameRankApi.entity.UserScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserScoreRepository extends JpaRepository<UserScore, String> {
    @Query(value = "SELECT * FROM user_score ORDER BY CAST(memory_game_score AS UNSIGNED) DESC LIMIT 10", nativeQuery = true)
    List<UserScore> findTop10ByMemoryGameScore();

    @Query(value = "SELECT * FROM user_score ORDER BY CAST(snake_game_score AS UNSIGNED) DESC LIMIT 10", nativeQuery = true)
    List<UserScore> findTop10BySnakeGameScore();

    @Query(value = "SELECT * FROM user_score ORDER BY CAST(jump_game_score AS UNSIGNED) DESC LIMIT 10", nativeQuery = true)
    List<UserScore> findTop10ByJumpGameScore();

    @Query(value = "SELECT * FROM user_score ORDER BY CAST(bird_game_score AS UNSIGNED) DESC LIMIT 10", nativeQuery = true)
    List<UserScore> findTop10ByBirdGameScore();
}