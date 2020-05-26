package com.example.PizzaRep.repository;

import com.example.PizzaRep.model.Ingredient;
import com.example.PizzaRep.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;


public interface PizzaRepository extends JpaRepository<Pizza, Long> {

    List<Pizza> findByName(String name);
    List<Pizza> findAll();

    default List<Pizza> findByIngredientName(String ingredientName){
        ArrayList<Pizza> filteredList = new ArrayList<Pizza>();
        List<Pizza> allPizzas = findAll();
        for (Pizza pizza:allPizzas
             ) {
            for(Ingredient ingredient : pizza.getIngredients()){
                if(ingredientName.equalsIgnoreCase(ingredient.getName())){
                    filteredList.add(pizza);
                }
            }
        }
        return filteredList;
    }


}
