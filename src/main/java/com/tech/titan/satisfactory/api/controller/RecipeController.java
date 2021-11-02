package com.tech.titan.satisfactory.api.controller;

import com.tech.titan.satisfactory.api.controller.contract.SearchableController;
import com.tech.titan.satisfactory.api.model.Recipe;
import com.tech.titan.satisfactory.api.model.RecipeItem;
import com.tech.titan.satisfactory.api.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/recipes")
public class RecipeController extends SearchableController<Recipe> {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService){
        this.recipeService = recipeService;
    }

    @Override
    public Recipe getByName(String name) {
        return recipeService.findByName(name);
    }

    @Override
    public List<Recipe> getAll() {
        return recipeService.getAll();
    }

    @Override
    public Recipe getById(Integer id) {
        return recipeService.findById(id);
    }

    @Override
    public Recipe create(Recipe newEntity) {
        return recipeService.save(newEntity);
    }

    @Override
    public Recipe update(Recipe entity) {
        return recipeService.save(entity);
    }

    @Override
    public ResponseEntity<?> deleteById(Integer id) {
        recipeService.deleteById(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/products/{id}")
    public List<Recipe> getAllByProductId(@PathVariable Integer id){
        return recipeService.findAllByProductId(id);
    }

    @GetMapping("/products/names/{name}")
    public List<Recipe> getAllByProductName(@PathVariable String name){
        return recipeService.findAllByProductName(name);
    }

    @GetMapping("/buildings/{id}")
    public List<Recipe> getAllByBuildingId(@PathVariable Integer id){
        return recipeService.findAllByBuildingId(id);
    }

    @GetMapping("/buildings/names/{name}")
    public List<Recipe> getAllByBuildingName(@PathVariable String name){
        return recipeService.findAllByBuildingName(name);
    }

    @GetMapping("/items")
    public List<RecipeItem> getAllRecipeItems(){
        return recipeService.findAllRecipeItems();
    }
}
