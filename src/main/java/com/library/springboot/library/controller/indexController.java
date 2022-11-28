package com.library.springboot.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class IndexController { // 페이지 경로 지정 [ Controller ]

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

    @GetMapping("/regSuccess") // 서브 검색 페이지
    public String regSuccessView() {
        return "regSuccess";
    }
}