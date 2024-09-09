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
    public UserScore updateUserScore(String uid, String gameName, String score) {
        logger.info("Updating score for user: {}, game: {}, score: {}", uid, gameName, score);
        UserScore userScore = userScoreRepository.findByUid(uid)
                .orElseGet(() -> {
                    UserData userData = userDataRepository.findByUid(uid)
                            .orElseThrow(() -> new RuntimeException("User not found"));
                    return new UserScore(uid, userData.getUserName());
                });

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
                logger.error("Invalid game name: {}", gameName);
                throw new IllegalArgumentException("Invalid game name: " + gameName);
        }

        UserScore savedScore = userScoreRepository.save(userScore);
        logger.info("Score updated successfully for user: {}", uid);
        return savedScore;
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