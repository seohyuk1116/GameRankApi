package com.example.GameRankApi.service;

import com.example.GameRankApi.entity.UserData;
import com.example.GameRankApi.entity.UserScore;
import com.example.GameRankApi.repository.UserDataRepository;
import com.example.GameRankApi.repository.UserScoreRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

import java.util.List;

@Getter
@Setter
@Service
@RequiredArgsConstructor
public class ScoreService {

    private final UserScoreRepository userScoreRepository;
    private final UserDataRepository userDataRepository;
    private static final Logger logger = LoggerFactory.getLogger(ScoreService.class);

    public List<UserScore> getGameScores(String gameName) {
        logger.info("Fetching top 10 scores for game: {}", gameName);
        List<UserScore> scores = switch (gameName) {
            case "memory_game" -> userScoreRepository.findTop10ByMemoryGameScore();
            case "snake_game" -> userScoreRepository.findTop10BySnakeGameScore();
            case "jump_game" -> userScoreRepository.findTop10ByJumpGameScore();
            case "bird_game" -> userScoreRepository.findTop10ByBirdGameScore();
            default -> throw new IllegalArgumentException("Invalid game name: " + gameName);
        };
        logger.info("Found {} scores for game: {}", scores.size(), gameName);
        return scores;
    }

    @Transactional
    public UserScore updateUserScore(String uid, String gameName, String newScore) {
        logger.info("Updating score for user: {}, game: {}, score: {}", uid, gameName, newScore);
        UserScore userScore = userScoreRepository.findByUid(uid)
                .orElseGet(() -> {
                    UserData userData = userDataRepository.findByUid(uid)
                            .orElseThrow(() -> new RuntimeException("User not found"));
                    return new UserScore(uid, userData.getUserName());
                });

        LocalDateTime now = LocalDateTime.now();
        boolean isNewHighScore = false;

        switch (gameName) {
            case "memory_game":
                if (isHigherScore(newScore, userScore.getMemoryGameScore())) {
                    userScore.setMemoryGameScore(newScore);
                    userScore.setMemoryGameScoreTime(now);
                    isNewHighScore = true;
                }
                break;
            case "snake_game":
                if (isHigherScore(newScore, userScore.getSnakeGameScore())) {
                    userScore.setSnakeGameScore(newScore);
                    userScore.setSnakeGameScoreTime(now);
                    isNewHighScore = true;
                }
                break;
            case "jump_game":
                if (isHigherScore(newScore, userScore.getJumpGameScore())) {
                    userScore.setJumpGameScore(newScore);
                    userScore.setJumpGameScoreTime(now);
                    isNewHighScore = true;
                }
                break;
            case "bird_game":
                if (isHigherScore(newScore, userScore.getBirdGameScore())) {
                    userScore.setBirdGameScore(newScore);
                    userScore.setBirdGameScoreTime(now);
                    isNewHighScore = true;
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid game name: " + gameName);
        }

        if (isNewHighScore) {
            UserScore savedScore = userScoreRepository.save(userScore);
            logger.info("New high score updated successfully for user: {}", uid);
            return savedScore;
        } else {
            logger.info("Score not updated as it's not a new high score for user: {}", uid);
            return null;
        }
    }

    private boolean isHigherScore(String newScore, String currentScore) {
        try {
            double newScoreValue = Double.parseDouble(newScore);
            double currentScoreValue = Double.parseDouble(currentScore);
            return newScoreValue > currentScoreValue;
        } catch (NumberFormatException e) {
            logger.error("Error parsing scores: new score = {}, current score = {}", newScore, currentScore);
            return false;
        }
    }

    public UserScore getUserScoreByUid(String uid) {
        logger.info("Fetching score for user: {}", uid);
        UserScore userScore = userScoreRepository.findById(uid).orElse(null);
        if (userScore == null) {
            logger.warn("No score found for user: {}", uid);
        } else {
            logger.info("Score found for user: {}", uid);
        }
        return userScore;
    }
}