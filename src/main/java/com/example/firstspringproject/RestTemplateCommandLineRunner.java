package com.example.firstspringproject;

import com.example.firstspringproject.Category.Category;
import com.example.firstspringproject.Category.CategoryRepository;
import com.example.firstspringproject.Post.Post;
import com.example.firstspringproject.Post.PostDTO;
import com.example.firstspringproject.Post.PostMapper;
import com.example.firstspringproject.Post.PostRepository;
import com.example.firstspringproject.Tag.Tag;
import com.example.firstspringproject.Tag.TagRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Set;

@Configuration
@Order(4)
@AllArgsConstructor
public class RestTemplateCommandLineRunner implements CommandLineRunner {
    TagRepository tagRepository;
    PostRepository postRepository;
    CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        Post post = new Post("runner post", "runner content");
        Tag tag = new Tag("runner tag");
        Category category = new Category("runner category");
        post.setTags(Set.of(tag));
        tag.setPosts(Set.of(post));
        post.setCategory(category);
        category.setPosts(List.of(post));
        categoryRepository.save(category);
        tagRepository.save(tag);
        postRepository.save(post);

        // using rest template
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/api/posts";

//        /* use get for entity and use json values */
//        ResponseEntity<String> response = restTemplate.getForEntity(url+"/1", String.class);
//        System.out.println(response.getStatusCode());
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode root = mapper.readTree(response.getBody());
//        JsonNode name = root.path("title");
//        System.out.println(name.asText());

//        // use getforobject
//        PostDTO postDTO = restTemplate.getForObject(url+"/1", PostDTO.class);
//        System.out.println(postDTO);

//        // headers
//        HttpHeaders headers = restTemplate.headForHeaders(url);
//        System.out.println(headers.getContentType());

//        // post

//        // use posrforobject
//        Post post = new Post("title", "content");
//        HttpEntity<PostDTO> request = new HttpEntity<>(PostMapper.INSTANCE.toPostDTO(post));
//        PostDTO postDTO1 = restTemplate.postForObject(url, request, PostDTO.class);
//        System.out.println(postDTO1);

//        // use postforlocation
//        Post post = new Post("title", "content");
//        HttpEntity<PostDTO> request = new HttpEntity<>(PostMapper.INSTANCE.toPostDTO(post));
//        URI uri = restTemplate.postForLocation(url, request, PostDTO.class);
//        System.out.println(url);

//        //use exchange
//        Post post = new Post("title", "content");
//        HttpEntity<PostDTO> request = new HttpEntity<>(PostMapper.INSTANCE.toPostDTO(post));
//        ResponseEntity<PostDTO> response = restTemplate.exchange(url, HttpMethod.POST, request, PostDTO.class);
//        System.out.println(response.getBody());

//        // use optionsforallow
//        Set<HttpMethod> allowed = restTemplate.optionsForAllow(url);
//        System.out.println(allowed);

//        // use put
//        Post newPost = new Post();
//        newPost.setTitle("editet title");
//        newPost.setId(1L);
//        HttpEntity<PostDTO> request = new HttpEntity<>(PostMapper.INSTANCE.toPostDTO(newPost));
//        restTemplate.put(url+"?id=1", request);

        // use delete
        restTemplate.delete(url+"/1");
    }
}
