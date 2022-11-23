package com.library.springboot.library.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class BookExistApi {
    
    // [도서 정보 나루] 도서관별 도서 소장여부 및 대출 가능여부 조회
    public static String BookExist(String isbn, String libCode) throws IOException{
        
        StringBuilder result = new StringBuilder();

        try {
            StringBuilder urlBuilder = new StringBuilder("http://data4library.kr/api/bookExist"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("authKey","UTF-8") + "=f0b5c758febbafb1bd55b1132b97d5be7857041599612d8633ec519072056bbc"); /*인증키*/
            urlBuilder.append("&" + URLEncoder.encode("libCode","UTF-8") + "=" + libCode); /*libCode*/
            urlBuilder.append("&" + URLEncoder.encode("isbn13","UTF-8") + "=" + isbn); /*isbn*/
            urlBuilder.append("&" + URLEncoder.encode("format","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*응답유형*/
            System.out.println(urlBuilder);
           
            
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            conn.setRequestProperty("Content-type", "application/json"); 
            
            System.out.println("Response code: " + conn.getResponseCode());
            
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

            System.out.println(result.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result + "";
    }
}
