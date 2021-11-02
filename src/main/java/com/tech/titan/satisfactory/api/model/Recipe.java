package com.tech.titan.satisfactory.api.model;


import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "recipes")
public class Recipe extends RepresentationModel<Recipe> implements Serializable {

    private Integer recipeId;
    private String name;
    private List<RecipeItem> items; //
    private Building building;
    private Item product;
    private double output;

    public Recipe() {
    }

    public Recipe(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public Recipe(String name, List<RecipeItem> items, Building building, Item product, double output) {
        this.name = name;
        this.items = items;
        this.building = building;
        this.product = product;
        this.output = output;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id", unique = true, nullable = false)
    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    @Column(name = "name", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "recipe")
    public List<RecipeItem> getItems() {
        return items;
    }

    public void setItems(List<RecipeItem> items) {
        this.items = items;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    public Item getProduct() {
        return product;
    }

    public void setProduct(Item product) {
        this.product = product;
    }

    @Column(name = "output", nullable = false)
    public double getOutput() {
        return output;
    }

    public void setOutput(double output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "recipeId=" + recipeId +
                ", name='" + name + '\'' +
                ", items=" + items +
                ", building=" + building +
                ", product=" + product +
                ", output=" + output +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Double.compare(recipe.output, output) == 0 && Objects.equals(recipeId, recipe.recipeId) && Objects.equals(name, recipe.name) && Objects.equals(items, recipe.items) && Objects.equals(building, recipe.building) && Objects.equals(product, recipe.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, name, items, building, product, output);
    }
}
