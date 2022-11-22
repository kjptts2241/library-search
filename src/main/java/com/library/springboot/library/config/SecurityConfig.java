package com.library.springboot.library.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                // user 페이지 설정
                .antMatchers("/user/**")
                .authenticated() // 로그인 필요
                // 기타 url은 모두 허용
                .anyRequest()
                .permitAll()
                .and()
                // 로그인 페이지 사용
                .formLogin()
                .loginPage("/login") // 로그인 페이지 경로 설정
                .defaultSuccessUrl("/"); // 로그인 성공 후 기본적으로 리다이렉트되는 경로
    }
}
