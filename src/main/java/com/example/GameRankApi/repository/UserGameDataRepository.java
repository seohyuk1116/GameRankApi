// UserGameDataRepository.java
package com.example.GameRankApi.repository;

import com.example.GameRankApi.entity.UserGameData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGameDataRepository extends JpaRepository<UserGameData, Long> {
    UserGameData findByUid(String uid);
}
