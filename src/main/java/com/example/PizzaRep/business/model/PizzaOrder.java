package com.example.PizzaRep.business.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "pizzaorder")
@Getter
@Setter
@NoArgsConstructor
public class PizzaOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "address")
    private String address;

    @Column(name = "phonenumber")
    private Long phoneNumber;

    @Column(name = "email")
    private String email;

    @ManyToMany(mappedBy = "pizzaOrders", cascade = CascadeType.PERSIST)
    private Set<Pizza> pizzas = new HashSet<>();

    public void setPizzas(List<Pizza> pizzas) {
        for(Pizza pizza : pizzas){
            pizza.getPizzaOrders().add(this);
            this.pizzas.add(pizza);
        }
    }
}
