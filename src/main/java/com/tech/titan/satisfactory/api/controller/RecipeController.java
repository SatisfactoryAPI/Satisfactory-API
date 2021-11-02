package com.tech.titan.satisfactory.api.controller;

import com.tech.titan.satisfactory.api.controller.contract.SearchableController;
import com.tech.titan.satisfactory.api.model.Building;
import com.tech.titan.satisfactory.api.model.Recipe;
import com.tech.titan.satisfactory.api.model.RecipeItem;
import com.tech.titan.satisfactory.api.service.RecipeService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin("*")
@RequestMapping("/recipes")
public class RecipeController extends SearchableController<Recipe> {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService){
        this.recipeService = recipeService;
    }

    @Override
    public CollectionModel<Recipe> getAll() {
        List<Recipe> recipes = recipeService.getAll();

        for(Recipe recipe : recipes){
            for(final RecipeItem recipeItem: recipe.getItems()){
                Link selfLink = linkTo(methodOn(ItemController.class)
                        .getById(recipeItem.getItem().getItemId())).withSelfRel();
                recipeItem.add(selfLink);
            }

            recipe.getBuilding().add(linkTo(methodOn(BuildingController.class)
                    .getById(recipe.getBuilding().getBuildingId())).withSelfRel());

            recipe.getProduct().add(linkTo(methodOn(ItemController.class)
                    .getById(recipe.getProduct().getItemId())).withSelfRel());

            recipe.add(linkTo(methodOn(RecipeController.class)
                    .getById(recipe.getRecipeId())).withSelfRel());
        }

        Link link = linkTo(methodOn(RecipeController.class).getAll()).withSelfRel();

        return CollectionModel.of(recipes, link);
    }

    @Override
    public EntityModel<Recipe> getById(Integer id) {
        Recipe recipe = recipeService.findById(id);

        for(final RecipeItem recipeItem: recipe.getItems()){
            Link selfLink = linkTo(methodOn(ItemController.class)
                    .getById(recipeItem.getItem().getItemId())).withSelfRel();
            recipeItem.add(selfLink);
        }

        recipe.getBuilding().add(linkTo(methodOn(BuildingController.class)
                .getById(recipe.getBuilding().getBuildingId())).withSelfRel());

        recipe.getProduct().add(linkTo(methodOn(ItemController.class)
                .getById(recipe.getProduct().getItemId())).withSelfRel());

        recipe.add(linkTo(methodOn(RecipeController.class)
                .getById(id)).withSelfRel());

        return EntityModel.of(recipe);
    }

    @Override
    public EntityModel<Recipe> create(Recipe newEntity) {
        System.out.println(newEntity);
        return EntityModel.of(recipeService.save(newEntity));
    }

    @Override
    public EntityModel<Recipe> update(Recipe entity) {
        return EntityModel.of(recipeService.save(entity));
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
