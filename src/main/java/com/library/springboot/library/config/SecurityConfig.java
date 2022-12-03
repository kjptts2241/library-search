package com.library.springboot.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.library.springboot.library.config.auth.PrincipalDetailService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration // Bean 등록
@EnableWebSecurity // 시큐리티 필터 등록
// Controller 에서 특정 권한이 있는 유저만 접근 허용하려면 @PreAuthorize 를 사용한다 
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근 하면 권한 및 인증을 미리 체크
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    private final PrincipalDetailService principalDetailService;

    // 암호화 IOC 등록
    @Bean
    public BCryptPasswordEncoder encodePWD() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD()); // (암호화) 패스워드 비교
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                    .antMatchers("/", "/auth/**", "/js/**", "/css/**", "/images/**") // /auth/** 는
                    .permitAll() // 누구나 들어올 수 있다
                    .anyRequest() // 나머지 요청은
                    .authenticated() // 로그인 필요
                .and()
                    .formLogin()
                    .loginPage("/auth/login") // 로그인 페이지 지정
                    .loginProcessingUrl("/auth/login") // 로그인 요청을 해당 url로 대체한다
                    .defaultSuccessUrl("/") // 로그인을 하면 / 로 간다.
                    .failureUrl("/auth/login"); // 로그인을 실패하면 로그인 페이지로 간다
        }
}
