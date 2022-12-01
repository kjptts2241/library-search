package com.library.springboot.library.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.springboot.library.service.UserLoginService;
import com.library.springboot.library.service.UserSha256;
import com.library.springboot.library.vo.UserVO;

@Controller
public class LoginController {
    
    @Autowired
	private UserLoginService login_service;

    @PostMapping("/userLogin")
	@ResponseBody
	public int userLoingPass(UserVO userVO, HttpSession httpSession, HttpServletRequest request,
			HttpServletResponse response) {

		// userLogin.jsp에서 아이디기억하기 name값(remember) 가져오기
		String user_check = request.getParameter("remember_userId");

		// 비밀번호 암호화
		String user_pw = userVO.getUser_pw();
		userVO.setUser_pw(UserSha256.encrypt(user_pw));

		// 암호화 확인
		System.out.println("user_pw : " + userVO.getUser_pw());
		// 로그인 메서드
		int result = login_service.userLogin_service(userVO, httpSession, user_check, response);

		// 매장 리스트 (현재 사용하지 않지만 소스를 지우지 않았습니다)
		// login_service.getStoreOption();

		// model.addAttribute("store_list2", login_service.getStoreOption());

		return result;
	}
}
