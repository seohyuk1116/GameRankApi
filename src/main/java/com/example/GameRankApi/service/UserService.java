// UserService.java
package com.example.GameRankApi.service;

import com.example.GameRankApi.entity.UserGameData;
import com.example.GameRankApi.entity.UserScore;
import com.example.GameRankApi.repository.UserGameDataRepository;
import com.example.GameRankApi.repository.UserScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserGameDataRepository userGameDataRepository;

    @Autowired
    private UserScoreRepository userScoreRepository;

    public UserGameData getUserGameData(String uid) {
        return userGameDataRepository.findByUid(uid);
    }

    public UserScore getUserScore(String uid) {
        return userScoreRepository.findByUid(uid);
    }

    // 추가적인 비즈니스 로직 구현
}
