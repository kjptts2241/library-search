package com.library.springboot.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class indexController {
    
    @GetMapping({"", "/"})
    public String index() {
        return "index";
    }

    @GetMapping("/mybook")
    public String mybook() {
        return "mybook";
    }

    @GetMapping("/map")
    public String map() {
        return "map";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/search")
    public String search() {
        return "search";
    }
}
