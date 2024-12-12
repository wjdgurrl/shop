package com.example.shop.item;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;

    /*@Autowired
    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }*/

    @GetMapping("/list")
    String list(Model model){
        itemService.listItem(model);
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
    String addPost(@RequestParam String title, Integer price, Authentication auth) {
        itemService.saveItem(title, price, auth.getName());
        return "redirect:/list";
    }


    //url파라미터
    @GetMapping("/detail/{id}")
    //(@PathVariable 타입 URL파라미터명) -> 유저가 입력한 내용을 가져옴
    String detail(@PathVariable Long id,Model model) throws Exception{
        Optional<Item> result = itemRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("data", result.get());
            return "detail.html";
        } else {
            return "redirect:/list";
        }
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        Optional<Item> result =itemRepository.findById(id);
        if(result.isPresent()){
            model.addAttribute("item",result.get());
            return "edit.html";
        }else{
            return "redirect:/list";
        }
    }

    @PostMapping("/edit")
    public String updateItem(@RequestParam String title, Integer price, Long id,Authentication authentication){
        if(title.length() < 100 && price > 0){
           itemService.updateItem(title,price,id,authentication.getName());
        }
        return "redirect:/list";
    }
    //삭제 컨트롤러
    @DeleteMapping("/data")
    public ResponseEntity<String> deleteItem(@RequestParam Long id){
        itemRepository.deleteById(id);
        //AJAX는 리다이렉트가 안됨
        return ResponseEntity.status(200).body("삭제완료");
    }
}
