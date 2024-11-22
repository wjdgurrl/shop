package com.example.shop;

import org.springframework.data.jpa.repository.JpaRepository;

//1.리포지터리 생성
public interface ItemRepository extends JpaRepository<Item, Long> {

}
