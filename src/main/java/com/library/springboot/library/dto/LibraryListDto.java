package com.library.springboot.library.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LibraryListDto { // LibraryList DTO
    
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
