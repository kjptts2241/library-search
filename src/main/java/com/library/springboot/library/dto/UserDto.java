package com.library.springboot.library.dto;

import com.library.springboot.library.dao.User;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    
    private int id; // 아이디
    private String userId; // 회원 아이디
    private String userPw; // 회원 이름
    private String userName; // 비밀번호
    private String userPhone; // 폰번호
    private int userBirth; // 생년월일
    private String userEmail; // 이메일

    @Builder
    public UserDto(String userId, String userPw, String userName, String userPhone, int userBirth, String userEmail) {
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userBirth = userBirth;
        this.userEmail = userEmail;
    }

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .userPw(userPw)
                .userName(userName)
                .userPhone(userPhone)
                .userBirth(userBirth)
                .userEmail(userEmail)
                .build();
    }
}
