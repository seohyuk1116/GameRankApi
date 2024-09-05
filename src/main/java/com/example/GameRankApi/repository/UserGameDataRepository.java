package com.example.GameRankApi.repository;

import com.example.GameRankApi.entity.UserGameData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGameDataRepository extends JpaRepository<UserGameData, String> {
    UserGameData findByUid(String uid);
}