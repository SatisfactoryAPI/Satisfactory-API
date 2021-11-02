package com.tech.titan.satisfactory.api.repository;

import com.tech.titan.satisfactory.api.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    Optional<Recipe> findByName(String name);
    List<Recipe> findAllByProduct_ItemId(Integer itemId);
    List<Recipe> findAllByBuilding_BuildingId(Integer buildingId);
    List<Recipe> findAllByProduct_Name(String name);
    List<Recipe> findAllByBuilding_Name(String name);
}
