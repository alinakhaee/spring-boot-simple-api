package com.example.firstspringproject.Tag;

import com.example.firstspringproject.Post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/tags")
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService){
        this.tagService = tagService;
    }

    @GetMapping
    public List<Tag> findAll(){
        return tagService.findAll();
    }

    @GetMapping(path = "{id}")
    public Tag findOne(@PathVariable Long id) throws Exception{
        return this.tagService.findOne(id);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Tag tag){
        tag.setCreatedAt(LocalDateTime.now());
        tagService.create(tag);
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body("tag created");
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable("id") Long id) throws Exception {
        tagService.delete(id);
    }

    @PutMapping
    public void update(@RequestParam(required = true) Long id, @RequestBody Tag tag) throws Exception {
        tagService.update(id, tag);
    }

}

