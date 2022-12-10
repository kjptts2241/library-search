package com.library.springboot.library.controller;

import java.security.Principal;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.springboot.library.config.auth.PrincipalDetail;
import com.library.springboot.library.dto.UserBookDto;
import com.library.springboot.library.service.UserBookService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserBookController {

    private final UserBookService userBookService;

    /*
     * 도서 저장
     */
    @PostMapping("/user/bookSave")
    public void UserBookSave(UserBookDto userBookDto, @AuthenticationPrincipal PrincipalDetail principal) { // 추가로 세션의 유저 정보 가져오기
        
        userBookService.UserBookSave(userBookDto, principal.getUser());
    }
}
