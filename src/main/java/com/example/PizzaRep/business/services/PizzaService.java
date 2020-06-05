package com.example.PizzaRep.business.services;

import com.example.PizzaRep.business.model.Ingredient;
import com.example.PizzaRep.business.model.Pizza;
import com.example.PizzaRep.business.repository.IngredientRepository;
import com.example.PizzaRep.business.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Pizza> findAll() {
        return this.pizzaRepository.findAll();
    }

    public PizzaService() {
        super();
    }

    public void add(String name, Ingredient... ingredients){

        this.pizzaRepository.saveAndFlush(new Pizza(name, ingredients)); //save czy saveandflush?
    }

    public void add(Pizza pizza){

        this.pizzaRepository.saveAndFlush(pizza); //save czy saveandflush?
    }

}
