package com.library.springboot.library.dao;

import javax.persistence.Column;
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

    @Column(nullable = false, length = 100)
    private String libname;

    @Column(nullable = false, length = 100)
    private String address;

    @Column(nullable = true, length = 50)
    private String tel;

    @Column(nullable = true, length = 50)
    private String fax;
    
    @Column(nullable = false)
    private float latitude;

    @Column(nullable = false)
    private float longitude;

    @Column(nullable = true, length = 100)
    private String homepage;
    
    @Column(nullable = true, length = 200)
    private String closed;
    
    @Column(nullable = true, length = 100)
    private String operatingtime;
    
    @Column(nullable = false, length = 100)
    private int libcode;
}
