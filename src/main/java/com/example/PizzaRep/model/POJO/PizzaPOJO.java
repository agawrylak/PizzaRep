package com.example.PizzaRep.model.POJO;

public class PizzaPOJO {

    private String name;
    private String[] ingredients;

    //constructor

    public PizzaPOJO(String name) {
        this.name = name;
    }

    //getters & setters

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String[] getIngredients() {
        return ingredients;
    }
    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }
}
