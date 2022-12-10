package com.library.springboot.library.service;


import java.sql.SQLException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.springboot.library.dao.RoleType;
import com.library.springboot.library.dao.repository.UserRepository;
import com.library.springboot.library.dto.UserDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserRegService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encode;
    
    /*
    회원가입
     */
    @Transactional
    public void userReg_service(UserDto userDto) {

        // 비밀번호 암호화(sha256)
        String encPassword = encode.encode(userDto.getPassword());
        userDto.setPassword(encPassword);
        
        // 권한 추가
        userDto.setRole(RoleType.USER);

        userRepository.save(userDto.toEntity());
    }

    /*
    중복 아이디 체크
     */
    @Transactional
    public int userIdCheck(String username) {

        return userRepository.checkOverId(username);
    }
}
