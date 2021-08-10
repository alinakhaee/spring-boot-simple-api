package com.example.firstspringproject.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<Category> categories = categoryService.findAll();
        List<CategoryDTO> categoryDTOs = new ArrayList<>();
        categories.forEach(category -> categoryDTOs.add(CategoryMapper.INSTANCE.toCategoryDTO(category)));
        return ResponseEntity.ok().body(categoryDTOs);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<CategoryDTO> findOne(@PathVariable Long id) throws Exception {
        CategoryDTO categoryDTO = CategoryMapper.INSTANCE.toCategoryDTO(this.categoryService.findOne(id));
        return ResponseEntity.ok().body(categoryDTO);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO categoryDTO) {
        Category category = CategoryMapper.INSTANCE.toCategory(categoryDTO);
        category.setCreatedAt(LocalDateTime.now());
        try {
            categoryService.create(category);
            CategoryDTO outputCategoryDTO = CategoryMapper.INSTANCE.toCategoryDTO(category);
            return ResponseEntity.status(HttpStatus.CREATED).body(outputCategoryDTO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().header(e.getMessage()).body(null);
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<CategoryDTO> delete(@PathVariable("id") Long id) throws Exception {
        Category deletedCategory = categoryService.delete(id);
        return ResponseEntity.ok().body(CategoryMapper.INSTANCE.toCategoryDTO(deletedCategory));
    }

    @PutMapping
    public ResponseEntity<CategoryDTO> update(@RequestParam(required = true) Long id, @RequestBody CategoryDTO categoryDTO) throws Exception {
        Category updatedCategory = categoryService.update(id, CategoryMapper.INSTANCE.toCategory(categoryDTO));
        return ResponseEntity.ok().body(CategoryMapper.INSTANCE.toCategoryDTO(updatedCategory));
    }

}

