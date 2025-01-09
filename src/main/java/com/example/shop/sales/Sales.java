package com.example.shop.sales;

import com.example.shop.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.HQLSelect;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private Integer price;

    //현재는 멤버 객체를 변수로 안받아서 아래 기능을 못씀
   /* @ManyToOne
    @JoinColumn
            (name = "member_id",
                    foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)//Foreign key 제약사항을 자동으로 걸지 말라는 뜻
            )
    private Member member;//member테이블 가리켜야 함*/
    private Long memberId;

    @CreationTimestamp
    private LocalDateTime created; // 행 추가할때 자동으로 현재시간 채워짐

}
