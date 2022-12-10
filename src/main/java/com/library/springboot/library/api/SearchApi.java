package com.library.springboot.library.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SearchApi {
    
    // [국립 중앙도서관] 도서 검색 api
    public static String Search(String keyword) throws IOException{

        StringBuilder result = new StringBuilder();

        try {
            StringBuilder urlBuilder = new StringBuilder("https://www.nl.go.kr/NL/search/openApi/search.do"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("key","UTF-8") + "=a30deda2a59679f7c5a940cc19a2e53c141107d2d077f5157183dbe447c060ab"); /*인증키*/
            urlBuilder.append("&" + URLEncoder.encode("srchTarget","UTF-8") + "=" + URLEncoder.encode("title", "UTF-8")); /*키워드*/
            urlBuilder.append("&" + URLEncoder.encode("kwd","UTF-8") + "=" + URLEncoder.encode(keyword, "UTF-8")); /*페이지 수*/
            urlBuilder.append("&" + URLEncoder.encode("pageNum","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*한 페이지 결과 수*/
            urlBuilder.append("&" + URLEncoder.encode("pageSize","UTF-8") + "=" + URLEncoder.encode("3", "UTF-8")); /*한 페이지 결과 수*/
            urlBuilder.append("&" + URLEncoder.encode("systemType","UTF-8") + "=" + URLEncoder.encode("오프라인자료", "UTF-8")); /*한 페이지 결과 수*/
            urlBuilder.append("&" + URLEncoder.encode("apiType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*한 페이지 결과 수*/
            // System.out.println(urlBuilder);

            
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET"); 

            conn.setRequestProperty("Content-type", "application/json");
            
            // System.out.println("Response code: " + conn.getResponseCode());
            
            BufferedReader rd;
            
            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            String line;

            while ((line = rd.readLine()) != null) {
                result.append(line + "\n");
            }

            rd.close();
            conn.disconnect();

            // System.out.println(result.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result + "";
        
    }
}
