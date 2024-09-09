package com.example.GameRankApi.repository;

import com.example.GameRankApi.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {
    Optional<UserData> findByUid(String uid);
    boolean existsByUid(String uid);
    boolean existsByUserName(String userName);
}