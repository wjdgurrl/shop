package com.example.shop.Item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
//테이블 생성
@ToString // 롬복 toString 문법
@Setter
@Getter
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //유니크 번호 , generatedValue는 상품을 하나 추가할때마다 1씩 증가
    public Long id; //컬럼 생성

    @Column(length = 200) //@Column() 컬럼위에 붙이면 제약사항을 입력할 수 있는데, 200자 제한
    private String title;
    public Integer price; //컬럼용 변수에는 원시형 추천

    /*@Override
    public String toString() {
        return this.title + this.price;
    } 롬복으로 구현함 */
}


