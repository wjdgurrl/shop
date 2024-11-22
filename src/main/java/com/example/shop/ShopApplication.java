package com.example.shop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
        /*var a = "a";
        var ab = 20;*/
        //var는 컴퓨터가 유추하는 변수

        identify id = new identify();

        id.setName("홍길도ㅓㅇ");
        id.setAge(12);
        id.setingAge(102);
        id.ageAdd();
        System.out.println(id.getAge());

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
@Getter
@Setter
class identify{
    private String name;
    private int age;

    public void ageAdd(){
        this.age = this.age+1;
    }
    public void setingAge(int age){
        if(age < 0 || age > 100){
            return;
        }
        this.age = age;
    }
}
