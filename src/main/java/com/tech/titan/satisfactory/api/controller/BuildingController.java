package com.tech.titan.satisfactory.api.controller;

import com.tech.titan.satisfactory.api.controller.contract.SearchableController;
import com.tech.titan.satisfactory.api.model.Building;
import com.tech.titan.satisfactory.api.model.Recipe;
import com.tech.titan.satisfactory.api.service.BuildingService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController()
@CrossOrigin("*")
@RequestMapping("/buildings")
public class BuildingController extends SearchableController<Building> {

    private final BuildingService buildingService;

    public BuildingController(BuildingService buildingService){
        this.buildingService = buildingService;
    }

    @Override
    public CollectionModel<Building> getAll() {
        List<Building> buildings = buildingService.getAll();

        for(final Building building : buildings){
            building.add(linkTo(methodOn(BuildingController.class)
                    .getById(building.getBuildingId())).withSelfRel());
            building.add(linkTo(methodOn(RecipeController.class)
                    .getAllByBuildingId(building.getBuildingId())).withRel("recipeList"));

            for(final Recipe recipe : building.getRecipes()){
                Link selfLink = linkTo(methodOn(RecipeController.class)
                        .getById(recipe.getRecipeId())).withSelfRel();
                recipe.add(selfLink);
            }
        }

        Link link = linkTo(methodOn(BuildingController.class).getAll()).withSelfRel();

        return CollectionModel.of(buildings, link);
    }

    @Override
    public EntityModel<Building> getById(Integer id) {
         Building building = buildingService.findById(id);

        for(final Recipe recipe : building.getRecipes()){
            Link selfLink = linkTo(methodOn(RecipeController.class)
                    .getById(recipe.getRecipeId())).withSelfRel();
            recipe.add(selfLink);
        }

        building.add(linkTo(methodOn(BuildingController.class)
                .getById(id)).withSelfRel());

        building.add(linkTo(methodOn(RecipeController.class)
                .getAllByProductId(id)).withRel("recipeList"));

        return EntityModel.of(building);
    }

    @Override
    public EntityModel<Building> create(Building newEntity) {
        return EntityModel.of(buildingService.save(newEntity));
    }

    @Override
    public EntityModel<Building> update(Building entity) {
        return EntityModel.of(buildingService.save(entity));
    }

    @Override
    public ResponseEntity<?> deleteById(Integer id) {
        buildingService.deleteById(id);
        return ResponseEntity.status(204).build();
    }
}
