package com.example.GameRankApi.repository;

import com.example.GameRankApi.entity.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RankRepository extends JpaRepository<Rank, Long> {
    List<Rank> findAllByOrderByMemoryGameAsc();
    List<Rank> findAllByOrderBySnakeGameDesc();
    List<Rank> findAllByOrderByJumpGameDesc();
    List<Rank> findAllByOrderByBirdGameDesc();
}