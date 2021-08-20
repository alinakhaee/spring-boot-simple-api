package com.example.firstspringproject.models.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public void create(Category tag){
        categoryRepository.save(tag);
    }

    public Category delete(Long id) throws Exception {
        if(!categoryRepository.existsById(id))
            throw new Exception("category with id " + id + " does not exist");
        Category deletedCategory = categoryRepository.getById(id);
        categoryRepository.deleteById(id);
        return deletedCategory;
    }

    @Transactional
    public Category update(Long id, Category newCategory) throws Exception {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new Exception("category with id " + id + " does not exist"));

        if( newCategory.getTitle() != null )
            category.setTitle(newCategory.getTitle());

        return categoryRepository.getById(id);
    }

    public Category findOne(Long id) throws Exception {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if(categoryOptional.isEmpty())
            throw new Exception("category with id " + id + " does not exist");
        return categoryOptional.get();
    }
}
