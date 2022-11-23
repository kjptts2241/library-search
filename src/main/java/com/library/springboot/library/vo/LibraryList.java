package com.library.springboot.library.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LibraryList { // 도서관 전체 데이터 (자바 객체)
    
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
}
