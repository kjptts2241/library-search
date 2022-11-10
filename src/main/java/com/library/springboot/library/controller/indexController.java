package com.library.springboot.library.controller;

import java.io.IOException;
import java.util.List;

import com.library.springboot.library.dto.LibraryListDto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.library.springboot.library.service.LibraryListService;

import groovyjarjarpicocli.CommandLine.Model;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final LibraryListService libraryListService; // 도서관 서비스

    @GetMapping({"", "/"}) // http://localhost:8080, http://localhost:8080/
    public String indexView() {
        return "index";
    }

    @GetMapping("/mybook")   // http://localhost:8080/mybook
    public String mybookView() {
        return "mybook";
    }

    @GetMapping("/map")  // @PostMapping 전달 
    public String mapView() {
        return "map";
    }

    @GetMapping("/login")
    public String loginView() {
        return "login";
    }

    @GetMapping("/register")
    public String registerView() {
        return "register";
    }

    @GetMapping("/search")
    public String searchView() {
        return "search";
    }

    @GetMapping("/subsearch")
    public String subsearchView() {
        return "subsearch";
    }

    @GetMapping("/LibraryListDto") // LibraryListDto를 불러오기
    public String LibraryListDto(Model model) throws IOException { // 라이브러리 전부 들고오기
        List<LibraryListDto> LibraryListDto = libraryListService.LibraryListSearch(); // libraryListService에 있는 LibraryListSearch 함수를 실행시켜 LibraryListDto를 보내준다
        model.addAttribute("LibraryListDto", LibraryListDto); // LibraryListDto를 subsearch로 보낸다
        return "subsearch";
    }
}
