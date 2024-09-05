package com.example.GameRankApi.repository;

import com.example.GameRankApi.entity.UserScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserScoreRepository extends JpaRepository<UserScore, Long> {
    List<UserScore> findByUid(String uid);
    List<UserScore> findByUidOrderByDateDesc(String uid);
}