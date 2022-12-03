package com.library.springboot.library.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.springboot.library.dao.User;
import com.library.springboot.library.dto.UserDto;
import com.library.springboot.library.service.UserLoginService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class LoginController {
    
	private final UserLoginService login_service;

   /*
    * 로그인
    */
    @PostMapping("/userLogin")
	public int userLoingPass(UserDto userDto, HttpSession httpSession, HttpServletRequest request, HttpServletResponse response) {

		// userLogin.html에서 아이디기억하기 name값(remember) 가져오기
		String user_check = request.getParameter("remember_userId");

		// 로그인 메서드
		int result = login_service.userLogin_service(userDto, httpSession, user_check, response);

		return result;
	}
}
