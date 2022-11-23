package com.library.springboot.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.springboot.library.service.UserRegService;
import com.library.springboot.library.vo.UserVO;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class IndexController { // 페이지 경로 지정 [ Controller ]

    private final UserRegService reg_service;

    @GetMapping({"", "/"}) // 메인 페이지
    public String indexView() {
        return "index";
    }

    @GetMapping("/mybook") //내 서제 페이지
    public String mybookView() {
        return "mybook";
    }

    @GetMapping("/map") // 구글 맵 페이지
    public String mapView() {
        return "map";
    }

    @GetMapping("/login") // 로그인 페이지
    public String loginView() {
        return "login";
    }

    @GetMapping("/register") // 회원가입 페이지
    public String registerView() {
        return "register";
    }

    @GetMapping("/search") // 검색 페이지
    public String searchView() {
        return "search";
    }

    @GetMapping("/subsearch") // 서브 검색 페이지
    public String subsearchView() {
        return "subsearch";
    }

    /*
    회원가입
     */
    @PostMapping("/signUp")
    public String userRegPass(UserVO userVO) {
        
        // 회원가입 메서드
        reg_service.userReg_service(userVO);

        return "login";
    }

    /*
    아이디 중복 체크
     */
    @GetMapping("/idCheck")
    @ResponseBody
    public int idCheck(@RequestParam("userId") String user_id) {
        
        return reg_service.userIdCheck(user_id);
    }
}