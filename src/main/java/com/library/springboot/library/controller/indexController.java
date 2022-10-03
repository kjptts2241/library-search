package com.library.springboot.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import groovyjarjarpicocli.CommandLine.Model;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class indexController {
    
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
}
