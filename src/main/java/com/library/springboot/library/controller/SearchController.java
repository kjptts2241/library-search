package com.library.springboot.library.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.springboot.library.dto.LibraryListDto;

import com.library.springboot.library.service.SearchService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class SearchController { // 데이터 전달해주는 [ RestController ]
    
    private final SearchService searchService; // 검색 관련 서비스(기능)

    @GetMapping("/data/v1/librarylist") // 도서관 전체 데이터를 불러오기
    public List<LibraryListDto> LibraryList() throws IOException {
         List<LibraryListDto> LibraryListDto = searchService.LibraryList(); // libraryListService에 있는 LibraryListSearch 함수를 실행시켜 LibraryListDto를 보내준다
         return LibraryListDto;
     }

    @GetMapping("/api/v1/search") // (제목을 받아서) 도서 검색 결과를 불러오기
    public String Search(String keyword) throws IOException{
        String searchList = searchService.SearchList(keyword);
        return searchList;
    }
    
    @GetMapping("/api/v1/searchDetails") // (isbn을 받아서) 도서 상세 검색 결과를 불러오기
    public String SearchDetails(String isbn) throws IOException{
        String searcDetailsList = searchService.SearchDetailsList(isbn);
        return searcDetailsList;
    }

    @GetMapping("/api/v1/bookExist") // (isbn, libCode를 받아서) 도서관별 도서 소장여부 및 대출 가능여부 불러오기
    public String BookExist(String isbn, String libCode) throws IOException{
        String bookExistList = searchService.BookExistList(isbn, libCode);
        return bookExistList;
    }

    @PostMapping("/api/v1/popularBook") // (날짜, 시간, 성별을 받아서) 인기대출 도서 불러오기
    public String PopularBook(String startYYYY, String startMM, String startDD, String endYYYY, String endMM, String endDD, String gender) throws IOException{
        String popularBookList = searchService.PopularBookList(startYYYY, startMM, startDD, endYYYY, endMM, endDD, gender);
        return popularBookList;
    }
}
 

 