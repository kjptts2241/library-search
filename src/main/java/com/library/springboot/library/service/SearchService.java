package com.library.springboot.library.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.library.springboot.library.api.SearchApi;
import com.library.springboot.library.dto.SearchDto;
import com.library.springboot.library.dto.SearchInfoDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SearchService {
    
    @Transactional
    public List<SearchDto> SearchList(String keyword) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        
        String searchJson = new Gson().toJson(SearchApi.api(keyword));
        System.out.println("11111111111111111111111111");
            
        SearchInfoDto searchInfoDto = mapper.readValue(searchJson, SearchInfoDto.class);
        System.out.println("2222222222222222222222222");

        List<SearchDto> bookList = searchInfoDto.getResult();
        System.out.println("33333333333333333333333");

        return bookList;
    }
}
