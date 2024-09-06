package com.example.GameRankApi.service;

import com.example.GameRankApi.entity.UserScore;
import com.example.GameRankApi.repository.UserScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoreService {

    private final UserScoreRepository userScoreRepository;

    public List<UserScore> getGameScores(String gameName) {
        return switch (gameName) {
            case "memory_game" -> userScoreRepository.findTop10ByMemoryGameScore();
            case "snake_game" -> userScoreRepository.findTop10BySnakeGameScore();
            case "jump_game" -> userScoreRepository.findTop10ByJumpGameScore();
            case "bird_game" -> userScoreRepository.findTop10ByBirdGameScore();
            default -> throw new IllegalArgumentException("Invalid game name: " + gameName);
        };
    }

    @Transactional
    public UserScore updateUserScore(String uid, String gameName, String score) {
        UserScore userScore = userScoreRepository.findById(uid)
                .orElse(new UserScore(uid));

        switch (gameName) {
            case "memory_game":
                userScore.setMemoryGameScore(score);
                break;
            case "snake_game":
                userScore.setSnakeGameScore(score);
                break;
            case "jump_game":
                userScore.setJumpGameScore(score);
                break;
            case "bird_game":
                userScore.setBirdGameScore(score);
                break;
            default:
                throw new IllegalArgumentException("Invalid game name: " + gameName);
        }

        return userScoreRepository.save(userScore);
    }

    public UserScore getUserScoreByUid(String uid) {
        return userScoreRepository.findById(uid)
                .orElseThrow(() -> new RuntimeException("User score not found for uid: " + uid));
    }
}