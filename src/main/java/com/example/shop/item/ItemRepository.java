package com.example.shop.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;



import java.util.List;
//jpa 함수 샏성하는곳
//1.리포지터리 생성
//db테이블 과 타입
public interface ItemRepository extends JpaRepository<Item, Long>{
    // id를 기준으로 오름차순 정렬
    default List<Item> findAllByIdAsc() {
        return findAll(Sort.by(Sort.Direction.ASC, "id")); // ASC: 오름차순
    }
    Page<Item> findPageBy(Pageable page); //페이지 방식
    //Slice<Item> findPageBy(Pageable page) // 슬라이스 방식, 전체 페이지 갯수를 알려주지 않아서 count 문법을 실행 안함 -> 성능적 우위
}
