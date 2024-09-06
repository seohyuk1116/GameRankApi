package com.example.GameRankApi.controller;

import com.example.GameRankApi.entity.UserData;
import com.example.GameRankApi.entity.UserScore;
import com.example.GameRankApi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{uid}")
    public ResponseEntity<UserData> getUserData(@PathVariable String uid) {
        return userService.getUserByUid(uid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{uid}/scores")
    public ResponseEntity<UserScore> getUserScores(@PathVariable String uid) {
        return userService.getUserScoreByUid(uid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserData userData) {
        try {
            UserData savedUser = userService.registerUser(userData);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "회원가입이 완료되었습니다.");
            response.put("createdAt", savedUser.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "회원가입에 실패했습니다: " + e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginData) {
        String uid = loginData.get("uid");
        String userPassword = loginData.get("userPassword");

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
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "아이디 또는 비밀번호가 올바르지 않습니다."));
        }
    }

    @PutMapping("/{uid}")
    public ResponseEntity<?> updateUserProfile(@PathVariable String uid, @RequestBody UserData userData) {
        try {
            userService.updateUserProfile(uid, userData);
            return ResponseEntity.ok().body(Map.of("success", true, "message", "프로필이 업데이트되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "프로필 업데이트에 실패했습니다: " + e.getMessage()));
        }
    }
}