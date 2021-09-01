package com.four19ja.controllers;

import com.four19ja.entities.Ingredient;
import com.four19ja.services.IngredientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/postUtilities")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping(path = "/addIngredient")
    public @ResponseBody
    String addNewIngredient(@RequestParam String name) {
        return ingredientService.addNewIngredient(name);
    }

    @GetMapping(path = "/allIngredients")
    public @ResponseBody
    Iterable<Ingredient> getAllIngredient() {
        return ingredientService.getAllIngredients();
    }
}