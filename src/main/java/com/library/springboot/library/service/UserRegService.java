package com.library.springboot.library.service;

import java.sql.SQLException;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.springboot.library.dao.UserDaoInterface;
import com.library.springboot.library.vo.UserVO;

@Service
public class UserRegService {

    @Autowired
    private SqlSessionTemplate userSqlSessin;
    private UserDaoInterface userDao;
    
    // 회원가입
    public int userReg_service(UserVO userVO) {
        
        int resultCnt = 0;

        userDao = userSqlSessin.getMapper(UserDaoInterface.class);
        try{
            resultCnt = userDao.regUser(userVO);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultCnt;
    }

    // 중복 아이디 체크
    public int userIdCheck(String user_id) {

        userDao = userSqlSessin.getMapper(UserDaoInterface.class);

        return userDao.checkOverId(user_id);
    }
}
