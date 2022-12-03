package com.library.springboot.library.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.library.springboot.library.dao.User;
import com.library.springboot.library.dao.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PrincipalDetailService implements UserDetailsService{

    private final UserRepository userRepository;

    // 스프링이 로그인 요청을 가로챌 때, username, password 변수 2개를 가로채는데
    // password 부분 처리는 알아서 한다
    // username이 DB에 있는지를 확인 해준다
    @Override
    public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {
        System.out.println("ㄹㅇㄴ매ㅑㅜ");
        
        User principal = userRepository.loginUser(user_id)
             .orElseThrow(()->{
                return new UsernameNotFoundException(user_id + " 사용자를 찾을 수 없습니다");
             });
        System.out.println("ㄹㅇㄴ매ㅑㅜ" + principal.getUser_name());
        return new PrincipalDetail(principal); // 시큐리티의 세션에 유저 정보가 저장 된다
    }
    
}
