package com.example.firstspringproject.models.Post;

import com.example.firstspringproject.models.Category.CategoryDTO;
import com.example.firstspringproject.models.Tag.TagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> findAll() {
        List<Post> posts = postService.findAll();
        List<PostDTO> postDTOs = new ArrayList<>();
        posts.stream().forEach(post -> postDTOs.add(PostMapper.INSTANCE.toPostDTO(post)));
        return ResponseEntity.ok().body(postDTOs);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<PostDTO> findOne(@PathVariable Long id) throws Exception {
        Post post = this.postService.findOne(id);
        PostDTO postDTO = PostMapper.INSTANCE.toPostDTO(post);
        return ResponseEntity.ok().body(postDTO);
    }

    @PostMapping
    public ResponseEntity<PostDTO> create(@RequestBody PostDTO postDTO) throws Exception {
        Set<ConstraintViolation<PostDTO>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(postDTO);
        for(ConstraintViolation<PostDTO> violation : violations){
            System.out.println(violation.getMessage());
            throw new Exception(violation.getMessage());
        }
        Post post = PostMapper.INSTANCE.toPost(postDTO);
        post.setCreatedAt(LocalDateTime.now());
        postService.create(post);
        return ResponseEntity.status(200).body(PostMapper.INSTANCE.toPostDTO(post));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<PostDTO> delete(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok().body(PostMapper.INSTANCE.toPostDTO(postService.delete(id)));
    }

    @DeleteMapping(path = "{id}/category")
    public ResponseEntity<PostDTO> deleteCategory(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok().body(PostMapper.INSTANCE.toPostDTO(postService.deleteCategory(id)));
    }
    @DeleteMapping(path = "{postId}/tags/{tagId}")
    public ResponseEntity<PostDTO> deleteTag(@PathVariable("postId") Long postId, @PathVariable("tagId") Long tagId) throws Exception {
        return ResponseEntity.ok().body(PostMapper.INSTANCE.toPostDTO(postService.deleteTag(postId, tagId)));
    }

    @PutMapping
    public ResponseEntity<PostDTO> update(@RequestParam(required = true) Long id, @RequestBody PostDTO postDTO) throws Exception {
        Post post = postService.update(id, PostMapper.INSTANCE.toPost(postDTO));
        return ResponseEntity.ok().body(PostMapper.INSTANCE.toPostDTO(post));
    }

    @PutMapping(path = "/{id}/category")
    public ResponseEntity<PostDTO> setCategory(@PathVariable("id") Long postId, @RequestBody CategoryDTO categoryDTO) throws Exception {
        return ResponseEntity.ok().body(PostMapper.INSTANCE.toPostDTO(postService.setCategory(postId, categoryDTO)));
    }

    @PutMapping(path = "/{id}/tags")
    public ResponseEntity<PostDTO> setTags(@PathVariable("id") Long postId, @RequestBody TagDTO tagDTO) throws Exception {
        return ResponseEntity.ok().body(PostMapper.INSTANCE.toPostDTO(postService.setTags(postId, tagDTO)));
    }

}
