package com.library.springboot.library.dto;

import java.sql.Timestamp;

import com.library.springboot.library.dao.RoleType;
import com.library.springboot.library.dao.User;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    
    private int id; // 아이디
    private String user_id; // 아이디
    private String password; // 비밀번호
    private String user_name; // 이름
    private String user_phone; // 폰번호
    private int user_birth; // 생년월일
    private String user_email; // 이메일
    private RoleType role; // 권한
    private Timestamp create_date; // 생성 날짜

    @Builder
    public UserDto(String user_id, String password, String user_name, String user_phone, int user_birth, String user_email) {
        this.user_id = user_id;
        this.password = password;
        this.user_name = user_name;
        this.user_phone = user_phone;
        this.user_birth = user_birth;
        this.user_email = user_email;
    }

    public User toEntity() {
        return User.builder()
                .user_id(user_id)
                .password(password)
                .user_name(user_name)
                .user_phone(user_phone)
                .user_birth(user_birth)
                .user_email(user_email)
                .role(role)
                .build();
    }
}
