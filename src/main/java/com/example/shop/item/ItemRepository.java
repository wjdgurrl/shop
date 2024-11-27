package com.example.shop.item;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//1.리포지터리 생성
//db테이블 과 타입
public interface ItemRepository extends JpaRepository<Item, Long>{
    // id를 기준으로 오름차순 정렬
    default List<Item> findAllByIdAsc() {
        return findAll(Sort.by(Sort.Direction.ASC, "id")); // ASC: 오름차순
    }
}
