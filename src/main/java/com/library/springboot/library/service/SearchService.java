package com.library.springboot.library.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.library.springboot.library.api.BookCollectionApi;
import com.library.springboot.library.api.BookExistApi;
import com.library.springboot.library.api.PopularBookApi;
import com.library.springboot.library.api.SearchApi;
import com.library.springboot.library.api.SearchDetailsApi;
import com.library.springboot.library.dao.repository.TbLibraryListRepository;
import com.library.springboot.library.vo.LibraryList;

@Service
public class SearchService {

    @Autowired
    private TbLibraryListRepository tbLibraryListRepository; // 도서관 데이터 crud 기능

    // 도서관 전체 리스트 가져오는 기능
    @Transactional
    public List<LibraryList> LibraryList() throws IOException {

        ObjectMapper mapper = new ObjectMapper(); // 도서관 데이터를 자바 객체(LibraryListDto)에 mapping 해주기 위한 ObjectMapper 객체 생성
                                                 // Mapping 파일에 기재된 SQL을 호출하기 위한 인터페이스

        String Librarylist = new Gson().toJson(tbLibraryListRepository.findAll()); // Gson기능으로 Librarylist를 json으로 변환 시켜서 변수에 저장

        // Librarylist를 LibraryListDto타입, List형식으로 mapping
        List<LibraryList> LibraryListDto = Arrays.asList(mapper.readValue(Librarylist, LibraryList[].class));

        return LibraryListDto; // LibraryListDto 타입인 List를 Searchcontroller로 전달
    }

    // 도서 검색 결과 가져오는 기능
    @Transactional
    public String SearchList(String keyword) throws IOException { // SearchController에서 키워드를 받아서 api를 실행시킨다음 데이터를 return
        String searchList = SearchApi.Search(keyword); // 키워드를 api로 넣어준 다음 받아온 데이터를
        return searchList; // Searchcontroller로 전달
    }

    // 도서 상세 검색 결과 가져오는 기능
    @Transactional
    public String SearchDetailsList(String isbn) throws IOException {
        String searcDetailsList = SearchDetailsApi.SearchDetails(isbn);
        return searcDetailsList;
    }

    // 도서관별 도서 소장여부 및 대출 가능여부 가져오는 기능
    @Transactional
    public String BookExistList(String isbn, String libCode) throws IOException {
        String bookExistList = BookExistApi.BookExist(isbn, libCode);
        return bookExistList;
    }

    // 도서 소장 도서관 결과 가져오는 기능
    @Transactional
    public String BookCollectionList(String isbn, String region) throws IOException {
        String bookCollectionList = BookCollectionApi.BookCollection(isbn, region);
        return bookCollectionList;
    }

    // 인기대출 도서 가져오는 기능
    @Transactional
    public String PopularBookList(String startYYYY, String startMM, String startDD, String endYYYY, String endMM, String endDD, String gender) throws IOException {
        String popularBookList = PopularBookApi.PopularBook(startYYYY, startMM, startDD, endYYYY, endMM, endDD, gender);
        return popularBookList;
    }
}