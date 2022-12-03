package com.library.springboot.library.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.library.springboot.library.dao.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    /*
     * 중복 아이디 체크
     */
    @Query(value = "SELECT COUNT(*) FROM `user` WHERE user_id = ?", nativeQuery = true)
    public int checkOverId(String userId);

    /*
     * 로그인 체크
     */
    @Query(value = "SELECT COUNT(*) FROM `user` WHERE user_id = ? AND user_pw = ?", nativeQuery = true)
    public int loginUser(String userId, String userPw);

    /*
     * 로그인 시 세션에 넣을 객체
     */
    @Query(value = "SELECT * FROM `user` WHERE user_id = ? AND user_pw = ?", nativeQuery = true)
    public User loginUserSession(String userId, String userPw);
}
