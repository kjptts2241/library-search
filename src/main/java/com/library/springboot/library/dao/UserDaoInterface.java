package com.library.springboot.library.dao;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.library.springboot.library.vo.UserVO;

@Mapper
public interface UserDaoInterface {
    
    /* 유저 정보 관련 */
    int regUser(UserVO userVO) throws SQLException; // 유저 회원가입 메서드
    int checkOverId(String user_id); // 아이디 중복 체크
    UserVO loginUser(@Param("user_id")String user_id);// 유저 로그인 메서드

}
