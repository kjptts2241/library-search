package com.library.springboot.library.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LibraryListDto {
    
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
