package com.example.shop.Item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void saveItem(String title, Integer price){
        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);
        itemRepository.save(item);
    }

    public void listItem(Model model){
        List<Item> result = itemRepository.findAllByIdAsc(); //테이블 모든 데이터
        model.addAttribute("items",result); //전달할 데이터 이름, 데이터
        var a = new Item();
        System.out.println(a.toString());
    }
    public void updateItem(String title, Integer price,Long id){
        Item item = new Item();
        item.setId(id);
        item.setTitle(title);
        item.setPrice(price);
        itemRepository.save(item);
    }
}
