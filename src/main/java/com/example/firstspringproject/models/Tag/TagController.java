package com.example.firstspringproject.models.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public ResponseEntity<List<TagDTO>> findAll(){
        List<TagDTO> tagDTOs = new ArrayList<>();
        tagService.findAll().forEach(tag -> tagDTOs.add(TagMapper.INSTANCE.toTagDTO(tag)));
        return ResponseEntity.ok().body(tagDTOs);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<TagDTO> findOne(@PathVariable Long id) throws Exception{
        Tag tag = this.tagService.findOne(id);
        return ResponseEntity.ok().body(TagMapper.INSTANCE.toTagDTO(tag));
    }

    @PostMapping
    public ResponseEntity<TagDTO> create(@RequestBody TagDTO tagDTO){
        Tag tag = TagMapper.INSTANCE.toTag(tagDTO);
        tag.setCreatedAt(LocalDateTime.now());
        tagService.create(tag);
        return ResponseEntity.status(HttpStatus.CREATED).body(TagMapper.INSTANCE.toTagDTO(tag));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<TagDTO> delete(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok().body(TagMapper.INSTANCE.toTagDTO(tagService.delete(id)));
    }

    @PutMapping
    public ResponseEntity<TagDTO> update(@RequestParam(required = true) Long id, @RequestBody Tag tag) throws Exception {
        return ResponseEntity.ok().body(TagMapper.INSTANCE.toTagDTO(tagService.update(id, tag)));
    }

}

