package com.example.GameRankApi.controller;

import com.example.GameRankApi.entity.UserData;
import com.example.GameRankApi.entity.UserScore;
import com.example.GameRankApi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @GetMapping("/{uid}")
    public ResponseEntity<UserData> getUserData(@PathVariable String uid) {
        logger.info("Fetching user data for UID: {}", uid);
        return userService.getUserByUid(uid)
                .map(userData -> {
                    logger.info("User data found for UID: {}", uid);
                    return ResponseEntity.ok(userData);
                })
                .orElseGet(() -> {
                    logger.warn("User data not found for UID: {}", uid);
                    return ResponseEntity.notFound().build();
                });
    }

    @GetMapping("/{uid}/scores")
    public ResponseEntity<UserScore> getUserScores(@PathVariable String uid) {
        logger.info("Fetching user scores for UID: {}", uid);
        return userService.getUserScoreByUid(uid)
                .map(userScore -> {
                    logger.info("User scores found for UID: {}", uid);
                    return ResponseEntity.ok(userScore);
                })
                .orElseGet(() -> {
                    logger.warn("User scores not found for UID: {}", uid);
                    return ResponseEntity.notFound().build();
                });
    }

    //유저 아이디 이름 중복 확인
    @GetMapping("/check-duplicate")
    public ResponseEntity<?> checkDuplicate(@RequestParam String field, @RequestParam String value) {
        boolean isDuplicate = userService.checkDuplicate(field, value);
        return ResponseEntity.ok(Map.of("isDuplicate", isDuplicate));
    }

    //패스워드 인코딩 여부 확인해서 로그인할때
    @GetMapping("/check-password-encoding")
    public ResponseEntity<?> checkPasswordEncoding(@RequestParam String uid) {
        boolean isEncoded = userService.isPasswordEncoded(uid);
        return ResponseEntity.ok(Map.of("isEncoded", isEncoded));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserData userData) {
        logger.info("Received registration request for user: {}", userData.getUid());
        try {
            UserData savedUser = userService.registerUser(userData);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "회원가입이 완료되었습니다.");
            response.put("createdAt", savedUser.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            logger.info("User registered successfully: {}", savedUser.getUid());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Failed to register user: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "회원가입에 실패했습니다: " + e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginData) {
        String uid = loginData.get("uid");
        String userPassword = loginData.get("userPassword");
        logger.info("Login attempt for UID: {}", uid);

        UserData user = userService.authenticateUser(uid, userPassword);
        if (user != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("user", Map.of(
                    "uid", user.getUid(),
                    "userName", user.getUserName(),
                    "userBirthday", user.getUserBirthday(),
                    "userComment", user.getUserComment(),
                    "createdAt", user.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            ));
            logger.info("Login successful for UID: {}", uid);
            return ResponseEntity.ok(response);
        } else {
            logger.warn("Login failed for UID: {}", uid);
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "아이디 또는 비밀번호가 올바르지 않습니다."));
        }
    }

    @PutMapping("/{uid}")
    public ResponseEntity<?> updateUserProfile(@PathVariable String uid, @RequestBody UserData userData) {
        logger.info("Updating profile for UID: {}", uid);
        try {
            userService.updateUserProfile(uid, userData);
            logger.info("Profile updated successfully for UID: {}", uid);
            return ResponseEntity.ok().body(Map.of("success", true, "message", "프로필이 업데이트되었습니다."));
        } catch (Exception e) {
            logger.error("Failed to update profile for UID: {}", uid, e);
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "프로필 업데이트에 실패했습니다: " + e.getMessage()));
        }
    }
}