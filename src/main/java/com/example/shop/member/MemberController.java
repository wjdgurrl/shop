package com.example.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @GetMapping("/register")
    public String register(){
        return "register.html";
    }

    @PostMapping("/member")
    public String addMember(@RequestParam String username, String password,String displayName) throws Exception {
        memberService.addMember(username, password, displayName);
        return "redirect:/list";
    }
    //todo 수정필요
    @GetMapping("/login")
    public String login(){
        return "login.html";
    }
}