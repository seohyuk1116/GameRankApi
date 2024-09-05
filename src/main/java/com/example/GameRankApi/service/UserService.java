package com.example.GameRankApi.service;

import com.example.GameRankApi.entity.UserGameData;
import com.example.GameRankApi.entity.UserScore;
import com.example.GameRankApi.repository.UserGameDataRepository;
import com.example.GameRankApi.repository.UserScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserGameDataRepository userGameDataRepository;

    @Autowired
    private UserScoreRepository userScoreRepository;

    public UserGameData getUserGameData(String uid) {
        return userGameDataRepository.findByUid(uid);
    }

    public List<UserScore> getUserScores(String uid) {
        return userScoreRepository.findByUidOrderByDateDesc(uid);
    }

    public UserGameData saveUserGameData(UserGameData userGameData) {
        return userGameDataRepository.save(userGameData);
    }

    public UserScore saveUserScore(UserScore userScore) {
        return userScoreRepository.save(userScore);
    }
}