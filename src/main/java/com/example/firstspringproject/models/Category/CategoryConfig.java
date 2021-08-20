package com.example.firstspringproject.models.Category;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.List;

@Configuration
public class CategoryConfig {

    @Bean
    @Order(2)
    CommandLineRunner categoryCommandLineRunner(CategoryRepository repository){
        return args -> {
            Category category = new Category("first category");
            Category category2 = new Category("second category");
            repository.saveAll(List.of(category, category2));
        };
    }
}
