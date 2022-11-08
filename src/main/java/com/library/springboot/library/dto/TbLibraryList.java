package com.library.springboot.library.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class LibraryList {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String ilbName;

    @Column(length = 100, nullable = false)
    private String address;

    @Column(length = 50, nullable = true)
    private String tel;

    @Column(length = 50, nullable = true)
    private String fax;
    
    @Column(nullable = false)
    private float latitude;

    @Column(nullable = false)
    private float longitude;

    @Column(length = 100, nullable = true)
    private String homepage;
    
    @Column(length = 200, nullable = true)
    private String closed;

    @Column(length = 100, nullable = true)
    private String operatingTime;
    
    @Column(nullable = false)
    private int libCode;
}
