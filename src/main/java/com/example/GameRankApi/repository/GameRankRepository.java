package com.example.GameRankApi.repository;

import com.example.GameRankApi.model.GameRanking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRankRepository extends JpaRepository<GameRanking, Long> {
}
