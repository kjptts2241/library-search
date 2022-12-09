package com.library.springboot.library.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLEncoder;

public class SearchDetailsApi {
    
    // [도서 정보 나루] isbn을 직접 넣어서 도서 상세 검색
    public static String SearchDetails(String isbn) throws IOException{
        StringBuilder result = new StringBuilder();

        try {
            StringBuilder urlBuilder = new StringBuilder("http://data4library.kr/api/srchDtlList"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("authKey","UTF-8") + "=f0b5c758febbafb1bd55b1132b97d5be7857041599612d8633ec519072056bbc"); /*인증키*/
            urlBuilder.append("&" + URLEncoder.encode("isbn13","UTF-8") + "=" + isbn); /*isbn*/
            // urlBuilder.append("&" + URLEncoder.encode("loaninfoYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); /*한 페이지 결과 수*/
            urlBuilder.append("&" + URLEncoder.encode("format","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*응답유형*/
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
