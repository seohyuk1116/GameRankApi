package com.example.GameRankApi.service;

import com.example.GameRankApi.config.PasswordEncoder;
import com.example.GameRankApi.entity.UserData;
import com.example.GameRankApi.entity.UserScore;
import com.example.GameRankApi.repository.UserDataRepository;
import com.example.GameRankApi.repository.UserScoreRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserDataRepository userDataRepository;
    private final UserScoreRepository userScoreRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public UserData registerUser(UserData userData) {
        logger.info("Registering new user: {}", userData.getUid());
        if (userDataRepository.existsByUid(userData.getUid())) {
            logger.warn("User with UID {} already exists", userData.getUid());
            throw new RuntimeException("이미 존재하는 아이디입니다.");
        }
        if (userDataRepository.existsByUserName(userData.getUserName())) {
            logger.warn("User with username {} already exists", userData.getUserName());
            throw new RuntimeException("이미 존재하는 사용자 이름입니다.");
        }

        String hashedPassword = passwordEncoder.encode(userData.getUserPassword());
        userData.setUserPassword(hashedPassword);

        userData.setCreatedAt(LocalDateTime.now());
        UserData savedUser = userDataRepository.save(userData);
        logger.info("User saved successfully: {}", savedUser.getUid());

        UserScore userScore = new UserScore(savedUser.getUid(), savedUser.getUserName());
        userScore.setMemoryGameScore("0");
        userScore.setSnakeGameScore("0");
        userScore.setJumpGameScore("0");
        userScore.setBirdGameScore("0");
        userScoreRepository.save(userScore);
        logger.info("Initial user score created for: {}", savedUser.getUid());

        return savedUser;
    }

    public Optional<UserData> getUserByUid(String uid) {
        return userDataRepository.findByUid(uid);
    }

    public Optional<UserScore> getUserScoreByUid(String uid) {
        return userScoreRepository.findById(uid);
    }

    public UserData authenticateUser(String uid, String password) {
        Optional<UserData> userData = userDataRepository.findByUid(uid);

        if (userData.isPresent()) {
            // 저장된 해시와 입력된 비밀번호를 비교
            if (passwordEncoder.matches(password, userData.get().getUserPassword())) {
                return userData.get();
            }
        }
        return null;
    }

    @Transactional
    public void updateUserProfile(String uid, UserData updatedUserData) {
        userDataRepository.findByUid(uid).ifPresent(userData -> {
            userData.setUserName(updatedUserData.getUserName());
            userData.setUserBirthday(updatedUserData.getUserBirthday());
            userData.setUserComment(updatedUserData.getUserComment());
            userDataRepository.save(userData);
        });
    }
}