package com.tech.titan.satisfactory.api.repository;

import com.tech.titan.satisfactory.api.model.RecipeItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeItemRepository extends JpaRepository<RecipeItem, Integer> {
}
