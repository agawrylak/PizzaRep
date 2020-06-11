package com.example.PizzaRep.business.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "pizza")
public class Pizza implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;


    @Column(name = "ischosen", nullable = false)
    private boolean isChosen = false;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "pizza_ingredient",
            joinColumns = @JoinColumn(name = "pizza_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id"))
    private List<Ingredient> ingredients = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "pizza_order",
            joinColumns = @JoinColumn(name = "pizza_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "pizzaorder_id", referencedColumnName = "id"))
    private List<PizzaOrder> pizzaOrders;

    @Transient
    private double pizzaCost;

    public Pizza() {
        super();
        isChosen = false;
    }

    public Pizza(String name, Ingredient... ingredients) {
        this.name = name;
        this.ingredients = Stream.of(ingredients).collect(Collectors.toList());
        this.ingredients.forEach(x -> x.getPizzas().add(this));
    }

    //getters & setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    //TODO: check if this is working

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return id == pizza.id &&
                name.equals(pizza.name) &&
                ingredients.equals(pizza.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, ingredients);
    }

    public boolean isChosen() {
        return isChosen;
    }

    public void setChosen(boolean chosen) {
        isChosen = chosen;
    }

    public List<PizzaOrder> getPizzaOrders() {
        return pizzaOrders;
    }

    public void setPizzaOrders(List<PizzaOrder> pizzaOrders) {
        this.pizzaOrders = pizzaOrders;
    }

    public double getPizzaCost() {
        double result = 10;
        for (Ingredient ingredient: this.getIngredients()){
            result+=3.50;
        }
        return result;
    }

    public void setPizzaCost(double pizzaCost) {
        this.pizzaCost = pizzaCost;
    }
}

