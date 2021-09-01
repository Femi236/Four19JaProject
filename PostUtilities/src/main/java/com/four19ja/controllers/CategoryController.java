package com.four19ja.controllers;

import com.four19ja.entities.Category;
import com.four19ja.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/postUtilities")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(path = "/addCategory")
    String addNewCategory(@RequestParam String name) {
        return categoryService.addNewCategory(name);
    }

    @GetMapping(path = "/allCategories")
    public @ResponseBody
    Iterable<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
