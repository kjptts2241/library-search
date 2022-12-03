package com.library.springboot.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class IndexController { // 페이지 경로 지정 [ Controller ]

    /*
     * 메인 페이지
     */
    @GetMapping({"", "/"})
    public String indexView() {
        return "index";
    }

    /*
     * 내서 서제 페이지
     */
    @GetMapping("/mybook")
    public String mybookView() {
        return "mybook";
    }

    /*
     * 구글 맵 페이지
     */
    @GetMapping("/map")
    public String mapView() {
        return "map";
    }

    /*
     * 로그인 페이지
     */
    @GetMapping("/login")
    public String loginView() {
        return "login";
    }

    /*
     * 회원가입 페이지
     */
    @GetMapping("/register")
    public String registerView() {
        return "register";
    }

    /*
     * 검색 페이지
     */
    @GetMapping("/search")
    public String searchView() {
        return "search";
    }

    /*
     * 서브 검색 페이지
     */
    @GetMapping("/subsearch")
    public String subsearchView() {
        return "subsearch";
    }
}