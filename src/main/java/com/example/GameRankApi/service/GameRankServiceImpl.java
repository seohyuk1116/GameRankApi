package com.example.GameRankApi.service;

import com.example.GameRankApi.model.GameRanking;
import com.example.GameRankApi.repository.GameRankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameRankServiceImpl implements GameRankService {

    private final GameRankRepository gameRankRepository;

    @Autowired
    public GameRankServiceImpl(GameRankRepository gameRankRepository) {
        this.gameRankRepository = gameRankRepository;
    }

    @Override
    public List<GameRanking> getAllGameRankings() {
        return gameRankRepository.findAll();
    }
}
