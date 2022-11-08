package com.library.springboot.library.dto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.springboot.library.dto.TbLibraryList;

public interface TbLibraryListRepository extends JpaRepository<TbLibraryList, Long>{
    
}