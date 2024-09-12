package com.example.GameRankApi.controller;

import com.example.GameRankApi.entity.UserScore;
import com.example.GameRankApi.service.ScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/scores")
public class ScoreController {

    private static final Logger logger = LoggerFactory.getLogger(ScoreController.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private ScoreService scoreService;

    @GetMapping("/rankings/{gameName}")
    public ResponseEntity<?> getGameRankings(@PathVariable String gameName) {
        try {
            logger.info("Fetching rankings for game: {}", gameName);
            List<UserScore> rankings = scoreService.getGameScores(gameName);

            List<Map<String, Object>> sortedRankings = rankings.stream()
                    .map(score -> {
                        Map<String, Object> rankMap = new HashMap<>();
                        rankMap.put("uid", score.getUid());
                        rankMap.put("userName", score.getUserName());
                        rankMap.put("score", getScoreForGame(score, gameName));
                        LocalDateTime scoreTime = getScoreTimeForGame(score, gameName);
                        rankMap.put("scoreTime", scoreTime != null ? scoreTime.format(formatter) : null);
                        return rankMap;
                    })
                    .sorted((a, b) -> {
                        double scoreA = Double.parseDouble((String) a.get("score"));
                        double scoreB = Double.parseDouble((String) b.get("score"));
                        return Double.compare(scoreB, scoreA);
                    })
                    .collect(Collectors.toList());

            for (int i = 0; i < sortedRankings.size(); i++) {
                sortedRankings.get(i).put("rank", i + 1);
            }

            return ResponseEntity.ok(sortedRankings);
        } catch (Exception e) {
            logger.error("Error fetching game rankings for {}: {}", gameName, e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "게임 랭킹을 가져오는데 실패했습니다: " + e.getMessage()));
        }
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitScore(@RequestBody Map<String, String> scoreData) {
        String uid = scoreData.get("uid");
        String gameName = scoreData.get("gameName");
        String score = scoreData.get("score");

        logger.info("Submitting score for user: {}, game: {}, score: {}", uid, gameName, score);

        try {
            UserScore updatedScore = scoreService.updateUserScore(uid, gameName, score);
            if (updatedScore != null) {
                return ResponseEntity.ok(Map.of("success", true, "message", "새로운 최고 점수가 업데이트되었습니다.", "updatedScore", updatedScore, "newHighScore", true));
            } else {
                return ResponseEntity.ok(Map.of("success", true, "message", "현재 점수가 최고 점수보다 낮아 업데이트되지 않았습니다.", "newHighScore", false));
            }
        } catch (Exception e) {
            logger.error("Error updating score: {}", e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "점수 업데이트에 실패했습니다: " + e.getMessage()));
        }
    }

    @GetMapping("/user/{uid}")
    public ResponseEntity<?> getUserScores(@PathVariable String uid) {
        try {
            logger.info("Fetching scores for user: {}", uid);
            UserScore userScore = scoreService.getUserScoreByUid(uid);
            if (userScore == null) {
                logger.warn("User score not found for uid: {}", uid);
                return ResponseEntity.notFound().build();
            }
            logger.info("User score found: {}", userScore);
            return ResponseEntity.ok(userScore);
        } catch (Exception e) {
            logger.error("Error fetching user scores for {}: {}", uid, e.getMessage(), e);
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "사용자 점수를 가져오는데 실패했습니다: " + e.getMessage()));
        }
    }

    private String getScoreForGame(UserScore score, String gameName) {
        return switch (gameName) {
            case "memory_game" -> score.getMemoryGameScore();
            case "snake_game" -> score.getSnakeGameScore();
            case "jump_game" -> score.getJumpGameScore();
            case "bird_game" -> score.getBirdGameScore();
            default -> "0";
        };
    }

    private LocalDateTime getScoreTimeForGame(UserScore score, String gameName) {
        return switch (gameName) {
            case "memory_game" -> score.getMemoryGameScoreTime();
            case "snake_game" -> score.getSnakeGameScoreTime();
            case "jump_game" -> score.getJumpGameScoreTime();
            case "bird_game" -> score.getBirdGameScoreTime();
            default -> null;
        };
    }
}