package com.example.shop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {
    @GetMapping("/list")
    String list(Model model){
        model.addAttribute("name","정혁기"); //전달할 데이터 이름, 데이터
        return "list.html";
    }


}
