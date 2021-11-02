package com.tech.titan.satisfactory.api.service;

import com.tech.titan.satisfactory.api.exception.RecipeNotFoundException;
import com.tech.titan.satisfactory.api.model.Item;
import com.tech.titan.satisfactory.api.model.Recipe;
import com.tech.titan.satisfactory.api.model.RecipeItem;
import com.tech.titan.satisfactory.api.repository.RecipeItemRepository;
import com.tech.titan.satisfactory.api.repository.RecipeRepository;
import com.tech.titan.satisfactory.api.service.contract.SearchableService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService extends SearchableService<Recipe> {

    private final RecipeRepository recipeRepository;
    private final RecipeItemRepository recipeItemRepository;

    public RecipeService(RecipeRepository recipeRepository, RecipeItemRepository recipeItemRepository){
        this.recipeRepository = recipeRepository;
        this.recipeItemRepository = recipeItemRepository;
    }

    @Override
    public Recipe findByName(String name) {
        return recipeRepository.findByName(name)
                .orElseThrow(() -> new RecipeNotFoundException(name));
    }

    @Override
    public List<Recipe> getAll() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe findById(Integer id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException(id));
    }

    @Override
    public Recipe save(Recipe entity) {
        List<RecipeItem> recipeItems = entity.getItems();
        entity = saveNewRecipe(entity);
        Integer id = entity.getRecipeId();

        entity.setItems(recipeItemRepository.saveAll(
                recipeItems.stream().map(item ->
                        new RecipeItem(
                            new Item(item.getItem().getItemId()),
                            new Recipe(id),
                            item.getQuantity()
                        ))
                        .collect(Collectors.toList())));

        return entity;
    }

    private Recipe saveNewRecipe(Recipe recipe){
        return recipeRepository.save(recipe);
    }

    @Override
    public void deleteById(Integer id) {
        try{
            recipeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new RecipeNotFoundException(id);
        }
    }

    public List<Recipe> findAllByProductId(Integer productId){
        return recipeRepository.findAllByProduct_ItemId(productId);
    }

    public List<Recipe> findAllByProductName(String name){
        return recipeRepository.findAllByProduct_Name(name);
    }

    public List<Recipe> findAllByBuildingId(Integer buildingId){
        return recipeRepository.findAllByBuilding_BuildingId(buildingId);
    }

    public List<Recipe> findAllByBuildingName(String name){
        return recipeRepository.findAllByBuilding_Name(name);
    }

    public List<RecipeItem> findAllRecipeItems(){
        return recipeItemRepository.findAll();
    }
}
