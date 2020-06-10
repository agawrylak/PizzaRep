package com.example.PizzaRep.services;

import com.example.PizzaRep.business.model.Ingredient;
import com.example.PizzaRep.business.model.Pizza;
import com.example.PizzaRep.business.repository.IngredientRepository;
import com.example.PizzaRep.business.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    public Pizza findbyId(int id){
        List<Pizza> allPizzas = findAll();
        for(Pizza pizza : allPizzas){
            if(pizza.getId() == id){
                return pizza;
            }
        }
        return null;
    }

    public List<Pizza> findAllChosen(){
        List<Pizza> allPizzas = findAll();
        List<Pizza> allChosen = new ArrayList<>();
        for(Pizza pizza : allPizzas){
            if(pizza.isChosen()){
                allChosen.add(pizza);
            }
        }
        return allChosen;
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
