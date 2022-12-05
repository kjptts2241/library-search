package com.library.springboot.library.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.library.springboot.library.dao.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    /*
     * 중복 아이디 체크
     */
    @Query(value = "SELECT COUNT(*) FROM `user` WHERE user_id = ?", nativeQuery = true)
    public int checkOverId(String user_id);

    @Query(value = "SELECT * FROM `user` WHERE user_id = ?", nativeQuery = true)
    Optional<User> loginUser(String user_id);
}
