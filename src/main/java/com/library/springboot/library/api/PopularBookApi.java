package com.library.springboot.library.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class PopularBookApi {
    
     // [도서 정보 나루] 인기대출 도서 조회
    public static String PopularBook(String startYYYY, String startMM, String startDD, String endYYYY, String endMM, String endDD, String gender) throws IOException{

        StringBuilder result = new StringBuilder();

        try {
            StringBuilder urlBuilder = new StringBuilder("http://data4library.kr/api/loanItemSrch"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("authKey","UTF-8") + "=f0b5c758febbafb1bd55b1132b97d5be7857041599612d8633ec519072056bbc"); /*인증키*/
            urlBuilder.append("&" + URLEncoder.encode("startDt","UTF-8") + "=" + startYYYY + "-" +  startMM + "-" + startDD); /*페이지 수*/
            urlBuilder.append("&" + URLEncoder.encode("endDt","UTF-8") + "=" + endYYYY + "-" + endMM + "-" + endDD); /*페이지 수*/
            urlBuilder.append("&" + URLEncoder.encode("gender","UTF-8") + "=" + gender); /*성별*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 수*/
            urlBuilder.append("&" + URLEncoder.encode("pageSize","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
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
