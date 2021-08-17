package com.example.firstspringproject.Post;

import com.example.firstspringproject.Tag.Tag;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.List;
import java.util.Set;

@Configuration
public class PostConfig {

    @Bean
    @Order(3)
    CommandLineRunner postCommandLineRunner(PostRepository repository){
        return args -> {
            Post post = new Post("first post title", "first post content");
            Post post2 = new Post("second post title", "second post content");

            Tag tag1 = new Tag("one tag");
            Tag tag2 = new Tag("two tag");

            post.setTags(Set.of(tag1, tag2));
            post2.setTags(Set.of(tag1));

            tag1.setPosts(Set.of(post, post2));
            tag2.setPosts(Set.of(post));

            repository.saveAll(List.of(post, post2));
        };
    }
}
