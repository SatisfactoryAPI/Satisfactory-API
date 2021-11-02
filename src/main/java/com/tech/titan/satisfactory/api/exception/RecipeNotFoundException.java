package com.tech.titan.satisfactory.api.exception;

import com.tech.titan.satisfactory.api.exception.contract.NotFoundException;
import com.tech.titan.satisfactory.api.model.Recipe;

public class RecipeNotFoundException extends NotFoundException {

    public RecipeNotFoundException() {

    }

    public RecipeNotFoundException(String name) {
        super("Could not find the recipe with name: " + name);
    }

    public RecipeNotFoundException(Integer recipeId) {
        super("Could not find the recipe with id: " + recipeId);
    }

    public RecipeNotFoundException(Recipe recipe) {
        super("Could not find the item: " + recipe);
    }
}
