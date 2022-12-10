package com.library.springboot.library.service;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.springboot.library.config.auth.PrincipalDetail;
import com.library.springboot.library.dao.User;
import com.library.springboot.library.dao.repository.UserBookRepository;
import com.library.springboot.library.dto.UserBookDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserBookService {
    
    private final UserBookRepository userBookRepository;

    /*
     * 도서 저장
     */
    @Transactional
    public void UserBookSave(UserBookDto userBookDto, User user) {

        userBookDto.setUser(user); // 세션의 유저 정보 추가 저장

        userBookRepository.save(userBookDto.toEntity()); // 도서 저장
    }
}
