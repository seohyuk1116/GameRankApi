package com.example.GameRankApi.controller;

import com.example.GameRankApi.entity.Rank;
import com.example.GameRankApi.service.RankService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ranks")
public class RankController {

    private final RankService rankService;

    public RankController(RankService rankService) {
        this.rankService = rankService;
    }

    @GetMapping
    public List<Rank> getAllRanks() {
        return rankService.getAllRanks();
    }

    @PostMapping
    public Rank saveRank(@RequestBody Rank rank) {
        return rankService.saveRank(rank);
    }
}
