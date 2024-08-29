// UserGameData.java
package com.example.GameRankApi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "userGameData")
public class UserGameData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    private String uid;
    private String userName;
    private String userPassword;
    private String userComment;

    // Getters and Setters
    // ...
}
