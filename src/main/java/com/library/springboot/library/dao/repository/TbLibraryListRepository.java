package com.library.springboot.library.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.springboot.library.dao.TbLibraryList;

public interface TbLibraryListRepository extends JpaRepository<TbLibraryList, Long>{ // TbLibraryList 테이블에 crud 기능을 넣어준다

}