package com.example.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//컨트롤러 어토네이션,서버기능 구현 가능
@Controller
public class BasicController {
    @GetMapping("/")
    //ResponseBody <-- 있으면 문자 그대로 보내주세요, 아니면 파일을 보내줌
    String hello(){
        return "index.html";
    }
    @GetMapping("/index")
    @ResponseBody
    String about(){
        return "site";
    }
    @GetMapping("/mypage")
    @ResponseBody
    String page(){
        return "마이페이지입니다.";
    }
}
