package com.example.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    //외부 라이브러리를 dependency injection식으로 쓸때 bean형태로 등록
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    //filterChain 어떤 페이지를 로그인 검사할지 설정가능
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //csrf
        //csrf 차단기능
        http.csrf((csrf) -> csrf.disable());
        http.authorizeHttpRequests((authorize) ->
                //특정 페이지 로그인 할지 결정가능
                authorize.requestMatchers("/**").permitAll()
        );
        return http.build();
    }
}