package com.library.springboot.library.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.springboot.library.dto.UserBookDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserBookController {

    /*
     * 도서 저장
     */
    @PostMapping("/user/bookSave")
    public void UserBookSave(UserBookDto userBook) {
         System.out.println("저장한 도서 : " + userBook);
    }
}
