package com.library.springboot.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class IndexController { // 페이지 경로 지정 [ Controller ]

    /*
     * 메인 검색 페이지
     */
    @GetMapping({"", "/"})
    public String indexView() {
        return "search";
    }

    /*
     * 서브 검색 페이지
     */
    @GetMapping("/auth/subsearch")
    public String subsearchView() {
        return "subsearch";
    }

    /*
     * 로그인 페이지
     */
    @GetMapping("/auth/login")
    public String loginView() {
        return "login";
    }

    /*
     * 회원가입 페이지
     */
    @GetMapping("/auth/register")
    public String registerView() {
        return "register";
    }

    /*
     * 구글 맵 페이지
     */
    @GetMapping("/auth/map")
    public String mapView() {
        return "map";
    }

    /*
     * 내서 서제 페이지
     */
    @GetMapping("/user/mybook")
    public String mybookView() {
        return "mybook";
    }
}