package com.library.springboot.library.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.springboot.library.dao.UserBook;

public interface UserBookRepository extends JpaRepository<UserBook, Long> {
    
}
