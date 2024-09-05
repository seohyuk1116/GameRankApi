package com.example.GameRankApi.service;

import com.example.GameRankApi.entity.Rank;
import com.example.GameRankApi.repository.RankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RankService {

    @Autowired
    private RankRepository rankRepository;

    public List<Rank> getGameRankings(String gameName) {
        switch (gameName) {
            case "memory_game":
                return rankRepository.findAllByOrderByMemoryGameAsc();
            case "snake_game":
                return rankRepository.findAllByOrderBySnakeGameDesc();
            case "jump_game":
                return rankRepository.findAllByOrderByJumpGameDesc();
            case "bird_game":
                return rankRepository.findAllByOrderByBirdGameDesc();
            default:
                throw new IllegalArgumentException("유효하지 않은 게임 이름: " + gameName);
        }
    }

    public Rank saveRank(Rank rank) {
        return rankRepository.save(rank);
    }
}