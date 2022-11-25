package com.library.springboot.library.vo;

import lombok.Data;

@Data
public class UserVO {
    
    private String user_id; // 회원 아이디
    private String user_name; // 회원 이름
    private String user_pw; // 비밀번호
    private String user_phone; // 폰번호
    private String user_birth; // 생년월일
    private String user_email; // 이메일
    private String user_key; // 인증 번호
}
