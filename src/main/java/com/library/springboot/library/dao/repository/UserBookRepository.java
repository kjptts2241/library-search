package com.library.springboot.library.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.library.springboot.library.dao.UserBook;

public interface UserBookRepository extends JpaRepository<UserBook, Long> {
    
    /*
     * 내 서재 리스트 조회
     */
    @Query(value = "SELECT * FROM `userbook` WHERE user_id = ?", nativeQuery = true)
    public List<UserBook> UserBookSearch(Long user_id);
}
