package com.example.shop.member;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
    public String register(Authentication auth){
        if(auth.isAuthenticated()){
            return "redirect:/list";
        }
        return "register.html";
    }

    @PostMapping("/member")
    public String addMember(@RequestParam String username, String password,String displayName) throws Exception {
        memberService.addMember(username, password, displayName);
        return "redirect:/list";
    }
    //로그아웃시에만
    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public String login(){
        return "login.html";
    }
    //api마다 검사하기 귀찮을시에는 어노테이션으로 검사가능
//    @PreAuthorize("isAuthenticated()") 로그인상태
//    @PreAuthorize("isAnonymous()") 로그아웃 상태
//    @PreAuthorize("hasAuthority()") 특정 권한 취득시

    @GetMapping("/my-page")
    public String myPage(Authentication auth){

        //로그인한 유저의 정보 출력 가능, authentication auth에 담겨있음
        System.out.println(auth);
        CustomUser result = (CustomUser) auth.getPrincipal();
        System.out.println(result.displayName);
        System.out.println(auth.getName());//아이디 출력
        System.out.println(auth.isAuthenticated());//로그인여부 검사가능
        System.out.println(auth.getAuthorities().contains(new SimpleGrantedAuthority("일반유저")));//현재 유저 권한 메모해둔거
        return "mypage.html";
    }


}
