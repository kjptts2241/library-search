package com.library.springboot.library.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.springboot.library.dto.UserDto;
import com.library.springboot.library.service.UserRegService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class RegController {
    
    private final UserRegService reg_service;

    /*
     * 회원가입
     */
    @PostMapping("/auth/signUp")
    public String userRegPass(UserDto userDto) {

        // 회원가입 메서드
        reg_service.userReg_service(userDto);

        return "redirect:login";
    }

    /*
     * 아이디 중복 체크
     */
    @GetMapping("/auth/idCheck")
    @ResponseBody
    public int idCheck(@RequestParam("user_id") String user_id) {
        return reg_service.userIdCheck(user_id);
    }
}
