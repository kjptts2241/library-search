package com.library.springboot.library.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.library.springboot.library.config.auth.PrincipalDetail;
import com.library.springboot.library.service.UserBookService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class IndexController { // 페이지 경로 지정 [ Controller ]

    private final UserBookService userBookService;

    /*
     * 메인 검색 페이지
     */
    // @AuthenticationPrincipal PrincipalDetail principal // 세션에 저장된 사용자 정보를 매개변수로 들고 올 수 있다
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
    @GetMapping("/user/userbook")
    public String mybookView(Model model, @AuthenticationPrincipal PrincipalDetail principal) { // 세션의 저장된 유저 정보 토대로 도서 리스트를 찾기에 PrincipalDetail의 User정보를 들고온다
        
        model.addAttribute("userBookList", userBookService.UserBookList(principal.getUser()));
        
        return "userbook";
    }
}