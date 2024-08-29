package com.example.GameRankApi.service;

import com.example.GameRankApi.entity.Rank;
import com.example.GameRankApi.repository.RankRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankService {

    private final RankRepository rankRepository;

    public RankService(RankRepository rankRepository) {
        this.rankRepository = rankRepository;
    }

    public List<Rank> getAllRanks() {
        return rankRepository.findAll();
    }

    public Rank saveRank(Rank rank) {
        return rankRepository.save(rank);
    }
}
