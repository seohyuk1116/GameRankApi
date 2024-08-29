// UserController.java
package com.example.GameRankApi.controller;

import com.example.GameRankApi.entity.UserGameData;
import com.example.GameRankApi.entity.UserScore;
import com.example.GameRankApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{uid}")
    public UserGameData getUserGameData(@PathVariable String uid) {
        return userService.getUserGameData(uid);
    }

    @GetMapping("/{uid}/score")
    public UserScore getUserScore(@PathVariable String uid) {
        return userService.getUserScore(uid);
    }

    // 추가적인 API 엔드포인트 구현


}
