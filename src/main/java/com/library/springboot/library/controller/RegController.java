package com.library.springboot.library.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.springboot.library.service.UserMailSendService;
import com.library.springboot.library.service.UserRegService;
import com.library.springboot.library.service.UserSha256;
import com.library.springboot.library.vo.UserVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class RegController {
    
	private UserMailSendService mailsender;
    private final UserRegService reg_service;

    /*
    회원가입
     */
    @PostMapping("/signUp")
    public String userRegPass(UserVO userVO, Model model, HttpServletRequest request) {
        
        
		// 비밀번호 암호화(sha256)
		String encryPassword = UserSha256.encrypt(userVO.getUser_pw());
		userVO.setUser_pw(encryPassword);

        // 회원가입 메서드
        reg_service.userReg_service(userVO);

        // 인증 메일 보내기 메서드
		mailsender.mailSendWithUserKey(userVO.getUser_email(), userVO.getUser_id(), request);

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

    @GetMapping("/key_alter")
    public String key_alterConfirm(@RequestParam("user_id") String user_id, @RequestParam("user_key") String key) {
        
        mailsender.alter_userKey_service(user_id, key);
        
        return "regSuccess";
    }
}
