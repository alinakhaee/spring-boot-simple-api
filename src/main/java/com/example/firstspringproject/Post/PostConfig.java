package com.example.firstspringproject.Post;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PostConfig {

    @Bean
    CommandLineRunner postCommandLineRunner(PostRepository repository){
        return args -> {
            Post post = new Post("first post title", "first post content");
            Post post2 = new Post("second post title", "second post content");
            repository.saveAll(List.of(post, post2));
        };
    }
}
