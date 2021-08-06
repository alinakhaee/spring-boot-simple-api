package com.example.firstspringproject.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping
    public List<Post> findAll(){
        return postService.findAll();
    }

    @GetMapping(path = "{id}")
    public Post findOne(@PathVariable Long id) throws Exception{
        return this.postService.findOne(id);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Post post){
        post.setCreatedAt(LocalDateTime.now());
        postService.create(post);
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body("post created");
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable("id") Long id) throws Exception {
        postService.delete(id);
    }

    @PutMapping
    public void update(@RequestParam(required = true) Long id, @RequestBody Post post) throws Exception {
        postService.update(id, post);
    }

}
