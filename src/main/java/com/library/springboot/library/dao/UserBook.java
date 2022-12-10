package com.library.springboot.library.dao;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER) // [연관 관계] Many = UserBook, One = User   =>   한명의 유저는 여러개의 도서를 저장할 수 있다
    @JoinColumn(name="user_id")
    private User user; // User 테이블의 user_id [ FK ]

    @Column(nullable = false, length = 100)
    private String imageUrl; // 이미지 URL

    @Column(nullable = false, length = 50)
    private String titleInfo; // 도서명

    @Column(nullable = false, length = 50)
    private String typeName; // 도서 자료 유형

    @Column(nullable = false, length = 50)
    private String authorInfo; // 저작자

    @Column(nullable = false, length = 50)
    private String pubInfo; // 출판사

    @Column(nullable = false, length = 50)
    private String menuName; // 온라인/오프라인 자료 구분

    @Column(nullable = false, length = 50)
    private String mediaName; // 매체 구분

    @Column(nullable = false, length = 50)
    private String licText; // 저작권 이용 가능 유무
    
    @Column(nullable = false, length = 50)
    private String regDate; // 비치일

    @Column(nullable = true, length = 50)
    private String isbn; // isbn

    @Column(nullable = false, length = 50)
    private String callNo; // 청구기호

    @Column(nullable = false, length = 50)
    private String kdcCode1s; // 동양서분류기호 대분류 코드

    @Column(nullable = false, length = 50)
    private String kdcName1s; // 동양서분류기호 대분류 명칭

    @CreationTimestamp // 시간 자동 입력
    private Timestamp create_date; // 생성 날짜

    @Builder
    public UserBook(User user, String imageUrl, String titleInfo, String typeName, String authorInfo, String pubInfo, String menuName, String mediaName, String licText, String regDate, String isbn, String callNo, String kdcCode1s, String kdcName1s) {
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
}
