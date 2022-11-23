package com.library.springboot.library.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class TbLibraryList { // TbLibraryList 테이블
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String libname;

    private String address;

    private String tel;

    private String fax;
    
    private float latitude;

    private float longitude;

    private String homepage;
    
    private String closed;

    private String operatingtime;
    
    private int libcode;
    // null값이 id or libcode로 입력이 되서 생기는 오류 확인
}
