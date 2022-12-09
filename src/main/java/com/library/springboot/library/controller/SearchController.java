package com.library.springboot.library.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.springboot.library.dto.LibraryListDto;
import com.library.springboot.library.service.SearchService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class SearchController {
    
    private final SearchService searchService; // 검색 관련 서비스(기능)

    /*
     * 도서관 전체 데이터 조회
     */
    @GetMapping("/auth/data/v1/librarylist")
    public List<LibraryListDto> LibraryList() throws IOException {
         List<LibraryListDto> LibraryListDto = searchService.LibraryList(); // libraryListService에 있는 LibraryListSearch 함수를 실행시켜 LibraryListDto를 보내준다
         return LibraryListDto;
    }

    /*
     * (제목을 받아서)
     * 도서 검색 결과 조회
     */
    @GetMapping("/auth/api/v1/search")
    public String Search(String keyword) throws IOException{
        String searchList = searchService.SearchList(keyword);
        return searchList;
    }
    
    /*
     * (isbn을 받아서)
     * 도서 상세 검색 결과 조회
     */
    @GetMapping("/auth/api/v1/searchDetails")
    public String SearchDetails(String isbn) throws IOException{
        String searcDetailsList = searchService.SearchDetailsList(isbn);
        return searcDetailsList;
    }

    /*
     * (isbn, libCode를 받아서)
     * 도서관별 도서 소장여부 및 대출 가능 여부 조회
     */
    @GetMapping("/auth/api/v1/bookExist")
    public String BookExist(String isbn, String libCode) throws IOException{
        String bookExistList = searchService.BookExistList(isbn, libCode);
        return bookExistList;
    }

    /*
     * (isbn, region을 받아서)
     * 도서 소장 도서관 조회
     */
    @GetMapping("/auth/api/v1/bookCollection")
    public String BookCollection(String isbn, String region) throws IOException{ // region=11;21;22 서울 부산 대구 순으로 정렬됨
        String bookCollectionList = searchService.BookCollectionList(isbn, region);
        return bookCollectionList;
    }

    /*
     * (날짜, 시간, 성별을 받아서)
     * 인기대출 도서 조회
     */
    @PostMapping("/auth/api/v1/popularBook")
    public String PopularBook(String startYYYY, String startMM, String startDD, String endYYYY, String endMM, String endDD, String gender) throws IOException{
        String popularBookList = searchService.PopularBookList(startYYYY, startMM, startDD, endYYYY, endMM, endDD, gender);
        return popularBookList;
    }
}
 

 