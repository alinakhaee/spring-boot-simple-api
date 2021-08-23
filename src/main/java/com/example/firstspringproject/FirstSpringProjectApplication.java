package com.example.firstspringproject;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class FirstSpringProjectApplication{

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(FirstSpringProjectApplication.class, args);

        RedissonClient client = Redisson.create();
        RAtomicLong myLong = client.getAtomicLong("myLong");
        myLong.set(2);
    client.shutdown();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
