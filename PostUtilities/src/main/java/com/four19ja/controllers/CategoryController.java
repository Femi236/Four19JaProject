package com.four19ja.controllers;

import com.four19ja.entities.Category;
import com.four19ja.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/postUtilities/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(path = "/add")
    String addNewCategory(@RequestParam String name) {
        return categoryService.addNewCategory(name);
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping(path = "/update")
    public @ResponseBody String updateCategory(@RequestParam Integer id, @RequestParam String name) {
        return categoryService.updateCategory(id, name);
    }

    @PostMapping(path = "/delete")
    public @ResponseBody String deleteCategory(@RequestParam Integer id) {
        return categoryService.deleteCategory(id);
    }
}
