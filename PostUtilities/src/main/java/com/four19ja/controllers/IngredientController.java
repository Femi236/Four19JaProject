package com.four19ja.controllers;

import com.four19ja.entities.Ingredient;
import com.four19ja.services.IngredientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/postUtilities/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping(path = "/add")
    String addNewIngredient(@RequestParam String name) {
        return ingredientService.addNewIngredient(name);
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Ingredient> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }

    @PostMapping(path = "/update")
    public @ResponseBody String updateIngredient(@RequestParam Integer id, @RequestParam String name) {
        return ingredientService.updateIngredient(id, name);
    }

    @PostMapping(path = "/delete")
    public @ResponseBody String deleteIngredient(@RequestParam Integer id) {
        return ingredientService.deleteIngredient(id);
    }
}
