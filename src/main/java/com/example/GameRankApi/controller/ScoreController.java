package com.example.GameRankApi.controller;

import com.example.GameRankApi.entity.UserScore;
import com.example.GameRankApi.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/scores")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @GetMapping("/{gameName}")
    public ResponseEntity<List<UserScore>> getGameScores(@PathVariable String gameName) {
        List<UserScore> scores = scoreService.getGameScores(gameName);
        return ResponseEntity.ok(scores);
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitScore(@RequestBody Map<String, String> scoreData) {
        String uid = scoreData.get("uid");
        String gameName = scoreData.get("gameName");
        String score = scoreData.get("score");

        try {
            UserScore updatedScore = scoreService.updateUserScore(uid, gameName, score);
            return ResponseEntity.ok(Map.of("success", true, "message", "점수가 업데이트되었습니다.", "updatedScore", updatedScore));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "점수 업데이트에 실패했습니다: " + e.getMessage()));
        }
    }

    @GetMapping("/user/{uid}")
    public ResponseEntity<?> getUserScores(@PathVariable String uid) {
        try {
            UserScore userScore = scoreService.getUserScoreByUid(uid);
            return ResponseEntity.ok(userScore);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "사용자 점수를 가져오는데 실패했습니다: " + e.getMessage()));
        }
    }
}