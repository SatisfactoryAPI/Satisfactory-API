package com.tech.titan.satisfactory.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
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

    public RecipeItem(){

    }

    public RecipeItem(Item item, Recipe recipe, double quantity){
        this.recipeItemId = new RecipeItemId(recipe.getRecipeId(),item.getItemId());
        this.item = item;
        this.recipe = recipe;
        this.quantity = quantity;
    }

    public RecipeItemId getRecipeItemId() {
        return recipeItemId;
    }

    public void setRecipeItemId(RecipeItemId recipeItemId) {
        this.recipeItemId = recipeItemId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "RecipeItem{" +
                "recipeItemId=" + recipeItemId +
                ", item=" + item +
                ", recipe=" + recipe +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RecipeItem that = (RecipeItem) o;
        return Double.compare(that.quantity, quantity) == 0 && Objects.equals(recipeItemId, that.recipeItemId) && Objects.equals(item, that.item) && Objects.equals(recipe, that.recipe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), recipeItemId, item, recipe, quantity);
    }
}
