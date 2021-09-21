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
        Category category = new Category();
        category.setName(name);
        categoryRepository.save(category);
        return "Saved";
    }

    public Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public String updateCategory(Integer id, String name) {
        Category category = categoryRepository.findById(id).orElse(null);
        if(category == null) {
            return "Not Saved";
        }
        category.setName(name);
        categoryRepository.save(category);
        return "Saved";
    }

    public String deleteCategory(Integer id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if(category == null) {
            return "Does not exist";
        }
        categoryRepository.deleteById(id);
        return "Deleted";
    }
}
