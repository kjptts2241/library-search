package com.library.springboot.library.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.springboot.library.dao.UserDaoInterface;
import com.library.springboot.library.vo.UserVO;

@Service
public class UserLoginService {
    
    @Autowired
	private SqlSessionTemplate userSqlSession;

	private UserDaoInterface userDao;

    public int userLogin_service(UserVO userVO, HttpSession httpSession, String user_check,
			HttpServletResponse response) {

		System.out.println("UserLoginService // 로그인 객체 확인 userVO : " + userVO);
		String user_id = userVO.getUser_id();
		String user_pw = userVO.getUser_pw();

		userDao = userSqlSession.getMapper(UserDaoInterface.class);
		UserVO vo = userDao.loginUser(user_id);
//		StoreVO storeVO = userDao.getUserStoreVO(store_id);

		System.out.println("UserLoginService // 로그인 객체 확인 vo : " + vo);

		// 로그인 결과값
		int result = 0;

		// 회원 정보가 없을 시
		if (vo == null) {
			result = 0;
			return result;
		}

		// 인증 안 했을 경우 인증하란 메세지 발생
		// String y = "Y";
		// if (!(vo.getUser_key().equals(y))) {
		// 	result = -2;
		// 	return result;
		// }

		// 입력한 아이디와 스토어id값을 통해 정보가 존재 할 경우
		if (vo != null) {
			// 아이디,비번,스토어id가 모두 같은경우
			System.out.println("1단계");
			if (vo.getUser_id().equals(user_id) && vo.getUser_pw().equals(user_pw)) {
				System.out.println("2단계");
				// 쿠키 체크 검사
				Cookie cookie = new Cookie("user_check", user_id);
				if (user_check.equals("true")) {
					response.addCookie(cookie);
					System.out.println("3단계-쿠키 아이디저장 O");
					// 쿠키 확인
					// System.out.println("Service check" + cookie);
				} else {
					System.out.println("3단계-쿠키 아이디저장 X");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}

				System.out.println("3단계-로그인단계");
				// 세션 저장하기 전에 비밀번호 가리기
				vo.setUser_pw("");

				// 세션에 vo 객체 저장
				httpSession.setAttribute("userSession", vo);
				System.out.println("회원아이디 세션 userSession : " + httpSession.getAttribute("userSession"));

				result = 1;

				// 중복로그인 start
				
				// 접속자 아이디를 세션에 담는다.
				// httpSession.setAttribute("loginId", userVO.getUser_id());

				// // 이미 접속한 아이디인지 체크한다.
				// loginManager.printloginUsers(); // 접속자 리스트
				// if (loginManager.isUsing(userVO.getUser_id())) {
				// 	result = -3;
				// 	System.out.println("@@@@@@@@@@@@@@@@@@@[중복로그인 발생]@@@@@@@@@@@@@@@@@@");
				// } else {
				// 	loginManager.setSession(httpSession, userVO.getUser_id());
				// }

				// 중복로그인 end
			}
		}

		return result;
	}
}
