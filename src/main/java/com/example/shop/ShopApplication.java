package com.example.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
        /*var a = "a";
        var ab = 20;*/
        //var는 컴퓨터가 유추하는 변수



    }
}
class Friend{
    String name = "aaa";
    int age = 20;
    Friend(){
        name = name;
        this.age = age;
    }
}
