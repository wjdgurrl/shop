package com.example.shop.item;

import com.example.shop.member.Member;
import com.example.shop.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    public void saveItem(String title, Integer price, String writer,String filename){
        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);
        item.setWriter(writer);
        item.setImgUrl(filename);
        itemRepository.save(item);

    }

    public void listItem(Model model){
        List<Item> result = itemRepository.findAllByIdAsc(); //테이블 모든 데이터
        model.addAttribute("items",result); //전달할 데이터 이름, 데이터
        var a = new Item();
        System.out.println(a.toString());
    }
    public void updateItem(String title, Integer price,Long id, String writer,String filename){
        Item item = new Item();
        item.setId(id);
        item.setTitle(title);
        item.setPrice(price);
        item.setWriter(writer);
        item.setImgUrl(filename);
        itemRepository.save(item);
    }
}
