package com.example.PizzaRep.business.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "pizza")
@Setter
@Getter
@EqualsAndHashCode
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

    public double getPizzaCost() {
        double result = 10;
        for (Ingredient ingredient: this.getIngredients()){
            result+=3.50;
        }
        return result;
    }


}

