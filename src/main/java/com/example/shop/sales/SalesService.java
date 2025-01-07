package com.example.shop.sales;


import com.example.shop.sales.SalesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SalesService {

    private final SalesRepository salesRepository;

    public void addCart(String itemName, int price, Long memberId){
        Sales sales = new Sales();
        sales.setItemName(itemName);
        sales.setPrice(price);
        sales.setMemberId(memberId);
        sales.setCreated(LocalDateTime.now());
        salesRepository.save(sales);
    }

}
