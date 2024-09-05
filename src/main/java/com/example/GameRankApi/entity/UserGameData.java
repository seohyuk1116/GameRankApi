package com.example.GameRankApi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "userGameData")
public class UserGameData {
    @Id
    private String uid;
    private String userName;
    private String userPassword;
    private String userComment;
}