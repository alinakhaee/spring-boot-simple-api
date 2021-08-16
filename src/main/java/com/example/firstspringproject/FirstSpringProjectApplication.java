package com.example.firstspringproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootApplication
public class FirstSpringProjectApplication{

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(FirstSpringProjectApplication.class, args);

    }

}
