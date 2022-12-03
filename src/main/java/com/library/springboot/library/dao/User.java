package com.library.springboot.library.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.*;

@Entity
@Getter
@NoArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String userId; // 회원 아이디

    private String userPw; // 회원 이름

    private String userName; // 비밀번호

    private String userPhone; // 폰번호

    private int userBirth; // 생년월일

    private String userEmail; // 이메일

    @Builder
    public User(String userId, String userPw, String userName, String userPhone, int userBirth, String userEmail) {
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userBirth = userBirth;
        this.userEmail = userEmail;
    }

}
