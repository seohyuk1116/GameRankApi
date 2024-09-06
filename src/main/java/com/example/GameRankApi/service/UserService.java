package com.example.GameRankApi.service;

import com.example.GameRankApi.entity.UserData;
import com.example.GameRankApi.entity.UserScore;
import com.example.GameRankApi.repository.UserDataRepository;
import com.example.GameRankApi.repository.UserScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDataRepository userDataRepository;
    private final UserScoreRepository userScoreRepository;

    @Transactional
    public UserData registerUser(UserData userData) {
        if (userDataRepository.findById(userData.getUid()).isPresent()) {
            throw new RuntimeException("이미 존재하는 아이디입니다.");
        }

        userData.setCreatedAt(LocalDateTime.now());
        UserData savedUser = userDataRepository.save(userData);

        UserScore userScore = new UserScore(savedUser.getUid());
        userScoreRepository.save(userScore);

        return savedUser;
    }

    public Optional<UserData> getUserByUid(String uid) {
        return userDataRepository.findById(uid);
    }

    public Optional<UserScore> getUserScoreByUid(String uid) {
        return userScoreRepository.findById(uid);
    }

    public UserData authenticateUser(String uid, String password) {
        Optional<UserData> userData = userDataRepository.findById(uid);
        if (userData.isPresent() && userData.get().getUserPassword().equals(password)) {
            return userData.get();
        }
        return null;
    }

    @Transactional
    public void updateUserProfile(String uid, UserData updatedUserData) {
        userDataRepository.findById(uid).ifPresent(userData -> {
            userData.setUserName(updatedUserData.getUserName());
            userData.setUserBirthday(updatedUserData.getUserBirthday());
            userData.setUserComment(updatedUserData.getUserComment());
            userDataRepository.save(userData);
        });
    }
}