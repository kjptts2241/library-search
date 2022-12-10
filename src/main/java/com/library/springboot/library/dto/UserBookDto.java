package com.library.springboot.library.dto;

import java.sql.Timestamp;

import com.library.springboot.library.dao.User;
import com.library.springboot.library.dao.UserBook;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBookDto {
    
    private Long id;
    private User user; // User 테이블의 user_id [ FK ]
    private String imageUrl; // 이미지 URL
    private String titleInfo; // 도서명
    private String typeName; // 도서 자료 유형
    private String authorInfo; // 저작자
    private String pubInfo; // 출판사
    private String menuName; // 온라인/오프라인 자료 구분
    private String mediaName; // 매체 구분
    private String licText; // 저작권 이용 가능 유무
    private String regDate; // 비치일
    private String isbn; // isbn
    private String callNo; // 청구기호
    private String kdcCode1s; // 동양서분류기호 대분류 코드
    private String kdcName1s; // 동양서분류기호 대분류 명칭
    private Timestamp create_date; // 생성 날짜

    @Builder
    public UserBookDto(User user, String imageUrl, String titleInfo, String typeName, String authorInfo, String pubInfo, String menuName, String mediaName, String licText, String regDate, String isbn, String callNo, String kdcCode1s, String kdcName1s) {
        this.user = user;
        this.imageUrl = imageUrl;
        this.titleInfo = titleInfo;
        this.typeName = typeName;
        this.authorInfo = authorInfo;
        this.pubInfo = pubInfo;
        this.menuName = menuName;
        this.mediaName = mediaName;
        this.licText = licText;
        this.regDate = regDate;
        this.isbn = isbn;
        this.callNo = callNo;
        this.kdcCode1s = kdcCode1s;
        this.kdcName1s = kdcName1s;
    }

    public UserBook toEntity() {
        return UserBook.builder()
                .user(user)
                .imageUrl(imageUrl)
                .titleInfo(titleInfo)
                .typeName(typeName)
                .authorInfo(authorInfo)
                .pubInfo(pubInfo)
                .menuName(menuName)
                .mediaName(mediaName)
                .licText(licText)
                .regDate(regDate)
                .isbn(isbn)
                .callNo(callNo)
                .kdcCode1s(kdcCode1s)
                .kdcName1s(kdcName1s)
                .build();
    }
}
