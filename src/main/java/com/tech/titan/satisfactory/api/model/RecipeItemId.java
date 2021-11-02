package com.tech.titan.satisfactory.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@Data
public class RecipeItemId implements Serializable {

    private Integer recipeId;
    private Integer itemId;

    public RecipeItemId(Integer recipeId, Integer itemId) {
        this.recipeId = recipeId;
        this.itemId = itemId;
    }
}
