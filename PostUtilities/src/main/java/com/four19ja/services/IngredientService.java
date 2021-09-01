package com.four19ja.services;

import com.four19ja.entities.Ingredient;
import com.four19ja.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public String addNewIngredient(String name) {
        Ingredient i = new Ingredient();
        i.setName(name);
        ingredientRepository.save(i);
        return "Saved";
    }

    public Iterable<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }
}
