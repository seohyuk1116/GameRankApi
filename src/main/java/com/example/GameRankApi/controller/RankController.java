package com.example.GameRankApi.controller;

import com.example.GameRankApi.entity.Rank;
import com.example.GameRankApi.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rankings")
public class RankController {

    @Autowired
    private RankService rankService;

    @GetMapping("/{gameName}")
    public ResponseEntity<List<Rank>> getGameRankings(@PathVariable String gameName) {
        try {
            List<Rank> rankings = rankService.getGameRankings(gameName);
            return ResponseEntity.ok(rankings);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/submit-score")
    public ResponseEntity<Rank> submitScore(@RequestBody Rank rank) {
        try {
            Rank savedRank = rankService.saveRank(rank);
            return ResponseEntity.ok(savedRank);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}