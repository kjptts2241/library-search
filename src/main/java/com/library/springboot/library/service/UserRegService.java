package com.library.springboot.library.service;


import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.library.springboot.library.dao.repository.UserRepository;
import com.library.springboot.library.dto.UserDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserRegService {

    private final UserRepository userRepository;
    
    /*
    회원가입
     */
    public void userReg_service(UserDto userDto) {

        // 비밀번호 암호화(sha256)
        String encryPassword = UserSha256.encrypt(userDto.getUserPw());
        userDto.setUserPw(encryPassword);

        userRepository.save(userDto.toEntity());
    }

    /*
    중복 아이디 체크
     */
    public int userIdCheck(String userId) {

        return userRepository.checkOverId(userId);
    }
}
