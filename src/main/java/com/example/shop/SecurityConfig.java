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
        //앞으로 폼으로 로그인 하겠다는 의미
        http.formLogin((formLogin) -> formLogin.loginPage("/login")
                .defaultSuccessUrl("/my-page") //성공시 이동할 페이지
                //.failureUrl("/fail") //실패시 이동할 페이지 실패시 기본적으로 /login?error로 이동
        );
        //로그아웃 기능
        http.logout(logout -> logout.logoutUrl("/logout"));
        return http.build();

    }

}