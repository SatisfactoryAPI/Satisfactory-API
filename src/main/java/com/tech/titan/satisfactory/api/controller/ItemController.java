package com.tech.titan.satisfactory.api.controller;

import com.tech.titan.satisfactory.api.controller.contract.SearchableController;
import com.tech.titan.satisfactory.api.model.Item;
import com.tech.titan.satisfactory.api.model.Recipe;
import com.tech.titan.satisfactory.api.service.ItemService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController()
@CrossOrigin("*")
@RequestMapping("/items")
public class ItemController extends SearchableController<Item> {

    private final ItemService itemService;

    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @Override
    public CollectionModel<Item> getAll() {
        List<Item> items = itemService.getAll();

        for(final Item item : items){
            item.add(linkTo(methodOn(ItemController.class)
                    .getById(item.getItemId())).withSelfRel());
            item.add(linkTo(methodOn(RecipeController.class)
                    .getAllByProductId(item.getItemId())).withRel("recipeList"));

            for(final Recipe recipe : item.getRecipes()){
                Link selfLink = linkTo(methodOn(RecipeController.class)
                        .getById(recipe.getRecipeId())).withSelfRel();
                recipe.add(selfLink);
            }
        }

        Link link = linkTo(methodOn(ItemController.class).getAll()).withSelfRel();

        return CollectionModel.of(items, link);
    }

    @Override
    public EntityModel<Item> getById(Integer id) {
        Item item = itemService.findById(id);

        for(final Recipe recipe : item.getRecipes()){
            Link selfLink = linkTo(methodOn(RecipeController.class)
                    .getById(recipe.getRecipeId())).withSelfRel();
            recipe.add(selfLink);
        }

        item.add(linkTo(methodOn(ItemController.class)
                .getById(id)).withSelfRel());

        item.add(linkTo(methodOn(RecipeController.class)
                .getAllByProductId(id)).withRel("recipeList"));

        return EntityModel.of(item);
    }

    @Override
    public EntityModel<Item> create(Item newEntity) {
        return EntityModel.of(itemService.save(newEntity));
    }

    @Override
    public EntityModel<Item> update(Item entity) {
        return EntityModel.of(itemService.save(entity));
    }

    @Override
    public ResponseEntity<?> deleteById(Integer id) {
        itemService.deleteById(id);
        return ResponseEntity.status(204).build();
    }
}
