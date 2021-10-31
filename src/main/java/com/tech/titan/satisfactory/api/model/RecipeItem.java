package com.tech.titan.satisfactory.api.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "recipe_items")
@AssociationOverrides({
        @AssociationOverride(name = "items",
            joinColumns = @JoinColumn(name = "item_id")),
        @AssociationOverride(name = "recipes",
            joinColumns = @JoinColumn(name = "recipe_id"))
})
public class RecipeItem implements Serializable {
    private RecipeItemId recipeItemId = new RecipeItemId();
    private double quantity;

    public RecipeItem() {
    }

    @EmbeddedId
    public RecipeItemId getRecipeItemId() {
        return recipeItemId;
    }

    public void setRecipeItemId(RecipeItemId recipeItemId) {
        this.recipeItemId = recipeItemId;
    }

    @Transient
    public Item getItem(){
        return getRecipeItemId().getItem();
    }

    public void setItem(Item item){
        getRecipeItemId().setItem(item);
    }

    @Transient
    public Recipe getRecipe(){
        return getRecipeItemId().getRecipe();
    }

    public void setRecipe(Recipe recipe){
        getRecipeItemId().setRecipe(recipe);
    }

    @Column(name = "quantity", nullable = false)
    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
