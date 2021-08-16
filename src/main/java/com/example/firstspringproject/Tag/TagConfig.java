package com.example.firstspringproject.Tag;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.List;

@Configuration
public class TagConfig {

    @Bean
    @Order(1)
    CommandLineRunner tagCommandLineRunner(TagRepository repository){
        return args -> {
            Tag tag = new Tag("first tag");
            Tag tag2 = new Tag("second tag");
            repository.saveAll(List.of(tag, tag2));
        };
    }
}
