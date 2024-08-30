package com.example.GameRankApi.service;

import com.example.GameRankApi.entity.UserGameData;
import com.example.GameRankApi.entity.UserScore;
import com.example.GameRankApi.repository.UserGameDataRepository;
import com.example.GameRankApi.repository.UserScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameRankService {

    @Autowired
    private UserGameDataRepository userGameDataRepository;

    @Autowired
    private UserScoreRepository userScoreRepository;

    public UserGameData getUserByUid(String uid) {
        Optional<UserGameData> user = Optional.ofNullable(userGameDataRepository.findByUid(uid));  // findByUid 메서드 호출
        return user.orElse(null);  // Optional에서 값이 없으면 null 반환
    }

    public List<UserScore> getUserScoresByUid(String uid) {
        return (List<UserScore>) userScoreRepository.findByUid(uid);
    }

    // 다른 서비스 메서드들...
}
