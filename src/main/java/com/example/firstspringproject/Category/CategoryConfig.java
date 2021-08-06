package com.example.firstspringproject.Category;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CategoryConfig {

    @Bean
    CommandLineRunner categoryCommandLineRunner(CategoryRepository repository){
        return args -> {
            Category category = new Category("first category");
            Category category2 = new Category("second category");
            repository.saveAll(List.of(category, category2));
        };
    }
}
