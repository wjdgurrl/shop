package com.example.shop.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
//jpa 함수 샏성하는곳
//1.리포지터리 생성
//db테이블 과 타입
@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
    // id를 기준으로 오름차순 정렬
    default List<Item> findAllByIdAsc() {
        return findAll(Sort.by(Sort.Direction.ASC, "id")); // ASC: 오름차순
    }
    Page<Item> findPageBy(Pageable page); //페이지 방식

    List<Item> findAllByTitleContains(String title);
    //Slice<Item> findPageBy(Pageable page) // 슬라이스 방식, 전체 페이지 갯수를 알려주지 않아서 count 문법을 실행 안함 -> 성능적 우위
    //@Query(value = "SELECT * FROM shop.item WHERE MATCH(title) AGAINST(?1)",  nativeQuery = true)//jpa에서 full text index검색 구현, ?1 이부분이 파라미터문법 근데 postgresSQL은 안됨
    //List<Item> fullTextSearch(String title);

    // postgres방식
    // N-gram을 사용한 LIKE 검색
    @Query(value = "SELECT * FROM item WHERE title ILIKE %:searchText% ORDER BY similarity(title, :searchText) DESC", nativeQuery = true)
    List<Item> searchByNgram(@Param("searchText") String searchText);

    // N-gram을 사용한 SIMILARITY 검색 (유사도 기반)
    @Query(value = "SELECT * FROM item WHERE similarity(title, :searchText) > 0.3 ORDER BY similarity(title, :searchText) DESC", nativeQuery = true) // 유사도
    List<Item> searchBySimilarity(@Param("searchText") String searchText);
}

