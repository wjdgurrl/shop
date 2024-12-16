package com.example.shop.member;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void addMember(String username, String password,String displayName)throws Exception{
        var result = memberRepository.findByUsername(username);
        if(result.isPresent()){
            throw new Exception("중복된 아이디");
        }
        if(username.length() < 4 || password.length() < 4){
            throw new Exception("too short");
        }
        Member member = new Member();
        member.setUsername(username);
        var hashPassword = passwordEncoder.encode(password);
        member.setPassword(hashPassword);
        member.setDisplayName(displayName);
        memberRepository.save(member);
    }
}
