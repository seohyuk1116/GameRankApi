// UserScoreRepository.java
package com.example.GameRankApi.repository;

import com.example.GameRankApi.entity.UserScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserScoreRepository extends JpaRepository<UserScore, Long> {
    UserScore findByUid(String uid);
}
