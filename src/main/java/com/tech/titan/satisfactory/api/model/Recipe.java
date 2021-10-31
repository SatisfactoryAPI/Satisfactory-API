package com.tech.titan.satisfactory.api.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipe implements Serializable {

    private Integer recipeId;
    private String name;
    private List<RecipeItem> items;
    private Building building;
    private Item product;
    private double output;

    public Recipe() {
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recipeItemId.recipe")
    public List<RecipeItem> getItems() {
        return items;
    }

    public void setItems(List<RecipeItem> items) {
        this.items = items;
    }

    @ManyToOne
    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    @ManyToOne
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
}
