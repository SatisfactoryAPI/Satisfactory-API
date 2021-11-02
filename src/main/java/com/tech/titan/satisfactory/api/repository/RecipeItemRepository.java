package com.tech.titan.satisfactory.api.repository;

import com.tech.titan.satisfactory.api.model.RecipeItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface RecipeItemRepository extends JpaRepository<RecipeItem, Integer> {
}
