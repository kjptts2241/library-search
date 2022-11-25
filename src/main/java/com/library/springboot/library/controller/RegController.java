package com.library.springboot.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.springboot.library.service.UserRegService;
import com.library.springboot.library.service.UserSha256;
import com.library.springboot.library.vo.UserVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class RegController {
    
    private final UserRegService reg_service;

    /*
    회원가입
     */
    @PostMapping("/signUp")
    public String userRegPass(UserVO userVO) {
        
        
		// 비밀번호 암호화(sha256)
		String encryPassword = UserSha256.encrypt(userVO.getUser_pw());
		userVO.setUser_pw(encryPassword);

        // 회원가입 메서드
        reg_service.userReg_service(userVO);

        return "redirect:login";
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
