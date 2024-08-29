package com.example.GameRankApi.controller;

import com.example.GameRankApi.model.GameRanking;
import com.example.GameRankApi.service.GameRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameRankController {

    private final GameRankService gameRankService;

    @Autowired
    public GameRankController(GameRankService gameRankService) {
        this.gameRankService = gameRankService;
    }

    @GetMapping("/rankings")
    public List<GameRanking> getGameRankings() {
        return gameRankService.getAllGameRankings();
    }
}
