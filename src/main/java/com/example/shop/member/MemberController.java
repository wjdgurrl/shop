package com.example.shop.member;


import com.example.shop.sales.Sales;
import com.example.shop.sales.SalesService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import lombok.Setter;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.shop.sales.SalesRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final SalesService salesService;

    @GetMapping("/register")
    public String register(Authentication auth) {
        if (auth != null && auth.isAuthenticated()) {
            return "redirect:/list";
        }
        return "register.html";
    }

    @PostMapping("/member")
    public String addMember(@RequestParam String username, String password, String displayName) throws Exception {
        memberService.addMember(username, password, displayName);
        return "redirect:/list";
    }

    //로그아웃시에만
    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public String login() {
        return "login.html";
    }
    //api마다 검사하기 귀찮을시에는 어노테이션으로 검사가능
//    @PreAuthorize("isAuthenticated()") 로그인상태
//    @PreAuthorize("isAnonymous()") 로그아웃 상태
//    @PreAuthorize("hasAuthority()") 특정 권한 취득시

    @GetMapping("/my-page")
    public String myPage(Authentication auth) {

        //로그인한 유저의 정보 출력 가능, authentication auth에 담겨있음
        System.out.println(auth);
        CustomUser result = (CustomUser) auth.getPrincipal();
        System.out.println(result.displayName);
        System.out.println(auth.getName());//아이디 출력
        System.out.println(auth.isAuthenticated());//로그인여부 검사가능
        System.out.println(auth.getAuthorities().contains(new SimpleGrantedAuthority("일반유저")));//현재 유저 권한 메모해둔
        return "mypage.html";
    }

    @GetMapping("/user/1")
    @ResponseBody
    public MemberDto getUser() {
        var a = memberRepository.findById(1L);
        var result = a.get();
        //이렇게 하면 유저 정보가 전부 전송되버림
        result.setPassword("null");
        //1. 해쉬맵을 생성해서 원하는 자료만 put해서 보내기
       /* var map = new HashMap<>();
        map.put("닉네임",a.get().getDisplayName());
        return map;*/

        //2. 오브젝트에서 따로 패스워드 부분만 삭제할 수 없으니 새로운 객체 생성해서 집어 넣어 보내기 그게 dto
        var data = new MemberDto(result.getUsername(), result.getDisplayName());
        //아래 과정을 안하고 constructor로 생성
        /*data.username = result.getUsername();
        data.displayName = result.getDisplayName();*/

        return data; // object를 여기에 넣으면 json으로 자동변환
    }

    //DTO 클래스
    //웬만하면 따로 파일로 생성하는게 좋음
    @Getter // 이거나 클래스 내부에 public이 있어야 자동으로 json변환해줌
    class MemberDto {
        public String username; //public 있어야 json변환해줌
        public String displayName;

        MemberDto(String username, String displayName) {
            this.username = username;
            this.displayName = displayName;
        }
    }

    @PostMapping("/bucket")
    public ResponseEntity<Map<String, String>> bucket(@RequestBody Map<String, String> data) {
        String itemName = data.get("itemName");
        int itemPrice = Integer.parseInt(data.get("itemPrice").replaceAll("[^0-9]", "")); //숫자 뒤에 "원"표시 제거
        Long memberId = Long.parseLong(data.get("memberId"));
        System.out.println(itemName +" "+itemPrice +" "+memberId);
        salesService.addCart(itemName,itemPrice,memberId);
        //ajax요청이라 json을 반환해줘야 함
        Map<String, String> response = new HashMap<>();
        response.put("message", "장바구니에 추가되었습니다.");
        return ResponseEntity.ok(response);
        //return "mypage.html";
    }
}
