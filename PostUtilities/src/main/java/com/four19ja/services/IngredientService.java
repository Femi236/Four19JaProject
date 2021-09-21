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
        Ingredient ingredient = new Ingredient();
        ingredient.setName(name);
        ingredientRepository.save(ingredient);
        return "Saved";
    }

    public Iterable<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public String updateIngredient(Integer id, String name) {
        Ingredient ingredient = ingredientRepository.findById(id).orElse(null);
        if(ingredient == null) {
            return "Not Saved";
        }
        ingredient.setName(name);
        ingredientRepository.save(ingredient);
        return "Saved";
    }

    public String deleteIngredient(Integer id) {
        Ingredient ingredient = ingredientRepository.findById(id).orElse(null);
        if(ingredient == null) {
            return "Does not exist";
        }
        ingredientRepository.deleteById(id);
        return "Deleted";
    }
}
