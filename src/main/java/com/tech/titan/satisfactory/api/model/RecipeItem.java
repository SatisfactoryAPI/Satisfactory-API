package com.tech.titan.satisfactory.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recipe_items")
public class RecipeItem extends RepresentationModel<RecipeItem> implements Serializable {

    @EmbeddedId
    private RecipeItemId recipeItemId;

    @OneToOne
    private Item item;

    @JsonIgnore
    @OneToOne
    private Recipe recipe;

    private double quantity;

    public RecipeItem(Item item, Recipe recipe, double quantity){
        this.recipeItemId = new RecipeItemId(recipe.getRecipeId(),item.getItemId());
        this.item = item;
        this.recipe = recipe;
        this.quantity = quantity;
    }
}
