package com.example.shop.member;

import com.example.shop.sales.Sales;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String displayName;
    private String password;

    @ToString.Exclude //무한 상호참조 방지
    @OneToMany(mappedBy = "member")// 이 컬럼을 쓰는 다른 컬럼명 넣어주기()
    private List<Sales> salse = new ArrayList<>(); //타입은 다른 테이블
    //누군가 멤버 테이블을 행을 출력 시 멤버가 기록된 sales 행도 전부 출력함
}
