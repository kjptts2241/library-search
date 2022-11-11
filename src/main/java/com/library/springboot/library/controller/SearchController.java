package com.library.springboot.library.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.library.springboot.library.dto.LibraryListDto;
import com.library.springboot.library.dto.SearchDto;

import com.library.springboot.library.service.LibraryListService;
import com.library.springboot.library.service.SearchService;

import lombok.RequiredArgsConstructor;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class SearchController {

    @Autowired
    private final LibraryListService libraryListService; // 도서관 서비스

    @Autowired
    private final SearchService searchService; // 검색 서비스
    
    // @GetMapping("/api") // [국립 중앙도서관] 도서 검색 api // http://localhost:8080/api
    // public String api(String keyword) throws IOException{

    //     StringBuilder result = new StringBuilder();

    //     try {
    //         StringBuilder urlBuilder = new StringBuilder("https://www.nl.go.kr/NL/search/openApi/search.do"); /*URL*/
    //         urlBuilder.append("?" + URLEncoder.encode("key","UTF-8") + "=a30deda2a59679f7c5a940cc19a2e53c141107d2d077f5157183dbe447c060ab"); /*인증키*/
    //         urlBuilder.append("&" + URLEncoder.encode("srchTarget","UTF-8") + "=" + URLEncoder.encode("title", "UTF-8")); /*키워드*/
    //         urlBuilder.append("&" + URLEncoder.encode("kwd","UTF-8") + "=" + URLEncoder.encode(keyword, "UTF-8")); /*페이지 수*/
    //         urlBuilder.append("&" + URLEncoder.encode("pageNum","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*한 페이지 결과 수*/
    //         urlBuilder.append("&" + URLEncoder.encode("pageSize","UTF-8") + "=" + URLEncoder.encode("5", "UTF-8")); /*한 페이지 결과 수*/
    //         urlBuilder.append("&" + URLEncoder.encode("systemType","UTF-8") + "=" + URLEncoder.encode("오프라인자료", "UTF-8")); /*한 페이지 결과 수*/
    //         urlBuilder.append("&" + URLEncoder.encode("apiType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*한 페이지 결과 수*/
            
    //         URL url = new URL(urlBuilder.toString());
    //         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    //         conn.setRequestMethod("GET"); 

    //         conn.setRequestProperty("Content-type", "application/json");
            
    //         System.out.println("Response code: " + conn.getResponseCode());
            
    //         BufferedReader rd;
            
    //         if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
    //             rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
    //         } else {
    //             rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
    //         }

    //         String line;

    //         while ((line = rd.readLine()) != null) {
    //             result.append(line + "\n");
    //         }

    //         rd.close();
    //         conn.disconnect();

    //         //System.out.println(result.toString());

    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }

    //     return result + "";
        
    // }

    // @GetMapping("/apiDetails") // [도서 정보 나루] isbn을 직접 넣어서 도서 상세 검색
    // public String apiDetails(String isbn13) throws IOException{
    //     StringBuilder result = new StringBuilder();

    //     try {
    //         StringBuilder urlBuilder = new StringBuilder("http://data4library.kr/api/srchDtlList"); /*URL*/
    //         urlBuilder.append("?" + URLEncoder.encode("authKey","UTF-8") + "=f0b5c758febbafb1bd55b1132b97d5be7857041599612d8633ec519072056bbc"); /*인증키*/
    //         urlBuilder.append("&" + URLEncoder.encode("isbn13","UTF-8") + "=" + isbn13); /*isbn*/
    //         urlBuilder.append("&" + URLEncoder.encode("format","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*응답유형*/
    //         System.out.println(urlBuilder);
           
            
    //         URL url = new URL(urlBuilder.toString());
    //         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    //         conn.setRequestMethod("GET");

    //         conn.setRequestProperty("Content-type", "application/json"); 
            
    //         System.out.println("Response code: " + conn.getResponseCode());
            
    //         BufferedReader rd;
            
    //         if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
    //             rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
    //         } else {
    //             rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
    //         }

    //         String line;

    //         while ((line = rd.readLine()) != null) {
    //             result.append(line + "\n");
    //         }

    //         rd.close();
    //         conn.disconnect();

    //         System.out.println(result.toString());

    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }

    //     return result + "";
        
    // }

    // @GetMapping("/apiPopularBook") // [도서 정보 나루] 인기대출 도서 조회
    // public String apiPopularBook(String startYYYY, String startMM, String startDD, String endYYYY, String endMM, String endDD, String gender) throws IOException{

    //     StringBuilder result = new StringBuilder();

    //     try {
    //         StringBuilder urlBuilder = new StringBuilder("http://data4library.kr/api/loanItemSrch"); /*URL*/
    //         urlBuilder.append("?" + URLEncoder.encode("authKey","UTF-8") + "=f0b5c758febbafb1bd55b1132b97d5be7857041599612d8633ec519072056bbc"); /*인증키*/
    //         urlBuilder.append("&" + URLEncoder.encode("startDt","UTF-8") + "=" + startYYYY + "-" +  startMM + "-" + startDD); /*페이지 수*/
    //         urlBuilder.append("&" + URLEncoder.encode("endDt","UTF-8") + "=" + endYYYY + "-" + endMM + "-" + endDD); /*페이지 수*/
    //         urlBuilder.append("&" + URLEncoder.encode("gender","UTF-8") + "=" + gender); /*성별*/
    //         urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 수*/
    //         urlBuilder.append("&" + URLEncoder.encode("pageSize","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
    //         urlBuilder.append("&" + URLEncoder.encode("format","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*응답유형*/
    //         System.out.println(urlBuilder);
        
            
    //         URL url = new URL(urlBuilder.toString());
    //         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    //         conn.setRequestMethod("GET");

    //         conn.setRequestProperty("Content-type", "application/json");
            
    //         System.out.println("Response code: " + conn.getResponseCode());
            
    //         BufferedReader rd;
            
    //         if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
    //             rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
    //         } else {
    //             rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
    //         }

    //         String line;

    //         while ((line = rd.readLine()) != null) {
    //             result.append(line + "\n");
    //         }

    //         rd.close();
    //         conn.disconnect();

    //         System.out.println(result.toString());

    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }

    //     return result + "";
        
    // }

    
    // @GetMapping("/apibookExist") // [도서 정보 나루] 도서관별 도서 소장여부 및 대출 가능여부 조회
    // public String apibookExist(String isbn13, String libCode) throws IOException{
    //     StringBuilder result = new StringBuilder();

    //     try {
    //         StringBuilder urlBuilder = new StringBuilder("http://data4library.kr/api/bookExist"); /*URL*/
    //         urlBuilder.append("?" + URLEncoder.encode("authKey","UTF-8") + "=f0b5c758febbafb1bd55b1132b97d5be7857041599612d8633ec519072056bbc"); /*인증키*/
    //         urlBuilder.append("&" + URLEncoder.encode("libCode","UTF-8") + "=" + libCode); /*libCode*/
    //         urlBuilder.append("&" + URLEncoder.encode("isbn13","UTF-8") + "=" + isbn13); /*isbn*/
    //         urlBuilder.append("&" + URLEncoder.encode("format","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*응답유형*/
    //         System.out.println(urlBuilder);
           
            
    //         URL url = new URL(urlBuilder.toString());
    //         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    //         conn.setRequestMethod("GET");

    //         conn.setRequestProperty("Content-type", "application/json"); 
            
    //         System.out.println("Response code: " + conn.getResponseCode());
            
    //         BufferedReader rd;
            
    //         if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
    //             rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
    //         } else {
    //             rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
    //         }

    //         String line;

    //         while ((line = rd.readLine()) != null) {
    //             result.append(line + "\n");
    //         }

    //         rd.close();
    //         conn.disconnect();

    //         System.out.println(result.toString());

    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }

    //     return result + "";
    // }

    @GetMapping("/api/v1/librarylist") // 도서관 데이터를 불러오기
    public List<LibraryListDto> LibraryListDto() throws IOException { // 라이브러리 전부 들고오기
         List<LibraryListDto> LibraryListDto = libraryListService.LibraryListSearch(); // libraryListService에 있는 LibraryListSearch 함수를 실행시켜 LibraryListDto를 보내준다
         return LibraryListDto;
     }

    @PostMapping("/api/v1/SearchList") // 검색 결과를 들고오기
    public List<SearchDto> Search(String keyword) throws IOException{
        List<SearchDto> bookList = searchService.SearchList(keyword);
        return bookList;  
    }
}
 

 