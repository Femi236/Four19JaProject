package com.four19ja.services;

import com.four19ja.entities.Category;
import com.four19ja.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public String addNewCategory(String name) {
        Category c = new Category();
        c.setName(name);
        categoryRepository.save(c);
        return "Saved";
    }

    public Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
