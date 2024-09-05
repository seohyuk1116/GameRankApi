package com.example.GameRankApi.controller;

import com.example.GameRankApi.entity.UserGameData;
import com.example.GameRankApi.entity.UserScore;
import com.example.GameRankApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{uid}")
    public UserGameData getUserGameData(@PathVariable String uid) {
        return userService.getUserGameData(uid);
    }

    @GetMapping("/{uid}/scores")
    public List<UserScore> getUserScores(@PathVariable String uid) {
        return userService.getUserScores(uid);
    }

    @PostMapping("/score")
    public UserScore saveUserScore(@RequestBody UserScore userScore) {
        return userService.saveUserScore(userScore);
    }
}