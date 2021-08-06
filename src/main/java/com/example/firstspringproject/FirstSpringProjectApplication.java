package com.example.firstspringproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FirstSpringProjectApplication{

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(FirstSpringProjectApplication.class, args);

//        MyClass myClass1 = context.getBean(MyClass.class);
//        myClass1.setMessage("myclass 1");
//        myClass1.getMessage();
//
//        MyClass myClass2 = context.getBean(MyClass.class);
//        myClass2.getMessage();
    }

}
