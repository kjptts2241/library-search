package com.library.springboot.library.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.springboot.library.dao.User;
import com.library.springboot.library.dao.repository.UserRepository;
import com.library.springboot.library.dto.UserDto;

import lombok.RequiredArgsConstructor;

// import com.library.springboot.library.dao.UserDaoInterface;

@RequiredArgsConstructor
@Service
public class UserLoginService {
    
	private final UserRepository userRepository;

    public int userLogin_service(UserDto userDto, HttpSession httpSession, String user_check, HttpServletResponse response) {

		// 비밀번호 암호화(sha256)
        String encryPassword = UserSha256.encrypt(userDto.getUserPw());
        userDto.setUserPw(encryPassword);

		int result = userRepository.loginUser(userDto.getUserId(), userDto.getUserPw());

		System.out.println("로그인 결과 ( 0 : 회원정보 조회 실패, 1 : 회원정보 조회 성공 ) : " + result);

		// 회원 정보가 없을 시
		if (result == 0) {
			return result;
		}

		// 회원 정보가 존재할 시
		if (result == 1) {

			// 쿠키 체크 검사
			Cookie cookie = new Cookie("user_check", userDto.getUserId());
			if (user_check.equals("true")) {
				response.addCookie(cookie);
				System.out.println("쿠키 아이디저장 O");
				// 쿠키 확인
				System.out.println("Service check" + cookie);
			} else {
				System.out.println("쿠키 아이디저장 X");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}

			// 세션 저장
			User userSession = userRepository.loginUserSession(userDto.getUserId(), userDto.getUserPw());
			httpSession.setAttribute("userSession", userSession);
		}

		return result;
	}
}
