package com.library.springboot.library.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchInfoDto {

    private int total;

    private String kwd;

    private int pageNum;

    private int pageSize;
    
    private String category;

    private String sort;

    private List<SearchDto> result;
}
