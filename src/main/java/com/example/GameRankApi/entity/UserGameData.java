package com.example.GameRankApi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "userGameData")
public class UserGameData {

    // Getters and setters
    @Id
    private String uid;
    private String userName;
    private String userPassword;
    private String userComment;

}
