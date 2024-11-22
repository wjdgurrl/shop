package com.example.shop;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    /*@Autowired
    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }*/

    @GetMapping("/list")
    String list(Model model){
        List<Item> result = itemRepository.findAll(); //테이블 모든 데이터
        model.addAttribute("items",result); //전달할 데이터 이름, 데이터
        var a = new Item();
        System.out.println(a.toString());

        return "list.html";
    }


    @GetMapping("/write")
    String write(){
        return "write.html";
    }

    /*@PostMapping("/add")
    String addPost(@RequestParam(name="title") String title,@RequestParam Integer price){
        System.out.println(title);
        System.out.println(price);
        return "redirect:/list";
    }*/

    @PostMapping("/add")
    //기존 <input>데이터들을 바로 object로 변환하려면 @ModelAttribute
    String addPost(@ModelAttribute Item item){
        //System.out.println(formData);
        /*Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);*/
        //System.out.println(item);
        itemRepository.save(item);
        return "redirect:/list";
    }

    //url파라미터
    @GetMapping("/detail/{id}")
    String detail() {
        return "detail.html";
    }




}
