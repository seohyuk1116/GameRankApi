package com.example.GameRankApi.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_data")
public class UserData {
    @Id
    @Column(name = "uid", length = 12, nullable = false)
    private String uid;

    @Column(name = "user_name", length = 16, nullable = false, unique = true)
    private String userName;

    @Column(name = "user_password", length = 256, nullable = false)
    private String userPassword;

//    @Column(name = "encrypt_password")
//    private boolean encryptPassword = true;

    @Column(name = "user_birthday", length = 32)
    private String userBirthday;

    @Column(name = "user_comment", length = 32)
    private String userComment;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}