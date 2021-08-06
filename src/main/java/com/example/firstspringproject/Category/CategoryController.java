package com.example.firstspringproject.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> findAll(){
        return categoryService.findAll();
    }

    @GetMapping(path = "{id}")
    public Category findOne(@PathVariable Long id) throws Exception{
        return this.categoryService.findOne(id);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Category category){
        category.setCreatedAt(LocalDateTime.now());
        categoryService.create(category);
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body("category created");
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable("id") Long id) throws Exception {
        categoryService.delete(id);
    }

    @PutMapping
    public void update(@RequestParam(required = true) Long id, @RequestBody Category category) throws Exception {
        categoryService.update(id, category);
    }

}

