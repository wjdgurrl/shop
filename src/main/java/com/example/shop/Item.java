package com.example.shop;

import jakarta.persistence.*;

import java.util.Date;

@Entity
//테이블 생성
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //유니크 번호 , generatedValue는 상품을 하나 추가할때마다 1씩 증가
    public Long id; //컬럼 생성

    @Column(length = 200) //@Column() 컬럼위에 붙이면 제약사항을 입력할 수 있는데, 200자 제한
    public String title;
    public Integer price; //컬럼용 변수에는 원시형 추천
}

@Entity
public class NoficationItem{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Date;
}
