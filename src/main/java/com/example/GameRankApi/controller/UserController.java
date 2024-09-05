package com.example.GameRankApi.controller;

import com.example.GameRankApi.entity.UserGameData;
import com.example.GameRankApi.entity.UserScore;
import com.example.GameRankApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{uid}")
    public ResponseEntity<UserGameData> getUserGameData(@PathVariable String uid) {
        UserGameData userData = userService.getUserGameData(uid);
        if (userData != null) {
            return ResponseEntity.ok(userData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{uid}/scores")
    public ResponseEntity<List<UserScore>> getUserScores(@PathVariable String uid) {
        List<UserScore> scores = userService.getUserScores(uid);
        return ResponseEntity.ok(scores);
    }

    @PostMapping("/register")
    public ResponseEntity<UserGameData> registerUser(@RequestBody UserGameData userGameData) {
        UserGameData savedUser = userService.saveUserGameData(userGameData);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/score")
    public ResponseEntity<UserScore> saveUserScore(@RequestBody UserScore userScore) {
        UserScore savedScore = userService.saveUserScore(userScore);
        return ResponseEntity.ok(savedScore);
    }
}