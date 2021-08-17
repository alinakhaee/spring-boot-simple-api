package com.example.firstspringproject;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FirstSpringProjectApplication{

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(FirstSpringProjectApplication.class, args);


    }

}
